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
@Table(name="oscars")
public class Oscar {

    @Id
    private Long id;
    private String nome;
    @Column(name = "ator", nullable = false)
    private String ator;
    private int ano;
    private String tipo;

    public Oscar(){
    }
}