package org.iqmanager.controller;

import org.iqmanager.dto.HeaderDTO;
import org.iqmanager.models.PerformerData;
import org.iqmanager.service.performerDataService.PerformerDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.iqmanager.ApplicationP.URL_WEB;


@RestController
@Validated
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = URL_WEB)
public class HeaderController {

    private final Logger logger = LoggerFactory.getLogger(HeaderController.class);
    private final PerformerDataService performerDataService;

    public HeaderController(PerformerDataService performerDataService) {
        this.performerDataService = performerDataService;
    }


    /** Данные для хедера */
    @GetMapping("/header")
    public ResponseEntity<HeaderDTO> getUser() {
        if (performerDataService.hasUserLoginned()) {
            try {
                PerformerData performerData = performerDataService.getLoginnedAccount();
                HeaderDTO headerDTO = new HeaderDTO(performerData.getName());
                return ResponseEntity.ok(headerDTO);
            } catch (Exception e) {
                e.printStackTrace();
                logger.warn("Performer -> HeaderController -> getUser ERROR");
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
