package com.hao.demo.shiro.pojo.dto;

import com.hao.demo.shiro.pojo.message.BaseUserMessage;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class SysUserLoginDTO {

    /**
     * 电子邮箱
     */
    @NotBlank(message = BaseUserMessage.USERNAME_NOT_BLANK_MESSAGE)
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = BaseUserMessage.PASSWORD_NOT_BLANK)
    private String password;

    /**
     * 验证码
     */
    private String captcha; // 暂时不添加校验

}
