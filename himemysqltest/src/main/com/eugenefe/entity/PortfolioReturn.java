package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * PortfolioReturn generated by hbm2java
 */
@Entity
@Table(name = "PORTFOLIO_RETURN")
public class PortfolioReturn implements Serializable{

	private PortfolioReturnId id;
	private Basedate basedate;
	private Portfolio portfolio;
	private BigDecimal bookAmt;
	private BigDecimal presValue;
	private BigDecimal dailyReturn;
	private BigDecimal monthlyReturn;
	private BigDecimal quartlyReturn;
	private BigDecimal annualReturn;
	private BigDecimal fiscalReturn;
	private BigDecimal deltaReturn;
	private BigDecimal gammaReturn;
	private BigDecimal vegaReturn;
	private BigDecimal thetaReturn;
	private BigDecimal rhoReturn;
	
	

	public PortfolioReturn() {
	}

	public PortfolioReturn(PortfolioReturnId id, Basedate basedate, Portfolio portfolio) {
		this.id = id;
		this.basedate = basedate;
		this.portfolio = portfolio;
	}

	public PortfolioReturn(PortfolioReturnId id, Basedate basedate, Portfolio portfolio, BigDecimal bookAmt,
			BigDecimal presValue, BigDecimal dailyReturn, BigDecimal monthlyReturn, BigDecimal quartlyReturn,
			BigDecimal annualReturn, BigDecimal fiscalReturn, BigDecimal deltaReturn, BigDecimal gammaReturn,
			BigDecimal vegaReturn, BigDecimal thetaReturn, BigDecimal rhoReturn) {
		this.id = id;
		this.basedate = basedate;
		this.portfolio = portfolio;
		this.bookAmt = bookAmt;
		this.presValue = presValue;
		this.dailyReturn = dailyReturn;
		this.monthlyReturn = monthlyReturn;
		this.quartlyReturn = quartlyReturn;
		this.annualReturn = annualReturn;
		this.fiscalReturn = fiscalReturn;
		this.deltaReturn = deltaReturn;
		this.gammaReturn = gammaReturn;
		this.vegaReturn = vegaReturn;
		this.thetaReturn = thetaReturn;
		this.rhoReturn = rhoReturn;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bssd", column = @Column(name = "BSSD", nullable = false, length = 8)),
			@AttributeOverride(name = "portId", column = @Column(name = "PORT_ID", nullable = false, length = 50)) })
	@NotNull
	public PortfolioReturnId getId() {
		return this.id;
	}

	public void setId(PortfolioReturnId id) {
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

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
//	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PORT_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
//	@Fetch(FetchMode.JOIN)
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name = "BOOK_AMT", scale = 4)
	public BigDecimal getBookAmt() {
		return this.bookAmt;
	}

	public void setBookAmt(BigDecimal bookAmt) {
		this.bookAmt = bookAmt;
	}
	
	
	@Column(name = "PRES_VALUE", scale = 4)
	public BigDecimal getPresValue() {
		return this.presValue;
	}

	public void setPresValue(BigDecimal presValue) {
		this.presValue = presValue;
	}

	@Column(name = "DAILY_RETURN", scale = 4)
	public BigDecimal getDailyReturn() {
		return this.dailyReturn;
	}

	public void setDailyReturn(BigDecimal dailyReturn) {
		this.dailyReturn = dailyReturn;
	}

	@Column(name = "MONTHLY_RETURN", scale = 4)
	public BigDecimal getMonthlyReturn() {
		return this.monthlyReturn;
	}

	public void setMonthlyReturn(BigDecimal monthlyReturn) {
		this.monthlyReturn = monthlyReturn;
	}

	@Column(name = "QUARTLY_RETURN", scale = 4)
	public BigDecimal getQuartlyReturn() {
		return this.quartlyReturn;
	}

	public void setQuartlyReturn(BigDecimal quartlyReturn) {
		this.quartlyReturn = quartlyReturn;
	}

	@Column(name = "ANNUAL_RETURN", scale = 4)
	public BigDecimal getAnnualReturn() {
		return this.annualReturn;
	}

	public void setAnnualReturn(BigDecimal annualReturn) {
		this.annualReturn = annualReturn;
	}

	@Column(name = "FISCAL_RETURN", scale = 4)
	public BigDecimal getFiscalReturn() {
		return this.fiscalReturn;
	}

	public void setFiscalReturn(BigDecimal fiscalReturn) {
		this.fiscalReturn = fiscalReturn;
	}

	@Column(name = "DELTA_RETURN", scale = 4)
	public BigDecimal getDeltaReturn() {
		return this.deltaReturn;
	}

	public void setDeltaReturn(BigDecimal deltaReturn) {
		this.deltaReturn = deltaReturn;
	}

	@Column(name = "GAMMA_RETURN", scale = 4)
	public BigDecimal getGammaReturn() {
		return this.gammaReturn;
	}

	public void setGammaReturn(BigDecimal gammaReturn) {
		this.gammaReturn = gammaReturn;
	}

	@Column(name = "VEGA_RETURN", scale = 4)
	public BigDecimal getVegaReturn() {
		return this.vegaReturn;
	}

	public void setVegaReturn(BigDecimal vegaReturn) {
		this.vegaReturn = vegaReturn;
	}

	@Column(name = "THETA_RETURN", scale = 4)
	public BigDecimal getThetaReturn() {
		return this.thetaReturn;
	}

	public void setThetaReturn(BigDecimal thetaReturn) {
		this.thetaReturn = thetaReturn;
	}

	@Column(name = "RHO_RETURN", scale = 4)
	public BigDecimal getRhoReturn() {
		return this.rhoReturn;
	}

	public void setRhoReturn(BigDecimal rhoReturn) {
		this.rhoReturn = rhoReturn;
	}

}
