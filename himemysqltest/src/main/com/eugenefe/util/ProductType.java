package com.eugenefe.util;

public enum ProductType {
	BOND(
			"BOND"
			,"IR"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,STOCK(
			"STOCK"
			,"EQ"
			,"select a from StockHis a where a.id.stockId =#{selectedProduct.mvId}"
	)
	,FUTURES(
			"FUTURES"
			,"NONE"
			,"select a from  FuturesHis a where a.id.futuresId=#{selectedProduct.mvId}"
	)
	,OPTION(
			"OPTION"
			,"NONE"
			,"select a from  OptionHis a where a.id.optionId=#{selectedProduct.mvId}"
	)
	,ETF(
			"ETF"
			,"NONE"
			,"select a from  EtfHis a where a.id.etfId=#{selectedProduct.mvId}"
	)
	,ELW(
			"ELW"
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,ELS(
			"ELS"
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,SWAP(
			"SWAP"
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,DEPOSIT(
			"DEPOSIT"
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	
	,FX_RATE(
			"FX RATE"
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,INT_RATE(
			"INT RATE"
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,S_INDEX(
			"S_INDEX"
			,"NONE"
			,"select a from  StockIndexHis a where a.id.stIndexId=#{selectedProduct.mvId}"
	)
	;
	
	private String type;
	private String rfType;
	private String query;
	
	private ProductType(String type, String rfType, String query) {
		this.type = type;
		this.rfType =rfType;
		this.query =query;
	}
	
	public String getType() {
		return this.type;
	}
	public String getQuery(){
		return this.query;
	}
	public String getRfType() {
		return rfType;
	}
}
