package com.lsy.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具类
 */
public class MailUtils {

    private static final Log logger = LogFactory.getLog(MailUtils.class);

    //发件人信息
    private static String From = "389509393@qq.com";
    //发件人邮箱
    private static String recipient = "389509393@qq.com";
    //邮箱密码
    private static String password = "nshfunpxhsxtcbac";
    //邮件发送的服务器
    private static String host = "smtp.qq.com";


    /**
     * 发送邮件（阿里云和华为云不支持smtp 25端口，改用ssl 465端口发送才好使）
     *
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param to      收件人
     */
    public static void sender(String subject, String content, String to) {
        try {
            Properties properties = new Properties();

            properties.setProperty("mail.host", "smtp.qq.com");

            properties.setProperty("mail.transport.protocol", "smtp");
            // 开启认证
            properties.setProperty("mail.smtp.auth", "true");
            // 设置链接超时
            properties.setProperty("mail.smtp.timeout", "25000");
            // 设置端口
            properties.setProperty("mail.smtp.port", "465");
            // 设置ssl端口
            properties.setProperty("mail.smtp.socketFactory.port", "465");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //创建一个session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(recipient, password);
                }
            });

            //不开启debug模式
            session.setDebug(false);
            Transport transport = null;
            try {
                //获取连接对象
                transport = session.getTransport();
                //连接服务器
                transport.connect(host, From, password);
                //创建一个邮件发送对象
                MimeMessage mimeMessage = new MimeMessage(session);
                //邮件发送人
                mimeMessage.setFrom(new InternetAddress(recipient));
                //邮件接收人
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                //邮件标题
                mimeMessage.setSubject(subject);
                //邮件内容
                mimeMessage.setContent(content, "text/html;charset=UTF-8");
                //发送邮件
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            } catch (NoSuchProviderException e) {
                logger.error("发送邮件失败，收件人邮箱:" + to, e);
            } finally {
                transport.close();
            }
        } catch (Exception e) {
            logger.error("发送邮件失败，收件人邮箱:" + to, e);
        }

    }


    public static void sender(String subject, String content, String... to) {
        /**
         * 若发件邮箱为qq邮箱  则 host为 smtp.qq.com
         * 若为腾讯企业邮箱  则 host为 smtp.exmail.qq.com
         */
        //邮件发送的服务器
        final String host = "smtp.qq.com";
//        final String host = "smtp.exmail.qq.com";
        final Integer port = 465;
        Properties properties = new Properties();
        // 开启认证
        properties.setProperty("mail.smtp.auth", "true");
        // 设置链接超时
        properties.setProperty("mail.smtp.timeout", "25000");
        // 设置端口
        properties.setProperty("mail.smtp.port", port + "");
        // 设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.port", port + "");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost(host);
            sender.setDefaultEncoding("UTF-8");
            sender.setUsername(recipient);
            sender.setPassword(password);
            sender.setPort(port);
            sender.setJavaMailProperties(properties);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(recipient);
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(content);
            sender.send(msg);
        } catch (Exception e) {
            logger.error("发送邮件失败，收件人邮箱:" + to, e);
        }
    }
}
