package com.wigm.tr.controller;

import com.wigm.tr.entities.dto.ActualizarOrdenDTO;
import com.wigm.tr.entities.dto.CrearOrdenDTO;
import com.wigm.tr.service.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/v1/orden")
public class OrdenController {

    private OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerOrdenes(){
        return new ResponseEntity<>(ordenService.obtenerOrdenes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody CrearOrdenDTO crearOrdenDTO){
        ordenService.crearNuevaOrden(crearOrdenDTO);
        return new ResponseEntity<>(crearOrdenDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> crearOrden(@RequestBody ActualizarOrdenDTO or){
        ordenService.actualizarOrden(or);
        return new ResponseEntity<>(or, HttpStatus.OK);
    }
}
