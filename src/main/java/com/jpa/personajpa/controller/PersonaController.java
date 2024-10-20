package com.jpa.personajpa.controller;

import com.jpa.personajpa.model.Persona;
import com.jpa.personajpa.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/formSelect")
    public String formSelect(){
        return "formSelect";
    }

    @GetMapping("/mostrarPersona")
    public String mostrarPersona(@RequestParam("id") int id, Model model) {
        Optional<Persona> persona = personaService.getPersona(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            return "mostrarPersona";  // Muestra la información de la persona
        } else {
            model.addAttribute("mensaje", "Persona no encontrada");
            return "formSelect";  // Regresa al formulario si no encuentra la persona
        }
    }

    @GetMapping("/selectAll")
    public String selectAll(Model model) {
        model.addAttribute("personas", personaService.getAllPersonas());
        return "selectAll";
    }

    @GetMapping("/formInsert")
    public String formInsert(Model model){
        model.addAttribute("persona", new Persona());
        return "formInsert";
    }

    @PostMapping("/savePersona")
    public String savePersona(@ModelAttribute("persona") Persona persona){
        personaService.addPersona(persona);
        return "success";
    }

    @GetMapping("/formDelete")
    public String formDelete(){
        return "formDelete";
    }

    @PostMapping("/deletePersona")
    public String deletePersona(@RequestParam("id") int id){
        personaService.deletePersona(id);
        return "success";
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdate(@PathVariable("id") int id, Model model) {
        Persona persona = personaService.getPersona(id).orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
        model.addAttribute("persona", persona);
        return "formUpdate";  // Nombre del archivo Thymeleaf (editar_persona.html)
    }

    // Procesar el formulario de actualización
    @PostMapping("/actualizar/{id}")
    public String updatePersona(@PathVariable int id, @ModelAttribute Persona persona) {
        personaService.updatePersona(id, persona);
        return "redirect:/";
    }




}
