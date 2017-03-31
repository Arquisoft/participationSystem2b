package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Comment;

public interface CommentDao {

	public void addComment(Comment comment);
	public void deleteComment(Comment comment);
	public Comment getComment(long suggId, long participId);
	public List<Comment> findAllCommentsBySuggestionId(long suggId);
	public List<Comment> findAllCommentsByParticipantId(long participantId);
}
