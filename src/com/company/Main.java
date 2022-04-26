package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main {

    public static void main(String[] args) {
        new EmailSenderGUI();
    }

    private static class EmailSenderGUI {

        public EmailSenderGUI() {
            new HomeScreen();
        }

        private class HomeScreen extends JFrame implements ActionListener {

            // constructs new progress tracker
            public HomeScreen() {
                super("Home Screen");

                setPreferredSize(new Dimension(500, 500));
                ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

                setLayout(new GridLayout(2,1));

                add(addButtonPanel());

                pack();
                setVisible(true);
            }

            // EFFECT: returns the button panel
            private JPanel addButtonPanel() {
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new GridLayout(3,1));

                JButton heartButton = new JButton(new Heart());
                buttonPanel.add(heartButton);

                JButton questionButton = new JButton(new Question());
                buttonPanel.add(questionButton);



                buttonPanel.add(new JButton(new QuitApplication()));
                return buttonPanel;
            }

            private class Heart extends AbstractAction {

                // constructs new log in action
                Heart() {
                    super("heart");
                }

                // EFFECT: redirects the user to log in page
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new SendEmail("heart");
                }
            }

            private class Question extends AbstractAction {

                // constructs new log in action
                Question() {
                    super("question");
                }

                // EFFECT: redirects the user to log in page
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new SendEmail("question");
                }
            }

            private class QuitApplication extends AbstractAction {

                // constructs new log in action
                QuitApplication() {
                    super("Quit");
                }

                // EFFECT: redirects the user to log in page
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.exit(0);
                    setVisible(false);
                }
            }

            // EFFECT: does nothing
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }
    }

    private static class SendEmail {

        public SendEmail(String emailType) {

            final String username = "m1115756947@gmail.com";
            final String password = "Lhjwjj832066";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("someone@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse("martinlou2002@gmail.com")
                );
                message.setSubject("Testing"); //todo: change later

                switch (emailType) {
                    case "heart":
                        message.setText(getHeart());
                        break;
                    case "question":
                        message.setText(getQuestion());
                        break;
                }


                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        public String getHeart() {
            String message =
                    "_$$$$$$___$$$$$$_ \n" +
                            "$$$$$$$$_$$$$$$$$ \n" +
                            "$$$$$$$$$$$$$$$$$ \n" +
                            "_$$$$$$$$$$$$$$$_ \n" +
                            "___$$$$$$$$$$$___ \n" +
                            "_____$$$$$$$_____ \n" +
                            "_______$$$_______ \n" +
                            "________$________";
            return message;
        }

        public String getQuestion() {
            String message ="            ________\n" +
                    "        _jgN########Ngg_\n" +
                    "      _N##N@@\"\"  \"\"9NN##Np_\n" +
                    "     d###P            N####p\n" +
                    "     \"^^\"              T####\n" +
                    "                       d###P\n" +
                    "                    _g###@F\n" +
                    "                 _gN##@P\n" +
                    "               gN###F\"\n" +
                    "              d###F\n" +
                    "             0###F\n" +
                    "             0###F\n" +
                    "             0###F\n" +
                    "             \"NN@'\n" +
                    "\n" +
                    "              ___\n" +
                    "             q###r\n" +
                    "              \"\"";
            return message;
        }
    }
}
