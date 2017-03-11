/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import nl.cwo_app.entity.*;
import nl.cwo_app.repository.CursistBehaaldEisenRepository;
import nl.cwo_app.repository.CursistHeeftDiplomaRepository;
import nl.cwo_app.repository.CursistRepository;
import nl.cwo_app.repository.CwoEisenRepository;
import nl.cwo_app.repository.DiplomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sonja
 */
@RestController
public class CursistBehaaldEisenController {
  
  private final CursistRepository cursistRepository;
  private final CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository;
  private final DiplomaRepository cwoDisciplineRepository;
  private final CwoEisenRepository cwoEisenRepository;
  private final CursistBehaaldEisenRepository cursistBehaaldEisenRepository;
  private final EntityManager entityManager;

  // ------------------ CONSTRUCTOR --------------------------------------------
  // TODO bepalen welke ik hier nodig heb.
  @Autowired
  public CursistBehaaldEisenController(CursistRepository cursistRepository,
          CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository,
          DiplomaRepository DiplomaRepository,
          CwoEisenRepository cwoEisenRepository,
          CursistBehaaldEisenRepository cursistBehaaldEisenRepository,
          EntityManager entityManager) {
    this.cursistRepository = cursistRepository;
    this.cursistHeeftDiplomaRepository = cursistHeeftDiplomaRepository;
    this.cwoDisciplineRepository = DiplomaRepository;
    this.cwoEisenRepository = cwoEisenRepository;
    this.cursistBehaaldEisenRepository = cursistBehaaldEisenRepository;
    this.entityManager = entityManager;
  }  
  
  @RequestMapping(value="/cursistBehaaldEisen", method = RequestMethod.POST)
  public void createCursistBehaaldEisen(@RequestBody Map<String, Object> json, HttpServletResponse response) {
    // TODO Kan dit niet makkelijker? Dat ik gelijk een Long haal uit de json in plaats van zo omslachtig?
    Long eisId = Long.valueOf((int) json.get("eisId"));
    Long cursistId = Long.valueOf((int) json.get("cursistId"));
    Cursist cursist = entityManager.getReference(Cursist.class, cursistId);
    CwoEisen eis = entityManager.getReference(CwoEisen.class, eisId);
    CursistBehaaldEisen cbe = new CursistBehaaldEisen(cursist, eis);
    cursistBehaaldEisenRepository.save(cbe);
    
  }
  
  @RequestMapping(value="/cursistBehaaldEisen", method = RequestMethod.DELETE)
  public void deleteCursistBehaaldEisen(@RequestBody Map<String, Object> json, HttpServletResponse response) {
    // TODO Kan dit niet makkelijker? Dat ik gelijk een Long haal uit de json in plaats van zo omslachtig?
    Long eisId = Long.valueOf((int) json.get("eisId"));
    Long cursistId = Long.valueOf((int) json.get("cursistId"));
    Cursist cursist = entityManager.getReference(Cursist.class, cursistId);
    CwoEisen eis = entityManager.getReference(CwoEisen.class, eisId);
    cursistBehaaldEisenRepository.deleteByCursistAndCwoEisen(cursist, eis);
    
    
  }  
  
}
