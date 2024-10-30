package com.example.juvica.repository;

import com.example.juvica.entity.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {

    // Puedes agregar métodos personalizados si es necesario
}
