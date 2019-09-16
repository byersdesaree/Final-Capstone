package com.example.LegacyBabies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Float price;
    @NotNull
    private Boolean domestic;
    @NotNull
    private Boolean imported;
    @NotNull
    private String category;
    @NotNull
    private Integer quantity;
    @NotNull
    private String imageUrl;
    @NotNull
    private Float total;
    private Float salesTax;
    private Float importFee;

    public Items(){}

    public Items(Integer id, String name, Float price, Boolean domestic, Boolean imported, String category, Integer quantity, String imageUrl, Float total, Float salesTax, Float importFee) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.domestic = domestic;
        this.imported = imported;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.total = total;
        this.salesTax = salesTax;
        this.importFee = importFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getDomestic() {
        return domestic;
    }

    public void setDomestic(Boolean domestic) {
        this.domestic = domestic;
    }

    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(Float salesTax) {
        this.salesTax = salesTax;
    }

    public Float getImportFee() {
        return importFee;
    }

    public void setImportFee(Float importFee) {
        this.importFee = importFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id) &&
                Objects.equals(name, items.name) &&
                Objects.equals(price, items.price) &&
                Objects.equals(domestic, items.domestic) &&
                Objects.equals(imported, items.imported) &&
                Objects.equals(category, items.category) &&
                Objects.equals(quantity, items.quantity) &&
                Objects.equals(imageUrl, items.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, domestic, imported, category, quantity, imageUrl);
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", domestic=" + domestic +
                ", imported=" + imported +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
