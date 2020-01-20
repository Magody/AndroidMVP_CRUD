package com.software2_grupo3.heysanta.modulo_compra.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.software2_grupo3.heysanta.R;
import com.software2_grupo3.heysanta.modelos.Persona;
import com.software2_grupo3.heysanta.modulo_compra.contracts.MainActivityContracts;
import com.software2_grupo3.heysanta.modulo_compra.presenter.MainActivityPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContracts.View {

    public static final String TAG = "MainActivity";

    private MainActivityContracts.Presenter mainActivityPresenter;


    private ProgressBar progressBar;
    private TextView textViewPersona, textViewListaPersonas;
    private EditText editTextPersonaNombre, editTextPersonaIdConsultar;
    private EditText editTextPersonaIdActualizar, editTextPersonaNombreNuevo;
    private EditText editTextPersonaIdEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVariables();
        mainActivityPresenter = new MainActivityPresenter(this, this);
        inicializarEventosBotones();
    }

    private void inicializarVariables(){
        progressBar = findViewById(R.id.progressBarMainActivity);
        textViewPersona = findViewById(R.id.textViewPersonaConsultada);
        textViewListaPersonas = findViewById(R.id.textViewPersonas);
        editTextPersonaNombre = findViewById(R.id.editTextPersonaNombre);
        editTextPersonaIdConsultar = findViewById(R.id.editTextPersonaIdConsultar);
        editTextPersonaIdActualizar = findViewById(R.id.editTextPersonaIdActualizar);
        editTextPersonaNombreNuevo = findViewById(R.id.editTextPersonaNombreActualizar);
        editTextPersonaIdEliminar = findViewById(R.id.editTextPersonaIdEliminar);
    }

    private void inicializarEventosBotones(){
        findViewById(R.id.buttonCrearPersona).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se ejecuta cuando se presiona el botón Crear
                String nombre = editTextPersonaNombre.getText().toString();
                mainActivityPresenter.enCrearPersonaPresionado(nombre);
            }
        });


        findViewById(R.id.buttonConsultarPersona).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se ejecuta cuando se presiona el botón Consultar
                String idTexto = editTextPersonaIdConsultar.getText().toString();

                if(!idTexto.equals("")){ // control de errores para la conversión Integer
                    int id = Integer.parseInt(idTexto);
                    textViewPersona.setText(null);
                    mainActivityPresenter.enLeerPersonaPresionado(id);
                }else{
                    mostrarMensaje("Error: debe ingresar el id en el campo de consultar persona");
                }

            }
        });

        findViewById(R.id.buttonActualizarPersona).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se ejecuta cuando se presiona el botón Actualizar
                String idTexto = editTextPersonaIdActualizar.getText().toString();

                if(!idTexto.equals("")){  // control de errores para la conversión Integer
                    int id = Integer.parseInt(idTexto);
                    String nuevoNombre = editTextPersonaNombreNuevo.getText().toString();
                    mainActivityPresenter.enActualizarPersonaPresionado(id, nuevoNombre);
                }else{
                    mostrarMensaje("Error: debe ingresar el id en el campo de actualizar persona");
                }
            }
        });

        findViewById(R.id.buttonEliminarPersona).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se ejecuta cuando se presiona el botón Eliminar
                String idTexto = editTextPersonaIdEliminar.getText().toString();

                if(!idTexto.equals("")){ // control de errores para la conversión Integer
                    int id = Integer.parseInt(idTexto);
                    mainActivityPresenter.enEliminarPersonaPresionado(id);
                }else{
                    mostrarMensaje("Error: debe ingresar el id en el campo de eliminar persona");
                }
            }
        });

        findViewById(R.id.buttonConsultarTodo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se ejecuta cuando se presiona el botón Consultar Tod0
                textViewListaPersonas.setText(null);
                mainActivityPresenter.enLeerTodasPersonasPresionado();
            }
        });
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarBarraProgreso() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarBarraProgreso() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarPersona(Persona persona) {
        textViewPersona.setText(persona.toString());
    }

    @Override
    public void mostrarListaDePersona(List<Persona> listaPersonas) {

        String salida = "";
        for (Persona persona:
             listaPersonas) {
            salida += persona.toString() + "\n";
        }

        textViewListaPersonas.setText(salida);
    }
}
