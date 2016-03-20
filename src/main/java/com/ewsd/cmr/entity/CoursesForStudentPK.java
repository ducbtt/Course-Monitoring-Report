package com.ewsd.cmr.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the COURSES_FOR_STUDENT database table.
 * 
 * @author lequoctruong
 */

@Embeddable
public class CoursesForStudentPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String crsCode;

	private int usrId;

	public CoursesForStudentPK() {
	}

	public String getCrsCode() {
		return this.crsCode;
	}

	public void setCrsCode(String crsCode) {
		this.crsCode = crsCode;
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CoursesForStudentPK)) {
			return false;
		}
		CoursesForStudentPK castOther = (CoursesForStudentPK) other;
		return this.crsCode.equals(castOther.crsCode) && (this.usrId == castOther.usrId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.crsCode.hashCode();
		hash = hash * prime + this.usrId;

		return hash;
	}
}