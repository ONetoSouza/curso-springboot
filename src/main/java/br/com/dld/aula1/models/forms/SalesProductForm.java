package br.com.dld.aula1.models.forms;

import br.com.dld.aula1.models.entities.Product;
import br.com.dld.aula1.models.entities.SalesProduct;
import br.com.dld.aula1.repositories.ProductRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesProductForm {


    private long productId;
    private Integer amount;

    private Double price;


    public SalesProduct convert(ProductRepository productRepository) {
        Optional<Product> product = productRepository.findById(productId);

        return new SalesProduct(product.get(), amount, price);
    }
}
