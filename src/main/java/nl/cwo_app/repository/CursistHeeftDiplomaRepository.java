/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.repository;

import nl.cwo_app.entity.CursistHeeftDiploma;
import nl.cwo_app.entity.Diploma;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface CursistHeeftDiplomaRepository extends CrudRepository<CursistHeeftDiploma, Long> {  
    Void deleteByCursistIdAndDiploma(Long cursistId, Diploma diploma);
    //CursistBehaaldEis deleteByCursistIdAndDiplomaEis(Long cursistId, DiplomaEis diplomaEis);
    
    CursistHeeftDiploma findByCursistIdAndDiploma(Long cursistId, Diploma diploma);
}
