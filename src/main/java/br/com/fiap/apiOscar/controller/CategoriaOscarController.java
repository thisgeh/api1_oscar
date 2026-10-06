package br.com.fiap.apiOscar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.apiOscar.model.CategoriaOscar;
import br.com.fiap.apiOscar.repository.CategoriaOscarRepository;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/${api.version}/categoriaOscar")
public class CategoriaOscarController {
    @Autowired
    private CategoriaOscarRepository repository;

    @PostMapping()
    public ResponseEntity<CategoriaOscar> create(@RequestBody CategoriaOscar categoriaOscar) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoriaOscar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaOscar> findById(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoriaOscar>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaOscar> update(@PathVariable Long id,
            @RequestBody CategoriaOscar categoriaOscar) {

        Optional<CategoriaOscar> optCategoriaOscar = repository.findById(id);

        if (optCategoriaOscar.isPresent()) {
            categoriaOscar.setId(id);
            CategoriaOscar categoriaOscarAlterado = repository.save(categoriaOscar);
            return ResponseEntity.ok(categoriaOscarAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id, @RequestBody CategoriaOscar categoriaOscar) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}