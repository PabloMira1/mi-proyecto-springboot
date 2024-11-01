package com.example.demo.Xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.Modelo.Product;

@XmlRootElement(name = "products")
public class ProductListWrapper {
    private List<Product> products;

    public ProductListWrapper() {}

    public ProductListWrapper(List<Product> products) {
        this.products = products;
    }

    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

