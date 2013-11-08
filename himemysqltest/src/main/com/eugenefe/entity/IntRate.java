package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Parameter;
import org.jboss.seam.annotations.Name;

import com.eugenefe.util.AnnoMethodTree;
import com.eugenefe.util.AnnoMethodTree.EColumnType;
import com.eugenefe.util.AnnoNavigationFilter;

/**
 * InterestRate generated by hbm2java
 */
@Name("intRateComp")
@Entity
@Table(name = "INT_RATE")
//@BatchSize(size= 5)
@AnnoNavigationFilter
public class IntRate implements java.io.Serializable{
//	public class IntRate implements java.io.Serializable{	

	private String irId;
	private MarketVariable marketVariable;
	private String irName;
	private String issueDate;
	private String maturityDate;
	private String compoundMtd;
	private String daycountMtd;
	private String intTerm;
	private BigDecimal couponRate;
	private String sourceTable;
	private String userName;
	private String lastUpdated;
	private BigDecimal versionNo;
	private List<IrCurve> irCurves ;
//	private IrCurve irCurve;
	
//	private List<IntRateHis> intRateHisList = new ArrayList<IntRateHis>();
	
	private Map<String, BigDecimal> intRate = new HashMap<String, BigDecimal>(); 
//	private BigDecimal currentIrValue;
//	private IntRateHis currentIntRateHis;
//	private List<IntRateHis> currentIntRateHis = new ArrayList<IntRateHis>();

	public IntRate() {
	}

//	public IntRate(MarketVariable marketVariable) {
//		this.marketVariable = marketVariable;
//	}

//	public InterestRate(MarketVariable marketVariable, String irName, String issueDate, String maturityDate,
//			String compoundMtd, String daycountMtd, String intTerm, BigDecimal couponRate, String sourceTable,
//			String userName, String lastUpdated, BigDecimal versionNo, Set<IrCurve> irCurves,
//			Set<InterateRateHis> interateRateHises) {
//		this.marketVariable = marketVariable;
//		this.irName = irName;
//		this.issueDate = issueDate;
//		this.maturityDate = maturityDate;
//		this.compoundMtd = compoundMtd;
//		this.daycountMtd = daycountMtd;
//		this.intTerm = intTerm;
//		this.couponRate = couponRate;
//		this.sourceTable = sourceTable;
//		this.userName = userName;
//		this.lastUpdated = lastUpdated;
//		this.versionNo = versionNo;
//		this.irCurves = irCurves;
//		this.interateRateHises = interateRateHises;
//	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "marketVariable"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "IR_ID", unique = true, nullable = false, length = 20)
	@Size(max = 20)
	@AnnoMethodTree(order=10, init=true)
	public String getIrId() {
		return this.irId;
	}

