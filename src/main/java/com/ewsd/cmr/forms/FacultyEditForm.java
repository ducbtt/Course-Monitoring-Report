package com.ewsd.cmr.forms;

import java.util.ArrayList;
import java.util.List;

import com.ewsd.cmr.entity.CmrUser;
import com.ewsd.cmr.entity.Faculty;

public class FacultyEditForm extends AbstractEditForm {

	private static final long serialVersionUID = -1710232925810088020L;

	private int facultyId;

	private String description;

	private String name;

	private List<CmrUser> dlts;

	private List<CmrUser> pvcs;

	private CmrUser facultyDlt;

	private CmrUser facultyPvc;

	private int dlt;
	private int pvc;

	public FacultyEditForm(Faculty faculty) {
		this.facultyId = faculty.getFacultyId();
		this.description = faculty.getDescription();
		this.name = faculty.getName();
		this.dlts = new ArrayList<>();
		this.pvcs = new ArrayList<>();
		this.facultyDlt = faculty.getCmrUser1();
		this.facultyPvc = faculty.getCmrUser2();
		this.pvc = (facultyPvc != null) ? facultyPvc.getUsrId() : 0;
		this.dlt = (facultyDlt != null) ? facultyDlt.getUsrId() : 0;
	}

	public FacultyEditForm() {
		this.dlts = new ArrayList<>();
		this.pvcs = new ArrayList<>();
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CmrUser> getDlts() {
		return dlts;
	}

	public void setDlts(List<CmrUser> dlts) {
		this.dlts = dlts;
	}

	public List<CmrUser> getPvcs() {
		return pvcs;
	}

	public void setPvcs(List<CmrUser> pvcs) {
		this.pvcs = pvcs;
	}

	public CmrUser getFacultyDlt() {
		return facultyDlt;
	}

	public void setFacultyDlt(CmrUser facultyDlt) {
		this.facultyDlt = facultyDlt;
	}

	public CmrUser getFacultyPvc() {
		return facultyPvc;
	}

	public void setFacultyPvc(CmrUser facultyPvc) {
		this.facultyPvc = facultyPvc;
	}

	public int getDlt() {
		return dlt;
	}

	public void setDlt(int dlt) {
		this.dlt = dlt;
	}

	public int getPvc() {
		return pvc;
	}

	public void setPvc(int pvc) {
		this.pvc = pvc;
	}

	
}
