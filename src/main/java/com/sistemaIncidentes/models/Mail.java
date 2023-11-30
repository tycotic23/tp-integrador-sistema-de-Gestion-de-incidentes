package com.sistemaIncidentes.models;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    public Mail(){

    }
/*
    public static void main(String[] args) {

        Properties props = new Properties();
            props.put ("mail.smtp.host","smtp.gmail.com");
            props.put ("mail.smtp.port","587");
            props.put ("mail.smtp.auth","true");
            props.put ("mail.smtp.starttls","true");


        Session session = Session.getDefaultInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                new PasswordAuthentication("verdesya@mgial.com","hay que averiguar lo de esta password");

                return null;
            }

            });

            try{
                MimeMessage message = new MimeMessage(session);
                message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress("direcciondestino@gmail.com", true)));
                message.setSubject("prueba");
                message.setText("blabla");
                System.out.println("sending..");
                Transport.send(message);
                System.out.println("sent message secesfully....");
            }catch(MessagingException e){
                System.out.println("exception" +e);
            }
    }
    */
}

