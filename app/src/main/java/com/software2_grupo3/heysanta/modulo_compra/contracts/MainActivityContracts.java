package com.software2_grupo3.heysanta.modulo_compra.contracts;

import com.software2_grupo3.heysanta.modelos.Persona;

import java.util.List;

public interface MainActivityContracts {

    interface View{

    	void mostrarMensaje(String mensaje);
    	void mostrarBarraProgreso();
    	void ocultarBarraProgreso();

    	void mostrarPersona(Persona persona);
        void mostrarListaDePersona(List<Persona> listaPersonas);

    }

    interface Presenter{
        //Se encarga de controlar lo que se ve en la pantalla. Tambien registra los eventos
        void enCrearPersonaPresionado(String nombre);
        void enCrearPersonaExitoso(Persona persona);
        void enCrearPersonaFallido(String error);

        void enLeerPersonaPresionado(int personaId);
        void enLeerPersonaExitoso(Persona persona);
        void enLeerPersonaFallido(String error);

        void enActualizarPersonaPresionado(int personaId, String nuevoNombre);
        void enActualizarPersonaExitoso(int personaId);
        void enActualizarPersonaFallido(String error);

        void enEliminarPersonaPresionado(int personaId);
        void enEliminarPersonaExitoso(String mensaje);
        void enEliminarPersonaFallido(String error);

        void enLeerTodasPersonasPresionado();
        void enLeerTodasPersonasExitoso(List<Persona> persona);
        void enLeerTodasPersonasFallido(String error);
    }

    interface Interactor{
        //aplica toda la lógica de negocio, es opcional. Puede usarse solo Presenter con la lógica,
        // pero Interactor ayuda a la organización de código y es recomendado

        void crearPersona(String nombre);
        void leerPresona(int personaId);
        void actualizarPersona(int personaId, String nuevoNombre);
        void eliminarPersona(int personaId);

        void leerTodasLasPresonas();

    }


}
