/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import nl.cwo_app.entity.*;
import nl.cwo_app.repository.CursistBehaaldEisenRepository;
import nl.cwo_app.repository.CursistHeeftDiplomaRepository;
import nl.cwo_app.repository.CursistRepository;
import nl.cwo_app.repository.DiplomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.cwo_app.repository.DiplomaEisRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Sonja
 */
@RestController
public class CursistBehaaldEisenController {

    private final CursistRepository cursistRepository;
    private final CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository;
    private final DiplomaRepository cwoDisciplineRepository;
    private final DiplomaEisRepository diplomaEisRepository;
    private final CursistBehaaldEisenRepository cursistBehaaldEisenRepository;
    private final EntityManager entityManager;

    // ------------------ CONSTRUCTOR --------------------------------------------
    // TODO bepalen welke ik hier nodig heb.
    @Autowired
    public CursistBehaaldEisenController(CursistRepository cursistRepository,
            CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository,
            DiplomaRepository DiplomaRepository,
            DiplomaEisRepository diplomaEisRepository,
            CursistBehaaldEisenRepository cursistBehaaldEisenRepository,
            EntityManager entityManager) {
        this.cursistRepository = cursistRepository;
        this.cursistHeeftDiplomaRepository = cursistHeeftDiplomaRepository;
        this.cwoDisciplineRepository = DiplomaRepository;
        this.diplomaEisRepository = diplomaEisRepository;
        this.cursistBehaaldEisenRepository = cursistBehaaldEisenRepository;
        this.entityManager = entityManager;
    }

    @RequestMapping(value = "/cursistBehaaldEisen", method = RequestMethod.POST)
    public void createCursistBehaaldEisen(@RequestBody Map<String, Object> json, HttpServletResponse response) throws Exception {
        if(json.containsKey("eisId") && json.containsKey("cursistId")) {
            int eisIdInt = (int) json.get("eisId");
            Long eisId = Long.valueOf(eisIdInt);
            Long cursistId = Long.valueOf((int) json.get("cursistId"));
            DiplomaEis eis = entityManager.getReference(DiplomaEis.class, eisId);
            CursistBehaaldEis cbe = new CursistBehaaldEis(cursistId, eis);
            cursistBehaaldEisenRepository.save(cbe);
        } else {
            throw new Exception();
        }
    }

    // TODO catch errors if trying to delete something that doesn't exist. 
//    @Transactional
    @RequestMapping(value = "/cursistBehaaldEisen", method = RequestMethod.DELETE)
    public void deleteCursist(@RequestBody Map<String, Object> json, HttpServletResponse response) throws Exception {
        if(json.containsKey("eisId") && json.containsKey("cursistId")) {
            int eisIdInt = (int) json.get("eisId");
            Long eisId = Long.valueOf(eisIdInt);
            Long cursistId = Long.valueOf((int) json.get("cursistId"));
            DiplomaEis eis = entityManager.getReference(DiplomaEis.class, eisId);
            CursistBehaaldEis cbe = cursistBehaaldEisenRepository.findByCursistIdAndDiplomaEis(cursistId, eis);
            cursistBehaaldEisenRepository.delete(cbe);
        } else {
            throw new Exception();
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
