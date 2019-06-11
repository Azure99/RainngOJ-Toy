package com.rainng.rainngojtoy.models.pojo;

public class LanguagePOJO {
    private int id;
    private String name;
    private boolean dbLanguage;

    public LanguagePOJO() {

    }

    public LanguagePOJO(int id, String name, boolean dbLanguage) {
        this.id = id;
        this.name = name;
        this.dbLanguage = dbLanguage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDbLanguage() {
        return dbLanguage;
    }

    public void setDbLanguage(boolean dbLanguage) {
        this.dbLanguage = dbLanguage;
    }
}
