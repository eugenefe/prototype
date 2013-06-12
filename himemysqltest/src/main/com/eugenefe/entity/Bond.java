package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
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

/**
 * Bond generated by hbm2java
 */
@Entity
@Table(name = "BOND")
public class Bond  implements java.io.Serializable {

	private String bondId;
	private MarketVariable marketVariable;
	private String issuerId;
	private Long faceAmt;
	private BigDecimal totalAmt;
	private String issueDate;
	private String maturityDate;
	private BigDecimal couponRate;
	private String intTerm;
	private String compoundMtd;
	private String daycountMtd;
	private String bondCfType;
	private String priorityCd;
	private String securitizationCd;
	private String creditRatingCd;
	private String currCd;
	private BigDecimal premiumAtEnd;
	private String bondType;
	private BigDecimal initInbIndex;
	private String redemMcd;
	private String amortStDate;
	private String deferTerm;
	private Long amortCnt;
	private Long amortFreq;
	private BigDecimal amortAmt;
	private Character isCpPrepay;
	private String refIrcId;
	private BigDecimal spreadRate;
	private String refixingTerm;
	private String firstCouponDate;
	private String cpDateGen;
	private Long refixingSlideNum;
	private BigDecimal capRate;
	private BigDecimal floorRate;
	private String stockLinkedType;
	private String refStockId;
	private String optionStDate;
	private String optionEdDate;
	private BigDecimal strikePrice;
	private BigDecimal conversionRatio;
//	private Set<BondIndexDetail> bondIndexDetails = new HashSet<BondIndexDetail>(0);
	private List<BondHis> bondHis;
//	private List<IMarketVariableHis> marketVariableHis;

	public Bond() {
	}

//	public Bond(MarketVariable marketVariable) {
//		this.marketVariable = marketVariable;
//	}

//	public Bond(MarketVariable marketVariable, String issuerId, Long faceAmt, BigDecimal totalAmt, String issueDate,
//			String maturityDate, BigDecimal couponRate, String intTerm, String compoundMtd, String daycountMtd,
//			String bondCfType, String priorityCd, String securitizationCd, String creditRatingCd, String currCd,
//			BigDecimal premiumAtEnd, String bondType, BigDecimal initInbIndex, String redemMcd, String amortStDate,
//			String deferTerm, Long amortCnt, Long amortFreq, BigDecimal amortAmt, Character isCpPrepay,
//			String refIrcId, BigDecimal spreadRate, String refixingTerm, String firstCouponDate, String cpDateGen,
//			Long refixingSlideNum, BigDecimal capRate, BigDecimal floorRate, String stockLinkedType, String refStockId,
//			String optionStDate, String optionEdDate, BigDecimal strikePrice, BigDecimal conversionRatio,
//			Set<BondIndexDetail> bondIndexDetails, Set<BondHis> bondHises) {
//		this.marketVariable = marketVariable;
//		this.issuerId = issuerId;
//		this.faceAmt = faceAmt;
//		this.totalAmt = totalAmt;
//		this.issueDate = issueDate;
//		this.maturityDate = maturityDate;
//		this.couponRate = couponRate;
//		this.intTerm = intTerm;
//		this.compoundMtd = compoundMtd;
//		this.daycountMtd = daycountMtd;
//		this.bondCfType = bondCfType;
//		this.priorityCd = priorityCd;
//		this.securitizationCd = securitizationCd;
//		this.creditRatingCd = creditRatingCd;
//		this.currCd = currCd;
//		this.premiumAtEnd = premiumAtEnd;
//		this.bondType = bondType;
//		this.initInbIndex = initInbIndex;
//		this.redemMcd = redemMcd;
//		this.amortStDate = amortStDate;
//		this.deferTerm = deferTerm;
//		this.amortCnt = amortCnt;
//		this.amortFreq = amortFreq;
//		this.amortAmt = amortAmt;
//		this.isCpPrepay = isCpPrepay;
//		this.refIrcId = refIrcId;
//		this.spreadRate = spreadRate;
//		this.refixingTerm = refixingTerm;
//		this.firstCouponDate = firstCouponDate;
//		this.cpDateGen = cpDateGen;
//		this.refixingSlideNum = refixingSlideNum;
//		this.capRate = capRate;
//		this.floorRate = floorRate;
//		this.stockLinkedType = stockLinkedType;
//		this.refStockId = refStockId;
//		this.optionStDate = optionStDate;
//		this.optionEdDate = optionEdDate;
//		this.strikePrice = strikePrice;
//		this.conversionRatio = conversionRatio;
//		this.bondIndexDetails = bondIndexDetails;
//		this.bondHises = bondHises;
//	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "marketVariable"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MV_ID", unique = true, nullable = false, length = 20)
	@Size(max = 20)
	public String getBondId() {
		return this.bondId;
	}
	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@NotNull
	@Size(max = 20)
	public MarketVariable getMarketVariable() {
		return this.marketVariable;
	}

	public void setMarketVariable(MarketVariable marketVariable) {
		this.marketVariable = marketVariable;
	}

