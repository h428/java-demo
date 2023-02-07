package com.hao.demo.bank.domain.types;

import com.hao.bank.types.AccountNumber;
import com.hao.bank.types.Money;
import com.hao.bank.types.UserId;
import com.hao.demo.bank.domain.entity.Account;
import lombok.ToString;

import java.util.Date;

@ToString
public class AuditMessage {
    private UserId userId;
    private AccountNumber source;
    private AccountNumber target;
    private Money money;
    private Date date;

    public AuditMessage(UserId userId, Account sourceAccount, Account targetAccount, Money targetMoney) {
        this.userId = userId;
        this.source = sourceAccount.getAccountNumber();
        this.target = targetAccount.getAccountNumber();
        this.money = targetMoney;
        this.date = new Date();
    }

    public String serialize() {
        return userId + "," + source + "," + target + "," + money + "," + date;
    }

    public static AuditMessage deserialize(String value) {
        // todo
        return null;
    }
}
