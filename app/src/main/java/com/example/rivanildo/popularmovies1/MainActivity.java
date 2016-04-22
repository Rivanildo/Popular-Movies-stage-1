package com.example.rivanildo.popularmovies1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private String[] getLinks(List<Movie> movies) {
        String[] links = new String[movies.size()];
        int x = 0;
        for (Movie f: movies) {
            links[x] = f.getLinkImg();
            x += 1;
        }
        return links;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
    //ok
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        List<Movie> movies = new ArrayList<Movie>();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String type = sp.getString("key", " ");

        Log.e(type, "olhaaaaaa");

        try {
            movies = new FetchMovieList(type).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        GridView grid = (GridView) findViewById(R.id.gridView);

        final List<Movie> finalMovies = movies;
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), DetailActivity.class);
                Bundle b = new Bundle();

                Movie m = finalMovies.get(position);
                b.putString("link", m.getLinkImg());
                b.putString("sinopse", m.getSinopse());
                b.putString("dataRelease", m.getDataRelease());
                b.putString("tituloOriginal", m.getTituloOriginal());
                b.putString("avaliacao", m.getAvaliacao());
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        });

        String[] links = getLinks(movies);
        ImageAdapter imageAdapter = new ImageAdapter(this, links);
        grid.setAdapter(imageAdapter);
       super.onResume();
    }
}
