package com.vstock.db.entity;

public class LanguageControl {

    private String id;
    private String english;
    private String chinese;
    private String create_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public LanguageControl() { }

    public LanguageControl(String id, String english, String chinese, String create_date) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
        this.create_date = create_date;
    }
}
