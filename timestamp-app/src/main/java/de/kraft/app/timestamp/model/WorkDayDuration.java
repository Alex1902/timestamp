package de.kraft.app.timestamp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class WorkDayDuration {
	private WorkDayDateTime start;
	private WorkDayDateTime end;
	
	public WorkDayDateTime getStart() {
		return start;
	}

	public WorkDayDateTime getEnd() {
		return end;
	}
	
	public WorkDayDuration(WorkDayDateTime start, WorkDayDateTime end){
		this.start = start;
		this.end = end;
	}
	
	public float getDuration(){
		return calculateDuration();
	}
	
	public String toString(){
		NumberFormat formatter = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.getDefault()));
		return formatter.format((float)calculateDuration());
	}
	
//	public String formatHours(float hours){
//		NumberFormat formatter = new DecimalFormat("#0.00");
//		return formatter.format((float)hours);
//	}
	
	public WorkDayDuration minus(WorkDayDuration subtrahend){
		long minuendMillis = end.getValue().getTimeInMillis() - start.getValue().getTimeInMillis();
		long subtrahendMillis = subtrahend.getEnd().getValue().getTimeInMillis() - subtrahend.getStart().getValue().getTimeInMillis();
		Calendar start = new GregorianCalendar();
		start.setTimeInMillis(subtrahendMillis);
		Calendar end = new GregorianCalendar();
		end.setTimeInMillis(minuendMillis);
		return new WorkDayDuration(new WorkDayDateTime(start), new WorkDayDateTime(end));
	}
	
	private float calculateDuration(){
		long durationInMilliSec = end.getValue().getTimeInMillis() - start.getValue().getTimeInMillis();
		float duration = (float)(durationInMilliSec)/1000/60/60;
		
		return scaleHours(duration);
	}
	
	private float scaleHours(float hours){
		BigDecimal fd = new BigDecimal(hours);
		BigDecimal scaled = fd.setScale(2, RoundingMode.CEILING);
		return scaled.floatValue();
	}
}
