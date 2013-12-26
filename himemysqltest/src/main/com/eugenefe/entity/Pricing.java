package com.eugenefe.entity;

// Generated Nov 25, 2013 6:47:59 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.eugenefe.util.AnnoMethodTree;
import com.eugenefe.util.AnnoNavigationFilter;
import com.eugenefe.util.EColumnType;

/**
 * Pricing generated by hbm2java
 */
@Entity
@Table(name = "PRICING")
@AnnoNavigationFilter
public class Pricing implements java.io.Serializable {

	private PricingId id;
	private VolCurve volCurve;
	private Pricer pricer;
	private PricingMethod pricingMethod;
	private Hifive hifive;
	private VcvMatrix vcvMatrix;
	private IrCurve irCurve;
	private Long mcNum;
	private Long fdmLatticeNum;
	private List<PricingUnderlying> pricingUnderlyings = new ArrayList<PricingUnderlying>();

	public Pricing() {
	}

	public Pricing(PricingId id, VolCurve volCurve, Pricer pricer, PricingMethod pricingMethod, Hifive hifive,
			VcvMatrix vcvMatrix, IrCurve irCurve) {
		this.id = id;
		this.volCurve = volCurve;
		this.pricer = pricer;
		this.pricingMethod = pricingMethod;
		this.hifive = hifive;
		this.vcvMatrix = vcvMatrix;
		this.irCurve = irCurve;
	}

	public Pricing(PricingId id, VolCurve volCurve, Pricer pricer, PricingMethod pricingMethod, Hifive hifive,
			VcvMatrix vcvMatrix, IrCurve irCurve, Long mcNum, Long fdmLatticeNum,
			List<PricingUnderlying> pricingUnderlyings) {
		this.id = id;
		this.volCurve = volCurve;
		this.pricer = pricer;
		this.pricingMethod = pricingMethod;
		this.hifive = hifive;
		this.vcvMatrix = vcvMatrix;
		this.irCurve = irCurve;
		this.mcNum = mcNum;
		this.fdmLatticeNum = fdmLatticeNum;
		this.pricingUnderlyings = pricingUnderlyings;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "pricingObject", column = @Column(name = "PRICING_OBJECT", nullable = false, length = 20)),
			@AttributeOverride(name = "prodId", column = @Column(name = "PROD_ID", nullable = false, length = 20)) })
	@AnnoMethodTree(order=10, init=true)
	public PricingId getId() {
		return this.id;
	}

	public void setId(PricingId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICING_OBJECT", nullable = false, insertable = false, updatable = false)
	@AnnoMethodTree(order=20, init=true)
	public PricingMethod getPricingMethod() {
		return this.pricingMethod;
	}

	public void setPricingMethod(PricingMethod pricingMethod) {
		this.pricingMethod = pricingMethod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICER_ID", nullable = false)
	@AnnoMethodTree(order=21, init=true)
	public Pricer getPricer() {
		return this.pricer;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROD_ID", nullable = false, insertable = false, updatable = false)
	@AnnoMethodTree(order=22, init=true)
	public Hifive getHifive() {
		return this.hifive;
	}

	public void setHifive(Hifive hifive) {
		this.hifive = hifive;
	}

	public void setPricer(Pricer pricer) {
		this.pricer = pricer;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IRC_ID", nullable = false)
	@AnnoMethodTree(order=30, init=true)
	public IrCurve getIrCurve() {
		return this.irCurve;
	}

	public void setIrCurve(IrCurve irCurve) {
		this.irCurve = irCurve;
	} 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VOL_CURVE_ID", nullable = false)
	@AnnoMethodTree(order=40, init=true)
	public VolCurve getVolCurve() {
		return this.volCurve;
	}

	public void setVolCurve(VolCurve volCurve) {
		this.volCurve = volCurve;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VCV_ID", nullable = false)
	@AnnoMethodTree(order=41, init=true)
	public VcvMatrix getVcvMatrix() {
		return this.vcvMatrix;
	}

	public void setVcvMatrix(VcvMatrix vcvMatrix) {
		this.vcvMatrix = vcvMatrix;
	}

	

	@Column(name = "MC_NUM", precision = 10, scale = 0)
	@AnnoMethodTree(order=50, init=true, align="right")
	public Long getMcNum() {
		return this.mcNum;
	}

	public void setMcNum(Long mcNum) {
		this.mcNum = mcNum;
	}

	@Column(name = "FDM_LATTICE_NUM", precision = 10, scale = 0)
	@AnnoMethodTree(order=51, init=true, align="right")
	public Long getFdmLatticeNum() {
		return this.fdmLatticeNum;
	}

	public void setFdmLatticeNum(Long fdmLatticeNum) {
		this.fdmLatticeNum = fdmLatticeNum;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pricing")
	@AnnoMethodTree(order=60, init=false, type=EColumnType.List)
	public List<PricingUnderlying> getPricingUnderlyings() {
		return this.pricingUnderlyings;
	}

	public void setPricingUnderlyings(List<PricingUnderlying> pricingUnderlyings) {
		this.pricingUnderlyings = pricingUnderlyings;
	}

}
