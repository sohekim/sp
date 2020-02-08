package com.example.sp.data;

public class Post {
    private String uid;
    private String postId;
    private String title;
    private String story;
    private long same;
    private long like;

    public Post(
            String postId,
            String uid,
            String title,
            String story,
            long same,
            long like
    ){
        this.like = like;
        this.title = title;
        this.same = same;
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
    public long getSame(){
        return same;
    }
    public long getLike(){
        return like;
    }
    public String getStory(){
        return story;
    }
}