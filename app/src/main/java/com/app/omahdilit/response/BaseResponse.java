package com.app.omahdilit.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("error")
    Boolean error;

    public BaseResponse(Boolean error) {
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }
}
