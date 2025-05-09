package mk.finki.ukim.mk.lab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Country {
    //id (Long), name (String), continent (String)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String continent;

    public Country() {}
    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
