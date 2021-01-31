package com.example.ghdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


   // private static String Json_URL = "https://run.mocky.io/v3/4aafda61-78d8-4ffd-854e-aa46fe7e8e09";
    private static String Json_URL = "https://run.mocky.io/v3/4ca7349c-2fcd-420b-baf6-3a0d7002623d";
    List<GameModelClass> gameList;
    RecyclerView recyclerView;
    private Adaptery adaptery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetData getData = new GetData();
        getData.execute();

        gameList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

    }



    public class GetData extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(Json_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }

                    return current;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("gamecards");


                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    GameModelClass model = new GameModelClass();

                    model.setId(jsonObject1.getString("id"));
                    model.setName(jsonObject1.getString("name"));
                    model.setImg(jsonObject1.getString("image"));
                    model.setGenre(jsonObject1.getString("genre"));
                    model.setPrize(jsonObject1.getString("prize"));
                    model.setGenre2(jsonObject1.getString("genre2"));
                    model.setReleaseDate(jsonObject1.getString("releaseDate"));
                    model.setReview(jsonObject1.getString("review"));
                    model.setLink(jsonObject1.getString("link"));
                    model.setNumar(jsonObject1.getString("nota"));
                    gameList.add(model);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

           PutDataIntoRecyclerView(gameList);

        }
    }

    private void PutDataIntoRecyclerView(List<GameModelClass> gameList){
        adaptery = new Adaptery(this,gameList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search name or category");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptery.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


}


