package com.eugenefe.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Filter;
import org.hibernate.Session;
//import org.hibernate.annotations.Filter;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.ConversationIdGenerator;
import org.jboss.seam.core.Events;
//import org.jboss.seam.framework.Query;
import org.jboss.seam.log.Log;
import org.jboss.seam.persistence.HibernateSessionFactory;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.entity.Basedate;
import com.eugenefe.entity.IntRateHis;
import com.eugenefe.entity.IrCurve;
import com.eugenefe.entity.IrcBucket;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.entity.VcvMatrix;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.enums.EMaturity;
import com.eugenefe.session.IrCurveList;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.ComparatorEMaturity;
import com.eugenefe.util.CrossTableModel;
import com.eugenefe.util.FlagBean;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;


@Name("tableIrCurveInit")
@Scope(ScopeType.CONVERSATION)
public class TableIrCurveInit {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;
//	@In
//	private Session hibernateSession;
	
	@In
	private Session session;
	@In(value="#{basedateBean.stBssd}")
	private String stBssd;
	@In(value="#{basedateBean.endBssd}")
	private String endBssd;
	@In(value="#{basedateBean.bssd}")
	private String bssd;
	
//	@In(value="#{flagBean.flagVcvType}")
//	private String flagVcvType;
//	
//	@In(value="#{flagBean.flagVcvRfType}")
//	private String flagVcvRfType;

	private List<IrCurve> irCurveList = new ArrayList<IrCurve>();

	
	public String getStBssd() {
		return stBssd;
	}
	public void setStBssd(String stBssd) {
		this.stBssd = stBssd;
	}
	public String getEndBssd() {
		return endBssd;
	}
	public void setEndBssd(String endBssd) {
		this.endBssd = endBssd;
	}
	public TableIrCurveInit() {
		System.out.println("Construction TableRfVcvMatrixInit");
	}
//****************Getter and Setter
	public List<IrCurve> getIrCurveList() {
		return irCurveList;
	}


	public void setIrCurveList(List<IrCurve> irCurveList) {
		this.irCurveList = irCurveList;
	}

	
	//*******************************************
//	@Observer("changeBssd_/view/v130RfVcvMatrix.xhtml")
	@Create
	public void create(){
		loadIrCurve();
		loadPivotTable();
	}
	
	
	public void loadIrCurve(){
//		Filter filter = hibernateSession.enableFilter("filterBtwnDate")
//			.setParameter("stBssd", "20130511")
//			.setParameter("endBssd", "20130619");
//		
		log.info("Ir Curve con:#{basedateBean.stBssd}, #{basedateBean.endBssd}");
		Filter filter = session.enableFilter("filterBtwnDate")
//								.setParameter("stBssd", stBssd)
				.setParameter("stBssd", "20120501")
								.setParameter("endBssd", endBssd);
//		org.hibernate.Query qr1 = session.createQuery("from IrCurve");
//		irCurveList = qr1.list();
		
		irCurveList = session.createQuery("from IrCurve").list();
		log.info("Ir Curve1 :#0,#1,#2", 
					irCurveList.get(0).getIrcName(),
					irCurveList.get(0).getIrcBucketList().size(),
					irCurveList.get(0).getIrcBucketList().get(0).getIntRate().getIntRateHisList().size());
	}
	@Observer("evtDateChange")
	public void onChange(){
		session.clear();
		loadIrCurve();
		loadPivotTable();
		Events.instance().raiseEvent("evtReloadIrCurve");
	}
	@Observer("changeBssd_/view/v140IrCurve.xhtml")
	public void onBssdChange(){
		session.clear();
		loadIrCurve();
		loadPivotTable();
//		Events.instance().raiseEvent("evtReloadIrCurve");
	}
	
	
	private List<ColumnModel> ircTsHeader;
	private List<CrossTableModel> pivotTableByCurve;
	
	public List<ColumnModel> getIrcTsHeader() {
		return ircTsHeader;
	}
	public void setIrcTsHeader(List<ColumnModel> ircTsHeader) {
		this.ircTsHeader = ircTsHeader;
	}
	public List<CrossTableModel> getPivotTableByCurve() {
		return pivotTableByCurve;
	}
	public void setPivotTableByCurve(List<CrossTableModel> pivotTableByCurve) {
		this.pivotTableByCurve = pivotTableByCurve;
	}
	
	
	
	public void loadPivotTable(){
		ircTsHeader = new ArrayList<ColumnModel>();
		pivotTableByCurve = new ArrayList<CrossTableModel>();
		
		List<EMaturity> maturityList= new ArrayList<EMaturity>();
		
		log.info("Load Pivot Table : #0, #1,#2", bssd, irCurveList.get(0).getIrcBucketList().size());
		for(IrCurve zz : irCurveList){
			Map<String, BigDecimal> tempMap =new HashMap<String, BigDecimal>() ;
			for(IrcBucket aa : zz.getIrcBucketList()){
				if( !maturityList.contains(aa.getMaturityId())){
					maturityList.add(aa.getMaturityId());
					log.info("Maturity1:#0", aa.getMaturityId());
				}
				
				for( IntRateHis bb : aa.getIntRate().getIntRateHisList()){
					if( bb.getBssd().equals(bssd)){
						tempMap.put(aa.getMaturityId().name(), bb.getIntRate());	
						log.info("Load Pivot Table1 : #0, #1,#2", aa.getIrcId().getIrcId(), aa.getMaturityId(), bb.getIntRate());
					}
				}
			}
			pivotTableByCurve.add(new CrossTableModel(zz.getIrcId(), tempMap));
		}
		Collections.sort(maturityList);
		for(EMaturity aa : maturityList){
			ircTsHeader.add(new ColumnModel(aa.name(), aa.name()));
		}
	}
//***************************************************************
	private void resetTable() {
	    DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
	            .findComponent("formIrCurve:tableIrCurve");
	    if (dataTable != null) {
//	    	log.info("In the dataTable");
	    	dataTable.setValueExpression("sortBy", null);
//	    	dataTable.setValueExpression("filterBy", null);
	    	dataTable.setFirst(0);
	        dataTable.reset();
	    }
	}
}
