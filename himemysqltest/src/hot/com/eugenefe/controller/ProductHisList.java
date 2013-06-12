package com.eugenefe.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;

import com.eugenefe.entity.IMarketVariableHis;
import com.eugenefe.entity.MarketVariable;

@Name("productHisList")
@Scope(ScopeType.CONVERSATION)
public class ProductHisList {
	@In
	private EntityManager entityManager;
	
	@Out
	private List<IMarketVariableHis> marketVariableHisList;
	
	private String query;
	
	public ProductHisList(){
		
	}
	
//	@Factory(value="marketVariableHisList")
	@Observer(value="selectProduct")
	public void onSelectProduct(MarketVariable selectedProduct){
		query =selectedProduct.getMvType().getQuery();
		marketVariableHisList = entityManager.createQuery(query).getResultList();
		Events.instance().raiseEvent("afterQueryResult", selectedProduct, marketVariableHisList);
	}
	
}
