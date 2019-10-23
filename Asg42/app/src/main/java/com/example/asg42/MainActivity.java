package com.example.asg42;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText userName, comment;
    Button btnSubmit;
    TextView commentsView;
    ArrayList<String> comments = new ArrayList();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        comment = findViewById(R.id.comment);
        btnSubmit = findViewById(R.id.buttonSubmit);
        commentsView = findViewById(R.id.comments);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean feedback = false;
                if (userName.getText().length() == 0) {
                    userName.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (comment.getText().length() == 0) {
                    comment.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (feedback)
                    Toast.makeText(getBaseContext(), "Missing data!", Toast.LENGTH_SHORT).show();
                else {
                    userName.setBackgroundColor(android.R.drawable.edit_text);
                    comment.setBackgroundColor(android.R.drawable.edit_text);
                    count++;
                    addComments(userName.getText().toString(), comment.getText().toString());
                    String viewComments = fetchComments();
                    commentsView.setText(viewComments);
                }
            }
        });
    }
    void addComments(String userName, String comment) {
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = s.format(new Date());
        String commentNew = count + "\t\t " + format + " \t\t " + userName + " \t\t " + comment;
        this.comments.add(commentNew);
    }

    String fetchComments() {
        String str="";
        for (int i=0; i <comments.size(); i++) {
            str += comments.get(i) + "\n";
        }
        return str;
    }
}

