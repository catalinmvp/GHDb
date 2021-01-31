package com.example.ghdb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        Bundle data = i.getExtras();
        final GameModelClass model = (GameModelClass) data.getSerializable("gameCards");

        TextView title = findViewById(R.id.GameTitle);
        TextView id = findViewById(R.id.GameID);
        ImageView img = findViewById(R.id.GameImg);
        TextView genre = findViewById(R.id.Genre);
        TextView genre2 = findViewById(R.id.Genre2);
        TextView prize = findViewById(R.id.Prize);
        TextView review = findViewById(R.id.Review);
        TextView release = findViewById(R.id.ReleaseDate);

        title.setText(model.getName());
        id.setText(model.getId());
        genre.setText(model.getGenre());
        genre2.setText(model.getGenre2());
        prize.setText(model.getPrize());
        review.setText(model.getReview());
        release.setText(model.getReleaseDate());

        Glide.with(Game.this).load(model.getImg()).into(img);



         findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getApplicationContext(),"works",Toast.LENGTH_SHORT).show();
               gotoUrl(model.getLink());
           }
       });
        RatingBar ratingBar = findViewById(R.id.ratingBar2);
        ratingBar.setNumStars(5);
       ratingBar.setRating(model.getNumar());


    }

    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
