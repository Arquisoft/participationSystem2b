package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="COMMENT")
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	@ManyToOne
	private Participant participant;
	@ManyToOne
	private Suggestion suggestion;
	private String content;
	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date date;
	
	Comment(){}
	public Comment(Participant participant,Suggestion suggestion,String content){
		this.participant=participant;
		this.suggestion=suggestion;
		this.content=content;
		this.date = new Date();
		Association.AddComment.link(suggestion, this, participant);
		
	}

	

	public Suggestion getSuggestion() {
		return suggestion;
	}



	void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}



	public Participant getParticipant() {
		return participant;
	}


	void _setParticipant(Participant participant) {
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
	public Long getId(){
		return this.id;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", date=" + date + "]";
	}
	
	
	
	
}
