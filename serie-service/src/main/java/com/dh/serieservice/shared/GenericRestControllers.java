package com.dh.serieservice.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public abstract  class GenericRestControllers <T,ID extends Serializable> {
    private final GenericServiceAPI<T,ID> serviceAPI;
    protected  GenericRestControllers(GenericServiceAPI<T,ID> serviceAPI){this.serviceAPI=serviceAPI;}

    @GetMapping("/all")
    public List<T> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{v}")
    public ResponseEntity<Object> find(@PathVariable ID v) {
        var entity = serviceAPI.getOne(v);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@Valid @RequestBody T entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    // Validador de campos
    public ResponseEntity<Object> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(),
                " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @DeleteMapping("/delete/{v}")
    public ResponseEntity<Object> delete(@PathVariable ID v) {
        var entity = serviceAPI.getOne(v);
        if (entity != null) {
            serviceAPI.delete(v);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
