package com.hao.demo.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.demo.consttant.MyContentType;
import com.hao.demo.vo.MyResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class CustomUrlBlockHandler implements BlockExceptionHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MyContentType.APPLICATION_JSON);
        MyResponse build = MyResponse.builder().code(999).msg("访问人数过多").build();
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(build));
    }
}
