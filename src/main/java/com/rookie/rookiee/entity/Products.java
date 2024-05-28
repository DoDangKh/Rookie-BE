package com.rookie.rookiee.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private Double price;

    @Column
    private Long amount;

    @Column
    @NotBlank
    private String description;

    @Column
    private Boolean feature;

    @ManyToMany()
    @JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "products_id"), inverseJoinColumns = @JoinColumn(name = "categories_id"))
    Set<Categories> categories;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<Images> images;

    @OneToMany(mappedBy = "products")
    private Set<Rates> rates;

    @OneToMany(mappedBy = "products")
    private Set<Carts> carts;

    @OneToMany(mappedBy = "products")
    private Set<Orders_Products> orders_products;

    @Override
    public boolean isNew() {
        return true;
    }

}
