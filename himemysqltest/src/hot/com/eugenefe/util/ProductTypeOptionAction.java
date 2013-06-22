package com.eugenefe.util;

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

import com.eugenefe.session.BasedateList;
import com.eugenefe.util.ProductType;


@Name("productTypeOptionAction")
@Scope(ScopeType.SESSION)
//@Scope(ScopeType.CONVERSATION)
public class ProductTypeOptionAction implements Serializable {

	private SelectItem[] productTypeOption;
	
	public ProductTypeOptionAction() {
	}
	
	@Factory(value="productTypeOption")
	public SelectItem[] getProductType() { 
		productTypeOption = new SelectItem[ProductType.values().length + 1];
		productTypeOption[0] = new SelectItem("", "All");  
		for(int i=0; i<ProductType.values().length;i++){
			productTypeOption[i+1] = new SelectItem(ProductType.values()[i].getType(),ProductType.values()[i].getType());
		}
        return productTypeOption;  
    }
}
