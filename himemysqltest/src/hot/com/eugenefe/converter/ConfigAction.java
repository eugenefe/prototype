package com.eugenefe.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

import com.eugenefe.entity.MarketVariable;

@Name("configAction")
@Scope(ScopeType.CONVERSATION)
public class ConfigAction {
	 
	@In(create=true) 
	private Map<String, String> messages;
	 
	private String productInfoXhtml;
	private List<ColumnModel> productHisColumns = new ArrayList<ColumnModel>();
	
	public ConfigAction(){
//		productInfoXhtml = "/fragment/bondInfo.xhtml";
		
	}
	public String getProductInfoXhtml() {
		return productInfoXhtml;
	}

	public void setProductInfoXhtml(String productInfoXhtml) {
		this.productInfoXhtml = productInfoXhtml;
	}
	
	public List<ColumnModel> getProductHisColumns() {
		return productHisColumns;
	}

	public void setProductHisColumns(List<ColumnModel> productHisColumns) {
		this.productHisColumns = productHisColumns;
	}


//***********************************************


	static public class ColumnModel implements Serializable {  
		  
        private String header;  
        private String property;  
  
        public ColumnModel(String header, String property) {  
            this.header = header;  
            this.property = property;  
        }  
  
        public String getHeader() {  
        	System.out.println("Get Header Method");
            return header;  
        }  
  
        public String getProperty() {  
            return property;  
        }  
    }  
	
	@Observer("selectProduct")
	public void updateColumns(MarketVariable marketVariable) {  
        //reset table state  
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":tabViewProduct:formProductHis:dataTableProductHis");  
//        table.setValueExpression("sortBy", null);  
          
        //update columns  
        createDynamicColumns(marketVariable);  
        
    }  
    
	@Observer("selectProduct")
    public void createDynamicColumns(MarketVariable marketVariable) {  
    	productHisColumns.clear();     
//    	if(marketVariable==null){
//    		productInfoXhtml = "/fragment/bondInfo.xhtml";
//    		return;
//    	}
    	switch (marketVariable.getMvType()) {
		case BOND:
			productInfoXhtml = "/fragment/bondInfo.xhtml";

			productHisColumns.add(new ColumnModel(messages.get("adjDuration"), "adjDuration"));
			productHisColumns.add(new ColumnModel(messages.get("mdDuration"), "mdDuration"));
			productHisColumns.add(new ColumnModel(messages.get("userDefined"), "adjDuration"));
			break;
		case STOCK:
			productInfoXhtml = "/fragment/stockInfo.xhtml";

			productHisColumns.add(new ColumnModel(messages.get("stockBeta"), "stockBeta"));
			break;
		default:
			break;
		}
    }  

}
