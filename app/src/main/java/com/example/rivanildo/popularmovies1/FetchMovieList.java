package com.example.rivanildo.popularmovies1;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Settings.Global.getString;

/**
 * Created by Rivanildo on 10/04/16.
 */
public class FetchMovieList extends AsyncTask<String, Void, List<Movie>> {

    private static final String API_KEY = "edc70511df1429e113d63565041dbd15" ;
    private final String LOG_TAG = FetchMovieList.class.getSimpleName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // Problemas para recuperar aqui
    //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
    //String type = sp.getString(SettingsActivity);

    @Override

    protected List<Movie> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader leitor = null;
        String json = null;

        try {
            //URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=edc70511df1429e113d63565041dbd15");

            Uri.Builder builder = createMovieListFetchURI("popular"); // Lembrar aqui
            URL url = new URL(builder.build().toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }

            leitor = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = leitor.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            json = buffer.toString();

            return getJson(json);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (leitor != null) {
                try {
                    leitor.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return null;
    }


    protected void onPostExecute(List<Movie> result) {
        super.onPostExecute(result);
    }


    private List<Movie> getJson(String jsonStr) throws JSONException {
        JSONObject json = new JSONObject(jsonStr);
        JSONArray jsonArray = json.getJSONArray("results");

        List<Movie> listMovie = new ArrayList<Movie>();
        Movie movie;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject f = jsonArray.getJSONObject(i);
            movie = new Movie("http://image.tmdb.org/t/p/w185" + f.getString("poster_path"), f.getString("overview"), f.getString("release_date"), f.getString("original_title"), f.getString("vote_average"));
            listMovie.add(movie);
            // Log.e("lol", filme.toString());
        }
        return listMovie;
    }

    private Uri.Builder createMovieListFetchURI(String tipe){
        //http://api.themoviedb.org/3/movie/top_rated?api_key=aaaaaapppppiiiiiikeeeeeyy
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(tipe)
                .appendQueryParameter("api_key", API_KEY);

        Log.v(LOG_TAG, "Uri movie list fetch: "+builder.toString());

        return builder;
    }


    public Context getActivity() {
        return getActivity();
    }
}

