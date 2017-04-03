package es.uniovi.asw.service.impl;

import java.util.List;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.persistence.impl.SuggestionDaoImpl;
import es.uniovi.asw.service.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService {

	private SuggestionDaoImpl dao = new SuggestionDaoImpl();
	@Override
	public void addSuggestion(Suggestion suggestion) {
		dao.addSuggestion(suggestion);
	}

	@Override
	public void deleteSuggestion(Suggestion suggestion) {
		dao.deleteSuggestion(suggestion);
	}

	@Override
	public void updateSuggestion(Suggestion suggestion) {
		dao.updateSuggestion(suggestion);
	}


	@Override
	public List<Suggestion> getAllSuggestions() {
		return dao.getAllSuggestions();
	}

	@Override
	public List<Suggestion> getSuggestionByParticipant(Participant participant) {
		return dao.getSuggestionByParticipant(participant);
	}

}
