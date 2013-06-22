package com.eugenefe.session;

import java.util.List;

import com.eugenefe.entity.*;
import com.eugenefe.util.ProductType;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.framework.EntityHome;

@Name("bondHome")
public class BondHome extends EntityHome<Bond> {

	public void setBondId(String id) {
		setId(id);
	}

	public String getBondId() {
		return (String) getId();
	}

	@Override
	protected Bond createInstance() {
		Bond bond = new Bond();
		return bond;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Bond getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	@Observer(value="selectProduct")
	public void onSelectBond(MarketVariable selectProduct){
		if(ProductType.BOND.equals(selectProduct.getMvType())){
//		if(selectProduct.getMvType().equals(ProductType.BOND.getType())){
//			if(selectProduct.getMvType().equals("BOND")){	
			setBondId(selectProduct.getMvId());
		}
	}
}
