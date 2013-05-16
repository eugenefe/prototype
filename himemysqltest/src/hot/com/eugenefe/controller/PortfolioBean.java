package com.eugenefe.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.entity.PortfolioReturn;
import com.eugenefe.session.PortfolioReturnBssdList;
import com.eugenefe.session.PortfolioReturnList;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("portfolioBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioBean implements Serializable {

	@Logger
	private Log log;

	@In(create = true)
	private PortfolioReturnBssdList portfolioReturnBssdList;
//	private PortfolioReturnList portfolioReturnList;

	private List<Portfolio> selectedHierarchies ;
	
//	@Out(required =false)
	private TreeNode portfolioRoot;
	private TreeNode portfolioSuperRoot;

	private TreeNode selectedNode;

	private Portfolio selectedPortfolio;
	private Portfolio selectedSubPortfolio;

	private List<Portfolio> fullPortfolios = new ArrayList<Portfolio>();

	private List<Portfolio> subPortfolios= new ArrayList<Portfolio>() ;
	private List<Portfolio> filterSubPorts;
	
	private String searchString;
	

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public PortfolioBean() {
		Portfolio superRoot = new Portfolio("superRoot", "SuperRoot");
		Portfolio root = new Portfolio("root", "Root");

		portfolioSuperRoot = new DefaultTreeNode(superRoot, null);
		portfolioRoot = new DefaultTreeNode(root, portfolioSuperRoot);
		
		portfolioRoot.setExpanded(true);
	}

// -----------------------Initalizer------------------------
	@Create
	@Observer("changeBssd")
	public void init() {
		fullPortfolios=new ArrayList<Portfolio>();
		subPortfolios=new ArrayList<Portfolio>();
		searchString = null;
		
		for (PortfolioReturn aa : portfolioReturnBssdList.getResultList()) {
			log.info("Loop in Initalizing PortfolioBean Creation with Given Bssd: #0", aa.getBasedate().getBssd());
			fullPortfolios.add(aa.getPortfolio());
		}
		log.info("End of Initalizing PortfolioBean Creation with Given Bssd: #0");
		initTree();
	}
	
//	@Factory(value="portfolioRoot" , autoCreate=true)
//	@Factory(value="portfolioRoot" )
	public void initTree() {
		log.info("Start Initalizer PortfolioTree");

		// Hierarchy º° Root Portfolio
		selectedHierarchies =   new ArrayList<Portfolio>();

		for (Portfolio aa : fullPortfolios) {
			if (aa.getParentPortfolio() == null) {
				selectedHierarchies.add(aa);
//				rootPortString.add(aa.getPortName());
			}
		}

		portfolioRoot.getChildren().clear();
		for (Portfolio bb : selectedHierarchies) {
			TreeNode childNode = new DefaultTreeNode(bb, portfolioRoot);
			childNode.setExpanded(true);
			recursive(fullPortfolios, childNode);
		}
		
		log.info("Initialize Portfolio Tree :#0" , portfolioRoot.getChildCount());
		
		if(portfolioRoot.getChildCount()!=0){
			portfolioRoot.getChildren().get(0).setSelected(true);
			selectedNode = portfolioRoot.getChildren().get(0);
			selectedPortfolio= (Portfolio)(portfolioRoot.getChildren().get(0).getData());
			subPortfolios= selectedPortfolio.getChildPortfolios();
//			Events.instance().raiseEvent("changeTree", selectedPortfolio);
			log.info("End of Raise Event : #0 , #1", selectedPortfolio.getPortId(),subPortfolios.size());
		}
	}

// --------------------------------Getter and Setter ---------------

	public TreeNode getPortfolioRoot() {
		return portfolioRoot;
	}

	public void setPortfolioRoot(TreeNode portfolioRoot) {
		this.portfolioRoot = portfolioRoot;
	}

	public TreeNode getPortfolioSuperRoot() {
		return portfolioSuperRoot;
	}

	public void setPortfolioSuperRoot(TreeNode portfolioSuperRoot) {
		this.portfolioSuperRoot = portfolioSuperRoot;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}

	public Portfolio getSelectedSubPortfolio() {
		return selectedSubPortfolio;
	}

	public void setSelectedSubPortfolio(Portfolio selectedSubPortfolio) {
		this.selectedSubPortfolio = selectedSubPortfolio;
	}

	public List<Portfolio> getFullPortfolios() {
		return fullPortfolios;
	}

	public void setFullPortfolios(List<Portfolio> fullPortfolios) {
		this.fullPortfolios = fullPortfolios;
	}

	public List<Portfolio> getSubPortfolios() {
		return subPortfolios;
	}

	public void setSubPortfolios(List<Portfolio> subPortfolios) {
		this.subPortfolios = subPortfolios;
	}

	public List<Portfolio> getFilterSubPorts() {
		return filterSubPorts;
	}

	public void setFilterSubPorts(List<Portfolio> filterSubPorts) {
		this.filterSubPorts = filterSubPorts;
	}

	public List<Portfolio> getSelectedHierarchies() {
		return selectedHierarchies;
	}

	public void setSelectedHierarchies(List<Portfolio> selectedHierarchies) {
		this.selectedHierarchies = selectedHierarchies;
	}
	
//----------------------Event Listener------------------
	
	public void onNodeSelect(NodeSelectEvent event) {
		subPortfolios = new ArrayList<Portfolio>();
		filterSubPorts = null;
		searchString = null;
//		selectedPortfolio = (Portfolio) event.getTreeNode().getData();
		selectedPortfolio = (Portfolio) selectedNode.getData();
		subPortfolios = selectedPortfolio.getChildPortfolios();
		log.info("Call Node Select Event: #0, #1, #2", 
					selectedPortfolio.getPortId(), selectedPortfolio.getChildPortfolios().size(), subPortfolios.size());
		
	}
	
//	@Observer("changeBssd")
//	public void onDateSelect(String bssd){
//		fullPortfolios.clear();
//		log.info("Change BaseDate Event 1: #0, #1");
//		for(PortfolioReturn aa: portfolioReturnBssdList.getResultList()){
//			fullPortfolios.add(aa.getPortfolio());
////			log.info("Change BaseDate Event 2: #0, #1");
//		}
//		
//		log.info("Change BaseDate Event 3 : #0, #1" , fullPortfolios.size());
//		initTree();
//	}
//	
	
	// ----------------------------- helper method----------------------------------------

	private void recursive(List<Portfolio> port, TreeNode node) {
		List<IPortfolio> tempList = new ArrayList<IPortfolio>();
//		Portfolio tempPort = (Portfolio)node.getData();
//		tempList = tempPort.getChildren();
		
		String parentId = ((Portfolio)node.getData()).getPortId();
		tempList = getSubPortfolios(parentId, port);
		log.info("ParentId in SubPort : #0,#1,#2", parentId, tempList.size(), node);

		for (IPortfolio k : tempList) {
			TreeNode childNode = new DefaultTreeNode(k, node);
			childNode.setExpanded(true);
			recursive(port, childNode);
		}
	}

	private List<IPortfolio> getSubPortfolios(String parentId, List<Portfolio> port) {
		List<IPortfolio> returnList = new ArrayList<IPortfolio>();
		for (Portfolio k : port) {
			if (k.getParentPortfolio() != null 
					&& k.getParentPortfolio().getPortId().equals(parentId)) {
				returnList.add(k);
			}
		}
		
		return returnList;
	}

}
