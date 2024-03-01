package com.mutabeni.store.service;

import com.mutabeni.store.model.Product;
import com.mutabeni.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final String FOLDER_PATH="/home/bacca/IdeaProjects/mutabeni/store/src/main/java/com/mutabeni/store/images/";

    //save product
    public String saveProduct(Product product) throws Exception {
        try{
            Path path = Path.of(product.getImage());
            Product prdct = new Product();
            String filePath=FOLDER_PATH+path.getFileName();
            Files.copy(path, Path.of(filePath));

           prdct.setSerial(product.getSerial());
           prdct.setName(product.getName());
           prdct.setCategory(product.getCategory());
           prdct.setImage(filePath);
           prdct.setDescription(product.getDescription());
           prdct.setCurrency(product.getCurrency());
           prdct.setPrice(product.getPrice());
           prdct.setUser(product.getUser());
           productRepository.save(prdct);
           return "Product Uploaded";
        }catch (Exception exception){
            //throw new Exception(exception);
            System.out.println("Failed to upload the product ");
            return null;
        }
    }

    //list all products
   public List<Product> lstProducts(){
        try{
           return productRepository.findAll();
        }catch (Exception exception){
            System.out.println("There was a connection problem");
            return  null;
        }
    }

    //list product by name
    public Optional<Product> lstByname(String name){
        try {
           return productRepository.findByName(name);
        }catch (Exception e){
            System.out.println("There was a connection problem");
            return  null;
        }
    }
    // edit product
    public String editProduct(Product prequest) throws Exception {
        try{
            String serial = prequest.getSerial();
            if(productRepository.existsBySerial(serial)){
                Product existingP =  productRepository.findBySerial(serial);

                Path path = Path.of(prequest.getImage());
                Path oldPath = Path.of(existingP.getImage());
                String filePath=FOLDER_PATH+path.getFileName();
                Files.copy(path, Path.of(filePath));
                Files.deleteIfExists(oldPath);

                existingP.setName(prequest.getName());
                existingP.setCategory(prequest.getCategory());
                existingP.setImage(filePath);
                existingP.setDescription(prequest.getDescription());
                existingP.setCurrency(prequest.getCurrency());
                existingP.setPrice(prequest.getPrice());
                productRepository.save(existingP);
                return "Product edited";
            }else {
                return "Product does not exist";
            }

        }catch (Exception e){
//          throw new Exception(e);
            System.out.println("There was a connection problem");
            return  null;
        }
    }
    // delete product
    public String delete(Long id) throws Exception {
        try{
            if(productRepository.existsById(id)){
                Product existingP =  productRepository.findById(id).get();
                Path oldPath = Path.of(existingP.getImage());
                Files.deleteIfExists(oldPath);
                productRepository.deleteById(id);

                return "Product deleted";
            }else {
                return "Product does not exist";
            }

        }catch (Exception e){
            throw new Exception(e);
//            System.out.println("There was a connection problem");
//            return  null;
        }
    }
}
