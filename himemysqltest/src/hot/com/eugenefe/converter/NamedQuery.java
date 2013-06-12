package com.eugenefe.converter;

public enum NamedQuery {
	PortfolioJoinReturnBssd(
			"select a from Portfolio a " 
			+ "join fetch a.portfolioReturns b "
			+ "where b.id.bssd = #{basedateBean.bssd} ")
	
	,Position("select a from Position a ")

	,PositionReturn("select a from PositionReturn a where a.id.bssd = #{basedateBean.bssd}")
	
	,Bonds ("select a from Bond a")
	
	,MarketVariables ("select a from MarketVariable a")
	
	,BondHisList("select a from BondHis a")
	
	,StockHisList("select a from StockHis a")
	;
	
	private String query;
	
	private NamedQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return this.query;
	}
}
