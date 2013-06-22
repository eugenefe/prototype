package com.eugenefe.util;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("viewUrl")
@Scope(ScopeType.SESSION)
public enum EView {
	 Home("/view/home.xhtml")
	,MarketVarialbe("/view/v100MarketVariableInit.xhtml")
	,RiskComponent("/view/v100MarketVariableInit.xhtml")
	,RiskAnalysis("/view/v100MarketVariableInit.xhtml")
	,WhatIf("/view/v100MarketVariableInit.xhtml")
	,StressTest("/view/v100MarketVariableInit.xhtml")
	,BackTest("/view/v100MarketVariableInit.xhtml")
	,Settings("/view/v100MarketVariableInit.xhtml")
	;
	
	public final String url;

	private EView(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	
	

}
