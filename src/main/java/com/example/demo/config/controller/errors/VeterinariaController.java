package com.example.demo.config.controller.errors;

import com.example.demo.dto.veterinaria;
import com.example.demo.service.VeterinariaService;
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
public class VeterinariaController {
    private Logger log = LoggerFactory.getLogger(VeterinariaController.class);

    private static final String ENITY_NAME = "veterinaria";
    private final VeterinariaService veterinariaService;

    public VeterinariaController(VeterinariaService veterinariaService) {this.veterinariaService = veterinariaService;}


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/veterinaria")
    public ResponseEntity<ResponseMessage<List<veterinaria>>> getAllVeterinaria() {
        log.debug("REST request to get all veterinaria");
        return ResponseEntity.ok(new ResponseMessage<>(0, null, veterinariaService.findAll()));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/veterinaria")
    public ResponseEntity<ResponseMessage<veterinaria>> saveVeterinaria(@Valid @RequestBody veterinaria Veterinaria) throws ApplicationCustomException {
        log.debug("REST request to save Veterinaria : {}", Veterinaria);
        veterinaria VeterinariaFind = veterinariaService.findOne(Veterinaria.getNmid());
        if(VeterinariaFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }

        veterinaria result = veterinariaService.save(Veterinaria);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/veterinaria")
    public ResponseEntity<ResponseMessage<veterinaria>> updateVeterinaria(@Valid @RequestBody veterinaria Veterinaria) throws ApplicationCustomException {
        log.debug("REST request to update Veterinaria : {} ", Veterinaria);
        if(Veterinaria.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        veterinaria result = veterinariaService.update(Veterinaria);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/veterinaria/{nmid}")
    public ResponseEntity<ResponseMessage<veterinaria>> getVeterinaria(@PathVariable int nmid) throws ApplicationCustomException {
        log.debug("REST request to get veterinaria : {}", nmid);
        veterinaria Veterinaria = veterinariaService.findOne(nmid);
        if(Veterinaria == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        return ResponseEntity.ok(new ResponseMessage<>(0,null,Veterinaria));
    }

    @DeleteMapping("/veterinaria/{id}")
    public ResponseEntity<ResponseMessage<veterinaria>> delete(@PathVariable Long id) {
        log.debug("REST request to delete Veterinaria : {}", id);
        veterinariaService.delete(id);
        return ResponseEntity.ok(new ResponseMessage<>(0, null, null));
    }

}
