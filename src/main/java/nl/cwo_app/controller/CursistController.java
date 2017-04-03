/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.util.Base64;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import nl.cwo_app.entity.*;
import nl.cwo_app.repository.CursistBehaaldEisenRepository;
import nl.cwo_app.repository.CursistHeeftDiplomaRepository;
import nl.cwo_app.repository.CursistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.cwo_app.repository.DiplomaRepository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import nl.cwo_app.repository.DiplomaEisRepository;

/**
 *
 * @author Sonja
 */
@RestController
public class CursistController {

    private final CursistRepository cursistRepository;
    private final CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository;
    private final DiplomaRepository diplomaRepository;
    private final DiplomaEisRepository cwoEisenRepository;
    private final CursistBehaaldEisenRepository cursistBehaaldEisenRepository;

    // ------------------ CONSTRUCTOR --------------------------------------------
    @Autowired
    public CursistController(CursistRepository cursistRepository,
            CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository,
            DiplomaRepository diplomaRepository,
            DiplomaEisRepository cwoEisenRepository,
            CursistBehaaldEisenRepository cursistBehaaldEisenRepository) {
        this.cursistRepository = cursistRepository;
        this.cursistHeeftDiplomaRepository = cursistHeeftDiplomaRepository;
        this.diplomaRepository = diplomaRepository;
        this.cwoEisenRepository = cwoEisenRepository;
        this.cursistBehaaldEisenRepository = cursistBehaaldEisenRepository;
    }

    /**
     * Create cursist
     *
     * @param cursist
     * @param errors
     * @param response
     */
    @RequestMapping(value = "/cursist", method = RequestMethod.POST)
    public void createCursist(@RequestBody @Valid Cursist cursist, Errors errors, HttpServletResponse response) throws IOException {
        if (errors.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        if(cursist.getFotoFileBase64()!= null && cursist.getFotoFileBase64() != "") {
            //byte[] data = Base64.getDecoder().decode(cursist.getFotoFileBase64());
            cursist.setFoto(cursist.getFotoFileBase64());
        }
        

        cursistRepository.save(cursist);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Read Cursist
     *
     * @param cursistId
     * @return
     */
    @RequestMapping(value = "/cursist/{cursistId}", method = RequestMethod.GET)
    public Cursist readCursist(@PathVariable long cursistId) {
        Cursist cursist = cursistRepository.findOne(cursistId);
        cursist.getCursistHeeftDiplomas();
        cursist.getCursistBehaaldEis().forEach((cbe) -> {
            cbe.getDiplomaEis().getDiploma().setDiplomaEisen(null);
        });
        

        cursist.getCursistHeeftDiplomas().forEach(cursistHeeftDiploma -> {
            cursistHeeftDiploma.getDiploma().setDiplomaEisen(null);
        });
        return cursist;
    }

    /**
     * Update cursist, response ..
     *
     * @param cursist
     * @param errors
     * @param response
     */
    @RequestMapping(value = "/cursist", method = RequestMethod.PUT)
    public void updateCursist(@RequestBody @Valid Cursist cursist, Errors errors, HttpServletResponse response) {
        if (errors.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        if(cursist.getFotoFileBase64()!= null && cursist.getFotoFileBase64() != "") {
            cursist.setFoto(cursist.getFotoFileBase64());
        }
        
        cursistRepository.save(cursist);
        
        // Make this smarter. It now saves to db, than returns it, then saves it again. 
        // possible FIX: Change android code to pass along CursistFoto json and get cursistFoto object in that way. 
        Cursist cursistFromDb = cursistRepository.findOne(cursist.getId());
        if(cursist.getFotoFileBase64()!= null && cursist.getFotoFileBase64() != "") {
            cursistFromDb.setFoto(cursist.getFotoFileBase64());
            cursistRepository.save(cursistFromDb);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //
    @RequestMapping(value = "/cursist/{cursistId}", method = RequestMethod.DELETE)
    public void deleteCursist(@PathVariable long cursistId, HttpServletResponse response) {
        cursistRepository.delete(cursistId);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * 
     * @return list of cursisten, met behaalde diplomas en behaalde THEORIE eisen. Geen andere eisen worden meegestuurd. 
     */
    @RequestMapping(value = "/cursist/lijst", method = RequestMethod.GET)
    public List<Cursist> cursisten() {
        List<Cursist> cursisten = (List) cursistRepository.findAllByOrderByVerborgenAsc();
        // TODO http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
        cursisten.forEach((cursist) -> {
            // Delete info about diploma eisen from diploma. 
            List<CursistBehaaldEis> cursistBehaaldEisLijst = cursist.getCursistBehaaldEis();
            cursistBehaaldEisLijst.forEach((cbe) -> {
                cbe.getDiplomaEis().getDiploma().setDiplomaEisen(null);
            });
            cursist.setCursistBehaaldEis(cursistBehaaldEisLijst);

            cursist.getCursistHeeftDiplomas().forEach(cursistHeeftDiploma -> {
                cursistHeeftDiploma.getDiploma().setDiplomaEisen(null);
            });
        });
        return cursisten;
    }
    
    /**
     * 
     * @return list of cursisten, met behaalde diplomas en behaalde THEORIE eisen. Geen andere eisen worden meegestuurd. 
     */
    @RequestMapping(value = "/cursist/lijst/verborgen/false", method = RequestMethod.GET)
    public List<Cursist> partialCursisten() {
        List<Cursist> cursisten = (List) cursistRepository.findAllByVerborgen(false);
        // TODO http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
        cursisten.forEach((cursist) -> {
            // Delete info about diploma eisen from diploma. 
            List<CursistBehaaldEis> cursistBehaaldEisLijst = cursist.getCursistBehaaldEis();
            cursistBehaaldEisLijst.forEach((cbe) -> {
                cbe.getDiplomaEis().getDiploma().setDiplomaEisen(null);
            });
            cursist.setCursistBehaaldEis(cursistBehaaldEisLijst);

            cursist.getCursistHeeftDiplomas().forEach(cursistHeeftDiploma -> {
                cursistHeeftDiploma.getDiploma().setDiplomaEisen(null);
            });
        });
        return cursisten;
    }

}
