package com.example.sp.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {

    private String uid;

    private String name;
    private String country;
    private String email;
    private String city;
    private ArrayList<String> postsID;


    public Profile(
            String uid,
            String name,
            String email,
            String country,
            String city
    )

    {
        this.uid = uid;
        this.name = name;
        this.country = country;
        this.city = city;
        this.email = email;
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
    public String getCountry(){
        return country;
    }
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

