package br.com.dld.aula1.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sales", cascade = {CascadeType.ALL})
    private List<SalesProduct> salesProduct;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    private Double value;


    public Sales(Customer customer, List<SalesProduct> salesProduct, Double value) {
        this.customer = customer;
        this.salesProduct = salesProduct;
        this.value = value;
    }

        @PrePersist
    public void prePersist() {
        setBeforeReference();
    }

    private void setBeforeReference() {
        salesProduct.forEach(item->{
            if (item.getSales() == null) {
                item.setSales(this);
            }
        });
    }
}
