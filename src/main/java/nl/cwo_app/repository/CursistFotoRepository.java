/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.repository;

import java.io.Serializable;
import nl.cwo_app.entity.CursistFoto;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sonja
 */
public interface CursistFotoRepository extends CrudRepository<CursistFoto, Serializable> {
    
    
}
