package com.eugenefe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.facelets.FaceletContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.MarketVariableTypeOptionAction;
import com.eugenefe.util.NamedQuery;

@Name("tableLazyMarketVariableInit")
@Scope(ScopeType.CONVERSATION)
public class TableLazyMarketVariableInit {
	@Logger
	private Log log;
	
	@In
	private EntityManager entityManager;
	
//	@In
	@RequestParameter  
	private String flagDataTable; 
	public String getFlagDataTable() {
		return flagDataTable;
	}
	public void setFlagDataTable(String flagDataTable) {
		this.flagDataTable = flagDataTable;
	}
	
	private String flagDataTable1; 
	public String getFlagDataTable1() {
		return flagDataTable1;
	}
	public void setFlagDataTable1(String flagDataTable1) {
		this.flagDataTable1 = flagDataTable1;
	}

	private List<MarketVariable> marketVariables;
	private List<MarketVariable> riskFactors = new ArrayList<MarketVariable>();
	
	
	@In(create=true)
	private MarketVariableTypeOptionAction mvTypeOptionAction;
	
	@Out
	private SelectItem[] currentMvTypeOption;
	
	
	@Out(scope=ScopeType.CONVERSATION)
	private LazyDataModel<MarketVariable> lazyModelMarketVariable;
	
//	@Out(scope=ScopeType.CONVERSATION)
//	private LazyDataModel<MarketVariable> lazyModelProduct;
//	
//	@Out(scope=ScopeType.CONVERSATION)
//	private LazyDataModel<MarketVariable> lazyModelRiskFactor;

	public TableLazyMarketVariableInit() {
		System.out.println("Construction MarketVariableInit");
	}
	
//**********************Getter and Setter *******************
	public SelectItem[] getCurrentMvTypeOption() {
		return currentMvTypeOption;
	}

	public void setCurrentMvTypeOption(SelectItem[] currentMvTypeOption) {
		this.currentMvTypeOption = currentMvTypeOption;
	}

	public LazyDataModel<MarketVariable> getLazyModelMarketVariable() {
		return lazyModelMarketVariable;
	}

	public void setLazyModelMarketVariable(LazyDataModel<MarketVariable> lazyModelMarketVariable) {
		this.lazyModelMarketVariable = lazyModelMarketVariable;
	}


	//	*****************************************
//	@Create
//	@Factory(value = "lazyModelMarketVariable")
//	public LazyDataModel<MarketVariable> getLazyModel() {
//		marketVariables = entityManager.createQuery(NamedQuery.Products.getQuery()).getResultList();
//		lazyModelProduct = new LazyModelMarketVariable(marketVariables);
//		marketVariables = entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
//		lazyModelRiskFactor = new LazyModelMarketVariable(marketVariables);
//		marketVariables = entityManager.createQuery(NamedQuery.AllMarketVariables.getQuery()).getResultList();
//		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
//		
//		currentMvTypeOption = mvTypeOptionAction.getAllMvTypeOption();
//		flag = 0 ;
//		return lazyModelMarketVariable;
//	}
	
	@Factory(value = "lazyModelMarketVariable")
	public void initMarketVariable(){
		 
//		FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
//		String flagDataTable = (String) faceletContext.getAttribute("flagDataTable");

//		String aaa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("flagDataTable");
//		log.info("flag : #0, #1,#2 ", flagDataTable1,flagDataTable, aaa);
		
		if(flagDataTable.equals("Rf")){
			onViewRiskFactor();
		
		}
		else if(flagDataTable.equals("Prod")){
			onViewProduct();
		}
		else{
			onViewFullMarketVariables();
		}
	}

	
	public void initModel() {
//		marketVariables = entityManager.createQuery(NamedQuery.Products.getQuery()).getResultList();
//		lazyModelProduct = new LazyModelMarketVariable(marketVariables);
//		marketVariables = entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
//		lazyModelRiskFactor = new LazyModelMarketVariable(marketVariables);
		marketVariables = entityManager.createQuery(NamedQuery.AllMarketVariables.getQuery()).getResultList();
		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
		
		currentMvTypeOption = mvTypeOptionAction.getAllMvTypeOption();
		
//		marketVariables = entityManager.createQuery(NamedQuery.AllMarketVariables.getQuery()).getResultList();
//		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
//		currentMvTypeOption = mvTypeOptionAction.getAllMvTypeOption();
//		flag = 0 ;
	}

	public void onViewFullMarketVariables() {
//		initModel();
		marketVariables = entityManager.createQuery(NamedQuery.AllMarketVariables.getQuery()).getResultList();
		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
		currentMvTypeOption = mvTypeOptionAction.getAllMvTypeOption();
		log.info("All flag : #0, #1,#2 ", currentMvTypeOption.length, flagDataTable);
	}

	public void onViewRiskFactor() {
		marketVariables =entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
		currentMvTypeOption = mvTypeOptionAction.getMvTypeOption();
		log.info("Rf flag : #0, #1,#2 ", currentMvTypeOption.length, flagDataTable);
	}
	
	
	public void onViewProduct() {
		marketVariables =entityManager.createQuery(NamedQuery.Products.getQuery()).getResultList();
		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
		currentMvTypeOption = mvTypeOptionAction.getProductTypeOption();
		log.info("Prod flag : #0, #1,#2 ", currentMvTypeOption.length, flagDataTable);
	}
	
/*	public void onViewChange() {
		if( flag.equals("prod")){
			marketVariables =entityManager.createQuery(NamedQuery.Products.getQuery()).getResultList();
		}
		else if(flag.equals("rf")){
			marketVariables =entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
		}
		else {
			marketVariables =entityManager.createQuery(NamedQuery.Products.getQuery()).getResultList();
		}
		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
		currentMvTypeOption = mvTypeOptionAction.getProductTypeOption();
	}*/

//	public void onViewChange(AjaxBehaviorEvent event) {
//		public void onViewChange(Integer flag) {
//			public void onViewChange() {
//		if( flag==3){
//			marketVariables =entityManager.createQuery(NamedQuery.Products.getQuery()).getResultList();
//		}
//		else if(flag==2){
//			marketVariables =entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
//		}
//		else {
//			marketVariables =entityManager.createQuery(NamedQuery.AllMarketVariables.getQuery()).getResultList();
//		}
//		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
//		currentMvTypeOption = mvTypeOptionAction.getProductTypeOption();
//	}

	
//***************************************************************

}
