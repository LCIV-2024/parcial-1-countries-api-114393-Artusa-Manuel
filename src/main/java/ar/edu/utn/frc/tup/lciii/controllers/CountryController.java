package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.clients.CountriesByLanguage;
import ar.edu.utn.frc.tup.lciii.clients.CountriesByRegion;
import ar.edu.utn.frc.tup.lciii.clients.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private final CountryService countryService;


    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getCountiresByName(@RequestParam(required = false) String name){
        if (name != null) {
            return ResponseEntity.ok(countryService.getCountriesByName(name));
        }
        return ResponseEntity.ok(countryService.getCountries());
    }

    @GetMapping("/countries/{continent}/continent")
    public ResponseEntity<List<CountriesByRegion>> getCountriesByRegion(@PathVariable String continent){
        return ResponseEntity.ok(countryService.getCountriesByRegion(continent));
    }
    @GetMapping("/countries/{language}/language")
    public ResponseEntity<List<CountriesByLanguage>> getCountriesByLanguage(@PathVariable String language){
        return ResponseEntity.ok(countryService.getCountriesByLanguage(language));
    }
    @GetMapping("/countries/most-borders")
    public  ResponseEntity<CountryDTO>getMostBorders(){
        return ResponseEntity.ok(countryService.getCountryWithMostBorders());
    }
    @PostMapping("/countries")
    public ResponseEntity<List<CountryDTO>>postCountries(Integer amount){
        return ResponseEntity.ok(countryService.saveRandomCountries(amount));
    }

}