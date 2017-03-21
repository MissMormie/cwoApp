/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.repository;

import java.io.Serializable;
import java.util.List;
import nl.cwo_app.entity.DiplomaEis;
import nl.cwo_app.entity.Diploma;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sonja
 */
public interface DiplomaEisRepository extends CrudRepository<DiplomaEis, Serializable>{
  List<DiplomaEis> findByDiploma(Diploma diploma);
}
