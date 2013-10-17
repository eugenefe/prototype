package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * VcvMatrixHisId generated by hbm2java
 */
@Embeddable
public class VolatilityHisId implements java.io.Serializable {

	public String bssd;
	public String volId;
	public String rfId;
	public String refRfId;

	public VolatilityHisId() {
	}

	public VolatilityHisId(String bssd, String vcvId, String rfId, String refRfId) {
		this.bssd = bssd;
		this.volId = vcvId;
		this.rfId = rfId;
		this.refRfId = refRfId;
	}

	@Column(name = "BSSD", nullable = false, length = 8)
	@NotNull
	@Size(max = 8)
	public String getBssd() {
		return this.bssd;
	}

	public void setBssd(String bssd) {
		this.bssd = bssd;
	}

	@Column(name = "VCV_ID", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	public String getVolId() {
		return this.volId;
	}

	public void setVolId(String vcvId) {
		this.volId = vcvId;
	}

	@Column(name = "RF_ID", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	public String getRfId() {
		return this.rfId;
	}

	public void setRfId(String rfId) {
		this.rfId = rfId;
	}

	@Column(name = "REF_RF_ID", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	public String getRefRfId() {
		return this.refRfId;
	}

	public void setRefRfId(String refRfId) {
		this.refRfId = refRfId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VolatilityHisId))
			return false;
		VolatilityHisId castOther = (VolatilityHisId) other;

		return ((this.getBssd() == castOther.getBssd()) || (this.getBssd() != null && castOther.getBssd() != null && this
				.getBssd().equals(castOther.getBssd())))
				&& ((this.getVolId() == castOther.getVolId()) || (this.getVolId() != null
						&& castOther.getVolId() != null && this.getVolId().equals(castOther.getVolId())))
				&& ((this.getRfId() == castOther.getRfId()) || (this.getRfId() != null && castOther.getRfId() != null && this
						.getRfId().equals(castOther.getRfId())))
				&& ((this.getRefRfId() == castOther.getRefRfId()) || (this.getRefRfId() != null
						&& castOther.getRefRfId() != null && this.getRefRfId().equals(castOther.getRefRfId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBssd() == null ? 0 : this.getBssd().hashCode());
		result = 37 * result + (getVolId() == null ? 0 : this.getVolId().hashCode());
		result = 37 * result + (getRfId() == null ? 0 : this.getRfId().hashCode());
		result = 37 * result + (getRefRfId() == null ? 0 : this.getRefRfId().hashCode());
		return result;
	}

}
