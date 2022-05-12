package com.hao.demo.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;

public class GenerateUtil {


    public static void main(String[] args) {

        final String url = "jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

        FastAutoGenerator.create(url, "root", "root")
            .globalConfig(builder -> {
                builder.author("hao") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("src\\main\\java"); // 指定源码输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.hao.demo") // 设置包名（包括 entity, mapper, service, controller）
                    .moduleName("generate") // 设置模块名，模块名也会出现在包名中
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src\\main\\resources\\mappers")); // 设置 mapperXml 生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("user"); // 设置需要生成的表名
                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
            })
//            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }

}
