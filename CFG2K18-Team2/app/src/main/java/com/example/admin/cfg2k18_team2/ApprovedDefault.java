package com.example.admin.cfg2k18_team2;

/**
 * Created by admin on 7/14/2018.
 */

public class ApprovedDefault
{
    String title,author,edition,newTags;
    String url;
    Long downloadcount;

    public String getVolunteername() {
        return volunteername;
    }

    public void setVolunteername(String volunteername) {
        this.volunteername = volunteername;
    }

    String volunteername;

    public Long getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Long downloadcount) {
        this.downloadcount = downloadcount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getNewTags() {
        return newTags;
    }

    public void setNewTags(String newTags) {
        this.newTags = newTags;
    }
}
