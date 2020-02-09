package com.example.sp.data;

public class Post {
    private String uid;
    private String postId;
    private String title;
    private String story;
    private String like;

    public Post(
            String postId,
            String uid,
            String title,
            String story,
            String like
    ){
        this.postId = postId;
        this.title = title;
        this.like = like;
        this.story = story;
        this.uid = uid;
    }
    public String getPostId(){
        return postId;
    }
    public String getId(){
        return uid;
    }
    public String getTitle(){
        return title;
    }
    public String getLike(){
        return like;
    }
    public String getStory(){
        return story;
    }
}