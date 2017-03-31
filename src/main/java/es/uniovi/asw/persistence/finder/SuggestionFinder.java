package es.uniovi.asw.persistence.finder;

import java.util.List;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.persistence.util.Jpa;

public class SuggestionFinder {

	public static List<Suggestion> findByParticipantId(Participant part) {
		return Jpa.getManager().createNamedQuery("Suggestion.findByPId",Suggestion.class).setParameter(1, part).getResultList();
		
	}
	
	

}
