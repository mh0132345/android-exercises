package com.example.asg9_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText urlEditText, textToSearch;
    TextView resultTextView;
    String urlContent;
    int occurrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlEditText = findViewById(R.id.et_url);
        textToSearch = findViewById(R.id.et_search);
        resultTextView = findViewById(R.id.result);
        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    // httpConnection.setInstanceFollowRedirects(true);

                    while ((inputLine = in.readLine()) != null)
                        html.append(inputLine);
                    in.close();
                }
                catch(IOException ie) {
                    ie.printStackTrace();
                }
                urlContent = html.toString();
                String inputTextToSearch = textToSearch.getText().toString();
                String urlContentWithoutHTML = Html.fromHtml(urlContent,
                        Html.FROM_HTML_MODE_LEGACY).toString();
                occurrences = countOccurrences(urlContent, inputTextToSearch);
                String result = inputTextToSearch + " appear " + occurrences + " times!\n";
                result += urlContentWithoutHTML;
                resultTextView.setText(result);
            }
        });
    }

    static int countOccurrences(String str, String word)
    {
        String a[] = str.split(" ");
        int count = 0;
        for (int i = 0; i < a.length; i++)
        {
            if (word.equals(a[i]))
                count++;
        }
        return count;
    }
}
