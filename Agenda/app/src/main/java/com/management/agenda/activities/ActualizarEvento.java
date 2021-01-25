package com.management.agenda.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.management.agenda.R;
import com.management.agenda.controller.DataBaseHelper;
import com.management.agenda.models.Evento;


public class ActualizarEvento extends AppCompatActivity {

    //private Button btUpdate;
    private EditText edTitle;
    private EditText edDate;
    private EditText edDesc;
    private static Evento evt;
    private DataBaseHelper dbh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_evento);

        Toolbar toolBar = findViewById(R.id.tool_bar);

        dbh = new DataBaseHelper(this);

        //btUpdate = findViewById(R.id.btnUpdate);
        edTitle  = findViewById(R.id.editTextTitleUpdate);
        edDate   = findViewById(R.id.editTextDateUpdate);
        edDesc   = findViewById(R.id.editTextDescUpdate);

        setActionBar(toolBar);

        fillTextView();
    }

    @Override
    public void onBackPressed(){
        if (!edTitle.getText().toString().isEmpty()||!edDate.getText().
                toString().isEmpty()||!edDesc.getText().toString().isEmpty()) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setMessage("Cancelar?");

            alertDialog.setNegativeButton("Não", null).
                    setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create().show();
        }else{
            finish();
        }

    }

    private void updateEvent(){

        if (evt.getId() > 0 &&!edTitle.getText().toString().isEmpty()&&!edDate.getText().toString().isEmpty()
                &&!edDesc.getText().toString().isEmpty()){

            if(dbh.update(evt.getId(), edTitle.getText().toString(), edDate.getText().toString(), edDesc.getText().toString())){
                finish();
                Toast.makeText(getApplicationContext(), "Evento actualizado com sucesso.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "Erro ao atualizar evento", Toast.LENGTH_SHORT).show();
            }
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setTitle("Aviso");
            alertDialog.setMessage("Preencha os campos.");
            alertDialog.setNeutralButton("Ok", null);

            alertDialog.create().show();
        }
    }

    protected static void catchEvent(Evento evento){
        evt = evento;
    }

    private void fillTextView(){
        edTitle.setText(evt.getTitle());
        edDate.setText(evt.getDate());
        edDesc.setText(evt.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actualizar_evento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.btnIconUpdate) {
            updateEvent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
