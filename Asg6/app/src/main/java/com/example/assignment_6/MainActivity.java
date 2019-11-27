package com.example.assignment_6;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;

import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText FirstNameEditText = null, LastNameEditText, PhoneNumberEditText, EducationEditText, HobbiesEditText;
    TextView summaryTextView = null, searchTextView = null;
    LayoutParams viewLayoutParams = null;
    Button SubmitButton, SearchButton;
    // Here we create the layout
    LinearLayout linearLayout;
    AutoCompleteTextView SearchByFirstName, SearchByLastName, SearchByPhone;
    ArrayList<Contact> phoneCatalog = new ArrayList<Contact>();
    ArrayList<String> firstNames = new ArrayList<String>();
    ArrayList<String> lastNames = new ArrayList<String>();
    ArrayList<String> phones = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter, arrayAdapter1, arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initial Some data

        Contact newContact1 = new Contact("Hoang", "Le", "123456789", "Bachelor", "abc");
        Contact newContact2 = new Contact("abc", "def", "0987654321", "HighSchool", "def");
        Contact newContact3 = new Contact("ghi", "jkl", "0123456789", "Master", "abcdefghijklmnobq");
        Contact newContact4 = new Contact("mno", "xyz", "20192020", "PhD", "ghii");

        phoneCatalog.add(newContact1);
        phoneCatalog.add(newContact2);
        phoneCatalog.add(newContact3);
        phoneCatalog.add(newContact4);

        // Here we define parameters for views
        viewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 10;
        viewLayoutParams.bottomMargin = 10;
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Here we define a text view
        TextView FirstNameTextView = new TextView(this);
        FirstNameTextView.setText("First Name");
        FirstNameTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(FirstNameTextView);

        // Here we define the edit text
        FirstNameEditText = new EditText(this);
        FirstNameEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(FirstNameEditText);
        // Here we define a text view
        TextView LastNameTextView = new TextView(this);
        LastNameTextView.setText("Last Name");
        LastNameTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(LastNameTextView);
        // Here we define the edit text
        LastNameEditText = new EditText(this);
        LastNameEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(LastNameEditText);
        // Here we define a text view
        TextView PhoneNumberTextView = new TextView(this);
        PhoneNumberTextView.setText("Phone Number");
        PhoneNumberTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(PhoneNumberTextView);
        // Here we define the edit text
        PhoneNumberEditText = new EditText(this);
        PhoneNumberEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        PhoneNumberEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(PhoneNumberEditText);
        // Here we define a text view
        TextView EducationTextView = new TextView(this);
        EducationTextView.setText("Education Level");
        EducationTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(EducationTextView);
        // Here we define the edit text
        EducationEditText = new EditText(this);
        EducationEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(EducationEditText);
        // Here we define a text view
        TextView HobbiesTextView = new TextView(this);
        HobbiesTextView.setText("Hobbies");
        HobbiesTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(HobbiesTextView);
        // Here we define the edit text
        HobbiesEditText = new EditText(this);
        HobbiesEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(HobbiesEditText);

        //Submit Button

        SubmitButton = new Button(this);
        SubmitButton.setText("Submit");
        SubmitButton.setLayoutParams(viewLayoutParams);
        SubmitButton.setOnClickListener(buttonSubmitOnClick);
        linearLayout.addView(SubmitButton);


        // Here we define a text view
        summaryTextView = new TextView(this);
        summaryTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(summaryTextView);

        TextView SearchTextView = new TextView(this);
        SearchTextView.setText("Search By First Name");
        SearchTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(SearchTextView);

        //Here we define the AutoCompleteTextView object
        SearchByFirstName = new AutoCompleteTextView(this);
        SearchByFirstName.setLayoutParams(viewLayoutParams);
        linearLayout.addView(SearchByFirstName);
        //Here we set the text color
        SearchByFirstName.setTextColor(Color.RED);
        //Here we define the required number of letters to be typed in the AutoCompleteTextView
        SearchByFirstName.setThreshold(1);

        //Here we set the array adapter for the AutoCompleteTextView
        TextView SearchTextView1 = new TextView(this);
        SearchTextView1.setText("Search By Last Name");
        SearchTextView1.setLayoutParams(viewLayoutParams);
        linearLayout.addView(SearchTextView1);

        //Here we define the AutoCompleteTextView object
        SearchByLastName = new AutoCompleteTextView(this);
        SearchByLastName.setLayoutParams(viewLayoutParams);
        linearLayout.addView(SearchByLastName);
        //Here we set the text color
        SearchByLastName.setTextColor(Color.RED);
        //Here we define the required number of letters to be typed in the AutoCompleteTextView
        SearchByLastName.setThreshold(1);
        //Here we set the array adapter for the AutoCompleteTextView

        // Here we define a text view
        TextView SearchTextView2 = new TextView(this);
        SearchTextView2.setText("Search By Phone Number");
        SearchTextView2.setLayoutParams(viewLayoutParams);
        linearLayout.addView(SearchTextView2);

        //Here we define the AutoCompleteTextView object
        SearchByPhone = new AutoCompleteTextView(this);
        SearchByPhone.setLayoutParams(viewLayoutParams);
        linearLayout.addView(SearchByPhone);
        //Here we set the text color
        SearchByPhone.setTextColor(Color.RED);
        //Here we define the required number of letters to be typed in the AutoCompleteTextView
        SearchByPhone.setThreshold(1);
        //Here we set the array adapter for the AutoCompleteTextView
        InitializingArrayAdapter();


        // Here we define a text view
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        scrollView.addView(linearLayout);
        this.addContentView(scrollView, layoutParams);
    }

    private void InitializingArrayAdapter() {
        for (Contact c : phoneCatalog) {
                firstNames.add(c.firstName + " | " + c.lastName + " | " + c.phoneNumber);
                lastNames.add(c.lastName + " | " + c.firstName + " | " + c.phoneNumber);
                phones.add(c.phoneNumber + " | " + c.firstName + " | " + c.lastName);
            };

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, firstNames );
        SearchByFirstName.setAdapter(arrayAdapter);

        arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, lastNames );
        SearchByLastName.setAdapter(arrayAdapter1);

        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, phones );
        SearchByPhone.setAdapter(arrayAdapter2);
    };

    private void updatingArrayAdapter(Contact c) {
        arrayAdapter.add(c.firstName + " | " + c.lastName + " | " + c.phoneNumber);
        arrayAdapter1.add(c.lastName + " | " + c.firstName + " | " + c.phoneNumber);
        arrayAdapter2.add(c.phoneNumber + " | " + c.firstName + " | " + c.lastName);
    };


    private OnClickListener buttonSubmitOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
                boolean feedback = false;
                if (FirstNameEditText.getText().length() == 0) {
                    FirstNameEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (LastNameEditText.getText().length() == 0) {
                    LastNameEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (PhoneNumberEditText.getText().length() == 0) {
                    PhoneNumberEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (EducationEditText.getText().length() == 0) {
                    EducationEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (HobbiesEditText.getText().length() == 0) {
                    HobbiesEditText.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (feedback)
                    Toast.makeText(getBaseContext(), "Missing data!", Toast.LENGTH_SHORT).show();
                else {
                    Contact newContact = new Contact(FirstNameEditText.getText().toString(),
                                                        LastNameEditText.getText().toString(),
                                                        PhoneNumberEditText.getText().toString(),
                                                        EducationEditText.getText().toString(),
                                                        HobbiesEditText.getText().toString());

                    phoneCatalog.add(newContact);
                    String summaryString ="";

                    for (Contact e : phoneCatalog) {
                        summaryString += e.toString() + "\n";
                    }
                    summaryTextView.setText(summaryString);
                    updatingArrayAdapter(newContact);
            };
        };
    };
}
