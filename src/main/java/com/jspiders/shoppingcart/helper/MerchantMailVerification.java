package com.jspiders.shoppingcart.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jspiders.shoppingcart.dto.Merchant;
import com.jspiders.shoppingcart.exception.UserDefinedException;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class MerchantMailVerification {
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration configuration;

	public void SendVerificationEmail(Merchant merchant) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		String mailcontent = getEmailContent(merchant);

		String subject = "Please verify your registration";
		String senderName = "Shopping Cart";

		try {
			helper.setFrom("saishkulkarni7@gmail.com", senderName);
		} catch (UnsupportedEncodingException | MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			helper.setTo(merchant.getEmail());
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			helper.setSubject(subject);
		} catch (MessagingException e2) {
			e2.printStackTrace();
		}
		try {
			helper.setText(mailcontent, true);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}

		try {
			mailSender.send(mimeMessage);
		} catch (MailSendException e) {
			throw new UserDefinedException("Check your internet Connection and email adress");
		}

	}

	public String getEmailContent(Merchant merchant) {
		StringWriter writer = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("merchant", merchant);
		try {
			configuration.getTemplate("Merchant-email-template.ftl").process(model, writer);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return writer.getBuffer().toString();
	}

}
