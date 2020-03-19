package msa.mouhamedndoyem2gl.dao;

import msa.mouhamedndoyem2gl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mouhamed NDOYE M2GL
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    // récupère un produit par son id
    Product findById(int id);

    // récupère la liste des produits dont le prix est supérieur à celle indiqué
    List<Product> findByPrixGreaterThan(int prixLimit);

    // rechercher un produit par son nom
    List<Product> findByNomLike(String recherche);

    // trié l'ensemble des produits par ordre alphabétique du nom
    List<Product> findAllByOrderByNom();
}
