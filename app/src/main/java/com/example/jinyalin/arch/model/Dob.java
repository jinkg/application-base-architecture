package com.example.jinyalin.arch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Yalin on 2018/11/28
 */
public class Dob {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("age")
    @Expose
    private int age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
