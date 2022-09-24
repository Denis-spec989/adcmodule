package com.denisspec.adcmodule.service;

import com.denisspec.adcmodule.models.MultipartModel;
import com.denisspec.adcmodule.models.PetrolStationDto;

import java.io.IOException;

public interface PetrolStationService {
    void save(PetrolStationDto patrolStationDto);

    void save(Iterable<PetrolStationDto> patrolStationDtos);
     void load(MultipartModel input) throws IOException;
}
