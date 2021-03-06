package com.hao.demo.service;

import com.hao.demo.constant.ResCode;
import com.hao.demo.dal.mapper.AccountMapper;
import com.hao.demo.pojo.dto.AccountDto;
import com.hao.demo.pojo.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService
public class AccountServiceImpl implements AccountService  {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ObjectResponse decreaseAccount(AccountDto accountDto) {
        ObjectResponse response=new ObjectResponse();
        try{
            int rs=accountMapper.decreaseAccount(accountDto.getUserId(),accountDto.getBalance().doubleValue());
            if(rs>0){
                response.setMsg(ResCode.SUCCESS.getMessage());
                response.setCode(ResCode.SUCCESS.getCode());
                return response;
            }
            response.setMsg(ResCode.FAILED.getMessage());
            response.setCode(ResCode.FAILED.getCode());
        }catch (Exception e){
            log.error("decreaseAccount Occur Exception:"+e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage()+"-"+e.getMessage());
        }
        return response;
    }
}
