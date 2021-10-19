package com.example.dog_api;

import com.google.gson.annotations.SerializedName;

public class DogClass {

    //@SerializedName("message") //Jodi JSON theke alada name dite chai
    private String message;
    private String status;

    public DogClass(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
