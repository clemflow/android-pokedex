package com.example.clem.androiduniver;

/**
 * Created by clem on 13/04/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.clem.androiduniver.models.PokemonTypeRequest;
import com.example.clem.androiduniver.models.type;
import com.example.clem.androiduniver.pokeService.pokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class poke_list extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private ArrayList<type> listPoke;
    ListView mListView;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poke_list);

        mListView = (ListView) findViewById(R.id.listView);

        retrofit = new Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        getDatasByType();
    }


    //// FONCTIONS generate viewlist !
    private void afficherListePokemon(List<type> type){
        pokeAdaptateur adapter = new pokeAdaptateur(poke_list.this, type);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, final int position,
                                    long id) {

                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(poke_list.this, poke_detail.class);
                        Bundle b = new Bundle();
                        b.putInt("id", listPoke.get(position).getPokemon().get_nationalId());
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }


    //// FONCTIONS REQUETE !
    private void getDatasByType() {
        Bundle b = getIntent().getExtras();

        pokeApiService service = retrofit.create(pokeApiService.class);
        Call<PokemonTypeRequest> PokemonByTypeRequest = service.getAllPokemonByType(b.getInt("key"));
        PokemonByTypeRequest.enqueue(new Callback<PokemonTypeRequest>() {
            @Override
            public void onResponse(Call<PokemonTypeRequest> call, Response<PokemonTypeRequest> response) {
                if (response.isSuccessful())
                {
                    PokemonTypeRequest pokemonTypeRequest = response.body();
                    listPoke = pokemonTypeRequest.getPokemon();
                    afficherListePokemon(listPoke);
                }
                else{
                    Log.e(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PokemonTypeRequest> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
