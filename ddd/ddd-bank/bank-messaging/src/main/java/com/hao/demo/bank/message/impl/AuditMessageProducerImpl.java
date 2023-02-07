package com.hao.demo.bank.message.impl;

import com.hao.demo.bank.domain.types.AuditMessage;
import com.hao.demo.bank.messaging.AuditMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 模拟消息队列发送实现
 */
@Component
@Slf4j
public class AuditMessageProducerImpl implements AuditMessageProducer {

    @Override
    public boolean send(AuditMessage message) {
        log.info("发送消息 {} 到消息队列", message);
        return true;
    }
}
