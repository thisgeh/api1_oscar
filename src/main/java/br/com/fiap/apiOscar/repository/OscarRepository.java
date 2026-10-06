package br.com.fiap.apiOscar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.apiOscar.model.Oscar;

@Repository
public interface OscarRepository extends JpaRepository<Oscar, Long> {

}