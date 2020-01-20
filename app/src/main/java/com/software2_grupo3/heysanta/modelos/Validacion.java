package com.software2_grupo3.heysanta.modelos;

public class Validacion {

    public static boolean camposLlenos(String[] campos){

        boolean llenos = true;

        for (String campo:
             campos) {
            if(campo.equals("")){
                llenos = false;
                break;
            }
        }


        return llenos;
    }


}
