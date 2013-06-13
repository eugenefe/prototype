package com.eugenefe.entity;

// Generated Apr 10, 2013 4:09:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * EtfHisId generated by hbm2java
 */
@Embeddable
public class EtfHisId implements java.io.Serializable {

	private String bssd;
	private String etfId;

	public EtfHisId() {
	}

	public EtfHisId(String bssd, String etfId) {
		this.bssd = bssd;
		this.etfId = etfId;
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

	@Column(name = "ETF_ID", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	public String getEtfId() {
		return this.etfId;
	}

	public void setEtfId(String etfId) {
		this.etfId = etfId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EtfHisId))
			return false;
		EtfHisId castOther = (EtfHisId) other;

		return ((this.getBssd() == castOther.getBssd()) || (this.getBssd() != null && castOther.getBssd() != null && this
				.getBssd().equals(castOther.getBssd())))
				&& ((this.getEtfId() == castOther.getEtfId()) || (this.getEtfId() != null
						&& castOther.getEtfId() != null && this.getEtfId().equals(castOther.getEtfId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBssd() == null ? 0 : this.getBssd().hashCode());
		result = 37 * result + (getEtfId() == null ? 0 : this.getEtfId().hashCode());
		return result;
	}

}
