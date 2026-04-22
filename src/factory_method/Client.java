package factory_method;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        NotificationFactory factory;
        Scanner s = new Scanner(System.in);
        System.out.println("Entrez le type de notification : SMS ou Email");
        String type = s.nextLine();
        switch (type) {
            case "SMS":
                factory = new SMSNotificationFactory();
                break;
            case "EMAIL":
                factory = new EmailNotificationFactory();
                break;
            default:
                System.out.println("Type de notification inconnu");
                return;
        }
        Notification notification = factory.creerNotification();
        notification.envoyer("Voila notre premier exemple avec le Factory Method");
    }
}
