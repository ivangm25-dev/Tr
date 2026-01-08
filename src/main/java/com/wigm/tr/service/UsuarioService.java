package com.wigm.tr.service;


public interface UsuarioService {

    /**
     * Busca el usuario.
     * @param user
     * @param password
     * @return
     */
    String findUser(String user, String password);
}
