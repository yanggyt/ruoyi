package com.ruoyi.common.utils.email;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件线程
 *
 * @author SKaiL
 * @date 2022/9/30
 */
public class EmailThread implements Runnable
{
    private JavaMailSender mailSender;

    private String sender;

    private String to;

    private String title;

    private String text;

    private Boolean htmlFlag;

    private String filePath;

    private String[] cc;

    public JavaMailSender getMailSender()
    {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Boolean getHtmlFlag()
    {
        return htmlFlag;
    }

    public void setHtmlFlag(Boolean htmlFlag)
    {
        this.htmlFlag = htmlFlag;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String[] getCc()
    {
        return cc;
    }

    public void setCc(String[] cc)
    {
        this.cc = cc;
    }

    @Override
    public void run()
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            //发送人
            helper.setFrom(sender);
            //邮件接收人
            helper.setTo(to);
            //抄送人
            if (StringUtils.isNotEmpty(cc)) {
                helper.setCc(cc);
            }
            //邮件标题
            helper.setSubject("主题："+title);
            //邮件内容
            helper.setText(text,htmlFlag);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
