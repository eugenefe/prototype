package com.eugenefe.util;

import java.io.Serializable;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;


@Name("mvTypeOptionAction")
@Scope(ScopeType.SESSION)
//@Scope(ScopeType.CONVERSATION)
public class MarketVariableTypeOptionAction implements Serializable {

	@Out
	private SelectItem[] productTypeOption;
	
	@Out
	private SelectItem[] mvTypeOption;
	
	@Out
	private SelectItem[] allMvTypeOption;
	
	public MarketVariableTypeOptionAction() {
	}
	
//	@Factory(value="productTypeOption")
//	public SelectItem[] getProductType() { 
//		productTypeOption = new SelectItem[MarketVariableType.values().length + 1];
//		productTypeOption[0] = new SelectItem("", "All");  
//		for(int i=0; i<MarketVariableType.values().length;i++){
//			if(MarketVariableType.values()[i].isProduct()){
//				productTypeOption[i+1] = new SelectItem(MarketVariableType.values()[i].getType(),MarketVariableType.values()[i].getType());
//			}
//		}
//        return productTypeOption;  
//    }
	
	
	@Factory(value="productTypeOption")
	public void initProductTypeOption() { 
		initOption();
    }
	
	@Factory(value="mvTypeOption")
	public void initMvTypeOption() { 
		initOption();
    }
	
	@Factory(value="allMvTypeOption")
	public void initAllMvTypeOption() { 
		initOption();
    }
	
	private void initOption() {
		int cnt=0 ;
		int prodInx=0;
		int mvInx=0;
		int totalSize =MarketVariableType.values().length;
		int prodSize = MarketVariableType.getProductTypes().size();
		
		allMvTypeOption = new SelectItem[totalSize+ 1];
		allMvTypeOption[0] = new SelectItem("", "All");  
		
		productTypeOption = new SelectItem[prodSize+1];
		productTypeOption[0] = new SelectItem("", "All");
		
		mvTypeOption = new SelectItem[totalSize - prodSize + 1];
		mvTypeOption[0] = new SelectItem("", "All");

		for(MarketVariableType aa : MarketVariableType.values()){
			cnt=cnt+1;
			allMvTypeOption[cnt] = new SelectItem(aa,aa.getType());
			if(aa.isProduct()){
				prodInx =prodInx+1;
				productTypeOption[prodInx] = new SelectItem(aa.getType(),aa.getType());
			}
			else{
				mvInx =mvInx+1;
				mvTypeOption[mvInx] = new SelectItem(aa.getType(),aa.getType());
			}
		}
		
//		for(int i=0; i<MarketVariableType.values().length;i++){
//			mvTypeOption[i+1] = new SelectItem(MarketVariableType.values()[i],MarketVariableType.values()[i].getType());
//			if(MarketVariableType.values()[i].isProduct()){
//				cnt =cnt+1;
//				productTypeOption[cnt] = new SelectItem(MarketVariableType.values()[i].getType(),MarketVariableType.values()[i].getType());
//			}
//		}
		
//		productTypeOption = new SelectItem[MarketVariableType.getProductTypes().size() + 1];
//		productTypeOption[0] = new SelectItem("", "All");  
//		for(int i=0; i < MarketVariableType.values().length;i++){
//			if(MarketVariableType.values()[i].isProduct()){
//				inx = inx+1;
//				productTypeOption[inx] = new SelectItem(MarketVariableType.values()[i].getType(),MarketVariableType.values()[i].getType());
//			}
//		}
    }

}
