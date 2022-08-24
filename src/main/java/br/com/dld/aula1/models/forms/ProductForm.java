package br.com.dld.aula1.models.forms;

import br.com.dld.aula1.models.entities.Category;
import br.com.dld.aula1.models.entities.Product;
import br.com.dld.aula1.models.enums.UN;
import br.com.dld.aula1.repositories.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductForm {

    private String name;
    private String unity;
    private Integer amount;
    private Double price;

    private long categoryId;


    public Product convert(CategoryRepository categoryRepository) {
        Product product = new Product();
        product.setName(name);
        product.setUnity(unity);
        product.setAmount(amount);
        product.setPrice(price);
        Optional<Category> category = categoryRepository.findById(categoryId);
        product.setCategory(category.get());

        return product;
    }


}
