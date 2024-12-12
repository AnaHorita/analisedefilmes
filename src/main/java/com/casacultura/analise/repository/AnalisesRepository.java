
package com.casacultura.analise.repository;

import com.casacultura.analise.model.Analises;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalisesRepository extends JpaRepository<Analises, Integer> {
    
     List<Analises> findByFilmeId(Integer id);
    
}
