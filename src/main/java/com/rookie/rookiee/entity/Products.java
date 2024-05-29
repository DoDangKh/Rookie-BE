package com.rookie.rookiee.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "products_id"), inverseJoinColumns = @JoinColumn(name = "categories_id"))
    Set<Categories> categories;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Images> images;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Rates> rates;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Carts> carts;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Orders_Products> orders_products;

    @Override
    public boolean isNew() {
        return true;
    }

    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj)
    // return true;
    // if (obj == null)
    // return false;
    // if (getClass() != obj.getClass())
    // return false;
    // Products other = (Products) obj;
    // if (id == null) {
    // if (other.id != null)
    // return false;
    // } else if (!id.equals(other.id))
    // return false;
    // if (name == null) {
    // if (other.name != null)
    // return false;
    // } else if (!name.equals(other.name))
    // return false;
    // if (price == null) {
    // if (other.price != null)
    // return false;
    // } else if (!price.equals(other.price))
    // return false;
    // if (amount == null) {
    // if (other.amount != null)
    // return false;
    // } else if (!amount.equals(other.amount))
    // return false;
    // if (description == null) {
    // if (other.description != null)
    // return false;
    // } else if (!description.equals(other.description))
    // return false;
    // if (feature == null) {
    // if (other.feature != null)
    // return false;
    // } else if (!feature.equals(other.feature))
    // return false;
    // if (categories == null) {
    // if (other.categories != null)
    // return false;
    // } else if (!categories.equals(other.categories))
    // return false;
    // if (images == null) {
    // if (other.images != null)
    // return false;
    // } else if (!images.equals(other.images))
    // return false;
    // if (rates == null) {
    // if (other.rates != null)
    // return false;
    // } else if (!rates.equals(other.rates))
    // return false;
    // if (carts == null) {
    // if (other.carts != null)
    // return false;
    // } else if (!carts.equals(other.carts))
    // return false;
    // if (orders_products == null) {
    // if (other.orders_products != null)
    // return false;
    // } else if (!orders_products.equals(other.orders_products))
    // return false;
    // return true;
    // }

    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + ((id == null) ? 0 : id.hashCode());
    // result = prime * result + ((name == null) ? 0 : name.hashCode());
    // result = prime * result + ((price == null) ? 0 : price.hashCode());
    // result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    // result = prime * result + ((description == null) ? 0 :
    // description.hashCode());
    // result = prime * result + ((feature == null) ? 0 : feature.hashCode());
    // result = prime * result + ((categories == null) ? 0 : categories.hashCode());
    // result = prime * result + ((images == null) ? 0 : images.hashCode());
    // result = prime * result + ((rates == null) ? 0 : rates.hashCode());
    // result = prime * result + ((carts == null) ? 0 : carts.hashCode());
    // result = prime * result + ((orders_products == null) ? 0 :
    // orders_products.hashCode());
    // return result;
    // }

}
