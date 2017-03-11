/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.repository;

import nl.cwo_app.entity.Cursist;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface CursistRepository extends CrudRepository<Cursist, Long> {  
}
