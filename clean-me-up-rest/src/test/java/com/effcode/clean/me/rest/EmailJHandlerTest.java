package com.effcode.clean.me.rest;

import com.effcode.clean.me.rest.exception.EmailRequestException;
import com.effcode.clean.me.rest.handler.EmailHandler;
import com.effcode.clean.me.rest.model.EmailMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class EmailJHandlerTest {

    @Autowired
    private EmailHandler emailHandler =  new EmailHandler();

    @Test
    public void testValidEmailAddress() {
            EmailMessage msg = new EmailMessage("rishma88@gmail.com", "Test Subject", "Test Content");
            emailHandler.send(msg);
    }

    @Test
    public void testInValidEmailAddress() {

        Assertions.assertThrows(EmailRequestException.class ,() -> {
            EmailMessage msg = new EmailMessage("rishma88@gmail","Test Subject","Test Content");
            emailHandler.send(msg);
        });
    }

    @Test
    public void testEmptySubject() {
        Assertions.assertThrows(EmailRequestException.class ,() -> {
            EmailMessage msg = new EmailMessage("rishma88@gmail.com","","Test Content");
            emailHandler.send(msg);
        });
    }

    @Test
    public void testEmptySubjectWithSpace() {

        Assertions.assertThrows(EmailRequestException.class ,() -> {
            EmailMessage msg = new EmailMessage("rishma88@gmail.com"," ","Test Content");
            emailHandler.send(msg);
        });
    }

}
