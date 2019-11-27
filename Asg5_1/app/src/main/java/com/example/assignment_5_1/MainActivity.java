package com.example.assignment_5_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams viewLayoutParams = null;
    LinearLayout.LayoutParams newLayoutParams = null;
    int i=10;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 10;
        viewLayoutParams.bottomMargin = 10;
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        button1 = new Button(this);
        button1.setText("Button 1");
        button1.setLayoutParams(viewLayoutParams);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (button2.isShown())
                    button2.setVisibility(View.INVISIBLE);
                else
                    button2.setVisibility(View.VISIBLE);
            }});
        linearLayout.addView(button1);
        button2 = new Button(this);
        button2.setText("Button 2");
        button2.setLayoutParams(viewLayoutParams);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i+=10;
                newLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                newLayoutParams.leftMargin = 40;
                newLayoutParams.rightMargin = 40;
                newLayoutParams.topMargin = i;
                newLayoutParams.bottomMargin = 10;
                button1.setLayoutParams(newLayoutParams);
            }
        });
        linearLayout.addView(button2);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        this.addContentView(linearLayout, linearLayoutParams);
    }
}
