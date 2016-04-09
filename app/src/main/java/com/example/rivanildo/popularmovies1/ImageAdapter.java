package com.example.rivanildo.popularmovies1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Rivanildo on 07/04/16.
 */

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private String [] imagens;

    public ImageAdapter(Context context, String[] imagens) {
        this.context = context;
        this.imagens = imagens;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public Object getItem(int position) {
        return imagens[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.adapterimage,parent, false);

        ImageView img = (ImageView)view.findViewById(R.id.img);
        Picasso.with(context).load(imagens[position]).into(img);
        //img.setImageResource(imagens[position]);
        return view;
    }
}
