package com.eugenefe.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioList;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("porfolioBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioBean implements Serializable {

	@Logger
	private Log log;

	@In(create = true)
	private PortfolioList portfolioList;

	private Portfolio portfolio;
	private Portfolio selectedPort;
	private List<Portfolio> portfolios = new ArrayList<Portfolio>();
	private List<Portfolio> filteredPorts = new ArrayList<Portfolio>();

	public PortfolioBean() {

	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public List<Portfolio> getPortfolios() {
		if (this.portfolios.size() == 0) {
			portfolios = portfolioList.getResultList();
			log.info("Call Portfolio Result from DB : #0", portfolios.size());
		}
		log.info("After Call Portfolio Result from DB : #0", portfolios.size());
		return portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public List<Portfolio> getFilteredPorts() {
		return filteredPorts;
	}

	public void setFilteredPorts(List<Portfolio> filteredPorts) {
		this.filteredPorts = filteredPorts;
	}

	public Portfolio getSelectedPort() {  
        return selectedPort;  
    }  
  
    public void setSelectedCar(Portfolio selectedPort) {  
        this.selectedPort = selectedPort;  
    }  
}
