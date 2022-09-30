package com.denisspec.adcmodule.controllers;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import com.denisspec.adcmodule.models.MultipartModel;
import com.denisspec.adcmodule.service.PetrolStationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crud")
public class CRUDController {
    @Autowired
    private PetrolStationService petrolStationService;
    @ApiOperation(value = " Get all petrol station data")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PetrolStationEntity>> getAllPetrolStations() throws IOException {
        return new ResponseEntity<>(petrolStationService.getAllPetrolStations(),HttpStatus.OK);
    }
}
