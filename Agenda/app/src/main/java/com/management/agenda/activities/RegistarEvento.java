package com.management.agenda.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.management.agenda.R;
import com.management.agenda.controller.DataBaseHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RegistarEvento extends AppCompatActivity {

    //private Button btSave;
    private EditText edTitle;
    private EditText edDate;
    private EditText edDesc;
    private DataBaseHelper dbh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_evento);

        dbh = new DataBaseHelper(this);

        Toolbar toolBar = findViewById(R.id.tool_bar);
        //btSave   = findViewById(R.id.btnSave);
        edTitle  = findViewById(R.id.editTextTitle);
        edDate   = findViewById(R.id.editTextDate);
        edDesc   = findViewById(R.id.editTextDesc);

        setActionBar(toolBar);
        formatDate();
    }

    private void formatDate(){
        edDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String date = edDate.getText().toString();
                    Calendar c = Calendar.getInstance();

                    @SuppressLint("DefaultLocale")
                    String strHoje = String.format("%02d/%02d/%d", c.get(Calendar.DATE), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));

                    @SuppressLint("SimpleDateFormat")
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date data = df.parse(date);
                        Date hoje = df.parse(strHoje);

                        assert data != null;
                        if (data.before(hoje)) {
                            edDate.setText(strHoje);
                            Toast.makeText(getApplicationContext(), "Não podem ser marcados eventos com datas anteriores a data actual.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch (ParseException e) {
                        edDate.setText(strHoje);
                        Toast.makeText(getApplicationContext(), "Use o formato: dd/mm/yyyy", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (!edTitle.getText().toString().isEmpty()||!edDate.getText().
                    toString().isEmpty()||!edDesc.getText().toString().isEmpty()) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                alertDialog.setMessage("Descartar evento?");

                alertDialog.setNegativeButton("Não", null).
                    setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoTelaPrincipal();
                        finish();
                    }
                }).create().show();
            }else{
                gotoTelaPrincipal();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void dialogMessage(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Aviso");
        alertDialog.setMessage("Preencha os campos.");
        alertDialog.setNeutralButton("Ok", null);

        alertDialog.create().show();
    }

    public void saveEvent() {

        if (!edTitle.getText().toString().isEmpty() && !edDate.getText().toString().isEmpty()
                && !edDesc.getText().toString().isEmpty()) {

            if (dbh.insert(edTitle.getText().toString(), edDate.getText().toString(), edDesc.getText().toString())) {

                Toast.makeText(getApplicationContext(), "Evento gravado com sucesso.", Toast.LENGTH_SHORT).show();

                edTitle.setText(null);
                edDate.setText(null);
                edDesc.setText(null);
            } else {
                Toast.makeText(getApplicationContext(), "Erro ao gravar evento", Toast.LENGTH_SHORT).show();
            }

        } else {
            dialogMessage();
        }
    }

    private void gotoTelaPrincipal(){
        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registar_evento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.btnIconSave) {
            saveEvent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
