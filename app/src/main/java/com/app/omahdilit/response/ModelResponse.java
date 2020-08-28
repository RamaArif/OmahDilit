package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponse {

    @SerializedName("body")
    @Expose
    private List<ModelItem> modelItems;

    public List<ModelItem> getModelItems() {
        return modelItems;
    }

    public ModelResponse(List<ModelItem> modelItems) {
        this.modelItems = modelItems;
    }
}
