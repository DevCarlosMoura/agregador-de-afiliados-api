package devcarlosmoura.github.io.e_commerce.services;

import devcarlosmoura.github.io.e_commerce.dtos.ProductDTO;
import devcarlosmoura.github.io.e_commerce.dtos.ProductRequestDTO;
import devcarlosmoura.github.io.e_commerce.entities.Product;
import devcarlosmoura.github.io.e_commerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDTO create( ProductRequestDTO request) {
        Product product = new Product();
        product.setTitle(request.title());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setImageUrl(request.imageUrl());
        product.setAffiliateLink(request.affiliateLink());

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }

    @Transactional
    public ProductDTO update(UUID id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.setTitle(productRequestDTO.title());
        product.setDescription(productRequestDTO.description());
        product.setPrice(productRequestDTO.price());
        product.setImageUrl(productRequestDTO.imageUrl());
        product.setAffiliateLink(productRequestDTO.affiliateLink());

        return new ProductDTO(product);
    }

    @Transactional
    public void delete(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> result = productRepository.findAll();
        return result.stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return new ProductDTO(product);
    }
}

