package es.uniovi.asw.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.persistence.CommentDao;
import es.uniovi.asw.persistence.finder.CommentFinder;
import es.uniovi.asw.persistence.util.Jpa;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void addComment(Comment comment) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
			Jpa.getManager().persist(comment);
		trx.commit();
	}

	@Override
	public void deleteComment(Comment comment) {

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Comment c =Jpa.getManager().find(Comment.class, comment.getId());
		Jpa.getManager().remove(c);
		
		trx.commit();
	}

	@Override
	public Comment getComment(long suggId, long participId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentsBySuggestionId(long suggId) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Comment> comments= CommentFinder.findCommentsBySugId(suggId);
		trx.commit();
		return comments;
	}

	@Override
	public List<Comment> findAllCommentsByParticipantId(long participantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
