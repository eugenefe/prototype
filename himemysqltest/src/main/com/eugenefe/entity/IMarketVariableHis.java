package com.eugenefe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.eugenfe.util.ProductType;

public interface IMarketVariableHis extends Serializable{
	
	public String getBssd();
	public String getMvId();
	public String getMvName();
//	public ProductType getMvType();
	public String getMvType();
	
	public double getCurrentPrice();
	public double getBeforePrice();
	
	public BigDecimal getVolume();
	public BigDecimal getVolumeAmt();
	
	public BigDecimal getAdjDuration();
	public BigDecimal getMdDuration();
	public BigDecimal getEffectDuration();
	public BigDecimal getAdjConvexity();
	public BigDecimal getEffectConvexity();
}
