package com.container.two.controller;

import com.container.two.dtos.FileInfo;
import com.container.two.dtos.SumInfo;
import com.container.two.services.CalculateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {

    CalculateService calculateService;

    public SumController() {
        this.calculateService = new CalculateService();
    }

    @PostMapping("/sum")
    public ResponseEntity<SumInfo> sum(@RequestBody FileInfo fileInfo) {
        SumInfo sumInfo = calculateService.calculateSum(fileInfo);
        return ResponseEntity.ok(sumInfo);
    }
}
