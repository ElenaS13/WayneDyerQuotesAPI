package com.e.sb;

public class Quote {
    private String id;
    private String content;

    public Quote() {
    }

    public Quote(String id, String content) {
        this.id = id;
        this.content = content;
    }


    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(String id) {
        this.id = String.valueOf(id);
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Quote{" + "id=" + this.id + ", content='" + this.content + '\'' + '}';
    }

}
