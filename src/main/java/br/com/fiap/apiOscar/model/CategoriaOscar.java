package br.com.fiap.apiOscar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name="categorias")
public class CategoriaOscar {

    @Id
    private Long id;
    private String nome;
    private String filme;
      @Column(name = "nota", nullable = false)
    private int nota;
    private String tipo;

    public CategoriaOscar(){
    }
}