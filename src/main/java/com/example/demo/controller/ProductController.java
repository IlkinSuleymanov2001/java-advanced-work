package com.example.demo.controller;

import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.criteria.PageCriteria;
import com.example.demo.model.criteria.ProductCriteria;
import com.example.demo.model.request.product.CreateProductRequest;
import com.example.demo.model.response.ProductResponse;
import com.example.demo.model.response.page.PageableResponse;
import com.example.demo.service.inter.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    ProductService productService;

    @GetMapping()
    public String test() {
         return "service online ";
    }


    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public ResponseModel<ProductResponse> create(@RequestBody CreateProductRequest createProductRequest) {
        ProductResponse product = productService.createProduct(createProductRequest);
        return   ResponseModel.
                <ProductResponse>builder()
                .data(product)
                .build();
    }

    @GetMapping("/all")
    @ResponseStatus(OK)
    public ResponseModel<PageableResponse<ProductResponse>> getAllProducts(PageCriteria pageCriteria, ProductCriteria criteria) {
        PageableResponse<ProductResponse> allProduct = productService.getAllProduct(pageCriteria, criteria);
        return   ResponseModel.
                <PageableResponse<ProductResponse>>builder()
                .data(allProduct)
                .build();
    }

}
