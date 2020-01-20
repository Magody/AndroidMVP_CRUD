package com.software2_grupo3.heysanta.modulo_compra.interactor;

import android.app.Person;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.software2_grupo3.heysanta.modelos.Persona;
import com.software2_grupo3.heysanta.modelos.Respuesta;
import com.software2_grupo3.heysanta.modelos.Validacion;
import com.software2_grupo3.heysanta.modelos.conexion_bd.ApiClient;
import com.software2_grupo3.heysanta.modelos.conexion_bd.ApiInterface;
import com.software2_grupo3.heysanta.modulo_compra.contracts.MainActivityContracts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.util.Log.i;

public class MainActivityInteractor implements MainActivityContracts.Interactor {

    public static final String TAG = "MainActivityInteractor";

    private Context mContext;

    private MainActivityContracts.Presenter mainActivityCallbackPresenter;

    public MainActivityInteractor(MainActivityContracts.Presenter presenter, Context mContext){
        this.mainActivityCallbackPresenter = presenter;
        this.mContext = mContext;
    }


    @Override
    public void crearPersona(final String nombre) {

        if(!Validacion.camposLlenos(new String[]{nombre})){  //validaciones del negocio
            mainActivityCallbackPresenter.enCrearPersonaFallido("Debe llenar el nombre de la persona a crear");
            return;
        }


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Respuesta> call = apiInterface.crearPersona(nombre);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Respuesta> call, @NonNull Response<Respuesta> response) {

                if(response.isSuccessful() && response.body() != null){

                    final Respuesta respuesta = response.body();

                    i(TAG, "función crearPersona(String nombre), onResponse: " + respuesta.toString());

                    if(respuesta.getCodigo() == 1){
                        //el 1 simboliza que desde el servidor le respondi que "Correcto"
                        Persona persona = new Persona(Integer.parseInt(respuesta.getMetadata()), nombre);
                        mainActivityCallbackPresenter.enCrearPersonaExitoso(persona);
                    }else{
                        //ocurrio un error y le muestro el mismo mensaje del servidor.
                        //se puede controlar el código para manejar errores
                        mainActivityCallbackPresenter.enCrearPersonaFallido(respuesta.getMensaje());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Respuesta> call,@NonNull Throwable t) {
                mainActivityCallbackPresenter.enCrearPersonaFallido(t.toString());
                Log.e(TAG, "Error en la función crearPersona(String nombre): " + t.toString());
            }
        });
    }

    @Override
    public void leerPresona(int idRegistro) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Persona> call = apiInterface.leerPersona(idRegistro);
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(@NonNull Call<Persona> call, @NonNull Response<Persona> response) {

                if(response.isSuccessful() && response.body() != null){

                    final Persona persona = response.body();

                    i(TAG, "función leerPresona(int idRegistro), onResponse: " + persona.toString());

                    mainActivityCallbackPresenter.enLeerPersonaExitoso(persona);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Persona> call,@NonNull Throwable t) {
                mainActivityCallbackPresenter.enLeerPersonaFallido(t.toString());
                Log.e(TAG, "Error en la función leerPresona(int idRegistro): " + t.toString());
            }
        });
    }

    @Override
    public void actualizarPersona(final int personaId, final String nuevoNombre) {

        if(!Validacion.camposLlenos(new String[]{nuevoNombre})){  //validaciones del negocio
            mainActivityCallbackPresenter.enCrearPersonaFallido("Debe llenar el nuevo nombre de la persona a crear");
            return;
        }


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Respuesta> call = apiInterface.actualizarPersona(personaId, nuevoNombre);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Respuesta> call, @NonNull Response<Respuesta> response) {

                if(response.isSuccessful() && response.body() != null){

                    final Respuesta respuesta = response.body();

                    i(TAG, "función actualizarPersona(int personaId, String nuevoNombre), onResponse: " + respuesta.toString());

                    if(respuesta.getCodigo() == 1){
                        //el 1 simboliza que desde el servidor le respondi que "Correcto"
                        mainActivityCallbackPresenter.enActualizarPersonaExitoso(personaId);
                    }else{
                        //ocurrio un error y le muestro el mismo mensaje del servidor.
                        //se puede controlar el código para manejar errores
                        mainActivityCallbackPresenter.enActualizarPersonaFallido(respuesta.getMensaje());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Respuesta> call,@NonNull Throwable t) {
                mainActivityCallbackPresenter.enActualizarPersonaFallido(t.toString());
                Log.e(TAG, "Error en la función actualizarPersona(int personaId, String nuevoNombre): " + t.toString());
            }
        });
    }

    @Override
    public void eliminarPersona(final int personaId) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Respuesta> call = apiInterface.eliminarPersona(personaId);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Respuesta> call, @NonNull Response<Respuesta> response) {

                if(response.isSuccessful() && response.body() != null){

                    final Respuesta respuesta = response.body();

                    i(TAG, "función eliminarPersona(int personaId), onResponse: " + respuesta.toString());

                    if(respuesta.getCodigo() == 1){
                        //el 1 simboliza que desde el servidor le respondi que "Correcto"
                        mainActivityCallbackPresenter.enEliminarPersonaExitoso("Se ha eliminado la persona con id " + personaId + " correctamente");
                    }else{
                        //ocurrio un error y le muestro el mismo mensaje del servidor.
                        //se puede controlar el código para manejar errores
                        mainActivityCallbackPresenter.enEliminarPersonaFallido(respuesta.getMensaje());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Respuesta> call,@NonNull Throwable t) {
                mainActivityCallbackPresenter.enEliminarPersonaFallido(t.toString());
                Log.e(TAG, "Error en la función eliminarPersona(int personaId): " + t.toString());
            }
        });
    }

    @Override
    public void leerTodasLasPresonas() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Persona>> call = apiInterface.leerTodasLasPersonas();
        call.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(@NonNull Call<List<Persona>> call, @NonNull Response<List<Persona>> response) {

                if(response.isSuccessful() && response.body() != null){

                    final List<Persona> listaPersonas = response.body();

                    i(TAG, "función leerTodasLasPresonas(), onResponse: " + listaPersonas.toString());

                    mainActivityCallbackPresenter.enLeerTodasPersonasExitoso(listaPersonas);

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Persona>> call,@NonNull Throwable t) {
                mainActivityCallbackPresenter.enLeerTodasPersonasFallido(t.toString());
                Log.e(TAG, "Error en la función leerTodasLasPresonas(): " + t.toString());
            }
        });
    }
}
