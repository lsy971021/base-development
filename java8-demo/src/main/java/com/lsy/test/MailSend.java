package com.lsy.test;

import com.lsy.utils.MailUtils;
import org.junit.Test;

public class MailSend {

    @Test
    public void sender(){
        String subject = "test";
        String content = "test";
        String to = "lsy971021@gmail.com";
        MailUtils.sender(subject,content,to);
    }
}
