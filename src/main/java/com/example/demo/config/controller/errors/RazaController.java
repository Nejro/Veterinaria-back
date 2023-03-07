package com.example.demo.config.controller.errors;


import com.example.demo.dto.Raza;
import com.example.demo.service.RazaService;
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
public class RazaController {
    private Logger log = LoggerFactory.getLogger(RazaController.class);

    private static final String ENITY_NAME = "raza";
    private final RazaService razaService;

    public RazaController(RazaService razaService) {this.razaService = razaService;}


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/raza")
    public ResponseEntity<ResponseMessage<List<Raza>>> getAllRaza() {
        log.debug("REST request to get all raza");
        return ResponseEntity.ok(new ResponseMessage<>(0, null, razaService.findAll()));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/raza")
    public ResponseEntity<ResponseMessage<Raza>> saveRaza(@Valid @RequestBody Raza raza) throws ApplicationCustomException {
        log.debug("REST request to save raza : {}", raza);
        Raza razaFind = razaService.findOne(raza.getNmid());
        if(razaFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }

        Raza result = razaService.save(raza);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/raza")
    public ResponseEntity<ResponseMessage<Raza>> updateRaza(@Valid @RequestBody Raza raza) throws ApplicationCustomException {
        log.debug("REST request to update Raza : {} ", raza);
        if(raza.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        Raza result = razaService.update(raza);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/raza/{nmid}")
    public ResponseEntity<ResponseMessage<Raza>> getRaza(@PathVariable int nmid) throws ApplicationCustomException {
        log.debug("REST request to get Especie : {}", nmid);
        Raza raza = razaService.findOne(nmid);
        if(raza == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        return ResponseEntity.ok(new ResponseMessage<>(0,null, raza));
    }

    @DeleteMapping("/raza/{id}")
    public ResponseEntity<ResponseMessage<Raza>> delete(@PathVariable Long id) {
        log.debug("REST request to delete raza : {}", id);
        razaService.delete(id);
        return ResponseEntity.ok(new ResponseMessage<>(0, null, null));
    }
}
