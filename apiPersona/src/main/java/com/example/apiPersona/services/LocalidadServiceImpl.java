package com.example.apiPersona.services;

import com.example.apiPersona.entities.Localidad;
import com.example.apiPersona.repositories.BaseRepository;
import com.example.apiPersona.repositories.LocalidadRepository;
import com.example.apiPersona.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository) {
        super(baseRepository);
    }
}
