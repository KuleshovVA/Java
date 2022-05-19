package org.iqmanager.controller;

import org.iqmanager.config.security.jwt.JwtRequest;
import org.iqmanager.dto.PerformerDataDTO;
import org.iqmanager.models.Contract;
import org.iqmanager.models.PerformerData;
import org.iqmanager.service.masterService.MasterService;
import org.iqmanager.service.performerDataService.PerformerDataService;
import org.iqmanager.util.CodeGenerator;
import org.iqmanager.util.SendSMS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.iqmanager.ApplicationP.URL_WEB;


@RestController
@Validated
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = URL_WEB)
public class LogController {

    private final Logger logger = LoggerFactory.getLogger(LogController.class);
    private final PerformerDataService performerDataService;
    private final MasterService masterService;

    public LogController(PerformerDataService performerDataService, MasterService masterService) {
        this.performerDataService = performerDataService;
        this.masterService = masterService;
    }


    /** Регистрация */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody JwtRequest userAuthDataDTO) {
        try {
            performerDataService.register(userAuthDataDTO.getUsername(), userAuthDataDTO.getPassword());
            return ResponseEntity.ok("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Performer -> LogController -> register ERROR");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registration");
        }
    }

    /** Верифицировать номер */
    @PostMapping("/verification/{p}")
    public ResponseEntity<String> verification(@PathVariable("p") String phone) {
        try {
            if (performerDataService.userNotExists(phone)) {
                String code = CodeGenerator.generate();
                SendSMS.sendAuthorization(phone, code);
                return ResponseEntity.ok(code);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error verification");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Performer -> LogController -> verification ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verification");
        }
    }

    /** Добавление данных о пользователе */
    @PostMapping("/addData")
    public ResponseEntity<String> addData(@RequestBody PerformerDataDTO performerDataDTO) {
        try {
            PerformerData performerData = performerDataService.getLoginnedAccount();
            performerData.setName(performerDataDTO.getName());
            performerData.setLastName(performerDataDTO.getLastName());
            performerData.setMaster(masterService.getMasterId(performerDataDTO.getCode()));
            return ResponseEntity.ok("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Performer ->LogController -> addData ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error addData");
        }
    }

    @PostMapping("/addContract")
    public ResponseEntity<String> addContract(@RequestBody Contract contract) {
        try {
            if(performerDataService.hasUserLoginned()) {
                performerDataService.saveContract(contract);
                return ResponseEntity.ok("Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error addContract");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Performer ->LogController -> addContract ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error addContract");
        }
    }


    /** Подтверждение номера */
    @GetMapping("/userVerification")
    public ResponseEntity<String> userVerification(@RequestParam(value = "p") String phone) {
        try {
            if(!performerDataService.userNotExists(phone)) {
                String code = CodeGenerator.generate();
                SendSMS.sendAuthorization(phone, code);
                return ResponseEntity.ok(code);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("userNotExists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Performer ->LogController -> userVerification ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error userVerification");
        }
    }

    /** Сброс пароля */
    @PostMapping("/passwordReset")
    public ResponseEntity<String> passwordReset(@RequestBody @Validated JwtRequest userAuthDataDTO) {
        try {
            performerDataService.passwordReset(userAuthDataDTO.getUsername(), userAuthDataDTO.getPassword());
            return ResponseEntity.ok("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Performer ->LogController -> passwordReset ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error passwordReset");
        }
    }
}
