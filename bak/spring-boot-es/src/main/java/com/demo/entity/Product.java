package com.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Document(indexName = "product", type = "_doc")
@ToString
public class Product {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    // 不作为查询条件，建议采用 Keyword 而不是 Text，Keyword 在存储时不会进行分词，
    // 同时，由于不准备作为查询条件，因此可以设置 index = false
    @Field(type = FieldType.Keyword, index = false)
    private String info;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Long)
    private Long cid1;

    @Field(type = FieldType.Long)
    private Long cid2;

    @Field(type = FieldType.Long)
    private Long cid3;

    // 不准备以 status 作为查询条件，因此可以设置 index = false
    @Field(type = FieldType.Integer, index = false)
    private Integer status;

    // 不准备以 deleted 作为查询条件，因此可以设置 index = false
    @Field(type = FieldType.Boolean, index = false)
    private Boolean deleted;

    public static Product randomInstance(int id) {
        return Product.builder()
            .id((long) id)
            .name("name" + id)
            .info("info" + id)
            .price(id % 10000)
            .cid1((long) id % 10)
            .cid2((long) id % 100)
            .cid3((long) id % 1000)
            .status(id % 5)
            .deleted(id % 2 == 0)
            .build();
    }

    public static List<Product> randomBatch(int size) {
        List<Product> productList = new ArrayList<>();
        for (int id = 0; id < size; id++) {
            String name = NAME_LIST[id % NAME_LIST.length];
            Product build = Product.builder()
                .id((long) id)
                .name(name + id)
                .info(name + id)
                .price(nextInt() % 10 * 100)
                .cid1((long) nextInt() % 3)
                .cid2((long) nextInt() % 5)
                .cid3((long) nextInt() % 10)
                .status(nextInt() % 5)
                .deleted(id % 2 == 0)
                .build();
            productList.add(build);
        }
        return productList;
    }

    private static Random RANDOM = new Random();

    private static int nextInt() {
        return RANDOM.nextInt() >>> 1;
    }

    private static String[] NAME_LIST = new String[]{
        "妙蛙种子", "妙蛙草", "妙蛙花", "小火龙", "火恐龙", "喷火龙", "杰尼龟", "卡咪龟",
        "水箭龟", "小火马", "烈焰马", "小拳石", "墨海马", "海刺龙", "化石翼龙", "火球鼠",
        "hello world", "nice world", "hello, mike", "nice, mike"
    };

    public static void print(Iterable<Product> products) {
        String line = "----------------------------";
        int i = 0;
        System.out.println(line);
        for (Product product : products) {
            System.out.println((++i) + " - " + product);
        }
        System.out.println(line);
    }


}
