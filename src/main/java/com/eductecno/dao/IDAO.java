package com.eductecno.dao;

import com.eductecno.modelo.Usuario;

import java.util.List;

public interface IDAO<T> {
    List<T> obtenerHoroscopo();

    T porId(Long id);
    T agregar(Usuario p);
    T eliminar(Long id);
}
