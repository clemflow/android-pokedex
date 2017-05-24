package com.example.clem.androiduniver.models;

import java.util.ArrayList;

/**
 * Created by clem on 13/04/17.
 */

public class pokemon {
    private int _nationalId;
    private int color;
    private String name;
    private String url;
    private ArrayList<names> names;



    public String getName() {
        return name;
    }

    public void set_name(String _name) {
        this.name = _name;
    }

    public String getUrl() {
        return url;
    }

    public void set_url(String _url) {
        this.url = _url;
    }

    public int get_nationalId() {
        String[] ulrSplit = url.split("/");
        return Integer.parseInt(ulrSplit[ulrSplit.length - 1]);
    }

    public void set_nationalId(int _nationalId) {
        this._nationalId = _nationalId;
    }

    public ArrayList<com.example.clem.androiduniver.models.names> getNames() {
        return names;
    }

    public void setNames(ArrayList<com.example.clem.androiduniver.models.names> names) {
        this.names = names;
    }
}
