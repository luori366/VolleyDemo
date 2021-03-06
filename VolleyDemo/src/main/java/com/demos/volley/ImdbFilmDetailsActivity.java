package com.demos.volley;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.demos.volley.model.imdb.FilmDetails;
import com.demos.volley.request.GsonRequest;

public class ImdbFilmDetailsActivity extends RequestActivity {
    public static final String EXTRA_ID = "id";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getStringExtra(EXTRA_ID);
        String url = "http://www.omdbapi.com/?i=" + id;

        GsonRequest<FilmDetails> request = new GsonRequest<FilmDetails>(FilmDetails.class, Request.Method.GET, url,
            new Response.Listener<FilmDetails>() {
                @Override
                public void onResponse(FilmDetails response) {
                    mProgressDialog.dismiss();
                    showFilmDetails(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ImdbFilmDetailsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        );

        mProgressDialog.show();
        mRequestQueue.add(request);
    }

    private void showFilmDetails(FilmDetails details) {
        setContentView(R.layout.activity_film_details);

        TextView titleView = (TextView) findViewById(R.id.title);
        TextView yearView = (TextView) findViewById(R.id.year);
        TextView summaryView = (TextView) findViewById(R.id.summary);
        TextView actorsView = (TextView) findViewById(R.id.actors);
        TextView genreView = (TextView) findViewById(R.id.genre);
        TextView imdbRatingView = (TextView) findViewById(R.id.rating);

        titleView.setText(details.getTitle());
        yearView.setText(String.valueOf(details.getYear()));
        summaryView.setText(details.getSummary());
        actorsView.setText(details.getActors());
        genreView.setText(details.getGenre());
        imdbRatingView.setText(String.valueOf(details.getRating()));
    }
}