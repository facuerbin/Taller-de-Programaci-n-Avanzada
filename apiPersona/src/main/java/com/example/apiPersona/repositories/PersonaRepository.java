package com.example.apiPersona.repositories;

import com.example.apiPersona.entities.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
}


