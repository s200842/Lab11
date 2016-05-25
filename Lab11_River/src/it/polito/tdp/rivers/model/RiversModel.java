package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class RiversModel {
	
	private RiversDAO dao;
	private List<River> rivers;
	private List<Flow> flows;
	
	public RiversModel(){
		dao = new RiversDAO();
		rivers = dao.getAllRivers();
		flows = dao.getAllFlows(rivers);
		//Per ogni fiume inserisco la lista delle sue misurazioni
		for(River r : rivers){
			for(Flow f : flows){
				if(f.getRiver().equals(r)){
					r.addToFlows(f);
				}
			}
			System.out.println(String.format("Aggiunte %d misurazioni al fiume %s con id %d", r.getFlows().size(), r.getName(), r.getId()));
		}
	}
	
	public List<River> getRivers(){
		return rivers;
	}
	
	public LocalDate getFirstMeasure(River r){
		List<Flow> ftemp = r.getFlows();
		LocalDate min = LocalDate.MAX;
		for(Flow f : ftemp){
			if(f.getDay().isBefore(min)){
				min = f.getDay();
			}
		}
		if(min.equals(LocalDate.MAX)){
			return null;
		}
		else return min;
	}
	
	public LocalDate getLastMeasure(River r){
		List<Flow> ftemp = r.getFlows();
		LocalDate max = LocalDate.MIN;
		for(Flow f : ftemp){
			if(f.getDay().isAfter(max)){
				max = f.getDay();
			}
		}
		if(max.equals(LocalDate.MIN)){
			return null;
		}
		else return max;
	}
	
	public double getMediumFlow(River r){
		double totalFlow = 0;
		for(Flow f : r.getFlows()){
			totalFlow += f.getFlow();
		}
		return (totalFlow/r.getFlows().size());
	}

}
