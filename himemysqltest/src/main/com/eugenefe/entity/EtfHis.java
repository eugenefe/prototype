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

import com.eugenfe.util.ProductType;

/**
 * EtfHis generated by hbm2java
 */
@Entity
@Table(name = "ETF_HIS")
public class EtfHis implements java.io.Serializable , IMarketVariableHis{

	private EtfHisId id;
	private Basedate basedate;
	private Etf etf;
	private BigDecimal basePrice;
	private BigDecimal openPrice;
	private BigDecimal highPrice;
	private BigDecimal lowPrice;
	private BigDecimal closePrice;
	private BigDecimal volume;
	private BigDecimal volumeAmt;
	private BigDecimal nav;

	public EtfHis() {
	}

	public EtfHis(EtfHisId id, Basedate basedate, Etf etf) {
		this.id = id;
		this.basedate = basedate;
		this.etf = etf;
	}

	public EtfHis(EtfHisId id, Basedate basedate, Etf etf, BigDecimal basePrice, BigDecimal openPrice,
			BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, BigDecimal volume, BigDecimal volumeAmt,
			BigDecimal nav) {
		this.id = id;
		this.basedate = basedate;
		this.etf = etf;
		this.basePrice = basePrice;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
		this.volumeAmt = volumeAmt;
		this.nav = nav;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bssd", column = @Column(name = "BSSD", nullable = false, length = 8)),
			@AttributeOverride(name = "etfId", column = @Column(name = "ETF_ID", nullable = false, length = 20)) })
	@NotNull
	public EtfHisId getId() {
		return this.id;
	}

	public void setId(EtfHisId id) {
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
	@JoinColumn(name = "ETF_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Etf getEtf() {
		return this.etf;
	}

	public void setEtf(Etf etf) {
		this.etf = etf;
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

	@Column(name = "NAV", scale = 4)
	public BigDecimal getNav() {
		return this.nav;
	}

	public void setNav(BigDecimal nav) {
		this.nav = nav;
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
		return getEtf().getEtfId();
	}

	@Override
	@Transient
	public String getMvName() {
		return getEtf().getEtfId();
	}
	@Override
	@Transient
	public String getMvType() {
		return ProductType.ETF.getType();
	}
//	public ProductType getMvType() {
//		return ProductType.STOCK;
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
	public BigDecimal getAdjDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	public BigDecimal getMdDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	public BigDecimal getEffectDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	public BigDecimal getAdjConvexity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transient
	public BigDecimal getEffectConvexity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transient
	public BigDecimal getStockBeta() {
		// TODO Auto-generated method stub
				return null;
	}
}
