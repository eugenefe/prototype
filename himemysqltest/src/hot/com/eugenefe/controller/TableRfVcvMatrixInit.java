package com.eugenefe.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.ConversationIdGenerator;
import org.jboss.seam.core.Events;
//import org.jboss.seam.framework.Query;
import org.jboss.seam.log.Log;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.converter.LazyModelVcvHis;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.entity.VcvMatrix;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.CrossTableModelOld;
import com.eugenefe.util.FlagBean;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;

@Name("tableRfVcvMatrixInit")
@Scope(ScopeType.CONVERSATION)
public class TableRfVcvMatrixInit {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;
	
	@In(value="#{flagBean.flagVcvType}")
	private String flagVcvType;
	
	@In(value="#{flagBean.flagVcvRfType}")
	private String flagVcvRfType;

	//	@Out
	private List<MarketVariable> allRiskFactors = new ArrayList<MarketVariable>();

//	@Out
	private List<MarketVariable> pickRiskFactors = new ArrayList<MarketVariable>();

//	@Out(scope=ScopeType.CONVERSATION)
	private List<ColumnModel> vcvMatrixColumns;
	
//	@Out(scope=ScopeType.CONVERSATION, required=false)
	private List<CrossTableModelOld> pivotTable;
	
//	@Out
	private List<VcvMatrixHis> allVcvMatrix;
	
	private List<VcvMatrixHis> allVcvHis;
	
	private LazyDataModel<VcvMatrixHis> lazyModelVcvHis;
	
	public TableRfVcvMatrixInit() {
		System.out.println("Construction TableRfVcvMatrixInit");
	}
//****************Getter and Setter
	public List<MarketVariable> getAllRiskFactors() {
		return allRiskFactors;
	}
	public void setAllRiskFactors(List<MarketVariable> allRiskFactors) {
		this.allRiskFactors = allRiskFactors;
	}

	public List<MarketVariable> getPickRiskFactors() {
		return pickRiskFactors;
	}
	public void setPickRiskFactors(List<MarketVariable> pickRiskFactors) {
		this.pickRiskFactors = pickRiskFactors;
	}
	public List<ColumnModel> getVcvMatrixColumns() {
//		if(vcvMatrixColumns!=null){
//			log.info("get vcvMatrix:#0", vcvMatrixColumns.size());
//		}
		return vcvMatrixColumns;
	}
	public void setVcvMatrixColumns(List<ColumnModel> vcvMatrixColumns) {
		this.vcvMatrixColumns = vcvMatrixColumns;
	}
	
	public List<CrossTableModelOld> getPivotTable() {
		return pivotTable;
	}
	public void setPivotTable(List<CrossTableModelOld> pivotTable) {
		this.pivotTable = pivotTable;
	}
	public List<VcvMatrixHis> getAllVcvMatrix() {
		return allVcvMatrix;
	}
	public void setAllVcvMatrix(List<VcvMatrixHis> allVcvMatrix) {
		this.allVcvMatrix = allVcvMatrix;
	}
	public List<VcvMatrixHis> getAllVcvHis() {
		return allVcvHis;
	}
	public void setAllVcvHis(List<VcvMatrixHis> allVcvHis) {
		this.allVcvHis = allVcvHis;
	}
	public LazyDataModel<VcvMatrixHis> getLazyModelVcvHis() {
		return lazyModelVcvHis;
	}
	public void setLazyModelVcvHis(LazyDataModel<VcvMatrixHis> lazyModelVcvHis) {
		this.lazyModelVcvHis = lazyModelVcvHis;
	}
	//*******************************************
//	@Observer("changeBssd_/view/v130RfVcvMatrix.xhtml")
	@Create
	public void create(){
//		vcvId ="SMA_1DAY";
		prepVcvMatrix();
		loadMatrix();
	}
	
	@Observer("evtPickRiskFactors")
	public void onPickRiskFactors(List<MarketVariable> selRf){
//		pickRiskFactors = new ArrayList<MarketVariable>();
		vcvMatrixColumns = new ArrayList<ColumnModel>();
		pickRiskFactors = selRf;
		log.info("selRiskFactors size :#0", pickRiskFactors.size());
		
		for(MarketVariable aa : pickRiskFactors){
			vcvMatrixColumns.add(new ColumnModel(aa.mvName, aa.getMvName()));
//			log.info("selRiskFactors  :#0", vcvMatrixColumns.size());
		}
		loadMatrix();
	}
	
	public void onVcvTypeChange(){
		loadMatrix();
	}
	
	
	public void prepVcvMatrix(){
		pickRiskFactors = new ArrayList<MarketVariable>();
		vcvMatrixColumns = new ArrayList<ColumnModel>();
		
		allRiskFactors = entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
		
		
		for(MarketVariable bb : allRiskFactors){
//			log.info("MV Type : #0, #1", bb.getMvType().getRfType(), flagVcvRfType);
			if(bb.getMvType().getRfType().equals(flagVcvRfType)){
				pickRiskFactors.add(bb);
				vcvMatrixColumns.add(new ColumnModel(bb.getMvName(), bb.getMvName()));
			}
		}
		log.info("VCV Matrix Prep:#0", vcvMatrixColumns.size());
	}
	
	@Observer("changeBssd_/view/v130RfVcvMatrix.xhtml")
	public void loadMatrix(){
		allVcvMatrix = entityManager.createQuery(NamedQuery.VcvMatrixHisBssd.getQuery()).getResultList();
		allVcvHis = entityManager.createQuery(NamedQuery.VcvMatrixHisBtwn.getQuery()).getResultList();
		
		lazyModelVcvHis = new LazyModelVcvHis(allVcvHis);
		
		log.info("All VcvMatrix: #{pickListRfActionaa.vcvId}, #0", allVcvMatrix.size());
		pivotTable = new ArrayList<CrossTableModelOld>();
		
		for(MarketVariable aa : pickRiskFactors){
			Map<String, BigDecimal> tempMap =new HashMap<String, BigDecimal>() ;
			for(VcvMatrixHis bb: allVcvMatrix){
//				log.info("Get Risk Factor:#0", bb.getRiskFactor().getMvId());
				if(aa.equals(bb.getRiskFactor())){
//TODO : remove the String
					if(flagVcvType.equals("1")){	
						tempMap.put(bb.getRefRiskFactor().getMvName(), bb.getCovariance());
					}
					else {
						tempMap.put(bb.getRefRiskFactor().getMvName(), bb.getCorrel());
					}
				}
			}
			pivotTable.add(new CrossTableModelOld(aa.getMvName(), tempMap));
		}
	}

	@Observer("evtDateChange_/view/v130RfVcvMatrix.xhtml")
	public void onDateChange() {
		resetTable();
		loadMatrix();
	}
//***************************************************************
	public void resetTable() {
	    DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
	            .findComponent("tabViewVcv:formVcvHis:tableVcvHis");
	    if (dataTable != null) {
//	    	log.info("In the dataTable");
	    	dataTable.setValueExpression("sortBy", null);
//	    	dataTable.setValueExpression("filterBy", null);
	    	dataTable.setFirst(0);
	        dataTable.reset();
	    }
	}
}
