package com.example.demo.config.controller.errors;
import com.example.demo.dto.Especie;
import com.example.demo.dto.Paciente;
import com.example.demo.service.EspecieService;
import com.example.demo.service.PacienteService;
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
public class PacienteController {

    private Logger log = LoggerFactory.getLogger(PacienteController.class);

    private static final String ENITY_NAME = "paciente";
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {this.pacienteService = pacienteService;}


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/paciente")
    public ResponseEntity<ResponseMessage<List<Paciente>>> getAllPaciente() {
        log.debug("REST request to get all paciente");
        return ResponseEntity.ok(new ResponseMessage<>(0, null, pacienteService.findAll()));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/paciente")
    public ResponseEntity<ResponseMessage<Paciente>> savePaciente(@Valid @RequestBody Paciente paciente) throws ApplicationCustomException {
        log.debug("REST request to save paciente : {}", paciente);
        Paciente pacienteFind = pacienteService.findOne(paciente.getNmid());
        if(pacienteFind != null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_ALREADY_EXISTS, ENITY_NAME));
        }

        Paciente result = pacienteService.save(paciente);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/paciente")
    public ResponseEntity<ResponseMessage<Paciente>> updatePaciente(@Valid @RequestBody Paciente paciente) throws ApplicationCustomException {
        log.debug("REST request to update Especie : {} ", paciente);
        if(paciente.getNmid() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        Paciente result = pacienteService.update(paciente);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/paciente/{nmid}")
    public ResponseEntity<ResponseMessage<Paciente>> getPaciente(@PathVariable int nmid) throws ApplicationCustomException {
        log.debug("REST request to get Paciente : {}", nmid);
        Paciente paciente = pacienteService.findOne(nmid);
        if(paciente == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        return ResponseEntity.ok(new ResponseMessage<>(0,null,paciente));
    }

    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<ResponseMessage<Especie>> delete(@PathVariable Long id) {
        log.debug("REST request to delete paciente : {}", id);
        pacienteService.delete(id);
        return ResponseEntity.ok(new ResponseMessage<>(0, null, null));
    }
}
