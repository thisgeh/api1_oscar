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

import br.com.fiap.apiOscar.model.Oscar;
import br.com.fiap.apiOscar.repository.OscarRepository;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/${api.version}/oscar")
public class OscarController {
    @Autowired
    private OscarRepository repository;

    @PostMapping()
    public ResponseEntity<Oscar> create(@RequestBody Oscar oscar) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(oscar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oscar> findById(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Oscar>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oscar> update(@PathVariable Long id,
            @RequestBody Oscar oscar) {

        Optional<Oscar> optOscar = repository.findById(id);

        if (optOscar.isPresent()) {
            oscar.setId(id);
            Oscar oscarAlterado = repository.save(oscar);
            return ResponseEntity.ok(oscarAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id, @RequestBody Oscar oscar) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}