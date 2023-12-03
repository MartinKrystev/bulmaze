package com.project.bulmaze.email;

import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NewUserEmailSender {

    public static void newUserRegisteredEmail() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("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");

        try {
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
            params.setProperty("subject", "BulMaze new registration");

            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent("<html><body><h1>New User has been registered!</h1></body></html>");
            sendSmtpEmail.setSubject("BulMaze new registration");
            sendSmtpEmail.setReplyTo(replyTo);
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);


            //Sending mail for NEW Registered User........
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Exception occurred:- " + e.getMessage());
        }
    }

}
