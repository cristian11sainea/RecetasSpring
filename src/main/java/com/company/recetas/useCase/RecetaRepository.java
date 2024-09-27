package com.company.recetas.useCase;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.recetas.model.Receta;

public interface RecetaRepository extends CrudRepository<Receta, Long>{

    List<Receta> findByNombre(String nombre);
    
    //List<Receta> findByDuracionGreaterThan(Integer duracionMinutos);

    //List<Receta> findByDuracionLessThan(Integer duracionMinutos);

}
