package com.dlabs.acs.dto.assessementresult.report;

public class IndividuReportDto implements java.io.Serializable{

	private int no;
	
	private Long competencyId;
	
	private String competencyName;
	
	private boolean selectedForReport;
	
	private int rl;
	
	private int il;
	
	private int gap;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCompetencyName() {
		return competencyName;
	}

	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}

	public int getRl() {
		return rl;
	}

	public void setRl(int rl) {
		this.rl = rl;
	}

	public int getIl() {
		return il;
	}

	public void setIl(int il) {
		this.il = il;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public Long getCompetencyId() {
		return competencyId;
	}

	public void setCompetencyId(Long competencyId) {
		this.competencyId = competencyId;
	}

	public boolean isSelectedForReport() {
		return selectedForReport;
	}

	public void setSelectedForReport(boolean selectedForReport) {
		this.selectedForReport = selectedForReport;
	}
	@Override
	public String toString() {
		return "IndividuReportDto [no=" + no + ", competencyId=" + competencyId + ", competencyName=" + competencyName
				+ ", selectedForReport=" + selectedForReport + ", rl=" + rl + ", il=" + il + ", gap=" + gap + "]";
	}
}
