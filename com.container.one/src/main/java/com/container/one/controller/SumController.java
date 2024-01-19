package com.container.one.controller;

import com.container.one.dtos.ErrorInput;
import com.container.one.dtos.FileInfo;
import com.container.one.dtos.SumInfo;
import com.container.one.services.BackingService;
import com.container.one.services.CleanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class SumController {

    CleanService cleanService;
    BackingService backingService;

    public SumController() {
        cleanService = new CleanService();
        backingService = new BackingService();
    }

    @PostMapping("/calculate")
    public ResponseEntity<Object> calculate(@RequestBody FileInfo fileInfo) {

        ErrorInput errorInput = cleanService.Validate(fileInfo);


        if(errorInput == null){
            SumInfo userDto = backingService.getSum(fileInfo);

            if(userDto.sum()==0)
                return ResponseEntity.ok(new ErrorInput(fileInfo.file(), "Input file not in CSV format."));

            return ResponseEntity.ok(userDto);
        }

        return ResponseEntity.ok(errorInput);
    }
}
