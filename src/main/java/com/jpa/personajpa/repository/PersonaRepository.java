package com.jpa.personajpa.repository;

import com.jpa.personajpa.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
