package com.eugenefe.util;

public enum NamedQuery {
	PortfolioJoinReturnBssd(
			"select a from Portfolio a " 
			+ "join fetch a.portfolioReturns b "
			+ "where b.id.bssd = #{basedateBean.bssd} " 
			+ "order by a.portId" )
	
	,Position("select a from Position a ")

	,PositionReturn("select a from PositionReturn a where a.id.bssd = #{basedateBean.bssd}")
	
	,Bonds ("select a from Bond a")
	
//	,Products ("select a from MarketVariable a where a.productYN ='Y'")
	
	,MarketVariables ("select a from MarketVariable a ")
	
	,BondHisList("select a from BondHis a")
	
	,StockHisList("select a from StockHis a")
	
	,StockIndexHisList("select a from StockIndexHis a")
	
	,IrCuverHisList("select a from IrCurveHis a")
	;
	;
	
	private String query;
	
	private NamedQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return this.query;
	}
}
