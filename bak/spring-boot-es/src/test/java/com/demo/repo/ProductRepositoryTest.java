package com.demo.repo;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.demo.BaseTest;
import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public class ProductRepositoryTest extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void deleteIndex() {
        assertTrue(this.elasticsearchTemplate.deleteIndex(Product.class));
    }

    @Test
    public void createIndex() {
        assertTrue(this.elasticsearchTemplate.createIndex(Product.class));
    }

    @Test
    public void indexExists() {
        assertTrue(this.elasticsearchTemplate.indexExists("product"));
    }

    @Test
    public void recreateIndex() {
        assertTrue(this.elasticsearchTemplate.deleteIndex(Product.class));
        assertTrue(this.elasticsearchTemplate.createIndex(Product.class));
    }


    @Test
    public void save() {
        Product product = Product.randomInstance(1);
        Product save = this.productRepository.save(product);
        assertEquals(product, save);
    }

    @Test
    public void saveAll() {
        int cnt = 100;
        List<Product> productList = Product.randomBatch(cnt);
        Iterable<Product> saveAll = this.productRepository.saveAll(productList);
        assertEquals(productList, saveAll);
    }

    @Test
    public void deleteById() {
        this.productRepository.deleteById(1L);
    }

    @Test
    public void delete() {
        // elastic repository 不支持根据条件删除，若设置 id 会报错
        // 本质上是根据 id 删除，其他属性设置了无效
        Product build = Product.builder().id(3L).cid3(9L).build();
        this.productRepository.delete(build);
    }

    @Test
    public void deleteBatch() {
        // 根据 id 批量删除
        List<Product> productList = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            productList.add(Product.builder().id((long) i).build());
        }
        this.productRepository.deleteAll(productList);
    }

    @Test
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

    @Test
    public void findById() {
        Optional<Product> optional = this.productRepository.findById(1L);
        assertNotNull(optional);
        assertTrue(optional.isPresent());
        assertNotNull(optional.get());
    }

    @Test
    public void findByIds() {
        List<Long> ids = new ArrayList<>();
        Collections.addAll(ids, 1L, 2L, 3L);
        List<Product> productList = (List<Product>) this.productRepository.findAllById(ids);
        assertEquals(3, productList.size());
    }

    @Test
    public void findAll() {
        // 查找结果是 AggregatedPage
        AggregatedPage<Product> products = (AggregatedPage<Product>) this.productRepository.findAll();
        assertEquals(100L, products.getTotalElements());
    }

    @Test
    public void findPage() {
        Order cid1Asc = Order.asc("cid1");
        Order cid2Desc = Order.desc("cid2");
        Sort sort = Sort.by(cid1Asc, cid2Desc);
        int size = 10;
        Page<Product> all = this.productRepository.findAll(PageRequest.of(0, size, sort));
        assertEquals(size, all.getTotalElements());
        Product.print(all);
    }

    @Test
    public void findSort() {
        Order cid1Asc = Order.asc("cid1");
        Order cid2Desc = Order.desc("cid2");
        Sort sort = Sort.by(cid1Asc, cid2Desc);

        Iterable<Product> all = this.productRepository.findAll(sort);
        Product.print(all);
    }


    @Test
    public void search() {
        // search 推荐使用 NativeSearchQueryBuilder 配合 QueryBuilders 提供的常见搜索器
        // 且多查询一般采用 BoolQueryBuilder

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        MatchAllQueryBuilder matchAll = QueryBuilders.matchAllQuery();

        queryBuilder.withQuery(matchAll)
            .withPageable(PageRequest.of(0, 100));

        Page<Product> productPage = this.productRepository.search(queryBuilder.build());

        assertEquals(100, productPage.getContent().size());
    }

    @Test
    public void term() {

        // 多条件精确查询，查询 cid1 = 0，cid2 = 2
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery
            .must(QueryBuilders.termQuery("cid1", 0))
            .must(QueryBuilders.termQuery("cid2", 2));

        queryBuilder.withQuery(boolQuery);

        Page<Product> productPage = this.productRepository.search(queryBuilder.build());

//        assertEquals(10, productPage.getContent().size());

        Product.print(productPage);
    }

    @Test
    public void match() {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 查询 cid1 = 0 且包含小火的记录
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
            .must(QueryBuilders.matchQuery("name", "小火"))
            .must(QueryBuilders.termQuery("cid1", 0));
        queryBuilder.withQuery(boolQuery);
        Page<Product> products = this.productRepository.search(queryBuilder.build());

        Product.print(products);
    }

    @Test
    public void matchWithPage() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 查询 cid1 = 0 的记录
        queryBuilder.withQuery(QueryBuilders.termQuery("cid1", 0));
        // 同时设置分页参数
        queryBuilder.withPageable(PageRequest.of(2, 5));

        Page<Product> products = this.productRepository.search(queryBuilder.build());
        Product.print(products);
    }
}
