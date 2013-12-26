package com.eugenefe.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.eugenefe.converter.LazyModelMarketVariable;
import com.eugenefe.converter.LazyModelVcvHis;
import com.eugenefe.entity.Hifive;
import com.eugenefe.entity.IntRate;
import com.eugenefe.entity.IrCurve;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.entity.VcvMatrix;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.entity.Volatility;
import com.eugenefe.entity.VolatilityHis;
import com.eugenefe.enums.EMaturity;
import com.eugenefe.util.ColumnModel;
import com.eugenefe.util.CrossTableModelOld;
import com.eugenefe.util.FlagBean;
import com.eugenefe.util.MarketVariableType;
import com.eugenefe.util.NamedQuery;
import com.eugenefe.util.PivotTableModel;

@Name("tableHifiveInit")
@Scope(ScopeType.CONVERSATION)
public class TableHifiveInit {
	@Logger
	private Log log;

	@In
	private Session session;

	private List<Hifive> hifiveList;
	
	public List<Hifive> getHifiveList() {
		return hifiveList;
	}

	public void setHifiveList(List<Hifive> hifiveList) {
		this.hifiveList = hifiveList;
	}

	private Hifive selectHifive;
	
	public Hifive getSelectHifive() {
		return selectHifive;
	}

	public void setSelectHifive(Hifive selectHifive) {
		this.selectHifive = selectHifive;
	}

	private List<Hifive> selectHifiveList;
	
	public List<Hifive> getSelectHifiveList() {
		return selectHifiveList;
	}

	public void setSelectHifiveList(List<Hifive> selectHifiveList) {
		this.selectHifiveList = selectHifiveList;
	}

	public TableHifiveInit() {
		System.out.println("Construction TableHifiveInit");
	}

	// *******************************************
	@Create
	public void create() {
		log.info("HiFive Creation:#0");
		loadHiFive();
	}
	
	public void loadHiFive() {
//		log.info("HiFive:#{basedateBean.bssd}");
//		Filter filter = session.enableFilter("filterBtwnDate")
//				.setParameter("stBssd", basedateBean.getStBssd())
//				.setParameter("endBssd", basedateBean.getEndBssd());
//		Filter filterBssd = session.enableFilter("filterCurrentDate").setParameter("bssd", basedateBean.getBssd());

		hifiveList = session.createQuery("from Hifive").list();
		
//		System.out.println("SizeAAA" + hifiveList.size());
//		int listSize = hifiveList.size();
//		if(listSize < 21){
//			for( int i=0; i< 21- listSize; i++){
//				Hifive temp = new Hifive();
//				temp.setProdId("temp"+ i);
//				temp.setHifiveType("-");
//				temp.setProdName("");
//				hifiveList.add(temp);
//			}
//		}
		selectHifive = hifiveList.get(0);
		log.info("selected: #0, #1", selectHifive.getProdId());
	}
	
//	public void onSelect(){
//		log.info("In the Event on Row Select");
//		
//		if (selectHifive != null) {
//			
//		}
//	}
	

}
