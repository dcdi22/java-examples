package com.company.rsvpedge.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Rsvp {
	private Integer rsvpId;
	@NotBlank
	@Size(min = 1, max = 50)
	private String guestName;
	@NotNull
	private Integer totalAttending;
	@NotBlank
	@Size(min = 1, max = 20)
	private String phoneNumber;

	public Rsvp() {
	}

	public Rsvp(Integer rsvpId, @NotBlank @Size(min = 1, max = 50) String guestName, @NotNull Integer totalAttending, @NotBlank @Size(min = 1, max = 20) String phoneNumber) {
		this.rsvpId = rsvpId;
		this.guestName = guestName;
		this.totalAttending = totalAttending;
		this.phoneNumber = phoneNumber;
	}

	public Integer getRsvpId() {
		return rsvpId;
	}

	public void setRsvpId(Integer rsvpId) {
		this.rsvpId = rsvpId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Integer getTotalAttending() {
		return totalAttending;
	}

	public void setTotalAttending(Integer totalAttending) {
		this.totalAttending = totalAttending;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rsvp rsvp = (Rsvp) o;
		return Objects.equals(rsvpId, rsvp.rsvpId) &&
			Objects.equals(guestName, rsvp.guestName) &&
			Objects.equals(totalAttending, rsvp.totalAttending) &&
			Objects.equals(phoneNumber, rsvp.phoneNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rsvpId, guestName, totalAttending, phoneNumber);
	}

	@Override
	public String toString() {
		return "Rsvp{" +
			"rsvpId=" + rsvpId +
			", guestName='" + guestName + '\'' +
			", totalAttending=" + totalAttending +
			", phoneNumber='" + phoneNumber + '\'' +
			'}';
	}
}
