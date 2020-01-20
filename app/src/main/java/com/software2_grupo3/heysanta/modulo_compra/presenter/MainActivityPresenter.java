package com.software2_grupo3.heysanta.modulo_compra.presenter;

import android.content.Context;

import com.software2_grupo3.heysanta.modelos.Persona;
import com.software2_grupo3.heysanta.modulo_compra.contracts.MainActivityContracts;
import com.software2_grupo3.heysanta.modulo_compra.interactor.MainActivityInteractor;

import java.util.List;

public class MainActivityPresenter implements MainActivityContracts.Presenter {


    private MainActivityContracts.View mainActivityView;
    private MainActivityContracts.Interactor mainActivityInteractor;

    public MainActivityPresenter(MainActivityContracts.View view, Context mContext) {
        this.mainActivityView = view;
        this.mainActivityInteractor = new MainActivityInteractor(this, mContext);
    }

    @Override
    public void enCrearPersonaPresionado(String nombre) {
        mainActivityView.mostrarBarraProgreso();
        mainActivityInteractor.crearPersona(nombre);
    }

    @Override
    public void enCrearPersonaExitoso(Persona persona) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje("Se agregó: " + persona.getNombre() + ", con Id:" + persona.getId());
    }

    @Override
    public void enCrearPersonaFallido(String error) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje(error);
    }

    @Override
    public void enLeerPersonaPresionado(int personaId) {
        mainActivityView.mostrarBarraProgreso();
        mainActivityInteractor.leerPresona(personaId);
    }

    @Override
    public void enLeerPersonaExitoso(Persona persona) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarPersona(persona);
    }

    @Override
    public void enLeerPersonaFallido(String error) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje(error);
    }

    @Override
    public void enActualizarPersonaPresionado(int personaId, String nuevoNombre) {
        mainActivityView.mostrarBarraProgreso();
        mainActivityInteractor.actualizarPersona(personaId, nuevoNombre);
    }

    @Override
    public void enActualizarPersonaExitoso(int personaId) {
        mainActivityView.ocultarBarraProgreso();
        this.enLeerPersonaPresionado(personaId);
        mainActivityView.mostrarMensaje("Actualización completada, arriba también se actualizará");
    }

    @Override
    public void enActualizarPersonaFallido(String error) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje(error);
    }

    @Override
    public void enEliminarPersonaPresionado(int idRegistro) {
        mainActivityView.mostrarBarraProgreso();
        mainActivityInteractor.eliminarPersona(idRegistro);
    }

    @Override
    public void enEliminarPersonaExitoso(String mensaje) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje(mensaje);
    }

    @Override
    public void enEliminarPersonaFallido(String error) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje(error);
    }

    @Override
    public void enLeerTodasPersonasPresionado() {
        mainActivityView.mostrarBarraProgreso();
        mainActivityInteractor.leerTodasLasPresonas();
    }

    @Override
    public void enLeerTodasPersonasExitoso(List<Persona> listaPersonas) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarListaDePersona(listaPersonas);
    }

    @Override
    public void enLeerTodasPersonasFallido(String error) {
        mainActivityView.ocultarBarraProgreso();
        mainActivityView.mostrarMensaje(error);
    }
}
