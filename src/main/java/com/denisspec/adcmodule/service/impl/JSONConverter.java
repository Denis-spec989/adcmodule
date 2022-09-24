package com.denisspec.adcmodule.service.impl;


import com.denisspec.adcmodule.models.JSON;
import com.denisspec.adcmodule.models.PetrolStationDto;
import com.denisspec.adcmodule.service.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class JSONConverter implements Converter<JSON, Iterable<PetrolStationDto>> {
    @Override
    public Iterable<PetrolStationDto> convert(JSON input) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(input.getMultipartFile().getBytes());
        String finalString = new String(stream.toByteArray());
        ArrayList<PetrolStationDto> stations = new ArrayList<PetrolStationDto>();
        stations = new ObjectMapper().readValue(finalString, new TypeReference<ArrayList<PetrolStationDto>>() {});
        return stations;
    }

    @Override
    public String getInputType() {
        return JSON.class.getName();
    }

    @Override
    public String getOutputType() {
        return Iterable.class.getName();
    }
}
