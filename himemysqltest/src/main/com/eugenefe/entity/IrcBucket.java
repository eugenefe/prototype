package com.eugenefe.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.eugenefe.enums.EMaturity;

@Entity
@Table(name = "IRC_BUCKET_DETAIL")
@BatchSize(size=5)
public class IrcBucket implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private IrcBucketId id;
	private IrCurve ircId;
	private EMaturity maturityId;
	private IntRate intRate;
	
	public IrcBucket() {
	}

	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ircId", column = @Column(name = "IRC_ID", nullable = false)),
			@AttributeOverride(name = "maturityId", column = @Column(name = "MATURITY_ID", nullable = false, length = 20)) })
	@NotNull
	public IrcBucketId getId() {
		return id;
	}

	public void setId(IrcBucketId id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IRC_ID", nullable = false, insertable = false, updatable = false)
	@BatchSize(size=3)
	@NotNull
	public IrCurve getIrcId() {
		return ircId;
	}

	public void setIrcId(IrCurve ircId) {
		this.ircId = ircId;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "MATURITY_ID", nullable = false, insertable = false, updatable = false)
//	@NotNull
	
//	@Column(name = "MATURITY_ID", nullable = false, insertable = false, updatable = false)
//	public String getMaturityId() {
//		return maturityId;
//	}
//
//	public void setMaturityId(String maturityId) {
//		this.maturityId = maturityId;
//	}
	@Column(name = "MATURITY_ID", nullable = false, insertable = false, updatable = false)
	@Enumerated
	public EMaturity getMaturityId() {
		return maturityId;
	}


	public void setMaturityId(EMaturity maturityId) {
		this.maturityId = maturityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
//	@Fetch(FetchMode.JOIN)
	@BatchSize(size=20)
	@JoinColumn(name = "IR_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public IntRate getIntRate() {
		return intRate;
	}

	


	public void setIntRate(IntRate intRate) {
		this.intRate = intRate;
	}

	
	
}
