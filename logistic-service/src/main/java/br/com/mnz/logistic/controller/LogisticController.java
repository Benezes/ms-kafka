package br.com.mnz.logistic.controller;

import br.com.mnz.logistic.dto.response.LogisticResponse;
import br.com.mnz.logistic.service.LogisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logistics")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class LogisticController {

    private final LogisticService logisticService;

    @GetMapping
    public ResponseEntity<List<LogisticResponse>> findAll() {
        return ResponseEntity.ok(logisticService.findAllLogisticProducts());
    }
}
