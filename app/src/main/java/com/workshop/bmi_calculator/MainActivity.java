package com.workshop.bmi_calculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import static com.workshop.bmi_calculator.R.menu.bmi_chart;


public class MainActivity extends AppCompatActivity {

    private GoogleApiClient client;

    //declaring variables
    public String weight, height, status, gender;
    public int wc,hc;
    public double weight_1, height_1,height_2,factor,factor_1;

    //declaring instances of edit views and text views
    EditText wt, ht;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing all the edit views and text views
        wt = (EditText) findViewById(R.id.weight);
        ht = (EditText) findViewById(R.id.height);
        tv1 = (TextView) findViewById(R.id.disp_weight);
        tv2 = (TextView) findViewById(R.id.disp_height);
        tv3 = (TextView) findViewById(R.id.disp_bmi);
        tv4 = (TextView) findViewById(R.id.disp_status);
        tv5 = (TextView) findViewById(R.id.disp_weight1);
        tv6 = (TextView) findViewById(R.id.disp_height1);

        //declaring instances of radio groups
        RadioGroup radioGroup_1 = (RadioGroup) findViewById(R.id.radiogroup_1);
        RadioGroup radioGroup_2 = (RadioGroup) findViewById(R.id.radiogroup_2);
        RadioGroup radioGroup_3 = (RadioGroup) findViewById(R.id.radiogroup_3);

        //setting the click event on first set of radio buttons
        radioGroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //performing action on click event
                switch(checkedId)
                {
                    case R.id.male:
                        gender="male";
                        break;

                    case R.id.female:
                        gender="female";
                        break;
                }
            }
        });

        radioGroup_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                {

                    //performing action on click event
                    switch (checkedId) {
                        case R.id.kgs:
                            factor = 1.0;
                            wc=0;
                            break;

                        case R.id.pounds:
                            factor = 0.45;
                            wc=1;
                            break;
                    }
                }
            }
        });

        radioGroup_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //performing action on click event
                switch (checkedId) {
                    case R.id.cms:
                        factor_1 = 1.0;
                        hc=0;
                        break;

                    case R.id.feet:
                        factor_1 = 0.3;
                        hc=1;
                        break;
                }

            }
        });


    }

    public void Click_me(View view) {

        //to take input from user and convert it into string type
        weight = wt.getText().toString();
        height = ht.getText().toString();

        //function to handle null entries in edit text
        if (TextUtils.isEmpty(weight)) {

            /* to handle null entry in edit text
           Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
             return; */

            //another way to handle null entry in edit text
            wt.setError("Please enter valid weight");
            return;
        }

        if (TextUtils.isEmpty(height)) {

            /* to handle null entry in edit text
            Toast.makeText(this, "Please enter valid height", Toast.LENGTH_SHORT).show();
            return; */

            //another way to handle null entry in edit text
            ht.setError("Please enter valid height");
            return;
        }

        //converting the inputted string into double type
        weight_1 = Double.parseDouble(weight) * factor;
        height_1 = (Double.parseDouble(height) / 100) * factor_1;
        height_2 = Double.parseDouble(height) * factor_1;

        //another way to convert string type value into float type
        //weight_1=Float.valueOf(weight);


        Intent intent = new Intent(MainActivity.this, Second_Activity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("WC", wc);
        bundle.putInt("HC", hc);
        bundle.putString("Weight",weight);
        bundle.putString("Height",height);
        bundle.putDouble("Weight_1", weight_1);
        bundle.putDouble("Height_1", height_1);
        bundle.putDouble("Height_2", height_2);

            //to align the text in center in java file
            // tv4.setGravity(Gravity.CENTER);
            intent.putExtras(bundle);
            startActivity(intent);
        }


    //to handle what will happen when back button is pressed
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {

            exitByBackKey();
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

            //setting up a dialog box in application
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("EXIT");
            builder.setMessage("ARE YOU SURE?");
            builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            builder.show();

        }

    //to create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(bmi_chart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_1:
                Intent  i = new Intent(MainActivity.this,BMI_Chart.class);
                startActivity(i);
                   break;

            case R.id.item_2:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nutritionstripped.com/underweight-nutrition-tips-gaining-weight/"));
                startActivity(i);
                break;

            case R.id.item_3:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodybuilding.com/fun/topicoftheweek92.htm"));
                startActivity(i);
                break;

            case R.id.item_4:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.livestrong.com/article/304859-diet-plans-for-obese-people/"));
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

