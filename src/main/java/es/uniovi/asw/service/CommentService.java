package es.uniovi.asw.service;

import java.util.List;

import es.uniovi.asw.model.Comment;

public interface CommentService {

	public void addComment(Comment comment);
	public void deleteComment(Comment comment);
	public Comment getComment(long suggId, long participId);
	public List<Comment> findAllCommentsBySuggestionId(long suggId);
	public List<Comment> findAllCommentsByParticipantId(long participantId);
	public Comment findCommentById(Long id);
	public void updateComment(Comment comment);
	
}
