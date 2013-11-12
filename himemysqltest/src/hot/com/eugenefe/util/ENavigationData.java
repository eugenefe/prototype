package com.eugenefe.util;

import java.util.Arrays;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;


public enum ENavigationData {
	Bond("Product","com.eugenefe.entity.Bond")
	,Etf("Product","com.eugenefe.entity.Etf")
	,Futures("Product","com.eugenefe.entity.Futures")
	,FxCash ("Product","com.eugenefe.entity.FxCash")
//	,IrcBucket("com.eugenefe.entity.IrcBucket")
	
	,IrCurve("Master", "com.eugenefe.entity.IrCurve")
	,IntRate("Master","com.eugenefe.entity.IntRate")
	,Counterparty("Master","com.eugenefe.entity.Counterparty")
	,Employee("Master","com.eugenefe.entity.Employee")
	,Bizunit("Master","com.eugenefe.entity.Bizunit")
	
	,BondHis("History","com.eugenefe.entity.BondHis")
	,EtfHis("History","com.eugenefe.entity.EtfHis")
	,FuturesHis("History","com.eugenefe.entity.FuturesHis")
	
	,IntRateHis("History",  "com.eugenefe.entity.IntRateHis")
	,FxRateHis("History","com.eugenefe.entity.FxRateHis")
	,VolatilityHis("History","com.eugenefe.entity.VolatilityHis")
	
	,PositionRisk("Position",  "com.eugenefe.entity.PositionRisk")
	,PositionReturn("Position",  "com.eugenefe.entity.PositionReturn")
	,Portfolio("Portfolio","com.eugenefe.entity.Portfolio")
	,PortfolioReturn("Portfolio","com.eugenefe.entity.PortfolioReturn")
	;

	
	private String group;
	private String qualifiedName;
	
	private ENavigationData(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
	private ENavigationData(String group,String qualifiedName) {
		this.group = group;
		this.qualifiedName = qualifiedName;
	}
	public String getQualifiedName() {
		return this.qualifiedName;
	}
	
	public String getShortName(){
		return this.name();
	}
	public String getGroup(){
		return this.group;
	}
	
}
