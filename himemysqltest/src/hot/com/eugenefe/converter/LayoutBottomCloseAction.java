package com.eugenefe.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.CurrentDate;
import org.jboss.seam.log.Log;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import com.eugenefe.session.BasedateList;
import com.eugenfe.util.ProductType;


@Name("layoutBottomCloseAction")
@Scope(ScopeType.SESSION)
//@Scope(ScopeType.CONVERSATION)
public class LayoutBottomCloseAction implements Serializable {

	@Out
	private boolean layoutBottomClosed = false;
	
	public LayoutBottomCloseAction() {
	}
	
	
	public boolean isLayoutBottomClosed() {
		return layoutBottomClosed;
	}

	public void setLayoutBottomClosed(boolean layoutBottomClosed) {
		this.layoutBottomClosed = layoutBottomClosed;
	}
	
	@Factory(value="layoutBottomClosed")
	public boolean initClose(){
		return false;
	}
	
	public void onProductTabChange(TabChangeEvent event){
		System.out.println("On Tab Change" + event.getTab().getTitle());
		layoutBottomClosed = event.getTab().getTitle().equals("tabStock");
	}
	
	
	
}
