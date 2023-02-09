package com.ruoyi.common.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 *
 */
@Component
public class BootEmail
{

    private static JavaMailSender mailSender;


    private static String Sender; //读取配置文件中的参数


    @Autowired
    public void setJavaMailSender(JavaMailSender mailSender)
    {
        BootEmail.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    public void setSender(String Sender)
    {
        BootEmail.Sender = Sender;
    }


    /**
     * 发送简单文本邮件
     * @param to
     * @param title
     * @param text
     */
    public static void sendSimpleMail(String to, String title, String text)
    {
        EmailThread emailThread = new EmailThread();
        emailThread.setMailSender(mailSender);
        emailThread.setSender(Sender);
        emailThread.setTo(to);
        emailThread.setTitle(title);
        emailThread.setText(text);
        emailThread.setHtmlFlag(false);
        new Thread(emailThread).start();
    }

    /**
     * 发送HTML 内容邮件
     */
    public static void testSendHtml(String to, String title, String html)
    {
        EmailThread emailThread = new EmailThread();
        emailThread.setMailSender(mailSender);
        emailThread.setSender(Sender);
        emailThread.setTo(to);
        emailThread.setTitle(title);
        emailThread.setText(html);
        emailThread.setHtmlFlag(true);
        new Thread(emailThread).start();
    }

    /**
     * 发送带有附件的邮件
     */
    public static void sendAttachmentsMail(String to, String title, String text, String path)
    {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(to);
            helper.setSubject("主题：" + title);
            helper.setText(text);
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File(path));
            //加入邮件
            helper.addAttachment("图片.jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    /**
     * 发送 带静态资源的邮件
     */
    public static void sendInlineMail(String to, String title, String text, String path)
    {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(to);
            helper.setSubject("主题：" + title);
            //第二个参数指定发送的是HTML格式,同时cid:是固定的写法
//            helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:picture' /></body></html>", true);
            helper.setText(text, true);

            FileSystemResource file = new FileSystemResource(new File(path));
            helper.addInline("picture", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }


    /**
     * 发送带抄送邮件
     */
    public static void sendMailWithCC(String to, String title, String text, String[] cc)
    {

        EmailThread emailThread = new EmailThread();
        emailThread.setMailSender(mailSender);
        emailThread.setSender(Sender);
        emailThread.setTo(to);
        emailThread.setTitle(title);
        emailThread.setText(text);
        emailThread.setHtmlFlag(true);
        emailThread.setCc(cc);
        new Thread(emailThread).start();
    }

    /**
     * 发送带有附件的邮件(更新APP通知)
     */
    public static void sendAttachmentsMailApp(String to, String title, String text, String path)
    {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(to);
            helper.setSubject("主题：" + title);
            helper.setText(text);
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File(path));
            //加入邮件
            helper.addAttachment("silergyApp-20211018.pptx", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
