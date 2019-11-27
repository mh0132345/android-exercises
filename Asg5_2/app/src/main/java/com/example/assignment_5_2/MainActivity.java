package com.example.assignment_5_2;

import android.graphics.Color;
        import android.os.Bundle;
        import android.text.InputType;
        import android.text.method.PasswordTransformationMethod;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.View.OnTouchListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.LinearLayout.LayoutParams;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText userNametEditText = null;
    EditText passwordEditText = null;
    TextView summaryTextView = null;
    TextView passwordTextView;
    LayoutParams viewLayoutParams = null;
    Button continueButton, backButton;

    LinearLayout linearLayout;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Here we define parameters for views
        viewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 10;
        viewLayoutParams.bottomMargin = 10;
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Here we define a text view
        TextView userNameTextView = new TextView(this);
        userNameTextView.setText("User name");
        userNameTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(userNameTextView);
        // Here we define the edit text
        userNametEditText = new EditText(this);
        userNametEditText.setLayoutParams(viewLayoutParams);
        userNametEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((EditText) v).setBackgroundColor(Color.YELLOW);
                return false;
            }
        });
        linearLayout.addView(userNametEditText);

        // Here we define a text view
        passwordTextView = new TextView(this);
        passwordTextView.setText("Password");
        passwordTextView.setLayoutParams(viewLayoutParams);

        // Here we define the edit text
        passwordEditText = new EditText(this);
        passwordEditText.setLayoutParams(viewLayoutParams);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        // Here we hide the content of the password field
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((EditText) v).setBackgroundColor(Color.YELLOW);
                return false;
            }
        });
        continueButton = new Button(this);
        continueButton.setText("Continue");
        continueButton.setLayoutParams(viewLayoutParams);
        continueButton.setOnClickListener(buttonContinueOnClick);
        linearLayout.addView(continueButton);

        backButton = new Button(this);
        backButton.setText("Back");
        backButton.setLayoutParams(viewLayoutParams);
        backButton.setOnClickListener(buttonBackOnClick);.

        // Here we define a text view
        summaryTextView = new TextView(this);
        summaryTextView.setLayoutParams(viewLayoutParams);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        this.addContentView(linearLayout, linearLayoutParams);
    }

    private OnClickListener buttonContinueOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            i++;
            if (i == 1) {
                linearLayout.removeView(continueButton);
                linearLayout.addView(passwordTextView);
                linearLayout.addView(passwordEditText);
                linearLayout.addView(continueButton);
                linearLayout.addView(backButton);
                linearLayout.addView(summaryTextView);
            } else if (i >= 2) {
                summaryTextView.setText("");
                boolean userField = false, passwordField = false;
                if (userNametEditText.getText().length() == 0) {
                    userNametEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    userField = true;
                }
                if (passwordEditText.getText().length() == 0) {
                    passwordEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    passwordField = true;
                }
                if (userField || passwordField){
                    Toast.makeText(getBaseContext(), "Missing data!", Toast.LENGTH_SHORT).show();
                }
                else{
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    String current_data = formatter.format(date);
                    summaryTextView.setText(userNametEditText.getText().toString() + "\n" + passwordEditText.getText().toString() + "\n" + current_data );
                }
            };
        };
    };

    private OnClickListener buttonBackOnClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            linearLayout.removeAllViews();
            linearLayout.addView(userNametEditText);
            linearLayout.addView(continueButton);
            i=0;
        }
    };
}