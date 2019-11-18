package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.product.Product;
import com.udacity.course3.reviews.domain.product.ProductRepository;
import com.udacity.course3.reviews.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductRepository productRepository;
    @Autowired
    public ProductsController( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @PostMapping
    ResponseEntity<?>  createProduct(@Valid @RequestBody Product product) throws URISyntaxException {
        Product resource =  productRepository.save(product);
        return new ResponseEntity(resource, HttpStatus.CREATED);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @GetMapping("/{id}")
    ResponseEntity<Product> findById(@Valid @PathVariable("id") Long id) {
        Product product = productRepository.findById(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @GetMapping
    ResponseEntity<Product> listProducts() {
        List<Product> resources = productRepository.findAll();
        return new ResponseEntity(resources, HttpStatus.OK);
    }
}
