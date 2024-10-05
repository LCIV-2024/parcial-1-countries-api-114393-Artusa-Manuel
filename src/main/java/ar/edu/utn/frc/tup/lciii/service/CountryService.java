package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.Entities.CountryEntitie;
import ar.edu.utn.frc.tup.lciii.clients.CountriesByLanguage;
import ar.edu.utn.frc.tup.lciii.clients.CountriesByRegion;
import ar.edu.utn.frc.tup.lciii.clients.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
        @Autowired
        private final CountryRepository countryRepository;
        @Autowired
        private final RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");

                return Country.builder()
                        .name((String) nameData.get("common"))
                        .code((String) countryData.get("cca3"))

                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .borders((List<String>) countryData.get("borders"))
                        .build();

        }


        private CountryDTO mapToDTO(Country country) {

                return new CountryDTO(country.getCode(), country.getName());
        }
        private CountriesByRegion mapToCountryByRegion(Country country) {

                return new CountriesByRegion(country.getCode(), country.getName(),country.getRegion());
        }
        private CountriesByLanguage mapToCountryByLanguage(Country country) {

                return new CountriesByLanguage(country.getCode(), country.getName(),country.getLanguages());
        }
        private CountryEntitie mapToEntitie(Country country) {
                return new CountryEntitie(1,country.getName(),country.getPopulation(),country.getArea(), country.getCode(),country.getRegion());

        }

        public List<CountryDTO> getCountries(){

                List<CountryDTO> countryDTOList = new ArrayList<>();

                List<Country> countries = getAllCountries();

                for (Country country : countries){

                      countryDTOList.add(mapToDTO(country));


                }
                return countryDTOList;
        }
        public List<CountryDTO> getCountriesByName(String name){
                List<CountryDTO> countryDTOList = new ArrayList<>();

                List<Country> countries = getAllCountries();

                for (Country country : countries){
                        if (country.getName().equals(name)) {
                                countryDTOList.add(mapToDTO(country));
                        }

                }
                return countryDTOList;
        }

        public List<CountriesByRegion> getCountriesByRegion(String region){
                List<CountriesByRegion>countriesByRegions =new ArrayList<>();

                List<Country> countries =getAllCountries();
                for (Country country : countries){
                        if (country.getRegion().equals(region)){
                                countriesByRegions.add(mapToCountryByRegion(country));
                        }
                }
                return countriesByRegions;
        }

        public List<CountriesByLanguage> getCountriesByLanguage(String language) {
                List<CountriesByLanguage> countriesByLanguages = new ArrayList<>();

                List<Country> countries = getAllCountries();

                for (Country country : countries) {

                        if (country.getLanguages() != null && country.getLanguages().containsValue(language)) {
                                countriesByLanguages.add(mapToCountryByLanguage(country));
                        }
                }

                return countriesByLanguages;
        }
        public CountryDTO getCountryWithMostBorders() {
                List<Country> countries = getAllCountries();


                CountryDTO countryWithMostBorders = new CountryDTO();
                int maxBorders = 0;

                for (Country country : countries) {
                        int numberOfBorders = country.getBorders() != null ? country.getBorders().size() : 0;
                        if (numberOfBorders > maxBorders) {
                                maxBorders = numberOfBorders;
                                countryWithMostBorders = mapToDTO(country);
                        }
                }


                return countryWithMostBorders;
        }
        public List<CountryDTO> saveRandomCountries(Integer amount) {
                List<Country> countries = getAllCountries();

                Collections.shuffle(countries);

                List<Country> selectedCountries = countries.subList(0, Math.min(amount, 10));
                List<CountryDTO>response = new ArrayList<>();

                for (Country country : selectedCountries) {
                        countryRepository.save(mapToEntitie(country));

                        CountryDTO countryDTO = mapToDTO(country);
                        response.add(countryDTO);
                }

                return response;
        }




}