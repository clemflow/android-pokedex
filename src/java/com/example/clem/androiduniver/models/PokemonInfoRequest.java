package com.example.clem.androiduniver.models;

import java.util.ArrayList;

/**
 * Created by clem on 14/04/17.
 */

public class PokemonInfoRequest {
    private ArrayList<names> names;
    private ArrayList<flavor_text_entries> flavor_text_entries;

    public ArrayList<com.example.clem.androiduniver.models.names> getNames() {
        return names;
    }

    public void setNames(ArrayList<com.example.clem.androiduniver.models.names> names) {
        this.names = names;
    }

    public ArrayList<com.example.clem.androiduniver.models.flavor_text_entries> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(ArrayList<com.example.clem.androiduniver.models.flavor_text_entries> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }
}
