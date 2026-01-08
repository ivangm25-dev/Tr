package com.wigm.tr.entities.exception;

public class NoAutorizacion extends RuntimeException {
    public NoAutorizacion(String message){
        super(message);
    }
}