package com.wigm.tr.config;

import com.wigm.tr.entities.repository.Usuario;
import com.wigm.tr.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CargaInicial {

    @Bean
    public CommandLineRunner cargaUsuario(UsuarioRepository usuarioRepository) {
        return args -> {
            Usuario admin = new Usuario();
            admin.setUsuario("admin");
            admin.setPassword("admin123");
            usuarioRepository.save(admin);
        };
    }
}
