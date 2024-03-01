package com.mutabeni.store.controller;

import com.mutabeni.store.model.Product;
import com.mutabeni.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    //save product
    @PostMapping("product/upload")
    public String save(@Validated @RequestBody Product product) throws Exception {
        return productService.saveProduct(product);
    }
    //list all products
    @GetMapping("/display/listAll")
    public List<Product> lst(){
        return productService.lstProducts();
    }

    //list product by name
    @GetMapping("/display/list/{name}")
    public Optional<Product> lstbyname(@PathVariable String name){
        return productService.lstByname(name);
    }

    // edit product
    @PutMapping("product/edit")
    public String edit(@RequestBody Product product) throws Exception {
        return productService.editProduct(product);
    }
    // delete
    @DeleteMapping("product/delete/{id}")
    public String deleteP(@PathVariable Long id) throws Exception {
        return productService.delete(id);
    }
}
