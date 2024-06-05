package com.aluracursos.LiterAlura.service;

public interface IConvierteDatos {
    //Usamos un tipo de dato generico y java lo puede declarar de esa forma <T> T
    <T> T obtenerDatos(String json, Class<T> clase);

}
