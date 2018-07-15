package com.example.admin.cfg2k18_team2;

public class RequestVolunteers {
    String volunteername;
    String bookname;
    String authorname;
    String edition;
    String tags;
    String url;
    public RequestVolunteers(){

    }
    public RequestVolunteers(String volunteername,String bookname,String authorname,String edition,String tags,String url){
        this.volunteername=volunteername;
        this.bookname=bookname;
        this.authorname=authorname;
        this.edition=edition;
        this.tags=tags;
        this.url=url;
    }

    public String getVolunteername() {
        return volunteername;
    }

    public void setVolunteername(String volunteername) {
        this.volunteername = volunteername;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}