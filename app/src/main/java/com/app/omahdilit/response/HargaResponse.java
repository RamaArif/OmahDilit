package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HargaResponse {
    @SerializedName("hargacukur")
    @Expose
    private Integer hargacukur;
    @SerializedName("hargalebih")
    @Expose
    private Integer hargalebih;
    @SerializedName("potongan")
    @Expose
    private Integer potongan;
    @SerializedName("hargatotal")
    @Expose
    private Integer hargatotal;

    public HargaResponse(Integer hargacukur, Integer hargalebih, Integer potongan, Integer hargatotal) {
        this.hargacukur = hargacukur;
        this.hargalebih = hargalebih;
        this.potongan = potongan;
        this.hargatotal = hargatotal;
    }

    public Integer getHargacukur() {
        return hargacukur;
    }

    public Integer getHargalebih() {
        return hargalebih;
    }

    public Integer getPotongan() {
        return potongan;
    }

    public Integer getHargatotal() {
        return hargatotal;
    }
}
