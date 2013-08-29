package com.eugenefe.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.eugenefe.entity.MarketVariable;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.CrossTableModel;
import com.eugenefe.util.NamedQuery;

@Name("vcvMatrixAction")
@Scope(ScopeType.CONVERSATION)
public class VcvMatrixAction {
	@Logger
	private Log log;

	private List<MarketVariable> selRiskFactors = new ArrayList<MarketVariable>();
	
	@In
	@Out
	private RiskFactorInit riskFactorInit;
	
	
	public VcvMatrixAction() {
		System.out.println("Construcion VcvMatrixAction");
	}
	
//	@Observer("selRiskFactors")
	public void aa(List<MarketVariable> selRf){
		log.info("AAAAA : #0", selRf.size());
		riskFactorInit.setRiskFactors(selRf);
		riskFactorInit.loadMatrix();
	}
 
}
