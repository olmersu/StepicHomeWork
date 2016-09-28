package com.BasicJava.PostOffice;

import javafx.geometry.Pos;
import sun.rmi.runtime.Log;

import java.util.logging.*;

/**
 * Created by bogomolov on 28.09.2016.
 */
public class PostOffice {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
        MailMessage mail = new MailMessage("Me", "Austin Powers", "Hi!!!");
        MailMessage mail1 = new MailMessage("Me", "pETER", "HELO!!!");
        MailPackage pack = new MailPackage("Me", "Vasay", new Package("Vodka",500));
        MailPackage pack1 = new MailPackage("Me", "Vasay", new Package("weapons",200));
        MailPackage pack2 = new MailPackage("Me", "Vasay", new Package("banned substance",200));
        Logger LOGGER = Logger.getLogger(PostOffice.class.getName());
        ConsoleHandler consHandler = new ConsoleHandler();
        consHandler.setLevel(Level.ALL);
        Spy spy = new Spy(LOGGER);
        consHandler.setFormatter(new SimpleFormatter());
        spy.processMail(mail);
        spy.processMail(mail1);
        Thief thief = new Thief(400);
        Inspector inspector = new Inspector();
        Sendable pp = thief.processMail(pack);
        System.out.println(((MailPackage)pp).getContent().getContent());
        System.out.println(thief.getStolenValue());
        inspector.processMail(pp);
//        inspector.processMail(thief.processMail(pack));
//        inspector.processMail(pack1);
//        inspector.processMail(pack2);
    }

    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }
    }

    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {

            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService{
        RealMailService realMailService = new RealMailService();
        MailService [] mailServices;

        public RealMailService getRealMailService() {
            return realMailService;
        }

        public UntrustworthyMailWorker(MailService [] mailServices) {
            this.mailServices = mailServices;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (int i = 0; i < mailServices.length; i++) {
                mail=mailServices[i].processMail(mail);
            }
            return this.getRealMailService().processMail(mail);
        }
    }

    public static class Spy implements MailService{
        private static Logger LOGGER;
        public Spy(Logger LOGGER) {
            Spy.LOGGER = LOGGER;
            Spy.LOGGER.setLevel(Level.INFO);
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage){
                String from = mail.getFrom();
                String to = mail.getTo();
                String message = ((MailMessage) mail).message;
                if (mail.getTo()==AUSTIN_POWERS || mail.getFrom()==AUSTIN_POWERS)
                    LOGGER.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\""
                            , new Object[]{from, to, message});
                else LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}"
                        , new Object [] {from, to});
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        int value;
        static int overAllValue = 0;

        public Thief(int value) {
            this.value = value;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage){
                if (((MailPackage)mail).getContent().getPrice()>=this.value){
                    overAllValue+=((MailPackage)mail).getContent().getPrice();
                    mail = new MailPackage(mail.getFrom(),mail.getTo(),new Package("stones instead of "+((MailPackage)mail).getContent().getContent(),0));
                }
            }
            return mail;
        }

        public int getStolenValue(){
            return overAllValue;
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof  MailPackage){
                String s = ((MailPackage)mail).getContent().getContent();
                if (s.contains(WEAPONS) || s.contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException("Illegal package found "+s);
                }
                if (s.contains("stones")){
                    throw new StolenPackageException("Illegal package found "+s);
                }

            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException () {
            super();
        }
        public IllegalPackageException (String message) {
            super(message);
        }
        public IllegalPackageException (String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException () {
            super();
        }
        public StolenPackageException (String message) {
            super(message);
        }
        public StolenPackageException (String message, Throwable cause) {
            super(message, cause);
        }
    }

}
