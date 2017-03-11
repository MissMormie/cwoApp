/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.repository;

import java.io.Serializable;
import javax.transaction.Transactional;
import nl.cwo_app.entity.Cursist;
import nl.cwo_app.entity.CursistBehaaldEisen;
import nl.cwo_app.entity.CwoEisen;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface CursistBehaaldEisenRepository extends CrudRepository<CursistBehaaldEisen, Serializable>{
  
  @Transactional
  CursistBehaaldEisen deleteByCursistAndCwoEisen(Cursist cursist, CwoEisen cwoEisen);
  
}
