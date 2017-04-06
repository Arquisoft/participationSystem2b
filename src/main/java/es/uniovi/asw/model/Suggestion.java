package es.uniovi.asw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SUGGESTION")
public class Suggestion {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	@ManyToOne
	private Participant participant;
	
	private String title;
	private String suggestion;
	@OneToMany(mappedBy="suggestion")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@ManyToOne
	private Category category;
	@Column(name="positive_votes")
	private int positiveVotes;
	@Column(name="negative_votes")
	private int negativeVotes;

	Suggestion(){}
	public Suggestion(Participant participant,String titulo,String suggestion,Category category) {
		this.title=titulo;
		this.participant=participant;
		this.suggestion=suggestion;
		this.category=category;
		this.creationDate= new Date();
		Association.AddSuggestion.link(this,participant);
		Association.AddCategory.link(this,category);
	}
	
	

	
	public String getSuggestionPreview(){
		String suggestPreview="";
		if(suggestion.length()>200){
			suggestPreview=suggestion.substring(0, 200);
			suggestPreview+=" ...";

		}else{
			suggestPreview= suggestion;
		}
		return suggestPreview;
	}
	Set<Comment> _getComments() {
		return this.comments;
	}
	public Set<Comment> getComments() {
		return new HashSet<Comment>(this.comments);
	}
	public Long getId(){
		return this.id;
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


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Participant getParticipant() {
		return participant;
	}

	void _setParticipant(Participant participant) {
		this.participant = participant;
	}
	


	public Category getCategory() {
		return category;
	}
	void _setCategory(Category category) {
		this.category = category;
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Suggestion other = (Suggestion) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Suggestion [id=" + id + ", participant=" + participant + ", title=" + title + ", creationDate="
				+ creationDate + ", category=" + category + ", positiveVotes=" + positiveVotes + ", negativeVotes="
				+ negativeVotes + "]";
	}
	
	
	
	
}
