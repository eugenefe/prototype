package com.eugenefe.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.ConversationIdGenerator;
import org.jboss.seam.core.Events;
//import org.jboss.seam.framework.Query;
import org.jboss.seam.log.Log;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.converter.LazyModelVcvHis;
import com.eugenefe.converter.TableDynamicColumn;
import com.eugenefe.converter.TableDynamicContent;
import com.eugenefe.entity.IntRate;
import com.eugenefe.entity.IntRateHis;
import com.eugenefe.entity.IrCurve;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.entity.VcvMatrix;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.entity.Volatility;
import com.eugenefe.entity.VolatilityHis;
import com.eugenefe.entity.VolatilityHisId;
import com.eugenefe.enums.EMaturity;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.ComponentReflection;
import com.eugenefe.util.CrossTableModelOld;
import com.eugenefe.util.ENavigationData;
import com.eugenefe.util.FlagBean;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;
import com.eugenefe.util.PivotTableModel;
import com.lowagie.text.Header;

@Name("tableDynamicModelInit")
@Scope(ScopeType.CONVERSATION)
public class TableDynamicModelInit<T> {
	@Logger
	private Log log;

	@In
	private Session session;

	@In(value = "#{basedateBean}")
	private BaseDateBean basedateBean;

	// @In(required=false, value="#{flagBean.navigationData}")
	// private ENavigationData navigationData;

	@RequestParameter
	private String navigation;
	
	private String savedNavigation;
	public String getSavedNavigation() {
		return savedNavigation;
	}
	public void setSavedNavigation(String savedNavigation) {
		this.savedNavigation = savedNavigation;
	}

	// @In(value= "#{treeObjectNavigationInit.rootNode}")
	private TreeNode rootNode;
	private TreeNode superNode;

	private TreeNode[] selectedNodes;

	private List<T> dynamicModelList;

	private T selectedDynamicModel;

	private List<TableDynamicColumn> pivotTableHeader;
	private List<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>> pivotTableContent;
	private List<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>> filterPivotTableContent;

	public TableDynamicModelInit() {
		System.out.println("Construction TableDynamicModelInit");
	}

	// *******************************************
	@Create
	@Begin(join = true)
	public void create() {
		savedNavigation = navigation;
		zzz();
		initPivotTableHeader();

		loadDynamicModel();
		loadTable();
	}

	public void columeChange() {
		initPivotTableHeader();
		loadTable();
	}
	
//	@Observer("evtDynamicModelInitialize")
//	@Begin(join = true)
//	public void create(TreeNode superNode) {
//		log.info("RootNode size:#0", superNode.getChildCount());
//		this.superNode = superNode;
//		this.rootNode = superNode.getChildren().get(0);
//
//		// this.rootNode = rootNode;
//		initPivotTableHeader();
//		loadDynamicModel();
//		loadTable();
//	}



	// ******************************************
	public void initPivotTableHeader() {
		pivotTableHeader = new ArrayList<TableDynamicColumn>();
		String temp = new String();
		String subTemp = new String();

		if (selectedNodes == null || selectedNodes.length == 0) {
			initSelection();
//			log.info("in the SelectionNode size: #0", selectedNodes.length);
		}
		for (TreeNode node : selectedNodes) {
			if (node.isLeaf()) {
				if (node.getParent() != null && !node.getParent().equals(superNode)) {
					temp = ComponentReflection.getRecursiveMethodName(node, rootNode);
//					log.info("New Header: #0", ComponentReflection.getRecursiveMethodName(node, rootNode));
					
					pivotTableHeader.add(new TableDynamicColumn(temp, temp));
				}
			}
			
		}
	}

	public void loadDynamicModel() {
		Filter filter = session.enableFilter("filterBtwnDate").setParameter("stBssd", basedateBean.getStBssd())
				.setParameter("endBssd", basedateBean.getEndBssd());
		// Filter filterBssd =
		// session.enableFilter("filterCurrentDate").setParameter("bssd",
		// basedateBean.getBssd());
		String query = "from " + savedNavigation;
		dynamicModelList = session.createQuery(query).list();
//		log.info("Query Result size:#0", dynamicModelList.size());
	}

	public void loadTable() {
		pivotTableContent = new ArrayList<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>>();
		// Collections.sort(pivotTableHeader);
		Object navi;

		for (T dyn : dynamicModelList) {
			
			Map<TableDynamicColumn, TableDynamicContent> tempContentMap = new HashMap<TableDynamicColumn, TableDynamicContent>();
			for (TableDynamicColumn header : pivotTableHeader) {
//				log.info("Header :#0", header.getColProperties());
				navi = dyn;
				// log.info("header Prep:#0,#1", header.getColProperties());
				try {
					for (String prop : header.getColProperty()) {
						Method temp = navi.getClass().getDeclaredMethod(prop);
//						Field temp = navi.getClass().getDeclaredField(prop);

//						temp.setAccessible(true);
//						navi = temp.get(navi);
						
						navi = temp.invoke(navi);
					}
					tempContentMap.put(header, new TableDynamicContent(String.valueOf(navi), 0));
				} catch (Exception e) {

				}
			}
			pivotTableContent.add(new PivotTableModel<T, TableDynamicColumn, TableDynamicContent>(dyn, tempContentMap));
			filterPivotTableContent = pivotTableContent;
		}
		// log.info("In the Vcv Matrix:#0,#1", pivotTableContent.size());
	}

