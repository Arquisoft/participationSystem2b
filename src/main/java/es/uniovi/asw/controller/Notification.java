package es.uniovi.asw.controller;

public class Notification {

    private NotificationType type;
    private Long suggestionId;

    public Notification(){
    }

    public Notification(NotificationType type, Long suggestionId) {
        this.type = type;
        this.suggestionId = suggestionId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Long getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(Long suggestionId) {
        this.suggestionId = suggestionId;
    }
}
