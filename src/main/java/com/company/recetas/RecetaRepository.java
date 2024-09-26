package com.company.recetas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecetaRepository extends CrudRepository<Receta, Long>{

    List<Receta> findByNombre(String nombre);
    
    //List<Receta> findByDuracionGreaterThan(Integer duracionMinutos);

    //List<Receta> findByDuracionLessThan(Integer duracionMinutos);

}
