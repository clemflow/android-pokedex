package com.example.clem.androiduniver;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.clem.androiduniver.models.flavor_text_entries;
import com.example.clem.androiduniver.pokeService.pokeApiService;
import com.example.clem.androiduniver.models.PokemonInfoRequest;
import com.example.clem.androiduniver.models.names;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by clem on 14/04/17.
 */

public class poke_detail extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    TextView pokeName;
    TextView pokeDescrip;
    ImageView imgPoke;
    ImageButton shareButton;
    ImageButton shareButtonFB;
    ImageButton shareButtonMess;
    int national_id;
    private ArrayList<flavor_text_entries> listDescription;
    private ArrayList<names> listNames;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poke_detail);

        retrofit = new Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Bundle b = getIntent().getExtras();
        national_id = b.getInt("id");

        getinfosByIdPokemon();
        imgPoke = (ImageView) findViewById(R.id.ImgPoke);
        shareButton = (ImageButton) findViewById(R.id.imageButtonTweet);
        shareButtonFB = (ImageButton) findViewById(R.id.imageButtonFb);
        shareButtonMess = (ImageButton) findViewById(R.id.imageButtonShare);
        pokeName = (TextView) findViewById(R.id.pokeName);
        pokeDescrip = (TextView) findViewById(R.id.pokeDescription);

        initShareButtonTweeter(shareButton);
        initShareButtonFb(shareButtonFB);
        initShareButtonMess(shareButtonMess);

    }

    private void initShareButtonMess(ImageButton shareButton) {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBodyContent = pokeName.getText() + " : " + pokeDescrip.getText();
                String shareSub = "Description : " + pokeName.getText();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyContent);
                startActivity(shareIntent);
            }
        });
    }

    private void initShareButtonTweeter(ImageButton shareButton) {
        shareButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String tweetUrl = String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                        urlEncode("pokemon : " + pokeName.getText() + ". " + pokeDescrip.getText()),
                        urlEncode("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                                + national_id +".png"));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

                List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                        intent.setPackage(info.activityInfo.packageName);
                    }
                }
                startActivity(intent);
            }
        });
    }

    private void initShareButtonFb(ImageButton shareButton) {
        shareButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String tweetUrl = String.format("https://www.facebook.com/sharer/sharer.php?u=%s",
                        urlEncode("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                                + national_id +".png"));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

                List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
                        intent.setPackage(info.activityInfo.packageName);
                    }
                }
                startActivity(intent);
            }
        });
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            Log.e(TAG, "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }

    private void setImage() {
        Glide.with(imgPoke.getContext())
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                        + national_id +".png")
                .centerCrop()
                .crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imgPoke);
    }

    private void getinfosByIdPokemon() {
        pokeApiService service = retrofit.create(pokeApiService.class);
        Call<PokemonInfoRequest> PokemonByIfoRequest = service.getPokemonInfos(national_id);
        PokemonByIfoRequest.enqueue(new Callback<PokemonInfoRequest>() {
            @Override
            public void onResponse(Call<PokemonInfoRequest> call, Response<PokemonInfoRequest> response) {
                if (response.isSuccessful())
                {
                    PokemonInfoRequest PokemonInfoRequest = response.body();
                    listNames = PokemonInfoRequest.getNames();
                    listDescription = PokemonInfoRequest.getFlavor_text_entries();

                    pokeName.setText(listNames.get(0).getName());
                    findDesciptionBtLanguage( listNames.get(0).getLanguage().getName());
                    setImage();
                }
                else
                {
                    Log.i(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PokemonInfoRequest> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void findDesciptionBtLanguage(String name) {
        for (int i = 0; i < listDescription.size(); i++){
            if (listDescription.get(i).getLanguage().getName().equals(name)){
                pokeDescrip.setText(listDescription.get(i).getFlavor_text());
                i = listDescription.size();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.langue_en:
                Toast.makeText(poke_detail.this, "The texts are now in English", Toast.LENGTH_SHORT).show();
                setTextLanguage("en");
                return true;
            case R.id.langue_fr:
                Toast.makeText(poke_detail.this, "Les textes sont maintenant en Français", Toast.LENGTH_SHORT).show();
                setTextLanguage("fr");
                return true;
            case R.id.langue_de:
                Toast.makeText(poke_detail.this, "Die Texte sind jetzt in Deutsch", Toast.LENGTH_SHORT).show();
                setTextLanguage("de");
                return true;
            case R.id.langue_it:
                Toast.makeText(poke_detail.this, "I testi sono ora in Italiano", Toast.LENGTH_SHORT).show();
                setTextLanguage("it");
                return true;
            case R.id.langue_es:
                Toast.makeText(poke_detail.this, "Los textos están ahora en Español", Toast.LENGTH_SHORT).show();
                setTextLanguage("es");
                return true;
            case R.id.langue_ja:
                Toast.makeText(poke_detail.this, "テキストは現在日本にあります", Toast.LENGTH_SHORT).show();
                setTextLanguage("ja");
                return true;
            case R.id.langue_co:
                Toast.makeText(poke_detail.this, "텍스트는 현재 한국어로되어 있습니다", Toast.LENGTH_SHORT).show();
                setTextLanguage("ko");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTextLanguage(String language) {
        for (int i = 0; i < listNames.size(); i++){
            if (listNames.get(i).getLanguage().getName().equals(language)){
                pokeName.setText(listNames.get(i).getName());
                findDesciptionBtLanguage( listNames.get(i).getLanguage().getName());
                i = listNames.size();
            }
        }
    }
}
