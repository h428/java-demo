package com.hao.demo.service;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;

// 定义资源方式二
public class OrderService {

    public static void doSomething() {

        if (SphO.entry("doSomething")) {

            try {
                // 被保护的业务逻辑
                System.out.println("hello world : " + System.currentTimeMillis());
            } finally {
                SphO.exit();
            }

        } else {
            // 处理流控逻辑
            System.out.println("被流控了");
            return;
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
