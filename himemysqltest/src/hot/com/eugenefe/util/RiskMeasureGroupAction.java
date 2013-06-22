package com.eugenefe.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.hibernate.mapping.Array;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.eugenefe.util.ProductType;
import com.eugenefe.util.RiskMeasure;
import com.eugenefe.util.RiskMeasureGroup;

@Name("riskMeasureGroupAction")
@Scope(ScopeType.SESSION)
public class RiskMeasureGroupAction {
	@In(create=true) 
	private Map<String, String> messages;
	
	
//	@Factory(value="riskMeasureGroup")
//	public List<RiskMeasureGroup> getRiskMeasureGroup() {
//		return Arrays.asList(RiskMeasureGroup.values());  
//    }
	@Factory(value="riskMeasureGroupMap")
	public Map<RiskMeasureGroup, String> getRiskMeasureGroup() {
		Map<RiskMeasureGroup, String> temp  = new HashMap<RiskMeasureGroup, String>();
		for(RiskMeasureGroup aa : RiskMeasureGroup.values()){
			temp.put(aa, messages.get(aa.label));
		}
		
		return temp;
    }
}
