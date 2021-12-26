package com.hao.demo.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.hao.demo.util.entity.User;
import java.util.Collections;

public class GenerateUtil {

    public static void main(String[] args) {

        FastAutoGenerator.create("localhost:3306", "root", "root")
            .globalConfig(builder -> {
                builder.author("hao") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("../entity"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.hao.demo.entity") // 设置父包名
                    .moduleName("system") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("t_simple") // 设置需要生成的表名
                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }

}
