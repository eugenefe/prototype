package com.eugenefe.session;

import com.eugenefe.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("stockList")
public class StockList extends EntityQuery<Stock> {

	private static final String EJBQL = "select a from Stock a";

	private static final String[] RESTRICTIONS = {
			"lower(bizunit.orgId) like lower(concat(#{bizunitList.bizunit.orgId},'%'))",
			"lower(bizunit.orgName) like lower(concat(#{bizunitList.bizunit.orgName},'%'))", };

	private Stock stock = new Stock();

	public StockList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Stock getStock() {
		return stock;
	}
}
