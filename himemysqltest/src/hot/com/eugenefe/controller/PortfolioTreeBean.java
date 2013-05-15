package com.eugenefe.controller;

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

@Name("portfolioTree")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeBean {

	@Logger
	private Log log;
	// @In
	// private FacesMessage facesMessages;
	// @In
	// EntityManager entityManager;

	private TreeNode portfolioSuperRoot;
	private TreeNode portfolioRoot;
	private TreeNode selectedNode;
	private boolean liveResize;

	@In(create = true)
	private PortfolioList portfolioList;

	public PortfolioTreeBean() {

		IPortfolio superRoot = new Portfolio("superRoot", "SuperRoot");
		IPortfolio root = new Portfolio("root", "Root");

		portfolioSuperRoot = new DefaultTreeNode(superRoot, null);
		portfolioRoot = new DefaultTreeNode(root, portfolioSuperRoot);
		// portfolioRoot.setSelectable(false);
		portfolioRoot.setExpanded(true);
	}

	// @Factory(autoCreate= true, value ="portfolioRoot")
	public void initTree() {
		// ÀüÃ¼ List
		List<Portfolio> fullPort = portfolioList.getResultList();
		// Hierarchy º° Root Portfolio
		List<IPortfolio> rootPort = new ArrayList<IPortfolio>();

		for (Portfolio aa : fullPort) {
			if (aa.getParentPortfolio() == null) {
				rootPort.add(aa);
			}
		}

		for (IPortfolio bb : rootPort) {
			TreeNode childNode = new DefaultTreeNode(bb, portfolioRoot);
			childNode.setExpanded(true);
			recursive(fullPort, childNode);
		}
	}

	public TreeNode getPortfolioSuperRoot() {
		if (portfolioRoot.getChildCount() == 0) {
			initTree();
		}
		return portfolioSuperRoot;
	}

	public void setPortfolioSuperRoot(TreeNode portfolioSuperRoot) {
		this.portfolioSuperRoot = portfolioSuperRoot;
	}

	public TreeNode getPortfolioRoot() {
		if (portfolioRoot.getChildCount() == 0) {
			initTree();
		}
		return portfolioRoot;
	}

	public void setPortfolioRoot(TreeNode portfolioRoot) {
		this.portfolioRoot = portfolioRoot;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void displaySelectedSingle() {
		if (selectedNode != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData()
					.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void deleteNode() {
		EntityManager em = portfolioList.getEntityManager();
		recursiveDelete(em, selectedNode);

		selectedNode.getChildren().clear();
		if (!selectedNode.getData().toString().equals("Root")) {
			selectedNode.getParent().getChildren().remove(selectedNode);
			selectedNode.setParent(null);

			selectedNode = null;
		}

		em.flush();
	}

	public boolean isLiveResize() {
		return true;
	}

	public void setLiveResize(boolean liveResize) {
		this.liveResize = liveResize;
	}

	// ----------------------------- helper method----------------------------------------

	public void recursive(List<Portfolio> port, TreeNode node) {
		List<IPortfolio> tempList = new ArrayList<IPortfolio>();
		String parentId = ((Portfolio)node.getData()).getPortId();
		tempList = getSubPortfolio(parentId, port);
		log.info("ParentId in SubPort : #0,#1,#2", parentId, tempList.size(), node);

		for (IPortfolio k : tempList) {
			TreeNode childNode = new DefaultTreeNode(k, node);
			childNode.setExpanded(true);
			recursive(port, childNode);
		}
	}

	public List<IPortfolio> getSubPortfolio(String parentId, List<Portfolio> port) {
		List<IPortfolio> returnList = new ArrayList<IPortfolio>();
		for (Portfolio k : port) {
			if (k.getParentPortfolio() != null 
					&& k.getParentPortfolio().getPortId().equals(parentId)) {
				returnList.add(k);
			}
		}
		return returnList;
	}

//	public TreeNode getPortfolioSuperRoot() {
//		if (portfolioRoot.getChildCount() == 0) {
//			initTree();
//		}
//		return portfolioSuperRoot;
//	}

	public void recursiveDelete(EntityManager em, TreeNode node) {
		for (TreeNode aa : node.getChildren()) {
			log.info("Recursive Delete : #0, #1", aa, aa.getChildCount());
			if (aa.getChildCount() == 0) {
				Portfolio port = em.find(Portfolio.class, aa.getData().toString());
				log.info("Deleted Port:#0", port.getPortId());
				em.remove(port);

			} else {
				recursiveDelete(em, aa);
			}
		}
		Portfolio parentPort = em.find(Portfolio.class, node.getData().toString());
		if (parentPort != null) {
			em.remove(parentPort);
		}
	}

	public void recursiveDelete1(EntityManager em, TreeNode node) {
		for (TreeNode aa : node.getChildren()) {
			recursiveDelete(em, aa);
		}

		if (node.getChildCount() == 0) {
			Portfolio port = em.find(Portfolio.class, node.getData().toString());
			log.info("Deleted Port:#0", port.getPortId());
			if (port != null) {
				em.remove(port);
			}
		}

	}

}
