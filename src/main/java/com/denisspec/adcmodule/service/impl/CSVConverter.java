package com.denisspec.adcmodule.service.impl;

import com.denisspec.adcmodule.models.CSV;
import com.denisspec.adcmodule.models.PetrolStationDto;
import com.denisspec.adcmodule.service.Converter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class CSVConverter implements Converter<CSV, Iterable<PetrolStationDto>> {
    @Override
    public Iterable<PetrolStationDto> convert(CSV input) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(input.getMultipartFile().getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|'))) {
            ArrayList<PetrolStationDto> stations = new ArrayList<PetrolStationDto>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                PetrolStationDto station = new PetrolStationDto(
                        csvRecord.get("address").trim(),
                        Double.parseDouble(csvRecord.get("latitude")),
                        Double.parseDouble(csvRecord.get("longtitude")),
                        csvRecord.get("name").trim(),
                        csvRecord.get("country").trim(),
                        csvRecord.get("phone").trim(),
                        csvRecord.get("region").trim()
                );

                stations.add(station);
            }

            return stations;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    @Override
    public String getInputType() {
        return CSV.class.getName();
    }

    @Override
    public String getOutputType() {
        return Iterable.class.getName();
    }
}
