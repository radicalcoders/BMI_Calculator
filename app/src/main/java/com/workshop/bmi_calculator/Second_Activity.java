package com.workshop.bmi_calculator;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


public class Second_Activity extends MainActivity {

    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    public double bmi, weight_1, height_1, height_2, weight, height;
    public String status;
    public int wc, hc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);


        tv1 = (TextView) findViewById(R.id.disp_weight);
        tv2 = (TextView) findViewById(R.id.disp_weight1);
        tv3 = (TextView) findViewById(R.id.disp_height);
        tv4 = (TextView) findViewById(R.id.disp_height1);
        tv5 = (TextView) findViewById(R.id.disp_bmi);
        tv6 = (TextView) findViewById(R.id.disp_status);

        Bundle bundle = getIntent().getExtras();

        weight = Double.parseDouble(bundle.getString("Weight"));
        height = Double.parseDouble(bundle.getString("Height"));
        weight_1 = bundle.getDouble("Weight_1");
        height_1 = bundle.getDouble("Height_1");
        height_2 = bundle.getDouble("Height_2");
        wc = bundle.getInt("WC");
        hc = bundle.getInt("HC");

        if (wc == 0) {
            tv1.setText("Your weight is: " + weight + " kgs");
            tv2.setText("Your equivalent weight in pounds is: " + String.format("%.2f", (weight_1 * 2.20)) + " pounds");
        } else {
            tv1.setText("Your weight is: " + weight + " pounds");
            tv2.setText("Your equivalent weight in kilograms is: " + String.format("%.2f", weight_1) + " kgs");
        }
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);


        if (hc == 0) {
            tv3.setText("Your height is: " + height + " cms");
            tv4.setText("Your equivalent height in feet: " + String.format("%.2f", (height_1 * 3.25)) + " feet");
        } else {
            tv3.setText("Your height is: " + height + " feet");
            tv4.setText("Your equivalent height in centimetres: " + String.format("%.2f", (height_2 * 100)) + " cms");
        }

        tv3.setVisibility(View.VISIBLE);
        tv4.setVisibility(View.VISIBLE);

        //calculating bmi
        if (hc == 0) {
            bmi = weight_1 / (height_1 * height_1);
        }

        if (hc == 1) {
            bmi = weight_1 / (height_2 * height_2);
        }

        tv5.setText("Your BMI value is: " + String.format("%.2f", bmi));
        tv5.setVisibility(View.VISIBLE);

        //displaying status as per gender
        if (bmi < 18.5)
            status = "overweight";

        else if (bmi >= 18.5 && bmi < 25)
            status = "healthy";

        else if (bmi >= 25 && bmi < 30)
            status = "overweight";

        else
            status = "obese";

        tv6.setText("Your status is: " + status);
        tv6.setVisibility(View.VISIBLE);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {

            exitByBackKey();
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

     public void exitByBackKey()
    {
        finish();
    }


}



