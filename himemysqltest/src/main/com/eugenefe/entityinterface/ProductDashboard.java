package com.eugenefe.entityinterface;

import java.math.BigDecimal;

import com.eugenfe.util.ProductType;

public class ProductDashboard {
//	private String mvType;
	private ProductType mvType;
	private long posCnt;
	private BigDecimal posAmt;
	
	public ProductDashboard(){
		
	}
	public ProductDashboard(ProductType mvType) {
		this.mvType = mvType;
	}
	public ProductDashboard(ProductType mvType, long posCnt) {
		this.mvType = mvType;
		this.posCnt = posCnt;
	}
	public ProductDashboard(ProductType mvType,  BigDecimal posAmt) {
		this.mvType = mvType;
		this.posAmt = posAmt;
	}
	public ProductDashboard(ProductType mvType, long posCnt, BigDecimal posAmt) {
		this.mvType = mvType;
		this.posCnt = posCnt;
		this.posAmt = posAmt;
	}


	public ProductType getMvType() {
		return mvType;
	}

	public void setMvType(ProductType mvType) {
		this.mvType = mvType;
	}

	public long getPosCnt() {
		return posCnt;
	}

	public void setPosCnt(long posCnt) {
		this.posCnt = posCnt;
	}

	public BigDecimal getPosAmt() {
		return posAmt;
	}

	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}
	

}
