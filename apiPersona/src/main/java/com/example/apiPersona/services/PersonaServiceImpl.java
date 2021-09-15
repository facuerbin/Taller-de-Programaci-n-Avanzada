package com.example.apiPersona.services;

import com.example.apiPersona.entities.Persona;
import com.example.apiPersona.repositories.BaseRepository;
import com.example.apiPersona.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(BaseRepository <Persona, Long> baseRepository) {
        super(baseRepository);
    }

}
