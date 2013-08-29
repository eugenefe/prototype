package com.eugenefe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;

@Name("tableLazyMarketVariableAction")
@Scope(ScopeType.CONVERSATION)
public class TableLazyMarketVariableAction {
	
	@Logger
	private Log log;
	@In
	private EntityManager entityManager;

	// @Out(scope=ScopeType.CONVERSATION)
	private List<MarketVariable> marketVariables;

	private List<MarketVariable> riskFactors = new ArrayList<MarketVariable>();

	@In
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private MarketVariable selectedMarketVariable;
	
	@In
	@Out(scope=ScopeType.CONVERSATION)
	private LazyDataModel<MarketVariable> lazyModelMarketVariable;


	public TableLazyMarketVariableAction() {
		System.out.println("Construction tableLazyMarketVariableAction");
	}

	
	public List<MarketVariable> getMarketVariables() {
		return marketVariables;
	}

	public void setMarketVariables(List<MarketVariable> marketVariables) {
		this.marketVariables = marketVariables;
	}

	public List<MarketVariable> getRiskFactors() {
		return riskFactors;
	}

	public void setRiskFactors(List<MarketVariable> riskFactors) {
		this.riskFactors = riskFactors;
	}

	public MarketVariable getSelectedMarketVariable() {
		return selectedMarketVariable;
	}

	public void setSelectedMarketVariable(MarketVariable selectedMarketVariable) {
		this.selectedMarketVariable = selectedMarketVariable;
	}

	
	public LazyDataModel<MarketVariable> getLazyModelMarketVariable() {
		return lazyModelMarketVariable;
	}


	public void setLazyModelMarketVariable(LazyDataModel<MarketVariable> lazyModelMarketVariable) {
		this.lazyModelMarketVariable = lazyModelMarketVariable;
	}


//	public void onViewFullMarketVariables() {
//		log.info("In the Full Product : #0" , marketVariables.size());
//		lazyModelMarketVariable = new LazyModelMarketVariable(marketVariables);
//	}
//
//	public void onViewRiskFactor() {
////		String qr = "select a from MarketVariable a where a.riskFactorYN ='Y'";
////		riskFactors =entityManager.createQuery(qr).getResultList();
//		riskFactors =entityManager.createQuery(NamedQuery.RiskFactors.getQuery()).getResultList();
//
//		lazyModelMarketVariable = new LazyModelMarketVariable(riskFactors);
//		log.info("In the riskFactors1: #0" , lazyModelMarketVariable.getRowCount());
//	}
//
	public void onRowSelect(SelectEvent event) {
		log.info("On Row Selection : #0", selectedMarketVariable.getMvId());

		FacesContext facesContext = FacesContext.getCurrentInstance();  
        String eventName = FacesContext.getCurrentInstance().getViewRoot().getViewId()+ "_evtSelectMarketVariable" ;
        log.info("View ID : #0", eventName);
        
        Events.instance().raiseEvent(eventName, selectedMarketVariable);
        		
//		Events.instance().raiseEvent("evtSelectMarketVariable", selectedMarketVariable);
	}

	public void initLayout() {
		System.out.println("initLayout");
		// RequestContext requestContext = RequestContext.getCurrentInstance();
		// System.out.println("context" +
		// requestContext.toString()+":"+requestContext.isAjaxRequest());
		// requestContext.execute("innerLayout1.hide('north')");
		// requestContext.execute("innerLayout1.hide('east')");
		// requestContext.update("layoutFullpage");
		System.out.println("initLayout1");
	}

}
