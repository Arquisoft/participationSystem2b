package es.uniovi.asw.service.impl;

import java.util.List;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.persistence.impl.CommentDaoImpl;
import es.uniovi.asw.service.CommentService;

public class CommentServiceImpl implements CommentService {
	private CommentDaoImpl dao = new CommentDaoImpl();

	@Override
	public void addComment(Comment comment) {
		dao.addComment(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		dao.deleteComment(comment);
	}

	@Override
	public Comment getComment(long suggId, long participId) {
		return dao.getComment(suggId, participId);
	}

	@Override
	public List<Comment> findAllCommentsBySuggestionId(long suggId) {
		return dao.findAllCommentsBySuggestionId(suggId);
	}

	@Override
	public List<Comment> findAllCommentsByParticipantId(long participantId) {
		return dao.findAllCommentsByParticipantId(participantId);
	}

}
