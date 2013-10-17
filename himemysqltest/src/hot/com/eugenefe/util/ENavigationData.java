package com.eugenefe.util;

import java.util.Arrays;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;


public enum ENavigationData {
	VolatilityHis("com.eugenefe.entity.VolatilityHis")
	,IntRateHis(  "com.eugenefe.entity.IntRateHis")
	,PositionRisk(  "com.eugenefe.entity.PositionRisk")
	,PositionReturn(  "com.eugenefe.entity.PositionReturn")
	,IrCurve( "com.eugenefe.entity.IrCurve")
	,FxCash ("com.eugenefe.entity.FxCash")
	;

	
	private String qualifiedName;
	
	private ENavigationData(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
	public String getQualifiedName() {
		return this.qualifiedName;
	}
	
	public String getShortName(){
		return this.name();
	}
	
	
}
