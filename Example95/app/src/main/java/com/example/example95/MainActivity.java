package com.example.example95;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText urlEditText, urlContentEditText;
    //Here we define an array of some URLs
    String[] urls = new String[]{
            "http://www.puv.fi/en/",
            "https://yle.fi/uutiset",
            "https://www.vaasa.fi/",
    };
    //Here we create a Random object
    Random random = new Random();
    DBAdapter dbAdapter = null;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new DBAdapter(getApplicationContext());

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        // Define a WebClient object to prevent the device from loading the page on the
        // device's browser in case the original page is redirected.
        webView.setWebViewClient(new Callback());
        webSettings.setBuiltInZoomControls(true);

        //Create UI components
        urlEditText = findViewById(R.id.et_url);
        Button goButton = findViewById(R.id.btn_go);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(urlEditText.getText().toString());
            }
        });

        urlEditText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Here we randomly choose a value from urls array to be set as the
                //text of urlEditText
                urlEditText.setText(urls[random.nextInt(urls.length)]);
                webView.loadUrl(urlEditText.getText().toString());
                return true;
            }
        });

        // Create some dynamic content to be displayed on the WebView object
        final String mimeType="text/html";
        final String encoding="UTF-8";
        String htmlContent="<h1>Welcome Page</h1>" +
                "<html><head>" +
                "<style> p { font-size:24;}   </style>" +
                "</head><body>" +
                "<h1>This is a welcome page created dynamically!</h1></body>";
        webView.loadData(htmlContent, mimeType, encoding);
        // Load a an external file located under assets folder of the application
        // webView.loadUrl("file:///android_asset/info.html");

        Button saveURLBtn = findViewById(R.id.btn_save);
        saveURLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.open();
                String inputUrl = urlEditText.getText().toString();
                String inputLine;
                StringBuffer html = new StringBuffer();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    URL url = new URL(inputUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setAllowUserInteraction(false);
                    conn.setInstanceFollowRedirects(true);
//                    conn.setReadTimeout(5000);
//                    boolean redirect = false;
//
//                    // normally, 3xx is redirect
//                    int status = conn.getResponseCode();
//                    if (status != HttpURLConnection.HTTP_OK) {
//                        if (status == HttpURLConnection.HTTP_MOVED_TEMP
//                                || status == HttpURLConnection.HTTP_MOVED_PERM
//                                || status == HttpURLConnection.HTTP_SEE_OTHER)
//                            redirect = true;
//                    }
//
//                    if (redirect) {
//                        // get redirect url from "location" header field
//                        String newUrl = conn.getHeaderField("Location");
//                        // get the cookie if need, for login
//                        String cookies = conn.getHeaderField("Set-Cookie");
//                        // open the new connection again
//                        conn = (HttpURLConnection) new URL(newUrl).openConnection();
//                        conn.setRequestProperty("Cookie", cookies);
//                    }

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    // httpConnection.setInstanceFollowRedirects(true);

                    while ((inputLine = in.readLine()) != null)
                        html.append(inputLine);
                    in.close();
                }
                catch(IOException ie) {
                    ie.printStackTrace();
                }
                String urlContent = html.toString();
                if (urlContent != null && !urlContent.isEmpty())
                    dbAdapter.addUrl(inputUrl ,urlContent);
                dbAdapter.close();
                Spinner urlPicker = findViewById(R.id.url_spinner);
                updateURLSpinner(urlPicker);
            }
        });

        final Spinner urlPicker = findViewById(R.id.url_spinner);
        updateURLSpinner(urlPicker);

        Button loadURLBtn = findViewById(R.id.btn_load);
        loadURLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.open();
                Cursor cursor = dbAdapter.getUrl(urlPicker.getSelectedItem().toString());
                String htmlContent = cursor.getString(1);
                Log.e("URL", htmlContent);
                webView.loadData(htmlContent, mimeType, encoding);
                dbAdapter.close();
            }
        });
    }

    // Define the Callback class
    private class Callback extends WebViewClient {

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }

    private void updateURLSpinner(Spinner urlPicker){
        ArrayList<String> allUrl = new ArrayList<String>();
        dbAdapter.open();
        Cursor cursor = dbAdapter.getAllUrl();
        if(cursor.moveToFirst()) {
            do {
                allUrl.add(cursor.getString(0));
            } while(cursor.moveToNext());
        }
        dbAdapter.close();
        ArrayAdapter urlAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, allUrl);
        urlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        urlPicker.setAdapter(urlAdapter);
    }

}