	private void initSelection() {
		int cnt = 0;
		String temp = new String();

		for (TreeNode node : rootNode.getChildren()) {
			temp = ComponentReflection.getRecursiveMethodName(node, rootNode);
			if (node.isLeaf()) {
				cnt = cnt + 1;
			} else if (temp.contains("Id")) {
				for (TreeNode subNode : node.getChildren()) {
					cnt = cnt + 1;
				}
			}
		}

		selectedNodes = new DefaultTreeNode[cnt];
		log.info("in the SelectionNode : #0", cnt);
		cnt = 0;
		
		for (TreeNode node : rootNode.getChildren()) {
			temp = ComponentReflection.getRecursiveMethodName(node, rootNode);
			if (node.isLeaf()) {
				selectedNodes[cnt] = node;
				log.info("in the SelectionNode : #0,#1", cnt, temp);
				cnt = cnt + 1;
			} else if (temp.contains("Id")) {
				for (TreeNode subNode : node.getChildren()) {
					selectedNodes[cnt] = subNode;
					log.info("in the SelectionNode1 : #0,#1", cnt, temp);
					cnt = cnt + 1;
				}
			}
		}
	}
	// ***************************************************************
	public void resetTable() {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("tabViewVcv:formVcvHis:tableVcvHis");
		if (dataTable != null) {
			// log.info("In the dataTable");
			dataTable.setValueExpression("sortBy", null);
			// dataTable.setValueExpression("filterBy", null);
			dataTable.setFirst(0);
			dataTable.reset();
		}
	}

	public void zzz() {
		Class klass;
		try {
			klass = Class.forName(ENavigationData.valueOf(savedNavigation).getQualifiedName());

			// rootNode = ComponentReflection.getPropertyTree(klass);
//			rootNode = ComponentReflection.getMethodTree(klass).getChildren().get(0);
			superNode = ComponentReflection.getMethodTree(klass);
			rootNode = superNode.getChildren().get(0);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void recursive(Field field, Field superField, Map<Field, Field> rst) {
		Class fieldKlazz = field.getType();
		// if(!fieldKlazz.isInterface()){
		if (!fieldKlazz.isPrimitive()) {
			// if (fieldKlazz.getPackage() != null ) {
			if (fieldKlazz.getPackage().getName().contains("com.eugenefe.entity")
					|| fieldKlazz.getPackage().getName().contains("com.eugenefe.controller")) {
				for (Field ff : fieldKlazz.getDeclaredFields()) {
					log.info("Pivo Header1 :#0,#1,#2", ff.getName(), ff.getDeclaredAnnotations().length);
					// for(Annotation zz: ff.getDeclaredAnnotations()){
					// log.info("Pivo Header :#0,#1,#2", zz.toString());
					// if(zz.toString().contains("ToMany")){
					// break;
					// }
					// }
					recursive(ff, field, rst);
				}
			} else {
				rst.put(field, superField);
			}
		} else {
			rst.put(field, superField);
		}
		// }
	}

	private void recursive(Class klazz, Map<Field, Class> rst) {
		Class fieldKlazz;

		for (Field field : klazz.getDeclaredFields()) {
			fieldKlazz = field.getType();
			if (field.getName().contains("List") || field.getName().contains("Set") || field.getName().contains("Map")) {
				break;
			}
			if (!fieldKlazz.isPrimitive()) {
				if (fieldKlazz.getPackage().getName().contains("com.eugenefe.entity")
						|| fieldKlazz.getPackage().getName().contains("com.eugenefe.controller")) {
					recursive(fieldKlazz, rst);
				} else {
					rst.put(field, klazz);
				}
			} else {
				rst.put(field, klazz);
			}
		}
	}

	private void recursive(String fieldName, Class fieldKlazz, List<String> rst) {
		// if(!fieldKlazz.isInterface()){
		if (!fieldKlazz.isPrimitive()) {
			// if (fieldKlazz.getPackage() != null ) {
			if (fieldKlazz.getPackage().getName().contains("com.eugenefe.entity")
					|| fieldKlazz.getPackage().getName().contains("com.eugenefe.controller")) {
				for (Field ff : fieldKlazz.getDeclaredFields()) {
					log.info("Pivo Header1 :#0,#1,#2", ff.getName(), ff.getDeclaredAnnotations().length);
					for (Annotation zz : ff.getDeclaredAnnotations()) {
						log.info("Pivo Header :#0,#1,#2", zz.toString());
						if (zz.toString().contains("ToMany")) {
							break;
						}
					}
					recursive(fieldName + "." + ff.getName(), ff.getType(), rst);
				}
			} else {
				rst.add(fieldName);
			}
		} else {
			rst.add(fieldName);
		}
		// }
	}

	// ****************Getter and Setter***********************
	

	public List<TableDynamicColumn> getPivotTableHeader() {
		return pivotTableHeader;
	}

	public void setPivotTableHeader(List<TableDynamicColumn> pivotTableHeader) {
		this.pivotTableHeader = pivotTableHeader;
	}

	public List<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>> getPivotTableContent() {
		return pivotTableContent;
	}

	public void setPivotTableContent(List<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>> pivotTableContent) {
		this.pivotTableContent = pivotTableContent;
	}

	public List<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>> getFilterPivotTableContent() {
		return filterPivotTableContent;
	}

	public void setFilterPivotTableContent(
			List<PivotTableModel<T, TableDynamicColumn, TableDynamicContent>> filterPivotTableContent) {
		this.filterPivotTableContent = filterPivotTableContent;
	}

	public T getSelectedDynamicModel() {
		return selectedDynamicModel;
	}

	public void setSelectedDynamicModel(T selectedDynamicModel) {
		this.selectedDynamicModel = selectedDynamicModel;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

}
