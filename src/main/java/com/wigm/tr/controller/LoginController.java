package com.wigm.tr.controller;

import com.wigm.tr.entities.dto.LoginRequestDTO;
import com.wigm.tr.security.JwtUtil;
import com.wigm.tr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("token", usuarioService.findUser(loginRequest.usuario(), loginRequest.password()));
        response.put("type", "Bearer");
        return ResponseEntity.ok(response);

    }
}