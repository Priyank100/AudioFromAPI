package com.priyank.user.audioapi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> data;
    RecyclerView recyclerView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        getData();

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("audio_api.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void getData() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray array = obj.getJSONArray("results");

            for (int i=0; i<array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String trackNameModel = jsonObject.getString("trackName");
                String imgUrlModel = jsonObject.getString("artworkUrl100");
                String audioUrlModel = jsonObject.getString("previewUrl");
                String collectionNameModel = jsonObject.getString("collectionName");

                Model model = new Model(trackNameModel, imgUrlModel, audioUrlModel, collectionNameModel);
                data.add(model);

                dialog.dismiss();

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                RecyclerAdapter mAdapter = new RecyclerAdapter(MainActivity.this, data);
                recyclerView.setAdapter(mAdapter);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
