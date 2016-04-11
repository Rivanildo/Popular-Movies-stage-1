package com.example.rivanildo.popularmovies1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mTituloOriginal;
    private TextView mTitulo;
    private TextView mIdioma;
    private TextView mData;
    private TextView mSinopse;
    private ImageView mImagem;

    private String linkImg;
    private String sinopse;
    private String dataRelease;
    private String tituloOriginal;
    private String idioma;
    private String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle b = getIntent().getExtras();

        mTituloOriginal = (TextView) findViewById(R.id.tituloOriginal);
        mTitulo = (TextView) findViewById(R.id.titulo);
        mIdioma = (TextView) findViewById(R.id.idioma);
        mData = (TextView) findViewById(R.id.date_text_view);
        mSinopse = (TextView) findViewById(R.id.main_info);
        mImagem = (ImageView) findViewById(R.id.poster_image_view);

        linkImg = b.getString("link");
        sinopse = b.getString("sinopse");
        dataRelease = b.getString("dataRelease");
        tituloOriginal = b.getString("tituloOriginal");
        idioma = b.getString("idioma");
        titulo = b.getString("titulo");

        Picasso.with(getBaseContext()).load(linkImg).into(mImagem);
        mTituloOriginal.setText("Titulo Original: " + tituloOriginal);
        mTitulo.setText("Titulo: " + titulo);
        mIdioma.setText("Idioma: " + idioma);
        mData.setText("Data Release: " + dataRelease);
        mSinopse.setText("Sinopse: " + sinopse);
    }

}
