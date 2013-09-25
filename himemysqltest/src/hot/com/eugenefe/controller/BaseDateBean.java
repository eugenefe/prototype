package com.eugenefe.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.CurrentDate;
import org.jboss.seam.log.Log;
import org.primefaces.event.SelectEvent;

import com.eugenefe.enums.EMaturity;
import com.eugenefe.session.BasedateList;
import com.eugenefe.util.FnCalendar;


@Name("basedateBean")
//@Scope(ScopeType.SESSION)
@Scope(ScopeType.CONVERSATION)
public class BaseDateBean implements Serializable {

	@Logger
	private Log log;
	
	@In
	private BasedateSession basedateSession;
	@In(value = "#{conversation.viewId}")
	private String viewId;
//	public BasedateSession getBasedateSession() {
//		return basedateSession;
//	}
//	public void setBasedateSession(BasedateSession basedateSession) {
//		this.basedateSession = basedateSession;
//	}
	
//	@In(create = true)
//	private BasedateList basedateList;


//	private FnCalendar cal;
	
	private Date baseDate;
	private Date stDate;
	private Date endDate;

//	@Out(scope=ScopeType.SESSION)
	private String bssd;
	private String stBssd;
	private String endBssd;
	
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
	
	
	public BaseDateBean() {
	}

//	@Create
	public void init(){
//		cal = FnCalendar.getInstance();
//		cal = new FnCalendar(2013, 4, 29);
//		log.info("Calendar : #0 , #1", cal.getTime());
		
//		baseDate = cal.getTime();
//		endDate =cal.getTime();
//		stDate = cal.minusTerm(EMaturity.M03, true).getTime();
//
//		bssd = format.format(cal.getTime());
//	    stBssd = format.format(stDate);
//	    endBssd = format.format(endDate);
					
	}
	
	@Create
	public void initNew(){
		baseDate = basedateSession.getBaseDate();
		stDate = basedateSession.getStDate();
		endDate = basedateSession.getEndDate();
		
		bssd = basedateSession.getBssd();
	    stBssd = basedateSession.getStBssd();
	    endBssd = basedateSession.getEndBssd();
	}
	
	public Date getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}

	public String getBssd() {
		return bssd;
	}

	public void setBssd(String bssd) {
		this.bssd = bssd;
	}
	

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public String getStBssd() {
		return stBssd;
	}

	public void setStBssd(String stBssd) {
		this.stBssd = stBssd;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndBssd() {
		return endBssd;
	}

	public void setEndBssd(String endBssd) {
		this.endBssd = endBssd;
	}

//	@Begin(join=true)
	public void handleDateSelect(SelectEvent event) {
		log.info("handleDateSelect Id1 :#0,#1", Conversation.instance().getId(), Conversation.instance().isLongRunning());
		log.info("Start ChangeBssd Event");
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		
        bssd = format.format(event.getObject());
        
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", bssd));
        
//        String eventName = "changeBssd_" + FacesContext.getCurrentInstance().getViewRoot().getViewId();
        String eventName = "changeBssd_" + viewId;
        log.info("View ID : #0",eventName);
        
        Events.instance().raiseEvent(eventName, bssd);
        log.info("End of ChangeBssd Event");
        log.info("handleDateSelect Id2 :#0,#1", Conversation.instance().getId(), Conversation.instance().isLongRunning());
	}
	
	public void handleStartDateSelect(SelectEvent event){
		stBssd = format.format(event.getObject());
//		String eventName = "evtDateChange_" + FacesContext.getCurrentInstance().getViewRoot().getViewId();
		String eventName = "evtDateChange_" + viewId;
		
		log.info("handleStartDateSelect : #0,#1", stBssd,eventName);
        Events.instance().raiseEvent(eventName, stBssd);
//        log.info("handleStartDateSelect1 : #0,#1", stBssd,stDate);
	}
	
	public void handleEndDateSelect(SelectEvent event){
        endBssd = format.format(event.getObject());
//        String eventName = "evtDateChange_" + FacesContext.getCurrentInstance().getViewRoot().getViewId();
        String eventName = "evtDateChange_" + viewId;
		log.info("handleEndDateSelect : #0,#1", endBssd,endDate);
        Events.instance().raiseEvent(eventName, endBssd);
	}

	
}
