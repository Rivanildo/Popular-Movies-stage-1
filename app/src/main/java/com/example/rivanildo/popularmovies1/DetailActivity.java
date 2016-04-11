package com.example.rivanildo.popularmovies1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mAvaliacao;
    private TextView mData;
    private TextView mSinopse;
    private ImageView mImagem;

    private String linkImg;
    private String sinopse;
    private String dataRelease;
    private String tituloOriginal;
    private String avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();

        mAvaliacao = (TextView)findViewById(R.id.rating_text_view);
        mData = (TextView) findViewById(R.id.date_text_view);
        mSinopse = (TextView) findViewById(R.id.overview_text_view);
        mImagem = (ImageView) findViewById(R.id.poster_image_view);

        linkImg = b.getString("link");
        sinopse = b.getString("sinopse");
        dataRelease = b.getString("dataRelease");
        tituloOriginal = b.getString("tituloOriginal");
        avaliacao = b.getString("avaliacao");

        Picasso.with(getBaseContext()).load(linkImg).into(mImagem);
        //mTituloOriginal.setText("Titulo Original: " + tituloOriginal);
        mAvaliacao.setText("avaliacao: " + avaliacao);
        mData.setText("Data Release: " + dataRelease);
        mSinopse.setText("Sinopse: " + sinopse);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(tituloOriginal);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}