package factory_method;

public class EmailNotification implements Notification {
    public void envoyer(String message) {
        System.out.println("Envoi d'un email : " + message);
    }
}
