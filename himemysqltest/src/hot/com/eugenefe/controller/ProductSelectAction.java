package com.eugenefe.controller;

import java.util.List;

import javax.faces.model.SelectItem;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.converter.NamedQuery;
import com.eugenefe.entity.MarketVariable;
import com.eugenfe.util.ProductType;

@Name("productSelectAction")
@Scope(ScopeType.CONVERSATION)
public class ProductSelectAction {
	@Logger
	private Log log;
	@In
	private EntityManager entityManager;

//	@Out(scope=ScopeType.CONVERSATION)
	private List<MarketVariable> products;
	
	@Out(scope=ScopeType.CONVERSATION , required=false)
	private MarketVariable selectedProduct;
	
//	@Out(scope=ScopeType.CONVERSATION)
	private  LazyDataModel<MarketVariable> lazyProducts;

	
	public MarketVariable getSelectedProduct() {
		log.info("Get Selected Product");
		return selectedProduct;
	}

	public void setSelectedProduct(MarketVariable selectedProduct) {
		log.info("Set Selected Product");
		this.selectedProduct = selectedProduct;
	}

	public LazyDataModel<MarketVariable> getLazyProducts() {
		return lazyProducts;
	}

	public void setLazyProducts(LazyDataModel<MarketVariable> lazyProducts) {
		this.lazyProducts = lazyProducts;
	}

	public ProductSelectAction(){
	}
	
//	@Factory(value="products" )
//	public void initBonds(){
//		init();
//	}
//	private void init(){
//		products = entityManager.createQuery(NamedQuery.MarketVariables.getQuery()).getResultList();
//		
//	}
	
	@Factory(value="lazyProducts" , scope=ScopeType.CONVERSATION)
	public LazyDataModel<MarketVariable> initModel(){
		products = entityManager.createQuery(NamedQuery.MarketVariables.getQuery()).getResultList();
//		log.info("product size:#0", products.size());
		lazyProducts = new LazyModelMarketVariable(products);
		
		return lazyProducts;
	}
	
	
	public void onRowSelect(SelectEvent event){
		log.info("On Row Selection : #0", selectedProduct.getMvId());
//		Events.instance().raiseEvent("selectProduct", (MarketVariable)event.getObject());
		Events.instance().raiseEvent("selectProduct", selectedProduct);
	}
}
