package de.kraft.app.timestamp.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class WorkDayDateTime {
	private Calendar value;
	
	public WorkDayDateTime() {
		value = new GregorianCalendar();
	}
	
	public WorkDayDateTime(Calendar cal) {
		value = cal;
	}
	
	public Calendar getValue() {
		return value;
	}
	
	public static WorkDayDateTime fromMilliseconds(long milliseconds){
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(milliseconds);
		return new WorkDayDateTime(cal);
	}
	
	@Override
	public String toString() {	
		return formatTime(value);
	}	
	
	private String formatTime(Calendar cal){
		DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
		return timeFormatter.format(cal.getTime());
	}
}
