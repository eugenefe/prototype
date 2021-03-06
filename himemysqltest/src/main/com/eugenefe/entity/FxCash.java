package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.eugenefe.util.AnnoMethodTree;
import com.eugenefe.util.AnnoNavigationFilter;

/**
 * FxCash generated by hbm2java
 */
@Entity
@Table(name = "FX_CASH")
@AnnoNavigationFilter
public class FxCash implements java.io.Serializable {

	private String fxId;
	private MarketVariable marketVariable;
	private String fxName;
	private String termCurr;
	private String baseCurr;
	private BigDecimal scaleFactor;
//	private Set<FxRateHis> fxRateHises = new HashSet<FxRateHis>(0);

	public FxCash() {
	}

	public FxCash(MarketVariable marketVariable) {
		this.marketVariable = marketVariable;
	}

	public FxCash(MarketVariable marketVariable, String fxName, String termCurr, String baseCurr,
			BigDecimal scaleFactor, Set<FxRateHis> fxRateHises) {
		this.marketVariable = marketVariable;
		this.fxName = fxName;
		this.termCurr = termCurr;
		this.baseCurr = baseCurr;
		this.scaleFactor = scaleFactor;
//		this.fxRateHises = fxRateHises;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "marketVariable"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "FX_ID", unique = true, nullable = false, length = 20)
	@Size(max = 20)
	@AnnoMethodTree(order=10, init=true)
	public String getFxId() {
		return this.fxId;
	}

	public void setFxId(String fxId) {
		this.fxId = fxId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@NotNull
	@Size(max = 20)
	@AnnoMethodTree(order=20, init=false)
	public MarketVariable getMarketVariable() {
		return this.marketVariable;
	}

	public void setMarketVariable(MarketVariable marketVariable) {
		this.marketVariable = marketVariable;
	}

	@Column(name = "FX_NAME", length = 50)
	@Size(max = 50)
	@AnnoMethodTree(order=11, init=true)
	public String getFxName() {
		return this.fxName;
	}

	public void setFxName(String fxName) {
		this.fxName = fxName;
	}

	@Column(name = "TERM_CURR", length = 3)
	@Size(max = 3)
	@AnnoMethodTree(order=30, init=true)
	public String getTermCurr() {
		return this.termCurr;
	}

	public void setTermCurr(String termCurr) {
		this.termCurr = termCurr;
	}

	@Column(name = "BASE_CURR", length = 3)
	@Size(max = 3)
	@AnnoMethodTree(order=31, init=true)
	public String getBaseCurr() {
		return this.baseCurr;
	}

	public void setBaseCurr(String baseCurr) {
		this.baseCurr = baseCurr;
	}

	@Column(name = "SCALE_FACTOR", precision = 10, scale = 4)
	@AnnoMethodTree(order=40, init=true)
	public BigDecimal getScaleFactor() {
		return this.scaleFactor;
	}

	public void setScaleFactor(BigDecimal scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fxCash")
//	public Set<FxRateHis> getFxRateHises() {
//		return this.fxRateHises;
//	}
//
//	public void setFxRateHises(Set<FxRateHis> fxRateHises) {
//		this.fxRateHises = fxRateHises;
//	}

}
