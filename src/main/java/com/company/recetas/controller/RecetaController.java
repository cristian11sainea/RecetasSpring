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
import com.company.recetas.model.Receta;
import com.company.recetas.useCase.CategoriaRepository;
import com.company.recetas.useCase.RecetaRepository;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;

    public RecetaController(RecetaRepository recetaRepository, CategoriaRepository categoriaRepository){
        this.recetaRepository = recetaRepository;
        this.categoriaRepository = categoriaRepository;
    }
    @GetMapping
    public String obtenerRecetas(Model model){
        List<Receta> recetas = (List<Receta>) recetaRepository.findAll();
        model.addAttribute("recetas", recetas);
        return "lista_recetas";  // Esta es la vista Thymeleaf que se mostrará
    }

    @GetMapping("/nueva")
    public String nuevaRecetaForm(Model model) {
        model.addAttribute("receta", new Receta());
        model.addAttribute("categorias", categoriaRepository.findAll()); // Lista de categorías
        return "nueva_receta";  // Vista para formulario de nueva receta
    }

    @PostMapping
    public String crearReceta(@ModelAttribute Receta receta){
       recetaRepository.save(receta);
        return "redirect:/recetas";
    }


    @GetMapping("/editar/{id}")
    public String editarRecetaForm(@PathVariable Long id, Model model) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de receta inválido: " + id));
        List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
        model.addAttribute("receta", receta);
        model.addAttribute("categorias", categorias); // Lista de categorías
        return "editar_receta";  // Vista para editar receta
    }

    @PostMapping("/actualizar/{id}")
    public String actulizarReceta(@PathVariable Long id, @ModelAttribute Receta receta){
        receta.setId(id);
        recetaRepository.save(receta);
        return "redirect:/recetas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReceta(@PathVariable Long id){
        if (!recetaRepository.existsById(id)) {
            return "no existe";
        }
        recetaRepository.deleteById(id);
        return "redirect:/recetas";

    }

}
