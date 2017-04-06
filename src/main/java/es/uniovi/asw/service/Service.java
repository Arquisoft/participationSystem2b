package es.uniovi.asw.service;

import es.uniovi.asw.service.impl.CategoryServiceImpl;
import es.uniovi.asw.service.impl.CommentServiceImpl;
import es.uniovi.asw.service.impl.ParticipantServiceImpl;
import es.uniovi.asw.service.impl.SuggestionServiceImpl;

public class Service {

	public static ParticipantServiceImpl getParticipantService(){
		return new ParticipantServiceImpl();
	}
	public static CommentServiceImpl getCommentService(){
		return new CommentServiceImpl();
	}
	public static SuggestionServiceImpl getSuggestionService(){
		return new SuggestionServiceImpl();
	}
	public static CategoryServiceImpl getCategoryService(){
		return new CategoryServiceImpl();
	}
}
