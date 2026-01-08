package com.wigm.tr.exception;

import com.wigm.tr.entities.dto.ErrorMessageDTO;
import com.wigm.tr.entities.exception.ExcepcionGenerica;
import com.wigm.tr.entities.exception.NoAutorizacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
public class ManejadorExcepciones {
    private static final Logger logger =
            LoggerFactory.getLogger(ManejadorExcepciones.class);

    @ExceptionHandler(value = NoAutorizacion.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorMessageDTO noAutorizado(NoAutorizacion ex) {
        logger.error("Sin Autorizacion: " + ex.getMessage());
        return new ErrorMessageDTO(ex.getMessage());
    }

    @ExceptionHandler(value = ExcepcionGenerica.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorMessageDTO handleException(ExcepcionGenerica ex) {
        return new ErrorMessageDTO(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorMessageDTO handleException(Exception ex) {
        logger.error("Internal error: " + ex.getMessage());
        return new ErrorMessageDTO(ex.getMessage());
    }

}