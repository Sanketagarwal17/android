package com.example.android.pathshalaapp;

public class facultyModelClass
{

    String name;
    String Subject;
    String PhoneNumber;
    int image;


    public facultyModelClass(String name, String subject, String phoneNumber, int image) {
        this.name = name;
        Subject = subject;
        PhoneNumber = phoneNumber;
        image=image;
    }


    public String getName() {
        return name;
    }

    public String getSubject() {
        return Subject;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public int getImage() {
        return image;
    }
}
