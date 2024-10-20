package com.jpa.personajpa.service;

import com.jpa.personajpa.model.Persona;
import com.jpa.personajpa.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    //Mostrar todas las personas
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    //Mostrar Persona por ID
    public Optional<Persona> getPersona(int id) {
        return personaRepository.findById(id);
    }

    //Insertar Persona
    public void addPersona(Persona persona) {
        personaRepository.save(persona);
    }

    //Eliminar Persona
    public void deletePersona(int id) {
        personaRepository.deleteById(id);
    }

    //Actualizar Persona
    public Persona updatePersona(int id, Persona personaDetails) {
        Persona persona = personaRepository.findById(id).orElseThrow();
        persona.setNombre(personaDetails.getNombre());
        persona.setApellido(personaDetails.getApellido());
        persona.setEmail(personaDetails.getEmail());
        return personaRepository.save(persona);
    }
}
