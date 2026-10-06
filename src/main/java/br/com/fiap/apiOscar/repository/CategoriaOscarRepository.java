package br.com.fiap.apiOscar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.apiOscar.model.CategoriaOscar;

@Repository
public interface CategoriaOscarRepository extends JpaRepository<CategoriaOscar, Long> {

}