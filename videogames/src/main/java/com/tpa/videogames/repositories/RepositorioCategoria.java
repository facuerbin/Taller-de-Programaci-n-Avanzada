package com.tpa.videogames.repositories;

import com.tpa.videogames.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {
    @Query(value = "SELECT * FROM categorias WHERE categorias.nombre LIKE %:q% AND categorias.activo =true", nativeQuery = true)
    List<Categoria> findByName(@Param("q")String q);
}