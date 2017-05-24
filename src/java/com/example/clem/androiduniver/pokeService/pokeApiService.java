package com.example.clem.androiduniver.pokeService;

import com.example.clem.androiduniver.models.PokemonInfoRequest;
import com.example.clem.androiduniver.models.PokemonTypeRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by clem on 13/04/17.
 */

public interface pokeApiService {

    @GET("type/{id}/")
    Call<PokemonTypeRequest> getAllPokemonByType(@Path("id") int Id);

    @GET("pokemon-species/{id}/")
    Call<PokemonInfoRequest> getPokemonInfos(@Path("id") int Id);

}