	@Column(name = "ISSUER_ID", length = 30)
	@Size(max = 30)
	public String getIssuerId() {
		return this.issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	@Column(name = "FACE_AMT", precision = 10, scale = 0)
	public Long getFaceAmt() {
		return this.faceAmt;
	}

	public void setFaceAmt(Long faceAmt) {
		this.faceAmt = faceAmt;
	}

	@Column(name = "TOTAL_AMT", scale = 0)
	public BigDecimal getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Column(name = "ISSUE_DATE", length = 8)
	@Size(max = 8)
	public String getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "MATURITY_DATE", length = 8)
	@Size(max = 8)
	public String getMaturityDate() {
		return this.maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	@Column(name = "COUPON_RATE", precision = 10, scale = 8)
	public BigDecimal getCouponRate() {
		return this.couponRate;
	}

	public void setCouponRate(BigDecimal couponRate) {
		this.couponRate = couponRate;
	}

	@Column(name = "INT_TERM", length = 10)
	@Size(max = 10)
	public String getIntTerm() {
		return this.intTerm;
	}

	public void setIntTerm(String intTerm) {
		this.intTerm = intTerm;
	}

	@Column(name = "COMPOUND_MTD", length = 10)
	@Size(max = 10)
	public String getCompoundMtd() {
		return this.compoundMtd;
	}

	public void setCompoundMtd(String compoundMtd) {
		this.compoundMtd = compoundMtd;
	}

	@Column(name = "DAYCOUNT_MTD", length = 10)
	@Size(max = 10)
	public String getDaycountMtd() {
		return this.daycountMtd;
	}

	public void setDaycountMtd(String daycountMtd) {
		this.daycountMtd = daycountMtd;
	}

	@Column(name = "BOND_CF_TYPE", length = 10)
	@Size(max = 10)
	public String getBondCfType() {
		return this.bondCfType;
	}

	public void setBondCfType(String bondCfType) {
		this.bondCfType = bondCfType;
	}

	@Column(name = "PRIORITY_CD", length = 10)
	@Size(max = 10)
	public String getPriorityCd() {
		return this.priorityCd;
	}

	public void setPriorityCd(String priorityCd) {
		this.priorityCd = priorityCd;
	}

	@Column(name = "SECURITIZATION_CD", length = 10)
	@Size(max = 10)
	public String getSecuritizationCd() {
		return this.securitizationCd;
	}

	public void setSecuritizationCd(String securitizationCd) {
		this.securitizationCd = securitizationCd;
	}

	@Column(name = "CREDIT_RATING_CD", length = 10)
	@Size(max = 10)
	public String getCreditRatingCd() {
		return this.creditRatingCd;
	}

	public void setCreditRatingCd(String creditRatingCd) {
		this.creditRatingCd = creditRatingCd;
	}

	@Column(name = "CURR_CD", length = 3)
	@Size(max = 3)
	public String getCurrCd() {
		return this.currCd;
	}

	public void setCurrCd(String currCd) {
		this.currCd = currCd;
	}

	@Column(name = "PREMIUM_AT_END", precision = 10, scale = 4)
	public BigDecimal getPremiumAtEnd() {
		return this.premiumAtEnd;
	}

	public void setPremiumAtEnd(BigDecimal premiumAtEnd) {
		this.premiumAtEnd = premiumAtEnd;
	}

	@Column(name = "BOND_TYPE", length = 10)
	@Size(max = 10)
	public String getBondType() {
		return this.bondType;
	}

	public void setBondType(String bondType) {
		this.bondType = bondType;
	}

	@Column(name = "INIT_INB_INDEX", precision = 10, scale = 4)
	public BigDecimal getInitInbIndex() {
		return this.initInbIndex;
	}

	public void setInitInbIndex(BigDecimal initInbIndex) {
		this.initInbIndex = initInbIndex;
	}

	@Column(name = "REDEM_MCD", length = 10)
	@Size(max = 10)
	public String getRedemMcd() {
		return this.redemMcd;
	}

	public void setRedemMcd(String redemMcd) {
		this.redemMcd = redemMcd;
	}

	@Column(name = "AMORT_ST_DATE", length = 8)
	@Size(max = 8)
	public String getAmortStDate() {
		return this.amortStDate;
	}

	public void setAmortStDate(String amortStDate) {
		this.amortStDate = amortStDate;
	}

	@Column(name = "DEFER_TERM", length = 10)
	@Size(max = 10)
	public String getDeferTerm() {
		return this.deferTerm;
	}

	public void setDeferTerm(String deferTerm) {
		this.deferTerm = deferTerm;
	}

	@Column(name = "AMORT_CNT", precision = 10, scale = 0)
	public Long getAmortCnt() {
		return this.amortCnt;
	}

	public void setAmortCnt(Long amortCnt) {
		this.amortCnt = amortCnt;
	}

	@Column(name = "AMORT_FREQ", precision = 10, scale = 0)
	public Long getAmortFreq() {
		return this.amortFreq;
	}

	public void setAmortFreq(Long amortFreq) {
		this.amortFreq = amortFreq;
	}

	@Column(name = "AMORT_AMT", scale = 4)
	public BigDecimal getAmortAmt() {
		return this.amortAmt;
	}

	public void setAmortAmt(BigDecimal amortAmt) {
		this.amortAmt = amortAmt;
	}

	@Column(name = "IS_CP_PREPAY", length = 1)
	public Character getIsCpPrepay() {
		return this.isCpPrepay;
	}

	public void setIsCpPrepay(Character isCpPrepay) {
		this.isCpPrepay = isCpPrepay;
	}

	@Column(name = "REF_IRC_ID", length = 20)
	@Size(max = 20)
	public String getRefIrcId() {
		return this.refIrcId;
	}

	public void setRefIrcId(String refIrcId) {
		this.refIrcId = refIrcId;
	}

	@Column(name = "SPREAD_RATE", precision = 10, scale = 4)
	public BigDecimal getSpreadRate() {
		return this.spreadRate;
	}

	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}