	public void setIrId(String irId) {
		this.irId = irId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@NotNull
	@Size(max = 20)
	@AnnoMethodTree(order=30, init=false)
	public MarketVariable getMarketVariable() {
		return this.marketVariable;
	}

	public void setMarketVariable(MarketVariable marketVariable) {
		this.marketVariable = marketVariable;
	}

	@Column(name = "IR_NAME", length = 50)
	@Size(max = 50)
	@AnnoMethodTree(order=20, init=true)
	public String getIrName() {
		return this.irName;
	}

	public void setIrName(String irName) {
		this.irName = irName;
	}

	@Column(name = "ISSUE_DATE", length = 8)
	@Size(max = 8)
	@AnnoMethodTree(order=40, init=true)
	public String getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "MATURITY_DATE", length = 8)
	@Size(max = 8)
	@AnnoMethodTree(order=50, init=true)
	public String getMaturityDate() {
		return this.maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	@Column(name = "COMPOUND_MTD", length = 10)
	@Size(max = 10)
	@AnnoMethodTree(order=60, init=true)
	public String getCompoundMtd() {
		return this.compoundMtd;
	}

	public void setCompoundMtd(String compoundMtd) {
		this.compoundMtd = compoundMtd;
	}

	@Column(name = "DAYCOUNT_MTD", length = 10)
	@Size(max = 10)
	@AnnoMethodTree(order=70, init=true)
	public String getDaycountMtd() {
		return this.daycountMtd;
	}

	public void setDaycountMtd(String daycountMtd) {
		this.daycountMtd = daycountMtd;
	}

	@Column(name = "INT_TERM", length = 10)
	@Size(max = 10)
	@AnnoMethodTree(order=80, init=true)
	public String getIntTerm() {
		return this.intTerm;
	}

	public void setIntTerm(String intTerm) {
		this.intTerm = intTerm;
	}

	@Column(name = "COUPON_RATE", precision = 10, scale = 8)
	@AnnoMethodTree(order=90, init=true, align="right")
	public BigDecimal getCouponRate() {
		return this.couponRate;
	}

	public void setCouponRate(BigDecimal couponRate) {
		this.couponRate = couponRate;
	}

	@Column(name = "SOURCE_TABLE", length = 50)
	@Size(max = 50)
	public String getSourceTable() {
		return this.sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	@Column(name = "USER_NAME", length = 20)
	@Size(max = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "LAST_UPDATED", length = 8)
	@Size(max = 8)
	public String getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "VERSION_NO", precision = 22, scale = 0)
	public BigDecimal getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(BigDecimal versionNo) {
		this.versionNo = versionNo;
	}

	@ManyToMany
	@JoinTable( name="IRC_BUCKET_DETAIL"
		, joinColumns={@JoinColumn(name="IR_ID", referencedColumnName ="IR_ID")} 
	 	, inverseJoinColumns={@JoinColumn(name="IRC_ID", referencedColumnName="IRC_ID")
	 	}
	)
	@AnnoMethodTree(order=95, init=false, type=EColumnType.List)
	public List<IrCurve> getIrCurves() {
		return irCurves;
	}

	public void setIrCurves(List<IrCurve> irCurves) {
		this.irCurves = irCurves;
	}
	
	
	

	/*@OneToMany(mappedBy="interestRate", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
//	@BatchSize(size= 50)
	@OrderBy("id.bssd")
//	@Filter(name="filterBtwnDate" , condition=" BSSD >= :stBssd  and BSSD <=:endBssd")
//	@Filter(name="filterBtwnDate" )
//	@Filter(name="filterBtwnDate" , condition=" BSSD >= #{basedateBean.stBssd} and BSSD <= #{basedateBean.endBssd}")
	@Filters(
		{	@Filter(name="filterBtwnDate" )	,@Filter(name="filterCurrentDate")}
	)
	public List<IntRateHis> getIntRateHisList() {
		return intRateHisList;
	}

	public void setIntRateHisList(List<IntRateHis> intRateHisList) {
		this.intRateHisList = intRateHisList;
	}*/

	

	@ElementCollection(fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@CollectionTable(name="INT_RATE_HIS" , joinColumns={@JoinColumn(name="IR_ID")})
	@MapKeyColumn(name="BSSD")
	@Column(name="INT_RATE")
	@Filters(
			{	@Filter(name="filterBtwnDate" )	,@Filter(name="filterCurrentDate")}
		)
	public Map<String, BigDecimal> getIntRate() {
		return intRate;
	}

	

	public void setIntRate(Map<String, BigDecimal> intRateHisMap) {
		this.intRate = intRateHisMap;
	}

//	@Override
//	@javax.persistence.Transient
//	@AnnoMethodTree(order=11, init=false)
//	public String getIdString() {
//		return getIrId();
//	}
	
	
//	@OneToMany(mappedBy="interestRate", fetch=FetchType.LAZY)
////	@Fetch(FetchMode.JOIN)
//	@Filter(name="filterCurrentDate" )
//	public List<IntRateHis> getCurrentIntRateHis() {
//		return currentIntRateHis;
//	}
//
//	public void setCurrentIntRateHis(List<IntRateHis> currentIntRateHis) {
//		this.currentIntRateHis = currentIntRateHis;
//	}

	
//	@Formula("(select a.INT_RATE from INT_RATE_HIS a where a.BSSD = '20130503' and a.IR_Id = irId )")
//	@Transient
//	public BigDecimal getCurrentIrValue() {
//		return currentIrValue;
//	}
//	public void setCurrentIrValue(BigDecimal currentIrValue) {
//		this.currentIrValue = currentIrValue;
//	}

//	@OneToOne
//	@JoinColumns(value={@JoinColumn(name="IR_ID",insertable=false, updatable=false)
//					   ,@JoinColumn(name="BSSD",insertable=false, updatable=false)
//						}
//	)
//	@JoinColumnsOrFormulas(value={
//			@JoinColumnOrFormula(formula=@JoinFormula(value="(select a.bssd from BASEDATE a where a.bssd='20120513')"))
//			,@JoinColumnOrFormula(formula=@JoinFormula(value="IR_ID"))
//			@JoinColumnOrFormula(formula=@JoinFormula(value="'20130503'", referencedColumnName="BSSD"))
//			,@JoinColumnOrFormula(formula=@JoinFormula(value="'1010000_M03'", referencedColumnName="IR_ID"))
//			,@JoinColumnOrFormula(column=@JoinColumn(name="IR_ID",insertable=false, updatable=false ,referencedColumnName="IR_ID"))
//		}
//	)				 
//	@Filter(name="filterCurrentDate")
//	public IntRateHis getCurrentIntRateHis() {
//		return currentIntRateHis;
//	}
//
//	public void setCurrentIntRateHis(IntRateHis currentIntRateHis) {
//		this.currentIntRateHis = currentIntRateHis;
//	}

	

	

//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "interestRates")
//	public Set<IrCurve> getIrCurves() {
//		return this.irCurves;
//	}
//
//	public void setIrCurves(Set<IrCurve> irCurves) {
//		this.irCurves = irCurves;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interestRate")
//	public Set<InterateRateHis> getInterateRateHises() {
//		return this.interateRateHises;
//	}
//
//	public void setInterateRateHises(Set<InterateRateHis> interateRateHises) {
//		this.interateRateHises = interateRateHises;
//	}

	
}
