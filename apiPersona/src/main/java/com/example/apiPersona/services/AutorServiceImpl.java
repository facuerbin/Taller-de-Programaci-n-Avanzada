package com.example.apiPersona.services;

import com.example.apiPersona.entities.Autor;
import com.example.apiPersona.repositories.AutorRepository;
import com.example.apiPersona.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService{

    @Autowired
    private AutorRepository autorRepository;

    public AutorServiceImpl(BaseRepository <Autor, Long> baseRepository) {
        super(baseRepository);
    }
}
