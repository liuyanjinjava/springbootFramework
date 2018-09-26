package com.futao.springmvcdemo.model.entity;

import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author futao
 * Created on 2018/9/20-15:39.
 * 用户实体
 */
@Validated
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Size(min = 3, max = 8, message = ErrorMessage.USERNAME_LEN_ILLEGAL)
    private String username;
    /**
     * 年龄
     */
    private String age;
    /**
     * 手机号
     */
    @Size(max = 11, message = ErrorMessage.MOBILE_LEN_ILLEGAL)
    private String mobile;

    /**
     * 用户邮箱
     */
    @Email(message = ErrorMessage.EMAIL_ILLEGAL)
    private String email;
    /**
     * 用户地址
     */
    @NotNull
    private String address;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}