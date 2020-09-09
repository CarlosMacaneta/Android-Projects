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
            double value1 = Double.parseDouble(dados[0]);
            double value2 = Double.parseDouble(dados[1]);

            editNum.setText(String.valueOf(value1 + value2));
        } else if(editNum.getText().toString().contains("-")){
            String[] dados = editNum.getText().toString().split("-");
            double value1 = Double.parseDouble(dados[0]);
            double value2 = Double.parseDouble(dados[1]);

            editNum.setText(String.valueOf(value1 - value2));
        } else if(editNum.getText().toString().contains("×")|| editNum.getText().toString().contains("*")){
            String[] dados = editNum.getText().toString().split("×");
            double value1 = Double.parseDouble(dados[0]);
            double value2 = Double.parseDouble(dados[1]);

            editNum.setText(String.valueOf(value1 * value2));
        } else if(editNum.getText().toString().contains("÷")|| editNum.getText().toString().contains("/")){
            String[] dados = editNum.getText().toString().split("÷");
            double value1 = Double.parseDouble(dados[0]);
            double value2 = Double.parseDouble(dados[1]);

            editNum.setText(String.valueOf(value1 / value2));
        }
    }
}