package com.eugenefe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.entity.IMarketVariableHis;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.util.EView;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;

@Name("tableMvHisDataInit")
@Scope(ScopeType.CONVERSATION)
public class TableMvHisDataInit {
	@In
	private EntityManager entityManager;
	
	@Out
	private List<IMarketVariableHis> mvHisList;
	
	private String query;
	
	public TableMvHisDataInit(){
		System.out.println("Construction TableMvHisDataHis");
	}
	
//	@Factory(value="marketVariableHisList")
	@Observer(value="/view/v101ViewHistoryData.xhtml_evtSelectMarketVariable")
	public void onSelectMarketVariable(MarketVariable selectedMarketVariable){
		query =selectedMarketVariable.getMvType().getQuery();
		mvHisList = entityManager.createQuery(query).getResultList();
//		Events.instance().raiseEvent("evtForCreateChart", selectedProduct, marketVariableHisList);
	}
	@Observer(value="/view/v110Product.xhtml_evtSelectMarketVariable")
	public void onSelectMarketVariable1(MarketVariable selectedMarketVariable){
		query =selectedMarketVariable.getMvType().getQuery();
		mvHisList = entityManager.createQuery(query).getResultList();
//		Events.instance().raiseEvent("evtForCreateChart", selectedProduct, marketVariableHisList);
	}
}