	@Column(name = "REFIXING_TERM", length = 10)
	@Size(max = 10)
	public String getRefixingTerm() {
		return this.refixingTerm;
	}

	public void setRefixingTerm(String refixingTerm) {
		this.refixingTerm = refixingTerm;
	}

	@Column(name = "FIRST_COUPON_DATE", length = 8)
	@Size(max = 8)
	public String getFirstCouponDate() {
		return this.firstCouponDate;
	}

	public void setFirstCouponDate(String firstCouponDate) {
		this.firstCouponDate = firstCouponDate;
	}

	@Column(name = "CP_DATE_GEN", length = 10)
	@Size(max = 10)
	public String getCpDateGen() {
		return this.cpDateGen;
	}

	public void setCpDateGen(String cpDateGen) {
		this.cpDateGen = cpDateGen;
	}

	@Column(name = "REFIXING_SLIDE_NUM", precision = 10, scale = 0)
	public Long getRefixingSlideNum() {
		return this.refixingSlideNum;
	}

	public void setRefixingSlideNum(Long refixingSlideNum) {
		this.refixingSlideNum = refixingSlideNum;
	}

	@Column(name = "CAP_RATE", precision = 10, scale = 4)
	public BigDecimal getCapRate() {
		return this.capRate;
	}

	public void setCapRate(BigDecimal capRate) {
		this.capRate = capRate;
	}

	@Column(name = "FLOOR_RATE", precision = 10, scale = 4)
	public BigDecimal getFloorRate() {
		return this.floorRate;
	}

	public void setFloorRate(BigDecimal floorRate) {
		this.floorRate = floorRate;
	}

	@Column(name = "STOCK_LINKED_TYPE", length = 10)
	@Size(max = 10)
	public String getStockLinkedType() {
		return this.stockLinkedType;
	}

	public void setStockLinkedType(String stockLinkedType) {
		this.stockLinkedType = stockLinkedType;
	}

	@Column(name = "REF_STOCK_ID", length = 20)
	@Size(max = 20)
	public String getRefStockId() {
		return this.refStockId;
	}

	public void setRefStockId(String refStockId) {
		this.refStockId = refStockId;
	}

	@Column(name = "OPTION_ST_DATE", length = 8)
	@Size(max = 8)
	public String getOptionStDate() {
		return this.optionStDate;
	}

	public void setOptionStDate(String optionStDate) {
		this.optionStDate = optionStDate;
	}

	@Column(name = "OPTION_ED_DATE", length = 8)
	@Size(max = 8)
	public String getOptionEdDate() {
		return this.optionEdDate;
	}

	public void setOptionEdDate(String optionEdDate) {
		this.optionEdDate = optionEdDate;
	}

	@Column(name = "STRIKE_PRICE", precision = 10, scale = 4)
	public BigDecimal getStrikePrice() {
		return this.strikePrice;
	}

	public void setStrikePrice(BigDecimal strikePrice) {
		this.strikePrice = strikePrice;
	}

	@Column(name = "CONVERSION_RATIO", precision = 10, scale = 4)
	public BigDecimal getConversionRatio() {
		return this.conversionRatio;
	}

	public void setConversionRatio(BigDecimal conversionRatio) {
		this.conversionRatio = conversionRatio;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bond")
//	public Set<BondIndexDetail> getBondIndexDetails() {
//		return this.bondIndexDetails;
//	}
//
//	public void setBondIndexDetails(Set<BondIndexDetail> bondIndexDetails) {
//		this.bondIndexDetails = bondIndexDetails;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bond")
//	public List<IMarketVariableHis> getMarketVariableHis() {
//		return this.marketVariableHis;
//	}
//
//	public void setMarketVariableHis(List<IMarketVariableHis> bondHis) {
//		this.marketVariableHis = bondHis;
//	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bond")
	public List<BondHis> getBondHis() {
		return this.bondHis;
	}

	public void setBondHis(List<BondHis> bondHis) {
		this.bondHis = bondHis;
	}
	

}
