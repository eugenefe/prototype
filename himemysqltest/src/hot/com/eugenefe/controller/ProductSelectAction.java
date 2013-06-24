package com.eugenefe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
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
import com.eugenefe.util.EView;
import com.eugenefe.util.NamedQuery;
import com.eugenefe.util.ProductType;
import com.eugenefe.util.RiskMeasure;
import com.eugenefe.util.RiskMeasureGroup;

@Name("productSelectAction")
@Scope(ScopeType.CONVERSATION)
public class ProductSelectAction {
	@Logger
	private Log log;
	@In
	private EntityManager entityManager;

	// @Out(scope=ScopeType.CONVERSATION)
	private List<MarketVariable> products;

	private List<MarketVariable> holdingProducts = new ArrayList<MarketVariable>();
	private List<MarketVariable> riskFactors = new ArrayList<MarketVariable>();

	@Out(scope = ScopeType.CONVERSATION, required = false)
	private MarketVariable selectedProduct;

	 @Out(scope=ScopeType.CONVERSATION)
	private LazyDataModel<MarketVariable> lazyProducts;

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

	public ProductSelectAction() {
	}

	// @Factory(value="products" )
	// public void initBonds(){
	// init();
	// }
	// private void init(){
	// products =
	// entityManager.createQuery(NamedQuery.MarketVariables.getQuery()).getResultList();
	//
	// }

	@Factory(value = "lazyProducts")
	public void initModel() {
		products = entityManager.createQuery(NamedQuery.MarketVariables.getQuery()).getResultList();
		// log.info("product size:#0", products.size());
		lazyProducts = new LazyModelMarketVariable(products);
	}
	
//	@Factory(value = "lazyProducts", scope = ScopeType.CONVERSATION)
//	public LazyDataModel<MarketVariable> initModel() {
//		products = entityManager.createQuery(NamedQuery.MarketVariables.getQuery()).getResultList();
//		// log.info("product size:#0", products.size());
//		lazyProducts = new LazyModelMarketVariable(products);
//		return lazyProducts;
//	}

	public void onViewFullProduct() {
		log.info("In the Full Product : #0" , products.size());
		lazyProducts = new LazyModelMarketVariable(products);
	}

	public void onViewHoldingProduct() {
		String qr = "select a from MarketVariable a join a.positions b where b is not null";
		holdingProducts =entityManager.createQuery(qr ).getResultList();

		log.info("In the Holding Product: #0" , holdingProducts.size());
		lazyProducts = new LazyModelMarketVariable(holdingProducts);
		log.info("In the Holding Product1: #0" , lazyProducts.getRowCount());
	}
	public void onViewRiskFactor() {
		String qr = "select a from MarketVariable a where a.riskFactorYN ='Y'";
		riskFactors =entityManager.createQuery(qr).getResultList();

		lazyProducts = new LazyModelMarketVariable(riskFactors);
		log.info("In the riskFactors1: #0" , lazyProducts.getRowCount());
	}

	public void onRowSelect(SelectEvent event) {
		log.info("On Row Selection : #0", selectedProduct.getMvId());

		FacesContext fc = FacesContext.getCurrentInstance();
		System.out.println("in the redirect1"+fc.getViewRoot().getViewId()+EView.MarketVariableSelect.url);
		if (EView.MarketVariable.url.equals(fc.getViewRoot().getViewId())) {
			System.out.println("In the redirect");
			fc.getApplication().getNavigationHandler()
					.handleNavigation(fc, "null", EView.MarketVariableSelect.url+"?faces-redirect=true");
		}

		// Events.instance().raiseEvent("selectProduct",
		// (MarketVariable)event.getObject());
		Events.instance().raiseEvent("selectProduct", selectedProduct);

		RequestContext requestContext = RequestContext.getCurrentInstance();
		if (selectedProduct.getPositions() == null || selectedProduct.getPositions().size() > 0) {
			requestContext.execute("wgInnerLayout.show('north')");
		} else {
			requestContext.execute("wgInnerLayout.hide('north')");
		}

		if (selectedProduct.mvType == ProductType.BOND || selectedProduct.mvType == ProductType.STOCK) {
			requestContext.execute("wgInnerLayout.show('east')");
		} else {
			requestContext.execute("wgInnerLayout.hide('east')");
		}
		
		
//		for(RiskMeasure aa: RiskMeasure.getMeasures(RiskMeasureGroup.RETURN)){
//			log.info("Measure : #0, #1", aa.getField(), aa.getMeasureGroup().name());
//		}
//		for(RiskMeasure aa: RiskMeasureGroup.RETURN.getMeasures()){
//			log.info("Measure : #0, #1", aa.getField(), aa.getMeasureGroup());
//		}
//		for(RiskMeasure aa: RiskMeasure.values()){
//			log.info("Measure : #0, #1,#2", aa, aa.getField(), aa.getMeasureGroup().name());
//		}
//		log.info("Measure :#0", RiskMeasure.AD.getMeasures().size());
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
