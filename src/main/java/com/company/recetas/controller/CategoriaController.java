package com.company.recetas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.recetas.model.Categoria;
import com.company.recetas.useCase.CategoriaRepository;
import com.company.recetas.useCase.RecetaRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

private final CategoriaRepository categoriaRepository;
private final RecetaRepository recetaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository, RecetaRepository recetaRepository){
        this.categoriaRepository = categoriaRepository;
        this.recetaRepository = recetaRepository;
    }

    @GetMapping
    public String obtenerCategorias(Model model){
        List<Categoria> categorias = (List<Categoria>)categoriaRepository.findAll(); 
        model.addAttribute("categorias", categorias);
        return "lista_categorias";
    }

    @GetMapping("/nueva")
    public String nuevaCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "nueva_categoria";  // Vista para formulario de nueva categoria
    }

    @PostMapping
    public String crearCategoria(@ModelAttribute Categoria categoria){
       categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoriaForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de categoria inválido: " + id));
        model.addAttribute("categoria", categoria);
        return "editar_categoria";  // Vista para editar categoria
    }

    @PostMapping("/actualizar/{id}")
    public String actulizarcategoria(@PathVariable Long id, @ModelAttribute Categoria categoria){
        categoria.setId(id);
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id){
        if (!categoriaRepository.existsById(id)) {
            return "no existe";
        }
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";

    }

}
