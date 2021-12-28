package com.hao.demo.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;

// 定义资源方式一
public class ProductService {

    public static void doSomething() {

        try (Entry entry = SphU.entry("doSomething")){
            // 业务逻辑处理
            System.out.println("hello world : " + System.currentTimeMillis());
        } catch (BlockException e) {
            // 处理被流控的逻辑

        }

    }

    private static void initFlowRules() {
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("doSomething");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        ruleList.add(rule);
        FlowRuleManager.loadRules(ruleList);
    }


    public static void main(String[] args) {
        initFlowRules();
        while (true) {
            doSomething();
        }
    }

}
