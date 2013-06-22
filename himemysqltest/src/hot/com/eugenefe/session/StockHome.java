package com.eugenefe.session;

import java.util.List;

import com.eugenefe.entity.*;
import com.eugenefe.util.ProductType;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.EntityHome;

@Name("stockHome")
public class StockHome extends EntityHome<Stock> {

	public void setStockId(String id) {
		setId(id);
	}

	public String getStockId() {
		return (String) getId();
	}

	@Override
	protected Stock createInstance() {
		Stock stock = new Stock();
		return stock;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Stock getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	@Observer(value="selectProduct")
	public void onSelectStock(MarketVariable selectProduct){
		if(ProductType.STOCK.equals(selectProduct.getMvType())){
			setStockId(selectProduct.getMvId());
			Events.instance().raiseEvent("selectStock", getInstance());
		}
	}
}
