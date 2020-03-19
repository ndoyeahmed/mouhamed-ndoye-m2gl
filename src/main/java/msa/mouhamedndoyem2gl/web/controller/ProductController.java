package msa.mouhamedndoyem2gl.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import msa.mouhamedndoyem2gl.dao.ProductDao;
import msa.mouhamedndoyem2gl.model.Product;
import msa.mouhamedndoyem2gl.web.exceptions.ProduitGratuitException;
import msa.mouhamedndoyem2gl.web.exceptions.ProduitIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller de la classe Product
 *
 * @author Mouhamed NDOYE M2GL
 */
@Api("API pour les opérations de CRUD sur les produits")
@RestController
public class ProductController {

    private ProductDao productDao;

    // injection de dépendance par setter
    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * Mouhamed NDOYE M2GL
     * Récupère la liste des produits
     *
     * @return retourne la liste de tous les produits
     */
    @GetMapping("/produits")
    @ApiOperation(value = "Retourne la liste des produits en stock")
    public MappingJacksonValue listeProduits() {
        Iterable<Product> produits = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }

    /**
     * Mouhamed NDOYE M2GL
     * Récupère un produit par son ID
     *
     * @param id du produit à afficher
     * @return retourne le produit à afficher s'il existe
     */
    @ApiOperation(value = "Retourne un produit grace à son ID à condition que le produit existe")
    @GetMapping("/produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        Product product = productDao.findById(id);

        if (product == null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est introuvable");

        return product;
    }

    /**
     * Mouhamed NDOYE M2GL
     * test les produits supérieur au prix indiqué
     * @param prixLimit le prix limite pour filtrer les produits
     * @return retourne une liste de produit supérieur au prix indiqué
     */
    @ApiOperation(value = "Retourne une liste de produits dont le prix est supérieur au prix indiqué")
    @GetMapping("test/produits/{prixLimit}")
    public List<Product> testRequest(@PathVariable int prixLimit) {
        return productDao.findByPrixGreaterThan(prixLimit);
    }

    /**
     * Mouhamed NDOYE M2GL
     * Récupère un produit par le nom
     * @param recherche le nom à chercher
     * @return retourne  la liste des produits contenant le critère de recherche
     */
    @ApiOperation(value = "Retourne la liste des produits contenant le critère de recherche par le nom du produit")
    @GetMapping("test/produits/{recherche}")
    public List<Product> testRequestNom(@PathVariable String recherche) {
        return productDao.findByNomLike("%" + recherche + "%");
    }

    /**
     * Mouhamed NDOYE M2GL
     * ajouter un produit
     * @param product le nouveau produit à ajouter
     * @return
     */
    @ApiOperation(value = "Permet d'ajouter un produit")
    @PostMapping("/produits")
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {
        if (product.getPrix() == 0)
            throw new ProduitGratuitException("Le produit est gratuit");

        Product productAdded = productDao.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Mouhamed NDOYE M2GL
     * supprimer un produit
     * @param id l'id du produit à supprimer
     */
    @ApiOperation(value = "Supprimer un produit")
    @DeleteMapping("/produits/{id}")
    public void supprimerProduit(@PathVariable int id) {
        productDao.delete(productDao.findById(id));
    }

    /**
     * Mouhamed NDOYE M2GL
     * mettre à jour un produit
     *
     * @param product le produit à mettre à jour
     */
    @ApiOperation(value = "Mettre à jour les informations d'un produit")
    @PutMapping("/produits")
    public void updateProduit(@Valid @RequestBody Product product) {
        productDao.save(product);
    }

    /**
     * Mouhamed NDOYE M2GL
     * trie liste par nom
     *
     * @return retourne la liste des produits triés
     */
    @GetMapping("/TriProduits")
    @ApiOperation(value = "Retourne la liste des produits triées en stock")
    public MappingJacksonValue trierProduitsParOrdreAlphabetique() {
        Iterable<Product> produits = productDao.findAllByOrderByNom();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }

    /**
     * Mouhamed NDOYE M2GL
     * calcul la marge du produit
     *
     * @return retourne une liste de clés et valeurs (produit -> clé, marge -> valeur
     */
    @GetMapping("/AdminProduits")
    @ApiOperation(value = "calcul la marge des produits")
    public ResponseEntity<?> calculerMargeProduit() {
        Iterable<Product> products = productDao.findAll();
        Map<String, Integer> produits = new HashMap<>();
        products.forEach(produit -> produits.put(produit.toString(), (produit.getPrix() - produit.getPrixAchat())));
        return ResponseEntity.ok(produits);
    }
}
