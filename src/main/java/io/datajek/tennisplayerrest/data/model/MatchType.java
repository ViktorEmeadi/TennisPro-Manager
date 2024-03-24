package io.datajek.tennisplayerrest.data.model;

public enum MatchType {
    SINGLES("SINGLES"), DOUBLES("DOUBLES");
    private final String name;

    MatchType(String name) {
        this.name = name;
    }
}
