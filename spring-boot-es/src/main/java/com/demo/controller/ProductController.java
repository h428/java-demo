package com.demo.controller;

import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import java.util.List;
import java.util.Random;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static Random random = new Random();

    @PostMapping("index")
    public Boolean index() {
        return this.elasticsearchTemplate.createIndex(Product.class);
    }

    @DeleteMapping("index")
    public Boolean deleteIndex() {
        return this.elasticsearchTemplate.deleteIndex(Product.class);
    }

    @PutMapping("index")
    public Boolean reIndex() {
        return this.elasticsearchTemplate.deleteIndex(Product.class)
            && this.elasticsearchTemplate.createIndex(Product.class);
    }

    @PostMapping("init")
    public Iterable<Product> init() {
        // 初始化 100 个商品数据
        List<Product> productList = Product.randomBatch(100);
        return this.productRepository.saveAll(productList);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return this.productRepository.save(product);
    }

    @PostMapping("random")
    public Product save() {
        return this.productRepository.save(Product.randomInstance(random.nextInt() >>> 1));
    }


    @PostMapping("id/{id}")
    public Product save(@PathVariable Integer id) {
        return this.productRepository.save(Product.randomInstance(id));
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        this.productRepository.deleteById(id);
        return true;
    }

    @GetMapping("{id}")
    public Product get(@PathVariable Long id) {
        return this.productRepository.findById(id).get();
    }

    @GetMapping
    public Iterable<Product> list() {
        return this.productRepository.findAll();
    }

    @GetMapping("page")
    public Page<Product> page(
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "5") int size) {
        return this.productRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("search/{key}")
    public Page<Product> search(@PathVariable String key,
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "5") int size) {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery( QueryBuilders.matchQuery("name", key))
            .withPageable(PageRequest.of(page, size));

        return this.productRepository.search(queryBuilder.build());
    }
}
