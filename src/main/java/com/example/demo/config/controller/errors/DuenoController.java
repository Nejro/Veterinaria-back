package com.example.demo.config.controller.errors;
import com.example.demo.dto.Dueno;

import com.example.demo.service.DuenoService;
import com.example.demo.util.MessagesConstants;
import com.example.demo.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api")
public class DuenoController {
    private Logger log = LoggerFactory.getLogger(DuenoController.class);

    private static final String ENITY_NAME = "dueno";
    private final DuenoService duenoService;

    public DuenoController(DuenoService duenoService) {this.duenoService = duenoService;}


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/dueno")
    public ResponseEntity<ResponseMessage<List<Dueno>>> getAllDueno() {
        log.debug("REST request to get all dueno");
        return ResponseEntity.ok(new ResponseMessage<>(0, null, duenoService.findAll()));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/dueno")
    public ResponseEntity<ResponseMessage<Dueno>> saveVeterinaria(@Valid @RequestBody Dueno dueno) throws ApplicationCustomException {
        log.debug("REST request to save dueno : {}", dueno);
        Dueno duenoFind = duenoService.findOne(dueno.getNmid());
        if(duenoFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }

        Dueno result = duenoService.save(dueno);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/dueno")
    public ResponseEntity<ResponseMessage<Dueno>> updateDueno(@Valid @RequestBody Dueno dueno) throws ApplicationCustomException {
        log.debug("REST request to update Dueno : {} ", dueno);
        if(dueno.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        Dueno result = duenoService.update(dueno);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/dueno/{nmid}")
    public ResponseEntity<ResponseMessage<Dueno>> getDueno(@PathVariable int nmid) throws ApplicationCustomException {
        log.debug("REST request to get Dueno : {}", nmid);
        Dueno dueno = duenoService.findOne(nmid);
        if(dueno == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        return ResponseEntity.ok(new ResponseMessage<>(0,null,dueno));
    }

    @DeleteMapping("/dueno/{id}")
    public ResponseEntity<ResponseMessage<Dueno>> delete(@PathVariable Long id) {
        log.debug("REST request to delete Dueno : {}", id);
        duenoService.delete(id);
        return ResponseEntity.ok(new ResponseMessage<>(0, null, null));
    }

}
