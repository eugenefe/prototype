package com.eugenefe.util;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

public enum EView {
	 Home("/view/home.xhtml")
	,MarketVariable("/view/v100MarketVariable.xhtml")
//	,MarketVariableSelect("/view/v100MarketVariable.xhtml")
	,Product("/view/v110ProductInit.xhtml")
	,ProductSelect("/view/v110Product.xhtml")
	,RiskFactor("/view/v120RiskFactor.xhtml")
	,RiskComponent("/view/v200RiskComponent.xhtml")
	,RiskAnalysis("/view/v300ReturnRisk.xhtml")
	,WhatIf("/view/v400WhatIf.xhtml")
	,StressTest("/view/v500StressTest.xhtml")
	,BackTest("/view/v600BackTest.xhtml")
	,LimitManagement("/view/v700Limit.xhtml")
	,Settings("/view/v800Settings.xhtml")
	;
	
	public final String url;

	private EView(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	
	

}
