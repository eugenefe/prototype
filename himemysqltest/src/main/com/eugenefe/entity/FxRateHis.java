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

import com.eugenefe.util.ProductType;

/**
 * FxRateHis generated by hbm2java
 */
@Entity
@Table(name = "FX_RATE_HIS")
public class FxRateHis implements java.io.Serializable , IMarketVariableHis{

	private FxRateHisId id;
	private Basedate basedate;
	private FxCash fxCash;
	private BigDecimal basePrice;
	private BigDecimal openPrice;
	private BigDecimal highPrice;
	private BigDecimal lowPrice;
	private BigDecimal closePrice;
	private BigDecimal volume;
	private BigDecimal volumeAmt;

	public FxRateHis() {
	}

	public FxRateHis(FxRateHisId id, Basedate basedate, FxCash fxCash) {
		this.id = id;
		this.basedate = basedate;
		this.fxCash = fxCash;
	}

	public FxRateHis(FxRateHisId id, Basedate basedate, FxCash fxCash, BigDecimal basePrice, BigDecimal openPrice,
			BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, BigDecimal volume, BigDecimal volumeAmt) {
		this.id = id;
		this.basedate = basedate;
		this.fxCash = fxCash;
		this.basePrice = basePrice;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
		this.volumeAmt = volumeAmt;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bssd", column = @Column(name = "BSSD", nullable = false, length = 8)),
			@AttributeOverride(name = "fxId", column = @Column(name = "FX_ID", nullable = false, length = 20)) })
	@NotNull
	public FxRateHisId getId() {
		return this.id;
	}

	public void setId(FxRateHisId id) {
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
	@JoinColumn(name = "FX_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public FxCash getFxCash() {
		return this.fxCash;
	}

	public void setFxCash(FxCash fxCash) {
		this.fxCash = fxCash;
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

	
	
//	******************Override Method**********************
	@Override
	@Transient
	public String getBssd() {
		return getBasedate().getBssd();
	}

	@Override
	@Transient
	public String getMvId() {
		return getFxCash().getFxId();
	}

	@Override
	@Transient
	public String getMvName() {
		return getFxCash().getFxName();
	}
	@Override
	@Transient
	public String getMvType() {
		return ProductType.BOND.getType();
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
	@Transient
	@Override
	public BigDecimal getAdjDuration() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transient
	@Override
	public BigDecimal getMdDuration() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transient
	@Override
	public BigDecimal getEffectDuration() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transient
	@Override
	public BigDecimal getAdjConvexity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public BigDecimal getEffectConvexity() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
