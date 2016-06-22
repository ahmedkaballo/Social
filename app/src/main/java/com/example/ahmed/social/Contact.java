package com.example.ahmed.social;

/**
 * Created by Ahmed on 6/19/2016.
 */
public class Contact {
    private String name , email , image;


    public Contact(String   Name , String Email , String image){
        this.setName(Name);
        this.setEmail(Email);
        this.setImage(image);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
