package eu.fischerserver.gitlab.pm.jsfui.model;

public record PMData(int id, boolean muteState) {
    public PMData(boolean muteState) {
        this((int) (Math.random() * Integer.MAX_VALUE), muteState);
    }
}
