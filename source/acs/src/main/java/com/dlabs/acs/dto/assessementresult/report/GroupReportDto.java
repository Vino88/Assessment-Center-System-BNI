package com.dlabs.acs.dto.assessementresult.report;

import com.dlabs.acs.entity.competency.CompetencyLibrary;

public class GroupReportDto {
	private CompetencyLibrary competencyLibrary;
	
	private int below;
	private int meet;
	private int above;
	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}
	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
	public int getBelow() {
		return below;
	}
	public void setBelow(int below) {
		this.below = below;
	}
	public int getMeet() {
		return meet;
	}
	public void setMeet(int meet) {
		this.meet = meet;
	}
	public int getAbove() {
		return above;
	}
	public void setAbove(int above) {
		this.above = above;
	}
	
	public double getBelowPercentage()
	{
		return getPercentage(this.below);
	}
	
	public double getMeetPercentage()
	{
		return getPercentage(this.meet);
	}
	
	public double getAbovePercentage()
	{
		return getPercentage(this.above);
	}
	
	
	private double getPercentage(int value)
	{
		if(value == 0)
		{
			return 0d;
		}
		
		double percentage = (value / (this.below + this.meet + this.above) )  * 100;
		
		return percentage;
	}
}
