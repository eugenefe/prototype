package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.eugenefe.util.MarketVariableType;

/**
 * BondHis generated by hbm2java
 */
@Entity
@Table(name = "BOND_HIS")
public class BondHis implements java.io.Serializable, IMarketVariableHis{

	private BondHisId id;
	private Basedate basedate;
	private Bond bond;
	private BigDecimal basePrice;
	private BigDecimal openPrice;
	private BigDecimal highPrice;
	private BigDecimal lowPrice;
	private BigDecimal closePrice;
	private BigDecimal volume;
	private BigDecimal volumeAmt;
	private BigDecimal adjDuration;
	private BigDecimal mdDuration;
	private BigDecimal effectDuration;
	private BigDecimal adjConvexity;
	private BigDecimal effectConvexity;

	public BondHis() {
	}

	public BondHis(BondHisId id, Basedate basedate, Bond bond) {
		this.id = id;
		this.basedate = basedate;
		this.bond = bond;
	}

	public BondHis(BondHisId id, Basedate basedate, Bond bond, BigDecimal basePrice, BigDecimal openPrice,
			BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, BigDecimal volume, BigDecimal volumeAmt,
			BigDecimal adjDuration, BigDecimal mdDuration, BigDecimal effectDuration, BigDecimal adjConvexity,
			BigDecimal effectConvexity) {
		this.id = id;
		this.basedate = basedate;
		this.bond = bond;
		this.basePrice = basePrice;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
		this.volumeAmt = volumeAmt;
		this.adjDuration = adjDuration;
		this.mdDuration = mdDuration;
		this.effectDuration = effectDuration;
		this.adjConvexity = adjConvexity;
		this.effectConvexity = effectConvexity;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bssd", column = @Column(name = "BSSD", nullable = false, length = 8)),
			@AttributeOverride(name = "bondId", column = @Column(name = "BOND_ID", nullable = false, length = 20)) })
	@NotNull
	public BondHisId getId() {
		return this.id;
	}

	public void setId(BondHisId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BSSD", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Basedate getBasedate() {
		return this.basedate;
	}

	public void setBasedate(Basedate basedate) {
		this.basedate = basedate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOND_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Bond getBond() {
		return this.bond;
	}

	public void setBond(Bond bond) {
		this.bond = bond;
	}

	@Column(name = "BASE_PRICE", scale = 4)
	public BigDecimal getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	@Column(name = "OPEN_PRICE", scale = 4)
	public BigDecimal getOpenPrice() {
		return this.openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	@Column(name = "HIGH_PRICE", scale = 4)
	public BigDecimal getHighPrice() {
		return this.highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	@Column(name = "LOW_PRICE", scale = 4)
	public BigDecimal getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	@Column(name = "CLOSE_PRICE", scale = 4)
	public BigDecimal getClosePrice() {
		return this.closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}
	
	@Override
	@Column(name = "VOLUME", scale = 0)
	public BigDecimal getVolume() {
		return this.volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	@Override
	@Column(name = "VOLUME_AMT", scale = 0)
	public BigDecimal getVolumeAmt() {
		return this.volumeAmt;
	}

	public void setVolumeAmt(BigDecimal volumeAmt) {
		this.volumeAmt = volumeAmt;
	}
	
	@Override
	@Column(name = "ADJ_DURATION", scale = 4)
	public BigDecimal getAdjDuration() {
		return this.adjDuration;
	}

	public void setAdjDuration(BigDecimal adjDuration) {
		this.adjDuration = adjDuration;
	}
	
	@Override
	@Column(name = "MD_DURATION", scale = 4)
	public BigDecimal getMdDuration() {
		return this.mdDuration;
	}

	public void setMdDuration(BigDecimal mdDuration) {
		this.mdDuration = mdDuration;
	}
	
	@Override
	@Column(name = "EFFECT_DURATION", scale = 4)
	public BigDecimal getEffectDuration() {
		return this.effectDuration;
	}

	public void setEffectDuration(BigDecimal effectDuration) {
		this.effectDuration = effectDuration;
	}

	@Column(name = "ADJ_CONVEXITY", scale = 4)
	@Override
	public BigDecimal getAdjConvexity() {
		return this.adjConvexity;
	}

	public void setAdjConvexity(BigDecimal adjConvexity) {
		this.adjConvexity = adjConvexity;
	}

	@Column(name = "EFFECT_CONVEXITY", scale = 4)
	@Override
	public BigDecimal getEffectConvexity() {
		return this.effectConvexity;
	}

	public void setEffectConvexity(BigDecimal effectConvexity) {
		this.effectConvexity = effectConvexity;
	}

	
//	******************Override Method**********************
	@Override
	@Transient
	public String getBssd() {
		return getBasedate().getBssd();
	}

	@Override
	@Transient
	public String getMvId() {
		return getBond().getBondId();
	}

	@Override
	@Transient
	public String getMvName() {
		return getBond().getBondId();
	}
	@Override
	@Transient
	public String getMvType() {
		return MarketVariableType.BOND.getType();
	}
//	public ProductType getMvType() {
//		return ProductType.BOND;
//	}
	@Override
	@Transient
	public double getCurrentPrice() {
		return getClosePrice().doubleValue();
	}

	@Override
	@Transient
	public double getBeforePrice() {
		return getBasePrice().doubleValue();
	}
	
	@Override
	@Transient
	public BigDecimal getStockBeta() {
		return null;
	}

}
