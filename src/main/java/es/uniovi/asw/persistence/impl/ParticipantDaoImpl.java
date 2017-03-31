package es.uniovi.asw.persistence.impl;


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

}
