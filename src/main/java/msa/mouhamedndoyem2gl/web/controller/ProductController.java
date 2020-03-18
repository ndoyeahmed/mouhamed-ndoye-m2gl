package msa.mouhamedndoyem2gl.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import msa.mouhamedndoyem2gl.dao.ProductDao;
import msa.mouhamedndoyem2gl.model.Product;
import msa.mouhamedndoyem2gl.web.exceptions.ProduitIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Mouhamed NDOYE M2GL
 */
@Api("API pour les opérations de CRUD sur les produits")
@RestController
public class ProductController {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    //Récupérer la liste des produits
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

    //Récupérer un produit par son Id
    @ApiOperation(value = "Retourne un produit grace à son ID à condition que le produit existe")
    @GetMapping("/produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        Product product = productDao.findById(id);

        if (product == null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est introuvable");

        return product;
    }

    /**
     *
     * @param prixLimit le prix limite pour filtrer les produits
     * @return retourne une liste de produit supérieur au prix indiqué
     */
    @GetMapping("test/produits/{prixLimit}")
    public List<Product> testRequest(@PathVariable int prixLimit) {
        return productDao.findByPrixGreaterThan(prixLimit);
    }

    /**
     *
     * @param recherche le nom à chercher
     * @return retourne  la liste des produits contenant le critère de recherche
     */
    @GetMapping("test/produits/{recherche}")
    public List<Product> testRequestNom(@PathVariable String recherche) {
        return productDao.findByNomLike("%" + recherche + "%");
    }

    /**
     * ajouter un produit
     * @param product le nouveau produit à ajouter
     * @return
     */
    @PostMapping("/produits")
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {
        Product productAdded =  productDao.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * supprimer un produit
     * @param id l'id du produit à supprimer
     */
    @DeleteMapping("/produits/{id}")
    public void supprimerProduit(@PathVariable int id) {
        productDao.delete(productDao.findById(id));
    }

    /**
     * mettre à jour un produit
     * @param product le produit à mettre à jour
     */
    @PutMapping("/produits")
    public void updateProduit(@Valid @RequestBody Product product) {
        productDao.save(product);
    }
}
