package com.project.bulmaze.email;

import com.project.bulmaze.model.entity.InquiryEntity;
import com.project.bulmaze.utils.PrivateEnums;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NewInquiryEmailSender {
    public static void sendInquiryMails(List<InquiryEntity> inquiries) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(PrivateEnums.API_KEY);

        try {
            StringBuilder sb = new StringBuilder();
            for (InquiryEntity inquiry : inquiries) {
                sb.append("<html><body><h1>New inquiry has been sent.</h1>");
                sb.append("<p>Sender name: ");
                sb.append(inquiry.getName());
                sb.append("</p>");

                sb.append("<p>Sender email: ");
                sb.append(inquiry.getEmail());
                sb.append("</p>");

                sb.append("<p>Inquiry subject:");
                sb.append(inquiry.getSubject());
                sb.append("</p>");

                sb.append("<p>Message: ");
                sb.append(inquiry.getMessage());
                sb.append("</p>");

                sb.append("<hr>");
                sb.append("</body></html>");
            }

            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail("bulmazebg@gmail.com");
            sender.setName("BulMazeTeam");

            List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail("bulmazebg@gmail.com");
            to.setName("BulMazeTeam");
            toList.add(to);

            SendSmtpEmailReplyTo replyTo = new SendSmtpEmailReplyTo();
            replyTo.setEmail("bulmazebg@gmail.com");
            replyTo.setName("BulMazeTeam");


            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");

            Properties params = new Properties();
            params.setProperty("subject", "New Inquiry");

            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent(sb.toString());
            sendSmtpEmail.setSubject("New Inquiry");
            sendSmtpEmail.setReplyTo(replyTo);
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);

            //Sending mail for NEW Inquiry........
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
