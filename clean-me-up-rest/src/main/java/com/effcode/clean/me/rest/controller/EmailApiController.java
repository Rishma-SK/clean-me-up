package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.handler.EmailHandler;
import com.effcode.clean.me.rest.model.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmailApiController - RESTful API endpoint for sending email
 */
@RestController
@RequestMapping("/api")
public class EmailApiController {

    @Autowired
    EmailHandler emailHandler;

    /**
     *
     * @param  EmailMessage sends email with JSON request - toAddress, subject & content
     * @return the message if email sent successfully
     * @throws EmailRequestException
     */
    @PostMapping(value="/sendEmail", consumes="application/json")
    public ResponseEntity<String> sendEmail(@RequestBody EmailMessage email) {
            emailHandler.send(email);
            return new ResponseEntity<>("Email sent successfully to "+email.getToAddress(), HttpStatus.OK);
    }

}
