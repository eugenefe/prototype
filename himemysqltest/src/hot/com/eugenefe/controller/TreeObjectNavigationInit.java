package com.eugenefe.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remove;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
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
import org.primefaces.component.tree.Tree;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.converter.LazyModelVcvHis;
import com.eugenefe.converter.LazyModelVolatilityHis;
import com.eugenefe.converter.TableDynamicColumn;
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
import com.eugenefe.util.AnnoMethodTree;
import com.eugenefe.util.AnnoMethodTree.EColumnType;
import com.eugenefe.util.AnnoNavigationFilter;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.ComponentReflection;
import com.eugenefe.util.ComponentReflectionNew;
import com.eugenefe.util.CrossTableModelOld;
import com.eugenefe.util.ENavigationData;
import com.eugenefe.util.FlagBean;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;
import com.eugenefe.util.PivotTableModel;

@Name("treeObjectNavigationInit")
@Scope(ScopeType.CONVERSATION)
public class TreeObjectNavigationInit {
	@Logger
	private Log log;

	@In
	private Map<String, String> messages;

	@RequestParameter
	private String navigation;

	private TreeNode rootNode;
	private TreeNode superNode;

	@Out
	private List<TableDynamicColumn> initPivotTableHeader;

	public TreeObjectNavigationInit() {
		System.out.println("Construction TreeObjectNavigationInit");
	}

