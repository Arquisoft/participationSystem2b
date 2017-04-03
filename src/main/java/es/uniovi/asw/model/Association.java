package es.uniovi.asw.model;

public class Association {

	public static class AddSuggestion{
		public static void link(Suggestion sugg, Participant parti){
			sugg._setParticipant(parti);
			parti._getSuggestions().add(sugg);
			
		}
		
	}
	
	public static class AddComment{
		public static void link(Suggestion sugg, Comment comment, Participant parti){
			comment._setParticipant(parti);
			comment._setSuggestion(sugg);
			sugg._getComments().add(comment);
			parti._getComments().add(comment);
		}
	}
}
