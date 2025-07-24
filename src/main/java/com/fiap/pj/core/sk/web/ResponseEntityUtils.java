package com.fiap.pj.core.sk.web;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.UUID;

@UtilityClass
public final class ResponseEntityUtils {

    public static ResponseEntity<Void> create(Class<?> classe, UUID id) {
        var uri = MvcUriComponentsBuilder.
                fromController(classe).path("/{id}").buildAndExpand(id.toString()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
