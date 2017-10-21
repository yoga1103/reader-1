//package name change it to youre own
package com.example.yogesh.reader;

//imports
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yogesh.reader.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.R.attr.id;


//main function
public class Post extends AppCompatActivity {
    WebView content;
    ProgressDialog progressDialog;//deprecated don't use
    Gson gson;
    Map<String, Object> mapPost;
    Map<String, Object> mapContent;
    String selection;

    //onCreate
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        //these vars have to be Finals for some reason beyond my understanding
        final String id = getIntent().getExtras().getString("id");
        final String div = getIntent().getExtras().getString("div");
        final String link = getIntent().getExtras().getString("link");

        //initialise the webview
        content = (WebView) findViewById(R.id.content);
        content.getSettings().setJavaScriptEnabled(true);
        content.setWebViewClient(new WebViewClient());

        //progress spinner this is deprecated so change it
        progressDialog = new ProgressDialog(Post.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        /*
        if the page to load is the "gallery" or "calendar"
        then just load the HTML "div" we want in the webview
        TODO this does'nt work
         */
        if (div.equalsIgnoreCase("cal")) {
            Connection.Response response = null;
            content.loadUrl("https://bahainf.mystagingwebsite.com/new-calendar/");
        }

        else {
            if (div.equalsIgnoreCase("gallery")) {
                Connection.Response response = null;
                content.loadUrl("https://bahainf.mystagingwebsite.com/gallery/");

            } else {
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
                        //if there's an error report it
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(Post.this, "Oh no! an error occurred loading this page", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue rQueue = Volley.newRequestQueue(Post.this);
                rQueue.add(request);

            }
        }
    }
}