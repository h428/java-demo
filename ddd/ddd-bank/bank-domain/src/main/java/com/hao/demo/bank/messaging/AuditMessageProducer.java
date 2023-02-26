package com.hao.demo.bank.messaging;

import com.hao.demo.bank.domain.types.AuditMessage;

/**
 * infrastructure - 消息队列接口
 */
public interface AuditMessageProducer {
    boolean send(AuditMessage message);
}
