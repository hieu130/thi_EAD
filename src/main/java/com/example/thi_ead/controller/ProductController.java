package com.example.thi_ead.controller;

import com.example.thi_ead.entity.Product;
import com.example.thi_ead.repository.SaleRepository;
import com.example.thi_ead.service.ProductService;
import io.swagger.models.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductService productService;
    @Autowired
    SaleRepository saleRepository;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> processSaveCategory(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "20") int limit,
                          Model model) {
        model.addAttribute("Pageable", productService.findAll(page, limit));
        return "/list";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();
        existProduct.setProductName(product.getProductName());
        return ResponseEntity.ok(productService.save(existProduct));
    }
}
