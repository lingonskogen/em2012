package se.lingonskogen.em2012.services.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import se.lingonskogen.em2012.domain.User;

public class MailService
{
    private static final String FROM_ADDRESS = "susanne.gladen@gmail.com";

    private static final String FROM_NAME = "Em-Tipset";

    private static final String SITEURL = "http://em2012.appspot.com";

    private final Map<String, String> subs = new HashMap<String, String>();
    
    public void setSubstitution(Substitution substitution, String value)
    {
        subs.put(substitution.name(), value);
    }
    
    public void sendMail(User user, Template template) throws Exception
    {
        subs.put(Substitution.SITEURL.name(), SITEURL);
        subs.put(Substitution.REALNAME.name(), user.getRealName());
        String filename = "WEB-INF/mail/" + template.getFilename();
        StringWriter writer = new StringWriter();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null)
        {
            for (String key : subs.keySet())
            {
                line = line.replaceAll(key, subs.get(key));
            }
            writer.write(line);
            writer.write("\r\n");
            line = reader.readLine();
        }
        System.out.println(writer.toString());
        Session session = Session.getDefaultInstance(new Properties(), null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_ADDRESS, FROM_NAME));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserName(), user.getRealName()));
        message.setSubject(template.getTitle());
        message.setText(writer.toString());
        Transport.send(message);
    }
    
    public enum Substitution
    {
        PASSWORD, REALNAME, SITEURL;
    }
    
    public enum Template
    {
        NEW_PWD("new-pw.txt", "Ditt nya lösenord");
        
        private final String title;
        
        private final String filename;

        private Template(String filename, String title)
        {
            this.filename = filename;
            this.title = title;
        }

        public String getFilename()
        {
            return filename;
        }

        public String getTitle()
        {
            return title;
        }
    }
}
