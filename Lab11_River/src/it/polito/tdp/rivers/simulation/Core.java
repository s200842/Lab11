package it.polito.tdp.rivers.simulation;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;


public class Core {
	
	//Strutture dati per gestione code
	Queue<Event> eventList = new PriorityQueue<Event>();
	Statistics stats = new Statistics();
	
	//Variabili quantitative
	private double k;
	private double Q;
	private double C;
	private int daysOk;
	private int daysKo;
	private int cTot;
	private int wOver;
	
	//Costruttore ed inizializzazione variabili
	public Core(double k){
		this.k = k;
		daysOk = 0;
		daysKo = 0;
		cTot = 0;
		wOver = 0;
	}
	
	//Metodi per aggiunta dati alle strutture
	public void addEvent(Event e){
		eventList.add(e);
	}

	//Metodo step che controlla caso per caso
	public void step(){
		Event e = eventList.remove();
		switch(e.getType()){
		//Se per il giorno corrente ho un flusso in entrata
		case FLOW_IN:
			double f_in = e.getFlow().getFlow()*86400;
			System.out.println(String.format("[Giorno %s] --- Flusso in entrata = %.5f", e.getDate().toString(), f_in));
			//Aumento la quantità di acqua nel bacino del valore f_in = f_med*86400
			C += f_in;
			System.out.println(String.format("Capacità odierna: %.5f metri cubi.", C));
			//Controllo se si verifica tracimazione
			if(C > Q){
				System.out.println("TRACIMAZIONE!! Livello acqua riportato a "+Q+" metri cubi");
				wOver++;
				C = Q;
			}
			cTot += C;
			//Calcolo la quantità richiesta giornaliera f_out_min
			double f_out_min = 0.8*e.getFlow().getFlow()*86400;
			//Controllo se i campi devono essere irrigati
			if(ThreadLocalRandom.current().nextDouble() <= 0.05){
				f_out_min *= 10;
				System.out.println("Richiesta più acqua per irrigazione ("+f_out_min+" metri cubi)");
			}
			else{
				System.out.println(String.format("Richiesti %.5f metri cubi di acqua", f_out_min));
			}
			//Controllo se riesco a garantire la quantità richiesta
			if(C >= f_out_min){
				C -= f_out_min;
				System.out.println("Erogati "+f_out_min+" metri cubi di acqua.");
				daysOk++;
			}
			else{
				System.out.println("Non si ha a disposizione acqua sufficiente per soddisfare le richieste giornaliere");
				daysKo++;
			}
			break;
		default:
			System.err.println("Caso non gestito");
			break;
		
		}
	}
	
	//Metodo simula che gestisce il susseguirsi degli step
	public void start(){
		//Q = k * 30 giorni * f_med;
		Q = 30*86400*k*(eventList.peek().getFlow().getFlow());
		C = Q/2;
		while(!eventList.isEmpty()){
			step();
		}
		stats.setDaysFailed(daysKo);
		stats.setDaysOk(daysOk);
		stats.setTotalC(cTot);
		stats.setWaterOver(wOver);
	}
	
	public Statistics getStats(){
		return stats;
	}
}
