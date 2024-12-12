
package com.casacultura.analise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//A anotação @Entity diz ao Spring que a classe FuncionarioEntity representa uma tabela do banco de dados.
@Data//com este não preciso criar gets e sets
@NoArgsConstructor//não precisa de construtor vazio
@AllArgsConstructor// não precisa de construtor
@Table(name="Filme")
public class Filme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String titulo;
    private String sinopse;
    private String genero;
    private Integer ano;
    private boolean avaliado;

   
    
    
}
