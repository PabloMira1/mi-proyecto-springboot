package com.example.demo.Xml;


import com.example.demo.Modelo.Product;
import com.example.demo.Repositorio.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

@Service
public class XmlExportService {

    @Autowired
    private ProductRepository productRepository; // Asegúrate de tener tu repositorio inyectado

    public String exportProductsToXml() throws JAXBException {
        List<Product> products = productRepository.findAll(); // Obtener todos los productos
        ProductListWrapper wrapper = new ProductListWrapper(products); // Envolver en ProductListWrapper

        JAXBContext jaxbContext = JAXBContext.newInstance(ProductListWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();   
        marshaller.marshal(wrapper, writer); // Convertir a XML
        
        return writer.toString(); // Devolver el XML como cadena
    }
}
