package it.polito.tdp.rivers.simulation;

import java.time.LocalDate;

import it.polito.tdp.rivers.model.Flow;

public class Event implements Comparable<Event>{

	public enum EventType {FLOW_IN};
	
	private LocalDate date;
	private EventType type;
	private Flow f;
	
	public Event(LocalDate date, EventType type, Flow f) {
		this.date = date;
		this.type = type;
		this.f = f;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public Flow getFlow() {
		return f;
	}

	@Override
	public int compareTo(Event arg0) {
		return Integer.compare(this.getType().ordinal(), arg0.getType().ordinal());
	}

}
