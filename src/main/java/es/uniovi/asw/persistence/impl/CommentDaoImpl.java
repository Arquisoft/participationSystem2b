package es.uniovi.asw.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.persistence.CommentDao;
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
		// TODO Auto-generated method stub

	}

	@Override
	public Comment getComment(long suggId, long participId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentsBySuggestionId(long suggId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentsByParticipantId(long participantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
