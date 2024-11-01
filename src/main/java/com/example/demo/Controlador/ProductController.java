package com.example.demo.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Modelo.Product;
import com.example.demo.Repositorio.ProductRepository;
import com.example.demo.Xml.XmlExportService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Crear un producto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Leer todos los productos
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow();
        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());
        return ResponseEntity.ok(productRepository.save(product));
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow();
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }

    @Autowired
    private XmlExportService xmlExportService; // Inyección del servicio

    // Exportar productos como XML (para vista en árbol XML)
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String getProductsAsXml() {
        try {
            return xmlExportService.exportProductsToXml();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "<error>Failed to generate XML</error>";
        }
    }

    // Informar valor total y porcentaje
    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        List<Product> products = productRepository.findAll();
        double totalValue = products.stream().mapToDouble(Product::getPrice).sum();
        long count = products.size();
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalValue", totalValue);
        summary.put("productCount", count);
        double totalProducts = count; 
        double percentage = (totalProducts > 0) ? (5 / totalProducts) * 100 : 0;
        summary.put("percentage", percentage);
        return summary;
    }

    // Filtrar productos por categoría (para usar como filtro)
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }
}
