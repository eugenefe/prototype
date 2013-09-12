package com.eugenefe.util;

import java.math.BigDecimal;
import java.util.Map;

import org.primefaces.model.SelectableDataModel;

public class CrossTableModel  {
	private String label;
	private Map<String, BigDecimal> contentMap;
	
	public CrossTableModel(String label, Map<String, BigDecimal> contentMap) {  
        this.label = label;  
        this.contentMap = contentMap;  
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, BigDecimal> getContentMap() {
		return contentMap;
	}

	public void setContentMap(Map<String, BigDecimal> contentMap) {
		this.contentMap = contentMap;
	}
}
