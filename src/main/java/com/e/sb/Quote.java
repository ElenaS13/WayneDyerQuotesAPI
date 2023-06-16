package com.e.sb;

public class Quote {
    private Long id;
    private String content;

    public Quote() {
    }

    public Quote(Long id, String content) {
        this.id = id;
        this.content = content;
    }


    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Quote{" + "id=" + this.id + ", content='" + this.content + '\'' + '}';
    }

}
