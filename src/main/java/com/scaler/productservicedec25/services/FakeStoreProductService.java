package com.scaler.productservicedec25.services;

import com.scaler.productservicedec25.dtos.FakeStoreProductDto;
import com.scaler.productservicedec25.models.Category;
import com.scaler.productservicedec25.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){

        this.restTemplate=restTemplate;
    }


    @Override
    public Product getSingleProduct(Long productId) {

        //call FakeStore to fetch the product by given id ==> http call
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);

        //convert FakeStoreProductDto into product
        return convertFakeStoreProductintoProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        //convert FakeStoreProductDto Array into Product List
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            products.add(convertFakeStoreProductintoProduct(fakeStoreProductDto));
        }
        return products;
    }

    //Partial update
    @Override
    public Product updateProduct(Long id, Product product) {
        //patch
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products" + id, HttpMethod.PATCH, requestCallback, responseExtractor);

        //convert FakeStoreProductDto into Product
        return convertFakeStoreProductintoProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private Product convertFakeStoreProductintoProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(category);

        return product;
    }
}
