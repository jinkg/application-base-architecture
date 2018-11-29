package com.example.jinyalin.arch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Yalin on 2018/11/28
 */
public class Timezone {
    @SerializedName("offset")
    @Expose
    private String offset;

    @SerializedName("description")
    @Expose
    private String desc;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
