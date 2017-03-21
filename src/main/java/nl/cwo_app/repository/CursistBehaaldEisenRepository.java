/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.repository;

import java.io.Serializable;
import javax.transaction.Transactional;
import nl.cwo_app.entity.Cursist;
import nl.cwo_app.entity.CursistBehaaldEis;
import nl.cwo_app.entity.DiplomaEis;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface CursistBehaaldEisenRepository extends CrudRepository<CursistBehaaldEis, Serializable>{
  
  //@Transactional
  CursistBehaaldEis deleteByCursistIdAndDiplomaEis(Long cursistId, DiplomaEis diplomaEis);
  
}
