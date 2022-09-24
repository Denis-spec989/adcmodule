package com.denisspec.adcmodule.controllers;

import com.denisspec.adcmodule.models.MultipartModel;
import com.denisspec.adcmodule.repository.PetrolStationRepository;
import com.denisspec.adcmodule.service.PetrolStationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/csv")
public class DownloadFileController {
    @Autowired
    private PetrolStationService petrolStationService;
    @ApiOperation(value = " Download new file")
    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<Object> createStationCsv(@RequestParam("file") MultipartFile file) throws IOException {
        petrolStationService.load(
                new MultipartModel(file)
        );
        return new ResponseEntity<>("Start downloading file",HttpStatus.CREATED);
    }
}
