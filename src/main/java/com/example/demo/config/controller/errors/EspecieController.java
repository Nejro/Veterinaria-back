package com.example.demo.config.controller.errors;
import com.example.demo.dto.Especie;
import com.example.demo.service.EspecieService;
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
public class EspecieController {
    private Logger log = LoggerFactory.getLogger(EspecieController.class);

    private static final String ENITY_NAME = "especie";
    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {this.especieService = especieService;}


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/especie")
    public ResponseEntity<ResponseMessage<List<Especie>>> getAllEspecie() {
        log.debug("REST request to get all especie");
        return ResponseEntity.ok(new ResponseMessage<>(0, null, especieService.findAll()));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/especie")
    public ResponseEntity<ResponseMessage<Especie>> saveEspecie(@Valid @RequestBody Especie especie) throws ApplicationCustomException {
        log.debug("REST request to save especie : {}", especie);
        Especie especieFind = especieService.findOne(especie.getNmid());
        if(especieFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }

        Especie result = especieService.save(especie);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/especie")
    public ResponseEntity<ResponseMessage<Especie>> updateEspecie(@Valid @RequestBody Especie especie) throws ApplicationCustomException {
        log.debug("REST request to update Especie : {} ", especie);
        if(especie.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        Especie result = especieService.update(especie);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/especie/{nmid}")
    public ResponseEntity<ResponseMessage<Especie>> getEspecie(@PathVariable int nmid) throws ApplicationCustomException {
        log.debug("REST request to get Especie : {}", nmid);
        Especie especie = especieService.findOne(nmid);
        if(especie == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        return ResponseEntity.ok(new ResponseMessage<>(0,null,especie));
    }

    @DeleteMapping("/especie/{id}")
    public ResponseEntity<ResponseMessage<Especie>> delete(@PathVariable Long id) {
        log.debug("REST request to delete especie : {}", id);
        especieService.delete(id);
        return ResponseEntity.ok(new ResponseMessage<>(0, null, null));
    }
}
