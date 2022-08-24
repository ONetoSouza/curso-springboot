package br.com.dld.aula1.models.entities;

import br.com.dld.aula1.models.enums.UN;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String unity;

    @Column(length = 15, nullable = false)
    private Integer amount;

    @Column(length = 15, nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
