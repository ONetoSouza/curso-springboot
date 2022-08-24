package br.com.dld.aula1.models.forms;

import br.com.dld.aula1.models.entities.Customer;
import br.com.dld.aula1.models.entities.Sales;
import br.com.dld.aula1.models.entities.SalesProduct;
import br.com.dld.aula1.repositories.CustomerRepository;
import br.com.dld.aula1.repositories.ProductRepository;
import br.com.dld.aula1.repositories.SalesRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesForm {

    private long customerId;

    private List<SalesProductForm> products = new ArrayList<>();

    private Double value;

    public Sales converter(CustomerRepository customerRepository, ProductRepository productRepository) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        value = 0.0;

        List<SalesProduct> salesProductsSave = products.stream()
                .map(item -> {
                    SalesProductForm salesProductForm = item;
                    value = value + salesProductForm.getAmount() * salesProductForm.getPrice();
                    return salesProductForm.convert(productRepository);
                }).collect(Collectors.toList());

        return new Sales(customer.get(), salesProductsSave, value);

    }


}
