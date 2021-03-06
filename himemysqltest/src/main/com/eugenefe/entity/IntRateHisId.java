package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eugenefe.util.AnnoMethodTree;
import com.eugenefe.util.AnnoNavigationFilter;

/**
 * InterateRateHisId generated by hbm2java
 */
@Embeddable
@AnnoNavigationFilter
public class IntRateHisId implements java.io.Serializable {

	private String bssd;
	private String irId;

	public IntRateHisId() {
	}

	public IntRateHisId(String bssd, String irId) {
		this.bssd = bssd;
		this.irId = irId;
	}

	@Column(name = "BSSD", nullable = false, length = 8)
	@NotNull
	@Size(max = 8)
	@AnnoMethodTree(order=10, init=true)
	public String getBssd() {
		return this.bssd;
	}

	public void setBssd(String bssd) {
		this.bssd = bssd;
	}

	@Column(name = "IR_ID", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	@AnnoMethodTree(order=20, init=true)
	public String getIrId() {
		return this.irId;
	}

	public void setIrId(String irId) {
		this.irId = irId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IntRateHisId))
			return false;
		IntRateHisId castOther = (IntRateHisId) other;

		return ((this.getBssd() == castOther.getBssd()) || (this.getBssd() != null && castOther.getBssd() != null && this
				.getBssd().equals(castOther.getBssd())))
				&& ((this.getIrId() == castOther.getIrId()) || (this.getIrId() != null && castOther.getIrId() != null && this
						.getIrId().equals(castOther.getIrId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBssd() == null ? 0 : this.getBssd().hashCode());
		result = 37 * result + (getIrId() == null ? 0 : this.getIrId().hashCode());
		return result;
	}

}
