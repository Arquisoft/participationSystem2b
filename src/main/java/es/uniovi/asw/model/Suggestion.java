package es.uniovi.asw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Suggestion {

	private Participant participant;
	private String titulo;
	private String suggestion;
	private List<Comment> comments;
	private Date creationDate;
	private String category;
	private int positiveVotes;
	private int negativeVotes;
	
	public Suggestion(Participant participant,String titulo,String suggestion,String category) {
		this.comments=new ArrayList<Comment>();
		this.titulo=titulo;
		this.participant=participant;
		this.suggestion=suggestion;
		this.category=category;
		this.creationDate= new Date();
	}

	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	public String getSuggestion() {
		return suggestion;
	}
	
	public void increasePositiveVotes(){
		this.positiveVotes++;
	}

	public void increaseNegativeVotes(){
		this.negativeVotes++;
	}

	public int getPositiveVotes() {
		return positiveVotes;
	}

	public int getNegativeVotes() {
		return negativeVotes;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}


	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
