package com.example.thi_ead.controller;

import com.example.thi_ead.entity.Product;
import com.example.thi_ead.entity.Sale;
import com.example.thi_ead.repository.ProductRepository;
import com.example.thi_ead.service.SaleService;
import io.swagger.models.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/sale")
public class SaleController {

    final SaleService saleService;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Sale> optionalSale = saleService.findById(id);
        if (!optionalSale.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalSale.get());
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Sale> create(@RequestBody Sale sale) {
        Optional<Product> product = productRepository.findById(sale.getSaleId());
        if (!product.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(saleService.save(sale));
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     Model model) {
        return ResponseEntity.ok(model.addAttribute("Pageable", saleService.findAll(page, limit)));
    }
}
