package com.example.apiPersona.controllers;

import com.example.apiPersona.entities.Localidad;
import com.example.apiPersona.services.LocalidadServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/localidades/")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl> {
}
