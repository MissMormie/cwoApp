/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import nl.cwo_app.entity.Cursist;
import nl.cwo_app.repository.CursistBehaaldEisenRepository;
import nl.cwo_app.repository.CursistHeeftDiplomaRepository;
import nl.cwo_app.repository.CursistRepository;
import nl.cwo_app.repository.CwoEisenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.cwo_app.repository.DiplomaRepository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Sonja
 */
@RestController
public class CursistController {
    private final CursistRepository cursistRepository;
  private final CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository;
  private final DiplomaRepository cwoDisciplineRepository;
  private final CwoEisenRepository cwoEisenRepository;
  private final CursistBehaaldEisenRepository cursistBehaaldEisenRepository;

  // ------------------ CONSTRUCTOR --------------------------------------------
  @Autowired
  public CursistController(CursistRepository cursistRepository,
          CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository,
          DiplomaRepository cwoDisciplineRepository,
          CwoEisenRepository cwoEisenRepository,
          CursistBehaaldEisenRepository cursistBehaaldEisenRepository) {
    this.cursistRepository = cursistRepository;
    this.cursistHeeftDiplomaRepository = cursistHeeftDiplomaRepository;
    this.cwoDisciplineRepository = cwoDisciplineRepository;
    this.cwoEisenRepository = cwoEisenRepository;
    this.cursistBehaaldEisenRepository = cursistBehaaldEisenRepository;
  }
  
  
  /**
   * Create cursist 
   * @param cursist
   * @param errors
   * @param response 
   */
  @RequestMapping(value="/cursist/new", method = RequestMethod.POST)
  public void createCursist(@RequestBody @Valid Cursist cursist, Errors errors, HttpServletResponse response) {
    if(errors.hasErrors()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    cursistRepository.save(cursist);
    response.setStatus(HttpServletResponse.SC_OK);
  }

  /**
   * Read Cursist
   * @param cursistId
   * @return 
   */
  @RequestMapping(value="/cursist/{cursistId}", method = RequestMethod.GET)
  public Cursist readCursist(@PathVariable long cursistId) {
    Cursist c = cursistRepository.findOne(cursistId);
    return c;
  }  
  
  /**
   * Update cursist, repsonse .. 
   * @param cursist
   * @param errors
   * @param response 
   */
  @RequestMapping(value="/cursist/", method = RequestMethod.PUT)
  public void updateCursist(@RequestBody @Valid Cursist cursist, Errors errors, HttpServletResponse response) {
    if(errors.hasErrors()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    cursistRepository.save(cursist);
    response.setStatus(HttpServletResponse.SC_OK);
  }  
  
  
  //
  @RequestMapping(value="/cursist/{cursistId}", method = RequestMethod.DELETE)
  public void deleteCursist(@PathVariable long cursistId, HttpServletResponse response) {
    cursistRepository.delete(cursistId);
    response.setStatus(HttpServletResponse.SC_OK);
  }

  
  @RequestMapping(value="/cursisten", method = RequestMethod.GET)
  public List<Cursist> cursisten() {
    List<Cursist> cursisten = (List) cursistRepository.findAll();
    return cursisten;
  }
  
  @RequestMapping(value="/cursisten/eisenbehaald", method = RequestMethod.GET)
  public List<Cursist> cursistenEisenBehaald() {
    List<Cursist> cursisten = (List) cursistRepository.findAll();
    return cursisten;

  }
  
  
  
}
