package com.eugenefe.util;

import java.util.ArrayList;
import java.util.List;

public enum MarketVariableType {
	BOND(
			"BOND"
			,true
			,"IR"
			,"select a from  BondHis a where a.id.bondId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,STOCK(
			"STOCK"
			,true
			,"EQ"
			," select a from StockHis a where a.id.stockId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "		
	)
	,FUTURES(
			"FUTURES"
			,true
			,"NONE"
			,"select a from  FuturesHis a where a.id.futuresId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,OPTION(
			"OPTION"
			,true
			,"NONE"
			,"select a from  OptionHis a where a.id.optionId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
			)
	,ETF(
			"ETF"
			,true			
			,"NONE"
			,"select a from  EtfHis a where a.id.etfId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,ELW(
			"ELW"
			,true			
			,"NONE"
			,"select a from  OptionHis a where a.id.optionId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,ELS(
			"ELS"
			,true
			,"NONE"
			,"select a from BondHis a where a.id.bondId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,SWAP(
			"SWAP"
			,true
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,DEPOSIT(
			"DEPOSIT"
			,true
			,"NONE"
			,"select a from  BondHis a where a.id.bondId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	
	,FX_RATE(
			"FX_RATE"
			,false
			,"NONE"
			,"select a from  FxRateHis a where a.id.fxId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,INT_RATE(
			"INT_RATE"
			,false
			,"NONE"
			,"select a from  IntRateHis a where a.id.irId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	,S_INDEX(
			"S_INDEX"
			,false
			,"NONE"
			,"select a from  StockIndexHis a where a.id.stIndexId=#{selectedMarketVariable.mvId}" +
					" and a.id.bssd > #{basedateBean.stBssd} " +
					" and a.id.bssd <=#{basedateBean.endBssd} "	
	)
	;
	
	private String type;
	private boolean isProduct; 
	private String rfType;
	private String query;
	
	private MarketVariableType(String type, boolean isProduct, String rfType, String query) {
		this.type = type;
		this.isProduct = isProduct;
		this.rfType =rfType;
		this.query =query;
	}
	
	public String getType() {
		return this.type;
	}
	
	public boolean isProduct() {
		return isProduct;
	}

	public String getQuery(){
		return this.query;
	}
	public String getRfType() {
		return rfType;
	}
	
	public static List<MarketVariableType> getProductTypes(){
		List<MarketVariableType> rst = new ArrayList<MarketVariableType>();
		for(MarketVariableType aa : MarketVariableType.values()){
			if(aa.isProduct){
				rst.add(aa);
			}
		}
		return rst;
	}
}
