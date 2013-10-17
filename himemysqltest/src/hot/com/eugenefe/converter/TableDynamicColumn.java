package com.eugenefe.converter;

import java.util.ArrayList;
import java.util.List;

public class TableDynamicColumn  implements Comparable<TableDynamicColumn>{
	private String colProperties;
	private List<String> colProperty;
	private String colName;
	
	
	public TableDynamicColumn(String colProperties, String colName) {
		this.colProperties = colProperties;
		this.colName = colName;
		this.colProperty = new ArrayList<String>();
//		colProperties.split("{")[1].split("}")[0]
//		for (String splitProp : colProperties.split("{")[1].split("}")[0].split("\\.")) {
			for (String splitProp : colProperties.split("\\.")) {	
			if(!splitProp.startsWith("#") && !splitProp.endsWith("}") ){
				colProperty.add(splitProp);
			}
		}
	}
	
	@Override
	public int compareTo(TableDynamicColumn other) {
		return this.colProperties.compareTo(other.colProperties);
	}

	public String getColProperties() {
		return colProperties;
	}

	public void setColProperties(String colProperties) {
		this.colProperties = colProperties;
	}

	public List<String> getColProperty() {
		return colProperty;
	}

	public void setColProperty(List<String> colProperty) {
		this.colProperty = colProperty;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}
	
	
	
	
}
