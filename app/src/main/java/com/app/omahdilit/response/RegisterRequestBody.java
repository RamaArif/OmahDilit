package com.app.omahdilit.response;

public class RegisterRequestBody {
    String name, addres, number, email, uid, photo, pushtoken;

    public RegisterRequestBody(String name, String addres, String number, String email, String uid, String photo, String pushtoken) {
        this.name = name;
        this.addres = addres;
        this.number = number;
        this.email = email;
        this.uid = uid;
        this.photo = photo;
        this.pushtoken = pushtoken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPushtoken(String pushtoken) {
        this.pushtoken = pushtoken;
    }
}
