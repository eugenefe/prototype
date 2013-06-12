package com.eugenefe.converter;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.eugenefe.entity.MarketVariable;

@Name("config")
@Scope(ScopeType.SESSION)
public class Config {
	
	
	private String productHisXhtml;
	public Config(){
		
	}
	
	public String getProductHisXhtml(MarketVariable mv) {
		if(mv==null){
			return "/fragment/productHis.xhtml";
		}
		switch (mv.getMvType()) {
		case BOND:
			productHisXhtml = "/fragment/productHis.xhtml";
			break;

		default:
			productHisXhtml ="/fragment/productHis.xhtml";
			break;
		}
		return productHisXhtml;
	}
	
	public String getProductHisXhtml() {
		productHisXhtml = "/fragment/productHis.xhtml";
		return productHisXhtml;
	}

	public void setProductHisXhtml(String productHisXhtml) {
		this.productHisXhtml = productHisXhtml;
	}
	
	
	

}
