package com.wigm.tr.service;

import com.wigm.tr.entities.repository.Usuario;
import com.wigm.tr.repository.UsuarioRepository;
import com.wigm.tr.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;
    private JwtUtil jwtUtils;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, JwtUtil jwtUtils) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String findUser(String nombre, String passwordPlana) {
        Usuario usuario = usuarioRepository.findByUsuario(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));
        if (!usuario.getPassword().equals(passwordPlana)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        return jwtUtils.createToken(nombre);
    }
}
