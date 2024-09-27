package com.company.recetas.useCase;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.recetas.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

    List<Categoria> findByNombre(String nombre);
}
