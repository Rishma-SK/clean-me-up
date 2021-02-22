package com.effcode.clean.me.rest.handler;

import com.effcode.clean.me.rest.exception.EmailRequestException;
import com.effcode.clean.me.rest.model.EmailMessage;
import com.effcode.clean.me.support.SmtpEmail;
import com.effcode.clean.me.support.SmtpHandler;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;


@Component
public class EmailHandler {
    
    @Autowired
    SmtpHandler smtpHandler;

    @Value("${smtp.email.username}")
    private String smtpUsername;

    @Value("${smtp.email.password}")
    private String smtpPassword;

    @Value("${smtp.email.contentlength}")
    private Integer CONTENT_LENGTH_LIMIT;

    Logger log = LoggerFactory.getLogger(EmailHandler.class);

    public void send(EmailMessage emailMessage) {

        log.debug(emailMessage.toString());
        if (validateEmailMessage(emailMessage)) {
            SmtpEmail smtpEmail = new SmtpEmail();
            smtpEmail.setToAddress(emailMessage.getToAddress());
            smtpEmail.setSubject(emailMessage.getSubject());
            smtpEmail.setContent(emailMessage.getContent());
            smtpEmail.setUsername(smtpUsername);
            smtpEmail.setPassword(smtpPassword);
            smtpHandler.post(smtpEmail);
            log.info(smtpEmail.toString());
        }

    }

    /**
     * Validate all the required parameters
     * @param emailMessage
     * @return true/false
     */
    private boolean validateEmailMessage(EmailMessage emailMessage){

        if(Strings.isNullOrEmpty(emailMessage.getSubject().trim()) || Strings.isNullOrEmpty(emailMessage.getToAddress().toString().trim()) ||
                Strings.isNullOrEmpty(emailMessage.getContent().trim())) {
            log.error("Required parameter is null or empty" + emailMessage.getSubject() + "|"+ emailMessage.getToAddress() + "|"+ emailMessage.getContent());
              throw new EmailRequestException("Required parameter is null or empty");
        }
        if(!isValidEmailAddress(emailMessage.getToAddress())){
            log.error("The email address "+emailMessage.getToAddress()+" is invalid.");
            throw new EmailRequestException("The email address  "+emailMessage.getToAddress()+" is invalid");
        }
        if(emailMessage.getContent().length() > CONTENT_LENGTH_LIMIT) {
            log.error("The email content length exceeded the limit: " + emailMessage.getContent().length());
            throw new EmailRequestException("The email content length exceeded the limit: " + emailMessage.getContent().length());
        }
        return true;
    }

    /**
     * Method to validate email Address with regex pattern
     * @param List of email Ids with delimiter ";"
     * @return true/false
     */
    private boolean isValidEmailAddress(String emailAddress){

        List<String> emailAddressList = Arrays.asList(emailAddress);
        Predicate<String> alphaNumaricFilter = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$").asPredicate();
        return emailAddressList.stream().allMatch(alphaNumaricFilter);
    }

}
