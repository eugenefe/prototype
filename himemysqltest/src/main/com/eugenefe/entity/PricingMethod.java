package com.eugenefe.entity;

// Generated Nov 25, 2013 6:47:59 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.eugenefe.util.AnnoMethodTree;
import com.eugenefe.util.AnnoNavigationFilter;

/**
 * PricingMethod generated by hbm2java
 */
@Entity
@Table(name = "PRICING_METHOD")
@AnnoNavigationFilter
public class PricingMethod implements java.io.Serializable {

	private String pricingObject;
	private String pricngObjectName;
	private List<Pricing> pricings = new ArrayList<Pricing>();

	public PricingMethod() {
	}

	public PricingMethod(String pricingObject) {
		this.pricingObject = pricingObject;
	}

	public PricingMethod(String pricingObject, String pricngObjectName, List<Pricing> pricings) {
		this.pricingObject = pricingObject;
		this.pricngObjectName = pricngObjectName;
		this.pricings = pricings;
	}

	@Id
	@Column(name = "PRICING_OBJECT", unique = true, nullable = false, length = 20)
	@AnnoMethodTree(order=10, init=true)
	public String getPricingObject() {
		return this.pricingObject;
	}

	public void setPricingObject(String pricingObject) {
		this.pricingObject = pricingObject;
	}

	@Column(name = "PRICNG_OBJECT_NAME", length = 50)
	@AnnoMethodTree(order=11, init=true)
	public String getPricngObjectName() {
		return this.pricngObjectName;
	}

	public void setPricngObjectName(String pricngObjectName) {
		this.pricngObjectName = pricngObjectName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pricingMethod")
	@AnnoMethodTree(order=20, init=false)
	public List<Pricing> getPricings() {
		return this.pricings;
	}

	public void setPricings(List<Pricing> pricings) {
		this.pricings = pricings;
	}

}