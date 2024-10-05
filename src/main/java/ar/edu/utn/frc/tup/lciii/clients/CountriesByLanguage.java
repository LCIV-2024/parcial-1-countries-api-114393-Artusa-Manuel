package ar.edu.utn.frc.tup.lciii.clients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesByLanguage {
    private String name;
    private String code;
    private Map<String, String> languages;
}
