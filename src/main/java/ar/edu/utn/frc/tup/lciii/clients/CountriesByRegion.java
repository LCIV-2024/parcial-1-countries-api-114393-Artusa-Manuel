package ar.edu.utn.frc.tup.lciii.clients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesByRegion {
    private String name;
    private String code;
    private String region;
}
