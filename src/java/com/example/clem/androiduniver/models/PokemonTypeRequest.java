package com.example.clem.androiduniver.models;

import java.util.ArrayList;

/**
 * Created by clem on 13/04/17.
 */

public class PokemonTypeRequest {
    private ArrayList<type> pokemon;

    public ArrayList<type> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<type> results) {
        this.pokemon = results;
    }
}
