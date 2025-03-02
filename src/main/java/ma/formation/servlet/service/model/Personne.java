package ma.formation.servlet.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personne {
    private Integer id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private Boolean marie = false;
    private Integer nbEnfants;
}
