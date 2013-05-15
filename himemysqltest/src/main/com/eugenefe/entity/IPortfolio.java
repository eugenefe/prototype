package com.eugenefe.entity;

import java.util.Set;

public interface IPortfolio {
	
	public String getStringId();
	public String getName();
	
	
	public void add(IPortfolio port);
	public void remove(IPortfolio port);
	public Set<IPortfolio> getChildren();
	public IPortfolio getChildren(String portId);
	
	public double getPresValue();
	public double getCalcuatedPv();
	
//  Retrun	������ Interface �� �������� �� �Ϲ����� ������ �����̳�.. �������� ������ ó���� ���� �ְڴ�...
//	public Set<IPortfolioReturn> getReturnInfo();
	public double getDailyReturn();
}
