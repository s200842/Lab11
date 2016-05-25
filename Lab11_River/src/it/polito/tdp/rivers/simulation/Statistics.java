package it.polito.tdp.rivers.simulation;

public class Statistics {
	
	private int daysFailed;
	private int daysOk;
	private double totalC;
	private int waterOver;
	
	public Statistics(){
		daysFailed = 0;
		daysOk = 0;
		totalC = 0;
		waterOver = 0;
	}

	public int getDaysFailed() {
		return daysFailed;
	}

	public void setDaysFailed(int daysFailed) {
		this.daysFailed = daysFailed;
	}

	public int getDaysOk() {
		return daysOk;
	}

	public void setDaysOk(int daysOk) {
		this.daysOk = daysOk;
	}

	public double getTotalC() {
		return totalC;
	}

	public void setTotalC(double totalC) {
		this.totalC = totalC;
	}
	
	public int getWaterOver() {
		return waterOver;
	}

	public void setWaterOver(int waterOver) {
		this.waterOver = waterOver;
	}

	public double getAverageC(){
		return totalC/(daysOk+daysFailed);
	}
	
	

}
