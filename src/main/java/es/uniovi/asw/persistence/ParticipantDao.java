package es.uniovi.asw.persistence;


import es.uniovi.asw.model.Participant;

public interface ParticipantDao {
	
	
	public void deleteAllParticipants();

	public Participant findParticipant(String dni);

	public void updateParticipant(Participant participant);

	public void addParticipant(Participant participant);

	void deleteParticipantByDni(String dni);


}
