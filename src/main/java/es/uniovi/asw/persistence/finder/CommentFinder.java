package es.uniovi.asw.persistence.finder;

import java.util.List;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.persistence.util.Jpa;

public class CommentFinder {

	public static List<Comment> findCommentsBySugId(Long id) {
		
		return Jpa.getManager().createNamedQuery("Comment.findBySugId",Comment.class).setParameter(1, id).getResultList();
	}

}
