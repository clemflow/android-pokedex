package com.example.clem.androiduniver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton norm = (ImageButton) findViewById(R.id.imageButtonNormal);
        ImageButton fire = (ImageButton) findViewById(R.id.imageButtonFire);
        ImageButton water = (ImageButton) findViewById(R.id.imageButtonWatter);
        ImageButton elect = (ImageButton) findViewById(R.id.imageButtonElectric);
        ImageButton grass = (ImageButton) findViewById(R.id.imageButtonGrass);
        ImageButton ice = (ImageButton) findViewById(R.id.imageButtonIce);
        ImageButton fight = (ImageButton) findViewById(R.id.imageButtonFight);
        ImageButton steel = (ImageButton) findViewById(R.id.imageButtonSteel);
        ImageButton bug = (ImageButton) findViewById(R.id.imageButtonBug);
        ImageButton dragon = (ImageButton) findViewById(R.id.imageButtonDragon);
        ImageButton fee = (ImageButton) findViewById(R.id.imageButtonfee);
        ImageButton poison = (ImageButton) findViewById(R.id.imageButtonPoison);
        ImageButton psy = (ImageButton) findViewById(R.id.imageButtonPsy);
        ImageButton ground = (ImageButton) findViewById(R.id.imageButtonGround);
        ImageButton specter = (ImageButton) findViewById(R.id.imageButtonSpecter);
        ImageButton dark = (ImageButton) findViewById(R.id.imageButtonDark);
        ImageButton fly = (ImageButton) findViewById(R.id.imageButtonFlight);
        ImageButton roche = (ImageButton) findViewById(R.id.imageButtonRock);

        generateDirection(norm, 1);
        generateDirection(fire, 10);
        generateDirection(water, 11);
        generateDirection(elect, 13);
        generateDirection(grass, 12);
        generateDirection(steel, 9);
        generateDirection(fight, 2);
        generateDirection(bug, 7);
        generateDirection(dragon, 16);
        generateDirection(fee, 18);
        generateDirection(poison, 4);
        generateDirection(psy, 14);
        generateDirection(ground, 5);
        generateDirection(specter, 8);
        generateDirection(dark, 17);
        generateDirection(fly, 3);
        generateDirection(ice, 15);
        generateDirection(roche, 6);
    }

    private void generateDirection(ImageButton button, final int Id)
    {
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, poke_list.class);
                 Bundle b = new Bundle();
                 b.putInt("key", Id);
                 intent.putExtras(b);
                 startActivity(intent);
                 finish();
             }
         });
    }
}
