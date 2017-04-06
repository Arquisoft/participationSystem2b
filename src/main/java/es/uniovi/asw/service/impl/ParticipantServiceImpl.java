package es.uniovi.asw.service.impl;


import es.uniovi.asw.model.Participant;
import es.uniovi.asw.persistence.impl.ParticipantDaoImpl;
import es.uniovi.asw.service.ParticipantService;

public class ParticipantServiceImpl implements ParticipantService {

	private ParticipantDaoImpl dao= new ParticipantDaoImpl();

	@Override
	public void deleteAllParticipants() {
		dao.deleteAllParticipants();

	}


	@Override
	public Participant findParticipant(String dni) {
		
		return dao.findParticipant(dni);
	}
	
	

	@Override
	public void updateParticipant(Participant participant) {
		dao.updateParticipant(participant);
	}


	@Override
	public void addParticipant(Participant participant) {
		dao.addParticipant(participant);
	}


	@Override
	public void deleteParticipantByDni(String dni) {
		dao.deleteParticipantByDni(dni);
	}


	@Override
	public void init() {
		dao.init();
	}

	@Override
	public Participant findLogableUser(String usuario, String password) {
		return dao.findLogableUser(usuario, password);
	}



}
