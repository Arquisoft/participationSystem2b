package es.uniovi.asw.service;


import es.uniovi.asw.model.Participant;

public interface ParticipantService {
	
	public void deleteAllParticipants();

	public Participant findParticipant(String dni);

	public void updateParticipant(Participant participant);
	public void deleteParticipantByDni(String dni);

	public void addParticipant(Participant participant);
	public void init();

	public Participant findLogableUser(String usuario, String password);

}
