package com.software2_grupo3.heysanta.modelos.conexion_bd;


import com.software2_grupo3.heysanta.modelos.Persona;
import com.software2_grupo3.heysanta.modelos.Respuesta;
import com.software2_grupo3.heysanta.modulo_administracion.Parametros;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    //Bloque para test
    @FormUrlEncoded
    @POST(Parametros.DIRECTORIO_PROYECTO +Parametros.DIRECTORIO_MODULO_TEST+"crearPersona.php")
    Call<Respuesta> crearPersona(@Field("personaNombre") String personaNombre);

    @FormUrlEncoded
    @POST(Parametros.DIRECTORIO_PROYECTO +Parametros.DIRECTORIO_MODULO_TEST+"leerPersona.php")
    Call<Persona> leerPersona(@Field("personaId") int personaId);

    @FormUrlEncoded
    @POST(Parametros.DIRECTORIO_PROYECTO +Parametros.DIRECTORIO_MODULO_TEST+"actualizarPersona.php")
    Call<Respuesta> actualizarPersona(@Field("personaId") int personaId,
                                      @Field("personaNuevoNombre") String personaNuevoNombre);

    @FormUrlEncoded
    @POST(Parametros.DIRECTORIO_PROYECTO +Parametros.DIRECTORIO_MODULO_TEST+"borrarPersona.php")
    Call<Respuesta> eliminarPersona(@Field("personaId") int personaId);

    @GET(Parametros.DIRECTORIO_PROYECTO +Parametros.DIRECTORIO_MODULO_TEST+"leerTodasLasPersonas.php")
    Call<List<Persona>> leerTodasLasPersonas();


    /*@GET("consultar_requests_android.php")
    Call<List<Pedido>> obtenerRequests();

    @FormUrlEncoded
    @POST("consultar_detalles_request_android.php")
    Call<List<DetallePedido>> obtenerDetallesRequests(@Field("request_id") int request_id,
    @Field("dato2") int dato2, @Field("dato3") int dato3);
*/

}
