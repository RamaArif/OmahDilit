package com.app.omahdilit.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("error")
    Boolean error;

    public LoginResponse(Boolean error) {
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }
}