	// *******************************************
	@Create
	public void create() {
		Class klass;
		superNode = new DefaultTreeNode("superRoot", null);
		superNode.setExpanded(true);

		dupl = new HashSet<Class>();

		try {
			klass = Class.forName(ENavigationData.valueOf(navigation).getQualifiedName());
			classStack.add(klass.getName());
			
			rootNode = new DefaultTreeNode(new TableDynamicColumn(klass.getSimpleName(), klass.getSimpleName(),
					AnnoMethodTree.EColumnType.String, null, 0, false, 1, true, "left"), superNode);
			rootNode.setExpanded(true);

			recursiveTableCoulumn(klass, rootNode);
			initTree(rootNode);

			// for(TreeNode nn : rootNode.getChildren()){
			// log.info("TreeNode :#0,#1",((TableDynamicColumn)
			// nn.getData()).getColumnId(), ((TableDynamicColumn)
			// nn.getData()).getColumnName());
			// }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Factory(value = "initPivotTableHeader", scope = ScopeType.CONVERSATION)
	public void initTree(TreeNode rootNode) {
		initPivotTableHeader = new ArrayList<TableDynamicColumn>();
		TableDynamicColumn temp;
		TableDynamicColumn subTemp;

		for (TreeNode node : rootNode.getChildren()) {
			temp = ((TableDynamicColumn) node.getData());
			if (temp.isInitialzied()) {
				node.setSelected(true);
				node.setExpanded(true);
				if (node.isLeaf()) {
					initPivotTableHeader.add(temp);
				}
				for (TreeNode subNode : node.getChildren()) {
					subTemp = (TableDynamicColumn) subNode.getData();
					if (subTemp.isInitialzied()) {
						subNode.setSelected(true);
						// subNode.setExpanded(true);
						if (subNode.isLeaf()) {
							initPivotTableHeader.add(((TableDynamicColumn) subNode.getData()));
						}
					}
				}
			}
		}
	}
	private List<String> classStack = new ArrayList<String>();
	private boolean isInCollection=false ;
	private int collectionNodeLevel =1;
	
	private void recursiveTableCoulumn(Class klazz, TreeNode node) {
		Class<?> methodRtnKlazz;
		Class<?> genericTypeKlazz;
		double orderScale;
		String temp = "";
		String tempParent;
		
		Method[] tempMethod = klazz.getDeclaredMethods();
		sortMethod(tempMethod);

		TableDynamicColumn parentColumn = (TableDynamicColumn) node.getData();
		
//		if (parentColumn.getParentColumnId() != null) {
		if(parentColumn.getParentColumn()!=null){
			tempParent = parentColumn.getColumnId() + "_";
//			tempParent = parentColumn.getColumnId() ;
		} else {
			tempParent = "";
		}

		for (Method mtd : tempMethod) {
			if (mtd.isAnnotationPresent(AnnoMethodTree.class)) {
				if( parentColumn.getColumnLevel() <= collectionNodeLevel){
					isInCollection = false;
				}
				
				methodRtnKlazz = mtd.getReturnType();
				genericTypeKlazz = null;
//				log.info("Method ZZ:#0, #1,#2,#3", mtd.getName(), parentColumn.isInTheCollection(),classStack);

				
				// Map, List ������ ��� ������� Map, List �� Node �� �߰����� �ʴ´�
				if (methodRtnKlazz.getName().contains("Map")) {
					if(isInCollection){
						continue;
					}
					isInCollection = true;
					collectionNodeLevel= Math.min(collectionNodeLevel, parentColumn.getColumnLevel());
//					if(parentColumn.getColumnLevel()> collectionNodeLevel){
//						collectionNodeLevel = parentColumn.getColumnLevel(); 
//					}
					genericTypeKlazz = (Class) ((ParameterizedType) mtd.getGenericReturnType())
							.getActualTypeArguments()[1];
				} else if (methodRtnKlazz.getName().contains("List")) {
					if(isInCollection){
						continue;
					}
					isInCollection = true;
					collectionNodeLevel= Math.min(collectionNodeLevel, parentColumn.getColumnLevel());
//					collectionNodeLevel = parentColumn.getColumnLevel();
					genericTypeKlazz = (Class) ((ParameterizedType) mtd.getGenericReturnType())
							.getActualTypeArguments()[0];
				}

				if (genericTypeKlazz != null) {
					methodRtnKlazz = genericTypeKlazz;
				}
				log.info("Before Continue :#0,#1", mtd.getName(),methodRtnKlazz.getName());
				
				if(classStack.contains(methodRtnKlazz.getName())&& classStack.size()>1){
					log.info("IN Continue :#0,#1,#2", methodRtnKlazz.getName(),classStack, classStack.size());
//					classStack.remove(klazz.getName());	
					continue;
				}
//				log.info("Method ZZ1:#0, #1", mtd.getName());
				temp = tempParent + mtd.getName();
				AnnoMethodTree anno = mtd.getAnnotation(AnnoMethodTree.class);
				orderScale = Math.pow(0.1, parentColumn.getColumnLevel() + 1);

//				TreeNode childNode = new DefaultTreeNode(isInCollection?"collection":"prop", new TableDynamicColumn(temp, messages.get(mtd.getName())
						TreeNode childNode = new DefaultTreeNode(new TableDynamicColumn(temp, messages.get(mtd.getName())		
								,anno.type(), parentColumn, parentColumn.getColumnLevel() + 1
								,isInCollection
								,parentColumn.getColumnOrder()+ anno.order() * orderScale, anno.init(), anno.align()), node);

				if (methodRtnKlazz.isAnnotationPresent(AnnoNavigationFilter.class)) {	
					classStack.add(methodRtnKlazz.getName());
//					log.info("Method ZZzz:#0, #1,#2,#3", mtd.getName(), collectionNodeLevel,classStack);
					recursiveTableCoulumn(methodRtnKlazz, childNode);
					classStack.remove(methodRtnKlazz.getName());		
				}
			}
		}
//		classStack.remove(klazz.getName());
	}

	private void sortMethod(Method[] methods) {
		Arrays.sort(methods, new Comparator<Method>() {

			@Override
			public int compare(Method mtd1, Method mtd2) {
				AnnoMethodTree mtdOrder1 = mtd1.getAnnotation(AnnoMethodTree.class);
				AnnoMethodTree mtdOrder2 = mtd2.getAnnotation(AnnoMethodTree.class);
				if (mtdOrder1 != null && mtdOrder2 != null) {
					return mtdOrder1.order() - mtdOrder2.order();
				} else if (mtdOrder1 != null && mtdOrder2 == null) {
					return -1;
				} else if (mtdOrder1 == null && mtdOrder2 != null) {
					return 1;
				}
				return mtd1.getName().compareTo(mtd2.getName());
			}
		});
	}

	// ***********************************Getter and Setter*********************
	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public TreeNode getSuperNode() {
		return superNode;
	}

	public void setSuperNode(TreeNode superNode) {
		this.superNode = superNode;
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	public List<TableDynamicColumn> getInitPivotTableHeader() {
		return initPivotTableHeader;
	}

	public void setInitPivotTableHeader(List<TableDynamicColumn> initPivotTableHeader) {
		this.initPivotTableHeader = initPivotTableHeader;
	}

	private Set<Class> dupl;
	private int collectionDepth;
	
	private void recursiveTableCoulumnOld(Class klazz, TreeNode node) {
		Class<?> methodRtnKlazz;
		Class<?> genericTypeKlazz;
		double orderScale;
		String temp = "";
		String tempParent;
		
//		boolean flag = classStack.contains(klazz.getName()); 
//		if(!flag){
//			classStack.add(klazz.getName());
//		}

		Method[] tempMethod = klazz.getDeclaredMethods();
		sortMethod(tempMethod);

		TableDynamicColumn parentColumn = (TableDynamicColumn) node.getData();
		
//		if (parentColumn.getParentColumnId() != null) {
			if(parentColumn.getParentColumn()!=null){	
			tempParent = parentColumn.getColumnId() + "_";
		} else {
			tempParent = "";
		}

		for (Method mtd : tempMethod) {
			if (mtd.isAnnotationPresent(AnnoMethodTree.class)) {
				if( parentColumn.getColumnLevel() <= collectionNodeLevel){
					isInCollection = false;
				}
				
				methodRtnKlazz = mtd.getReturnType();
				genericTypeKlazz = null;
//				log.info("Method ZZ:#0, #1,#2,#3", mtd.getName(), parentColumn.isInTheCollection(),classStack);

				
				
				
				// Map, List ������ ��� ������� Map, List �� Node �� �߰����� �ʴ´�
				if (methodRtnKlazz.getName().contains("Map")) {
					if(isInCollection){
						continue;
					}
					isInCollection = true;
					collectionNodeLevel= Math.min(collectionNodeLevel, parentColumn.getColumnLevel());
//					if(parentColumn.getColumnLevel()> collectionNodeLevel){
//						collectionNodeLevel = parentColumn.getColumnLevel(); 
//					}
					genericTypeKlazz = (Class) ((ParameterizedType) mtd.getGenericReturnType())
							.getActualTypeArguments()[1];
				} else if (methodRtnKlazz.getName().contains("List")) {
					if(isInCollection){
						continue;
					}
					isInCollection = true;
					collectionNodeLevel= Math.min(collectionNodeLevel, parentColumn.getColumnLevel());
//					collectionNodeLevel = parentColumn.getColumnLevel();
					genericTypeKlazz = (Class) ((ParameterizedType) mtd.getGenericReturnType())
							.getActualTypeArguments()[0];
				}

				if (genericTypeKlazz != null) {
					methodRtnKlazz = genericTypeKlazz;
				}
				log.info("Before Continue :#0,#1", mtd.getName(),methodRtnKlazz.getName());
				
				if(classStack.contains(methodRtnKlazz.getName())){
					log.info("IN Continue :#0,#1", methodRtnKlazz.getName(),classStack);
//					classStack.remove(klazz.getName());	
					continue;
				}
//				log.info("Method ZZ1:#0, #1", mtd.getName());
				temp = tempParent + mtd.getName();
				AnnoMethodTree anno = mtd.getAnnotation(AnnoMethodTree.class);
				orderScale = Math.pow(0.1, parentColumn.getColumnLevel() + 1);

				TreeNode childNode = new DefaultTreeNode(new TableDynamicColumn(temp, messages.get(mtd.getName())
								,anno.type(), parentColumn, parentColumn.getColumnLevel() + 1
								,isInCollection
								,parentColumn.getColumnOrder()+ anno.order() * orderScale, anno.init(), anno.align()), node);

//				if(methodRtnKlazz.isAnnotationPresent(AnnoNavigationFilter.class)){
//				}
//					if (methodRtnKlazz.isAnnotationPresent(AnnoNavigationFilter.class) && !flag && !classStack.contains(methodRtnKlazz.getName())) {
						if (methodRtnKlazz.isAnnotationPresent(AnnoNavigationFilter.class)) {	
//						if (methodRtnKlazz.isAnnotationPresent(AnnoNavigationFilter.class)) {	
							classStack.add(methodRtnKlazz.getName());
							log.info("Method ZZzz:#0, #1,#2,#3", mtd.getName(), collectionNodeLevel,classStack);
						recursiveTableCoulumn(methodRtnKlazz, childNode);
						classStack.remove(methodRtnKlazz.getName());		
					}
			}
		}
//		classStack.remove(klazz.getName());
	}
}
