package msa.mouhamedndoyem2gl.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;


/**
 * @author Mouhamed NDOYE M2GL
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
// permet d'ajouter un filtre
@JsonFilter("monFiltreDynamique")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Length(min = 3, max = 20)
    private String nom;
    @Min(value = 1)
    private int prix;
    //information que nous ne souhaitons pas exposer
    private int prixAchat;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", prixAchat=" + prixAchat +
                '}';
    }
}
