package com.inputdata.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText editNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNum = findViewById(R.id.editNum);
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