package factory_method;

public class SMSNotification implements Notification{
    public void envoyer(String message) {
        System.out.println("Envoi d'un SMS : " + message);
    }
}
