package com.denisspec.adcmodule.service.impl;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import com.denisspec.adcmodule.models.CSV;
import com.denisspec.adcmodule.models.MultipartModel;
import com.denisspec.adcmodule.models.PetrolStationDto;
import com.denisspec.adcmodule.repository.PetrolStationRepository;
import com.denisspec.adcmodule.service.PetrolStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetrolStationServiceImpl implements PetrolStationService {
    @Autowired
    private CSVConverter csvConverter;
    @Autowired
     private PetrolStationRepository petrolStationRepository;
    PetrolStationEntity dtoToEntity(PetrolStationDto petrolStationDto) {
        return new PetrolStationEntity(
                petrolStationDto.getAddress(),
                petrolStationDto.getLatitude(),
                petrolStationDto.getLongtitude(),
                petrolStationDto.getName(),
                petrolStationDto.getCountry(),
                petrolStationDto.getPhone(),
                petrolStationDto.getRegion()
        );
    }
    @Override
    public void save(PetrolStationDto petrolStationDto) {
        petrolStationRepository.save(
                dtoToEntity(petrolStationDto)
        );
    }

    @Override
    public void save(Iterable<PetrolStationDto> petrolStationDtos) {
        ArrayList<PetrolStationEntity> petrolStationEntities = new ArrayList<>();
        for (PetrolStationDto dto : petrolStationDtos) {
            petrolStationEntities.add(
                    dtoToEntity(dto)
            );
        }
        petrolStationRepository.saveAll(petrolStationEntities);
    }

    @Override
    public void load(MultipartModel input) throws IOException {
        List<PetrolStationDto> petrolStationDtoListAfterConverting = new ArrayList<>();
       if(input.getMultipartFile().getOriginalFilename().contains(".csv")){
           System.out.println("Was got .csv file");
           //get converted dtos before save
           petrolStationDtoListAfterConverting = (List<PetrolStationDto>) csvConverter.convert(new CSV(input.getMultipartFile()));
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
