package com.hao.demo.impl;

import com.hao.demo.spi.Compiler;
import org.apache.dubbo.common.extension.Adaptive;

@Adaptive
public class AdaptiveCompiler implements Compiler {

    @Override
    public String compile() {
        return "adaptiveCompiler";
    }
}
