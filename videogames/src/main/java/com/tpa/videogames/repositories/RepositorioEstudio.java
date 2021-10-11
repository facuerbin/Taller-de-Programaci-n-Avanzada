package com.tpa.videogames.repositories;

import com.tpa.videogames.entities.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioEstudio extends JpaRepository<Estudio, Long> {
    @Query(value = "SELECT * FROM estudios WHERE estudios.nombre LIKE %:q% AND estudios.activo =true", nativeQuery = true)
    List<Estudio> findByName(@Param("q")String q);
}
