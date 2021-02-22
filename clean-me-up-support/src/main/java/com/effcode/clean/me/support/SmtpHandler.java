package com.effcode.clean.me.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Supporting class to send email using SMTP protocol
 *
 */
public class SmtpHandler {

    Logger log = LoggerFactory.getLogger(SmtpHandler.class);
    public void post(SmtpEmail email) {
       log.debug("Email posted: " + email);
    }
}
