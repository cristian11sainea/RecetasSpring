package com.company.recetas;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaRepository recetaRepository;

    public RecetaController(RecetaRepository recetaRepository){
        this.recetaRepository = recetaRepository;
    }

    @GetMapping
    public List<Receta> obtenerrecetas(){
        return (List<Receta>) recetaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Receta> crearReceta(@RequestBody Receta receta){
        Receta nuevaReceta = recetaRepository.save(receta);
        return new ResponseEntity<>(nuevaReceta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actulizarReceta(@PathVariable Long id, @RequestBody Receta receta){
        if (!recetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        receta.setId(id);
        Receta actualizado = recetaRepository.save(receta);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id){
        if (!recetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        recetaRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
