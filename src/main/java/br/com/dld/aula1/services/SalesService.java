package br.com.dld.aula1.services;

import br.com.dld.aula1.models.entities.Customer;
import br.com.dld.aula1.models.entities.Sales;
import br.com.dld.aula1.models.forms.SalesForm;
import br.com.dld.aula1.models.forms.SalesProductForm;
import br.com.dld.aula1.repositories.CustomerRepository;
import br.com.dld.aula1.repositories.ProductRepository;
import br.com.dld.aula1.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesProductService  salesProductService;

    @Autowired
    private ProductRepository productRepository;

    public Optional<Sales> findById(long id) {
        return salesRepository.findById(id);
    }


    public Sales save(SalesForm salesForm){

        return salesRepository.save(salesForm.converter(customerRepository, productRepository));

    }

    public void delete(long id) {
        salesRepository.deleteById(id);
    }
}
