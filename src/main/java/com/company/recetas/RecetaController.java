package com.company.recetas;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaRepository recetaRepository;

    public RecetaController(RecetaRepository recetaRepository){
        this.recetaRepository = recetaRepository;
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
        model.addAttribute("receta", receta);
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
