package com.effcode.clean.me.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 *  POJO class for input Json request
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    private String toAddress;
    private String subject;
    private String content;
}
