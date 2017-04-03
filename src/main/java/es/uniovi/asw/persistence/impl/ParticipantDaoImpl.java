package es.uniovi.asw.persistence.impl;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.persistence.ParticipantDao;
import es.uniovi.asw.persistence.finder.ParticipantFinder;
import es.uniovi.asw.persistence.util.Jpa;

public class ParticipantDaoImpl implements ParticipantDao {

	@Override
	public void addParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(ParticipantFinder.findByDni(participant.getDni())==null){
			Jpa.getManager().persist(participant);
		}
		trx.commit();
		
		

	}


	@Override
	public void updateParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(participant!=null)
			Jpa.getManager().merge(participant);
		trx.commit();

	}

	@Override
	public Participant findParticipant(String dni) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Participant c =ParticipantFinder.findByDni(dni);
		return c;
	}


	@Override
	public void deleteAllParticipants() {
		//Participant p = Jpa.getManager().find(Participant.class)
	}

	@Override
	public void deleteParticipantByDni(String dni) {
		
		Participant p = ParticipantFinder.findByDni(dni);
		
		p=Jpa.getManager().find(Participant.class, p.getId());
		if(p !=null && p.getComments().size()==0 && p.getSuggestions().size()==0){
			Jpa.getManager().remove(p);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void init(){
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Participant user = new Participant("Daniel", "Orviz", "orviz@prueba", "dir", "España", "23453212Y",
				new Date(1995 - 1900, 2, 14),"DanielOrviz");
		if(ParticipantFinder.findByDni(user.getDni())== null){
			Jpa.getManager().persist(user);
		}
		user  = new Participant("Admin", "1", "admin@prueba", "dir", "España", "65431789T",
				new Date(1995 - 1900, 2, 14),"Admin1");
		if(ParticipantFinder.findByDni(user.getDni())== null){
			Jpa.getManager().persist(user);
		}
		trx.commit();
	}


	@Override
	public Participant findLogableUser(String usuario, String password) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		return ParticipantFinder.findLogableUser(usuario,password);
		
		
	}

}
