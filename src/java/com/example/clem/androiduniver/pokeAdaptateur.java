package com.example.clem.androiduniver;
/**
 * Created by clem on 17/04/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.clem.androiduniver.models.type;

import java.util.List;


public class pokeAdaptateur extends ArrayAdapter<type> {

    Context _context;

    public pokeAdaptateur(Context context, List<type> listPokeType) {
        super(context, 0, listPokeType);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_poke,parent, false);
        }

        PokeViewHolder viewHolder = (PokeViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PokeViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        type type = getItem(position);
        viewHolder.pseudo.setText(type.getPokemon().getName());
        viewHolder.text.setText("id : " + type.getPokemon().get_nationalId());

        Glide.with(getContext())
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                        + type.getPokemon().get_nationalId() +".png")
                .centerCrop()
                .crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.avatar);

        return convertView;
    }

    private class PokeViewHolder{
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;

    }
}
