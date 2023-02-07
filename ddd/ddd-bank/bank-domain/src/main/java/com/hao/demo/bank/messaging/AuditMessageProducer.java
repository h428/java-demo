package com.hao.demo.bank.messaging;

import com.hao.demo.bank.domain.types.AuditMessage;

public interface AuditMessageProducer {
    boolean send(AuditMessage message);
}
