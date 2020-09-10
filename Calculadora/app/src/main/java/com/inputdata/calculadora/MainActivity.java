package com.inputdata.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText editNum;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnDot;
    private Button btnSum;
    private Button btnMinus;
    private Button btnDiv;
    private Button btnMult;
    private Button btnClean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNum = findViewById(R.id.editNum);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDot = findViewById(R.id.btnDot);
        btnSum = findViewById(R.id.btnSum);
        btnMinus = findViewById(R.id.btnMinus);
        btnDiv = findViewById(R.id.btnDiv);
        btnMult = findViewById(R.id.btnMult);
        btnClean = findViewById(R.id.btnClean);
    }

    public void buttonPressed(View v){

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNum.setText(null);
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn0.getText().toString();
                editNum.setText(text);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn1.getText().toString();
                editNum.setText(text);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn2.getText().toString();
                editNum.setText(text);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn3.getText().toString();
                editNum.setText(text);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn4.getText().toString();
                editNum.setText(text);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn5.getText().toString();
                editNum.setText(text);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn6.getText().toString();
                editNum.setText(text);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn7.getText().toString();
                editNum.setText(text);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn8.getText().toString();
                editNum.setText(text);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btn9.getText().toString();
                editNum.setText(text);
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btnSum.getText().toString();
                editNum.setText(text);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btnMinus.getText().toString();
                editNum.setText(text);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btnDiv.getText().toString();
                editNum.setText(text);
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btnMult.getText().toString();
                editNum.setText(text);
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editNum.getText().toString()+btnDot.getText().toString();
                editNum.setText(text);
            }
        });
    }

    public void solve(View v){

        if(editNum.getText().toString().contains("+")) {
            String[] dados = editNum.getText().toString().split("[+]");
            double total = 0;

            for(String num: dados){
                total += Double.parseDouble(num);
            }

            editNum.setText(String.valueOf(total));
        } else if(editNum.getText().toString().contains("-")){
            String[] nums = editNum.getText().toString().split("-");
            double total = Double.parseDouble(nums[0]);

            for (int i = 1; i < nums.length; i++){
                total -= Double.parseDouble(nums[i]);
            }

            editNum.setText(String.valueOf(total));
        } else if(editNum.getText().toString().contains("×")|| editNum.getText().toString().contains("*")){

            String[] nums;

            if (editNum.getText().toString().contains("×")) {
                nums = editNum.getText().toString().split("×");
            }else {
                nums = editNum.getText().toString().split("[*]");
            }

            double total_ = 1;

            for(String num: nums){
                total_ *= Double.parseDouble(num);
            }

            editNum.setText(String.valueOf(total_));
        } else if(editNum.getText().toString().contains("÷")|| editNum.getText().toString().contains("/")){
            String[] nums;

            if (editNum.getText().toString().contains("×")) {
                nums = editNum.getText().toString().split("÷");
            }else {
                nums = editNum.getText().toString().split("/");
            }
            double total = Double.parseDouble(nums[0]);

            for (int i = 1; i < nums.length; i++){
                total /= Double.parseDouble(nums[i]);
            }

            editNum.setText(String.valueOf(total));
        }
    }
}