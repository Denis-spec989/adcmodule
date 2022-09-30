package com.denisspec.adcmodule.service.impl;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import com.denisspec.adcmodule.models.*;
import com.denisspec.adcmodule.repository.PetrolStationRepository;
import com.denisspec.adcmodule.service.PetrolStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetrolStationServiceImpl implements PetrolStationService {
    @Autowired
    private CSVConverter csvConverter;
    @Autowired
    private XmlConverter xmlConverter;
    @Autowired
    private JSONConverter jsonConverter;
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
    @Transactional
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
        List<PetrolStationDto> newPetrolStationsDtoFromFileAfterConverting = new ArrayList<>();
       if(input.getMultipartFile().getOriginalFilename().contains(".csv")){
           System.out.println("Was got .csv file");
           //get converted dtos before save
           petrolStationDtoListAfterConverting = (List<PetrolStationDto>) csvConverter.convert(new CSV(input.getMultipartFile()));
           for(PetrolStationDto petrolStationDto:petrolStationDtoListAfterConverting){
               if(petrolStationRepository.findByNameAndRegion(petrolStationDto.getName(),petrolStationDto.getRegion()).isEmpty()){
                   newPetrolStationsDtoFromFileAfterConverting.add(petrolStationDto);
               }
           }
           save(newPetrolStationsDtoFromFileAfterConverting);
       }
        if(input.getMultipartFile().getOriginalFilename().contains(".json")){
            System.out.println("Was got .json file");
            //get converted dtos before save
            petrolStationDtoListAfterConverting = (List<PetrolStationDto>)  jsonConverter.convert(new JSON(input.getMultipartFile()));
            for(PetrolStationDto petrolStationDto:petrolStationDtoListAfterConverting){
                if(petrolStationRepository.findByNameAndRegion(petrolStationDto.getName(),petrolStationDto.getRegion()).isEmpty()){
                    newPetrolStationsDtoFromFileAfterConverting.add(petrolStationDto);
                }
            }
            save(newPetrolStationsDtoFromFileAfterConverting);
        }
        if(input.getMultipartFile().getOriginalFilename().contains(".xml")){
            System.out.println("Was got .xml file");
            //get converted dtos before save
            petrolStationDtoListAfterConverting = (List<PetrolStationDto>) xmlConverter.convert(new XML(input.getMultipartFile()));
            for(PetrolStationDto petrolStationDto:petrolStationDtoListAfterConverting){
                if(petrolStationRepository.findByNameAndRegion(petrolStationDto.getName(),petrolStationDto.getRegion()).isEmpty()){
                    newPetrolStationsDtoFromFileAfterConverting.add(petrolStationDto);
                }
            }
            save(newPetrolStationsDtoFromFileAfterConverting);
        }
    }

    @Override
    @Transactional
    public List<PetrolStationEntity> getAllPetrolStations() {
        return petrolStationRepository.findAll();
    }
}
