package com.example.shaany.reader;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.R.attr.id;


public class Post extends AppCompatActivity {
    TextView title;
    WebView content;
    ProgressDialog progressDialog;
    Gson gson;
    Map<String, Object> mapPost;
    Map<String, Object> mapContent;
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        final String id = getIntent().getExtras().getString("id");
        final String div = getIntent().getExtras().getString("div");

        title = (TextView) findViewById(R.id.title);
        content = (WebView) findViewById(R.id.content);
        content.getSettings().setJavaScriptEnabled(true);

        progressDialog = new ProgressDialog(Post.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String url = "https://bahainf.mystagingwebsite.com/wp-json/wp/v2/pages/" + id + "";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                gson = new Gson();
                mapPost = (Map<String, Object>) gson.fromJson(s, Map.class);
                mapContent = (Map<String, Object>) mapPost.get("content");
                //get the specific html to display
                selection = mapContent.toString();
                Document doc = Jsoup.parse(selection, "UTF-8");
                //if the div name is cat
                if (div.equals("cat")) {
                    Elements stuff = doc.getAllElements();
                    //display it
                    String load = stuff.toString();
                    content.loadData(load, "text/html", "UTF-8");
                    //if the div name isn't cat
                } else {
                    Element stuff = doc.getElementById(div);
                    //display it
                    String load = stuff.toString();
                    content.loadData(load, "text/html", "UTF-8");
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                Toast.makeText(Post.this, id, Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Post.this);
        rQueue.add(request);
    }
}