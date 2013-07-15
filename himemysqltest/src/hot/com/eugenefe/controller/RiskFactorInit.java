package com.eugenefe.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
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
import org.jboss.seam.log.Log;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.CrossTableModel;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;

@Name("riskFactorInit")
@Scope(ScopeType.CONVERSATION)
public class RiskFactorInit {
	@Logger
	private Log log;
	@In
	private EntityManager entityManager;

	@Out
	private List<MarketVariable> allRiskFactors = new ArrayList<MarketVariable>();

	@Out
	private List<MarketVariable> riskFactors = new ArrayList<MarketVariable>();
	
	@Out(scope=ScopeType.CONVERSATION)
	private LazyDataModel<MarketVariable> lazyRiskFactors;

	@Out(scope=ScopeType.CONVERSATION)
	private List<ColumnModel> vcvMatrixColumns;
	
	@Out(scope=ScopeType.CONVERSATION)
	private List<CrossTableModel> pivotList;
	
//	@Out
	private List<VcvMatrixHis> allVcvMatrix;
	
//	@Out
//	private DualListModel<MarketVariable> pickRiskFactors;

	public RiskFactorInit() {
		System.out.println("Construction RiskFactorInit");
	}
//****************Getter and Setter
	public List<MarketVariable> getAllRiskFactors() {
		return allRiskFactors;
	}
	public void setAllRiskFactors(List<MarketVariable> allRiskFactors) {
		this.allRiskFactors = allRiskFactors;
	}
	public List<MarketVariable> getRiskFactors() {
		return riskFactors;
	}
	public void setRiskFactors(List<MarketVariable> riskFactors) {
		this.riskFactors = riskFactors;
	}
	public LazyDataModel<MarketVariable> getLazyRiskFactors() {
		return lazyRiskFactors;
	}
	public void setLazyRiskFactors(LazyDataModel<MarketVariable> lazyRiskFactors) {
		this.lazyRiskFactors = lazyRiskFactors;
	}
	public List<ColumnModel> getVcvMatrixColumns() {
		return vcvMatrixColumns;
	}
	public void setVcvMatrixColumns(List<ColumnModel> vcvMatrixColumns) {
		this.vcvMatrixColumns = vcvMatrixColumns;
	}
	public List<CrossTableModel> getPivotList() {
		return pivotList;
	}
	public void setPivotList(List<CrossTableModel> pivotList) {
		this.pivotList = pivotList;
	}
	public List<VcvMatrixHis> getAllVcvMatrix() {
		return allVcvMatrix;
	}
	public void setAllVcvMatrix(List<VcvMatrixHis> allVcvMatrix) {
		this.allVcvMatrix = allVcvMatrix;
	}
//	public DualListModel<MarketVariable> getPickRiskFactors() {
//		return pickRiskFactors;
//	}
//
//	public void setPickRiskFactors(DualListModel<MarketVariable> pickRiskFactors) {
//		this.pickRiskFactors = pickRiskFactors;
//	}
	
//*******************************************


	@Factory(value = "lazyRiskFactors")
	@Begin(join=true)
	public void initModel() {
//		riskFactors = entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
//		// log.info("product size:#0", products.size());
//		lazyRiskFactors = new LazyModelMarketVariable(riskFactors);
		log.info("initModel Id :#0,#1", Conversation.instance().getId(), Conversation.instance().isLongRunning());
		initRiskFactorView();
	}
	
	@Factory(value="pivotList")
	@Observer("changeBssd_/view/v120RiskFactor.xhtml")
	@Begin(join=true)
	public void initVcvMatrix(){
		log.info("initVcvMatrix Id :#0,#1", Conversation.instance().getId(), Conversation.instance().isLongRunning());
		initRiskFactorView();
	}
	
	private void initRiskFactorView(){
		riskFactors = new ArrayList<MarketVariable>();
		vcvMatrixColumns = new ArrayList<ColumnModel>();

		allRiskFactors = entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
		lazyRiskFactors = new LazyModelMarketVariable(allRiskFactors);
		
		allVcvMatrix = entityManager.createQuery(NamedQuery.VcvMatrixHisBssd.getQuery()).getResultList();
		
		
		prepData();
		loadMatrix();
//		loadPickListaaa();
		log.info("converation Id :#0,#1", Conversation.instance().getId(), Conversation.instance().isLongRunning());
		
	}
	public void prepData(){
		for(VcvMatrixHis aa: allVcvMatrix){
			if(!riskFactors.contains(aa.getRiskFactor())){
				riskFactors.add(aa.getRiskFactor());
				vcvMatrixColumns.add(new ColumnModel(aa.getRiskFactor().mvName, aa.getRiskFactor().getMvName()));
			}
		}
	}
	public void loadMatrix(){
		pivotList = new ArrayList<CrossTableModel>();
			
		for(MarketVariable aa : riskFactors){
			Map<String, BigDecimal> tempMap =new HashMap<String, BigDecimal>() ;
			for(VcvMatrixHis bb: allVcvMatrix){
				log.info("Get Risk Factor:#0", bb.getRiskFactor().getMvId());
				if(aa.equals(bb.getRiskFactor())){
					tempMap.put(bb.getRefRiskFactor().getMvName(), bb.getCovariance());
				}
			}
			pivotList.add(new CrossTableModel(aa.getMvName(), tempMap));
		}
	}

	@Observer("selRiskFactors")
	public void aaa(List<MarketVariable> selRf){
		riskFactors = new ArrayList<MarketVariable>();
		vcvMatrixColumns = new ArrayList<ColumnModel>();
		riskFactors =selRf;
		log.info("selRiskFactors size :#0", riskFactors.size());
		
		for(MarketVariable aa : riskFactors){
			vcvMatrixColumns.add(new ColumnModel(aa.mvName, aa.getMvName()));
			log.info("selRiskFactors  :#0", vcvMatrixColumns.size());
		}
		loadMatrix();
		
	}
	
//***************************************************************

}
