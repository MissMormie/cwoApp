/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.util.List;
import nl.cwo_app.entity.Diploma;
import nl.cwo_app.repository.CursistBehaaldEisenRepository;
import nl.cwo_app.repository.CursistHeeftDiplomaRepository;
import nl.cwo_app.repository.CursistRepository;
import nl.cwo_app.repository.CwoEisenRepository;
import nl.cwo_app.repository.DiplomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sonja
 */
@RestController
public class EisenController {
  
    // TODO bekijken welke hiervan nodig zijn.
  private final CursistRepository cursistRepository;
  private final CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository;
  private final DiplomaRepository diplomaRepository;
  private final CwoEisenRepository cwoEisenRepository;
  private final CursistBehaaldEisenRepository cursistBehaaldEisenRepository;

  @Autowired
  public EisenController(CursistRepository cursistRepository,
          CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository,
          DiplomaRepository diplomaRepository,
          CwoEisenRepository cwoEisenRepository,
          CursistBehaaldEisenRepository cursistBehaaldEisenRepository) {
    this.cursistRepository = cursistRepository;
    this.cursistHeeftDiplomaRepository = cursistHeeftDiplomaRepository;
    this.diplomaRepository = diplomaRepository;
    this.cwoEisenRepository = cwoEisenRepository;
    this.cursistBehaaldEisenRepository = cursistBehaaldEisenRepository;
  }
  
  
    @RequestMapping(value="/diploma/{diplomaId}/eisen", method = RequestMethod.GET)
  public List eisenBijDiploma(@PathVariable long diplomaId) {
    Diploma diploma = diplomaRepository.findOne(diplomaId);
    return cwoEisenRepository.findByDiploma(diploma);
  }
  
}
