package com.effcode.clean.me.support;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * POJO class to send email with user authorization
 */
@Data
@ToString
public class SmtpEmail {

    private String username;
    private String password;
    private String toAddress;
    private String subject;
    private String content;

}
