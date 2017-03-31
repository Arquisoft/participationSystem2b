package es.uniovi.asw.model;

import java.util.Date;

public class Comment {
	
	private Participant participant;
	private Suggestion suggestion;
	private String content;
	private Date date;
	

	public Comment(Participant participant,Suggestion suggestion,String content){
		this.participant=participant;
		this.suggestion=suggestion;
		this.content=content;
		this.date = new Date();
	}

	

	public Suggestion getSuggestion() {
		return suggestion;
	}



	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}



	public Participant getParticipant() {
		return participant;
	}


	public void setParticipant(Participant participant) {
		this.participant = participant;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
