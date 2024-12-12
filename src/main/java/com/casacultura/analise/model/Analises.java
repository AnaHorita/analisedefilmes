
package com.casacultura.analise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Analises")
public class Analises {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String analise;
    private Double nota;
    
    @ManyToOne
    @JoinColumn (name="id_filme")
    private Filme filme;
  

}
