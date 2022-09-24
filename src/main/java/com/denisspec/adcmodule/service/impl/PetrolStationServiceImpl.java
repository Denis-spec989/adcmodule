package com.denisspec.adcmodule.service.impl;

import com.denisspec.adcmodule.models.MultipartModel;
import com.denisspec.adcmodule.models.PetrolStationDto;
import com.denisspec.adcmodule.service.PetrolStationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetrolStationServiceImpl implements PetrolStationService {
    @Override
    public void save(PetrolStationDto patrolStationDto) {

    }

    @Override
    public void save(Iterable<PetrolStationDto> patrolStationDtos) {

    }

    @Override
    public void load(MultipartModel input) throws IOException {
        List<PetrolStationDto> petrolStationDtoListAfterConverting = new ArrayList<>();
       if(input.getMultipartFile().getOriginalFilename().contains(".csv")){
           System.out.println("Was got .csv file");
           //get converted dtos before save
           save(petrolStationDtoListAfterConverting);
       }
        if(input.getMultipartFile().getOriginalFilename().contains(".json")){
            System.out.println("Was got .json file");
            //get converted dtos before save
            save(petrolStationDtoListAfterConverting);
        }
        if(input.getMultipartFile().getOriginalFilename().contains(".xml")){
            System.out.println("Was got .xml file");
            //get converted dtos before save
            save(petrolStationDtoListAfterConverting);
        }
    }
}
