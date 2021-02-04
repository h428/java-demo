package com.hao.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Long addTime;


    public static Article randomOne(int id) {
        int mod = 100;
        return Article.builder()
            .id(id)
            .title("title"+id%mod)
            .content("content"+id%mod)
            .addTime(System.currentTimeMillis())
            .build();
    }

    private static Random random = new Random();

    public static List<Article> randomBatch(int size) {
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            articleList.add(randomOne(random.nextInt() >>> 1));
        }
        return articleList;
    }
}
