package com.software2_grupo3.heysanta.modelos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Respuesta {

    @Expose
    @SerializedName("codigo") private int codigo;
    @Expose
    @SerializedName("mensaje") private String mensaje;
    @Expose
    @SerializedName("metadata") private String metadata;
    @Expose
    @SerializedName("version") private String version;

    public Respuesta(int codigo, String mensaje, String metadata) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.metadata = metadata;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getMetadata() {
        return metadata;
    }

    @NonNull
    @Override
    public String toString() {
        return "Persona{" +
                "id=" + codigo +
                ", nombre='" + mensaje + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
