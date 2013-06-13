package com.eugenfe.util;

public enum ProductType {
	BOND(
			"BOND"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,STOCK(
			"STOCK"
			,"select a from StockHis a where a.id.stockId =#{selectedProduct.mvId}"
	)
	,FUTURES(
			"FUTURES" 
			,"select a from  FuturesHis a where a.id.futuresId=#{selectedProduct.mvId}"
	)
	,OPTION("OPTION"
			,"select a from  OptionHis a where a.id.optionId=#{selectedProduct.mvId}"
	)
	,ETF(
			"ETF"
			,"select a from  EtfHis a where a.id.etfId=#{selectedProduct.mvId}"
	)
	,ELW(
			"ELW"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	,ELS(
			"ELS"
			,"select a from  BondHis a where a.id.bondId=#{selectedProduct.mvId}"
	)
	;
	
	private String type;
	private String query;
	
	private ProductType(String type, String query) {
		this.type = type;
		this.query =query;
	}
	public String getType() {
		return this.type;
	}
	public String getQuery(){
		return this.query;
	}
}
