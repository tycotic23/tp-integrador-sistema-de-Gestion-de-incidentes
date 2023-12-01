package com.sistemaIncidentes.models;



import java.util.Properties;
        import javax.mail.Message;
        import javax.mail.MessagingException;
        import javax.mail.Session;
        import javax.mail.Transport;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;

public class MailSender {
    private final Properties properties = new Properties();

    private String password;

    private Session session;

    private void init() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port",25);
        properties.put("mail.smtp.mail.sender","sistemagestionincidente@gmail.com");
        properties.put("mail.smtp.user", "sistemagestionincidente@gmail.com");
        properties.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);
    }

    public void sendEmail(String adressEmail, String subject, String body){

        init();
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adressEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport t = session.getTransport("smtp");
            t.connect((String)properties.get("mail.smtp.user"), "zcna ncfq jfrx qhwf");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }catch (MessagingException me){
            System.out.println("exception" +me);

        }

    }

}
