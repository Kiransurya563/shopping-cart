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

import com.jspiders.shoppingcart.dto.Customer;
import com.jspiders.shoppingcart.exception.UserDefinedException;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class CustomerMailVerification {
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration configuration;

	public void SendVerificationEmail(Customer customer) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		String mailcontent = getEmailContent(customer);

		String subject = "Please verify your registration";
		String senderName = "Online Shopping Cart";

		try {
			helper.setFrom("saishkulkarni7@gmail.com", senderName);
		} catch (UnsupportedEncodingException | MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			helper.setTo(customer.getEmail());
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			helper.setSubject(subject);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			helper.setText(mailcontent, true);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}

		try {
			mailSender.send(mimeMessage);
		} catch (MailSendException e) {
			throw new UserDefinedException("Check your internet connection");
		}

	}

	public String getEmailContent(Customer customer) {
		StringWriter writer = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("customer", customer);
		try {
			configuration.getTemplate("Customer-email-template.ftl").process(model, writer);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return writer.getBuffer().toString();
	}

}
