package com.example.sp.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {

    private String uid;
    private String name;
    private String city;
    private String email;
    private String age;
    private String pronoun;
    private ArrayList<String> postsID;


    public Profile(
            String uid,
            String name,
            String email,
            String age,
            String pronoun,
            String city
    )

    {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.age = age;
        this.pronoun = pronoun;
        this.city = city;
        postsID = new ArrayList<>();
    }

    public String getId(){
        return uid;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getAge(){
        return age;
    }
    public String getPronoun() {return pronoun;}
    public String getCity(){
        return city;
    }

    public boolean addPostID(String id){
        if(id == null || id.length() <= 0){
            return false;
        }
        postsID.add(id);
        return true;
    }

}

