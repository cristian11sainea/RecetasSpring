package com.company.recetas.useCase;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.recetas.model.Usuario;

@Repository
public interface  UsuarioRepository extends CrudRepository<Usuario, Long>{

    List<Usuario> findByUser(String user);
    
}
