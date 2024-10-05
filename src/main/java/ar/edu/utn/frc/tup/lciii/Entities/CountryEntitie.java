package ar.edu.utn.frc.tup.lciii.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryEntitie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long population;
    private double area;
    private String code;
    private String region;

}
