package com.app.utilities;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class MailUtils {

    public static String plainBody;
    public static List<String> plainBodyList = new ArrayList<>();
    public static Date revieveDate;
    public static long sentTime;
    public static String from;

    public static String host = "pop.gmail.com";// change accordingly
    public static String mailStoreType = "pop3";
    public static String username;
    public static String password;


    public static void navigateToLinkForResetPassword(long time, String email, String pass, String userName) {

        sentTime = time;
        username = email;
        password = pass;
        BrowserUtils.waitFor(4);
        fetch(host, mailStoreType, username, password, userName);
        sentTime = time;


    }


    public static void fetch1(String pop3Host, String storeType, String user,
                             String password, String expectedUserName) {
        try {
            // create properties field

            Properties properties = new Properties();
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            // emailSession.setDebug(true);
            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            store.connect(pop3Host, user, password);
            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();


            for (int i = 1; i <= 5; i++) {
                revieveDate = messages[messages.length - i].getSentDate();
                Message currentMessage = messages[messages.length - i];
                writePart(currentMessage);

                plainBodyList.add(plainBody);

                int beginIndexOfUsername = plainBody.indexOf("Hi") + 3;
                int endIndexOfUsername = plainBody.indexOf("You recently requested");

                String actualUserName = plainBody.substring(beginIndexOfUsername, endIndexOfUsername);


                if (((revieveDate.getTime() - sentTime) / 1000 < 5)   // elapsed time between send time and recieve time should be less than 5 seconds
                        && expectedUserName.trim().equals(actualUserName.trim()) // username should be match
                        && (((new Date().getTime() - currentMessage.getSentDate().getTime()) / 1000) <= 60)) {  // elapsed time between receive and now should be less than a minute

                    int startIndex = plainBody.indexOf("Reset your password") + 19;
                    int endingIndex = plainBody.indexOf("If you did not request");
                    String LINK = plainBody.substring(startIndex, endingIndex).trim();
                    BrowserUtils.log("LINKKK = " + LINK);
                    BrowserUtils.log("sentDate = " + revieveDate);
                    BrowserUtils.log("actualUserName = " + actualUserName);
                    BrowserUtils.log("user = " + user);
                    BrowserUtils.log("password = " + password);


                    Driver.get().navigate().to(LINK);
                    emailFolder.close(false);

                    store.close();
                    return;
                }
            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();
            throw new Exception("WARNING: NoSuchEMail: email can not ne found in last 5 emails");

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void fetch(String pop3Host, String storeType, String user,
                             String password, String expectedUserName) {
        try {
            // create properties field

            Properties properties = new Properties();
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            // emailSession.setDebug(true);
            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            store.connect(pop3Host, user, password);
            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();


            for (int i = 1; i <= 5; i++) {
                revieveDate = messages[messages.length - i].getSentDate();
                Message currentMessage = messages[messages.length - i];
                writePart(currentMessage);

                plainBodyList.add(plainBody);


                System.out.println("plainBody.toString() = " + plainBody.toString());


//                    int beginIndexOfUsername = plainBody.indexOf("Hi") + 3;
//                int endIndexOfUsername = plainBody.indexOf("You recently requested");
//
//                int beginIndexOfUsername = plainBody.indexOf("Username:") + 9;
//                int endIndexOfUsername = plainBody.indexOf("Email:");

//                String actualUserName = plainBody.substring(beginIndexOfUsername, endIndexOfUsername).trim();


                if (((revieveDate.getTime() - sentTime) / 1000 < 5)   // elapsed time between send time and recieve time should be less than 5 seconds
                        && (plainBody.contains(expectedUserName)) // username should be match
                        && (((new Date().getTime() - currentMessage.getSentDate().getTime()) / 1000) <= 60)) {  // elapsed time between receive and now should be less than a minute

                    String LINK=null;
                    try{
                        int startIndex = plainBody.indexOf("Reset your password") + 19;
                        int endingIndex = plainBody.indexOf("If you did not request");
                        LINK = plainBody.substring(startIndex, endingIndex).trim();

                    }catch (StringIndexOutOfBoundsException s){
                        int startIndex = plainBody.indexOf("Create Password") + 15;
                        int endingIndex = plainBody.indexOf("(c) 2021 A-SAFE. All rights reserved.");
                        LINK = plainBody.substring(startIndex, endingIndex).trim();
                    }

                    BrowserUtils.log("LINK = " + LINK);
                    BrowserUtils.log("sentDate = " + revieveDate);
                    BrowserUtils.log("actualUserName = " + username);
                    BrowserUtils.log("user = " + user);
                    BrowserUtils.log("password = " + password);


                    Driver.get().navigate().to(LINK);
                    emailFolder.close(false);

                    store.close();
                    return;
                }
            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();
            throw new Exception("WARNING: NoSuchEMail: email can not ne found in last 5 emails");

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * This method checks for content-type
     * based on which, it processes and
     * fetches the content of the message
     */
    public static void writePart(Part p) throws Exception {
        if (p instanceof Message)
            //Call methos writeEnvelope
            writeEnvelope((Message) p);

//        System.out.println("----------------------------");
//        System.out.println("CONTENT-TYPE: " + p.getContentType());

        //check if the content is plain text
        if (p.isMimeType("text/plain")) {
//            System.out.println("This is plain text");
//            System.out.println("---------------------------");
//            System.out.println((String) p.getContent());
            plainBody = ((String) p.getContent());
        }
        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
//            System.out.println("This is a Multipart");
//            System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i));
        }
        //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
//            System.out.println("This is a Nested Message");
//            System.out.println("---------------------------");
            writePart((Part) p.getContent());
        }
        //check if the content is an inline image
        else if (p.isMimeType("image/jpeg")) {
//            System.out.println("--------> image/jpeg");
            Object o = p.getContent();

            InputStream x = (InputStream) o;

        } else if (p.getContentType().contains("image/")) {

            //System.out.println("content type" + p.getContentType());
            File f = new File("image" + new Date().getTime() + ".jpg");
            DataOutputStream output = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(f)));
            com.sun.mail.util.BASE64DecoderStream test =
                    (com.sun.mail.util.BASE64DecoderStream) p
                            .getContent();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = test.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } else {
            Object o = p.getContent();
            if (o instanceof String) {
//                System.out.println("This is a string");
//                System.out.println("---------------------------");
//                System.out.println((String) o);
            } else if (o instanceof InputStream) {
//                System.out.println("This is just an input stream");
//                System.out.println("---------------------------");
                InputStream is = (InputStream) o;
                is = (InputStream) o;
                int c;
                while ((c = is.read()) != -1) {
                    //System.out.write(c);
                }
            } else {
//                System.out.println("This is an unknown type");
//                System.out.println("---------------------------");
//                System.out.println(o.toString());
            }
        }

    }

    /*
     * This method would print FROM,TO and SUBJECT of the message
     */
    public static void writeEnvelope(Message m) throws Exception {
//        System.out.println("This is the message envelope");
//        System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                from = a[j].toString();
            //System.out.println("FROM: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());

    }

}
