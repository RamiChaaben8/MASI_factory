package factory_abstract;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotificationFactory factory;

        System.out.println("Entrez le type de famille : SMS ou EMAIL");
        String type = scanner.nextLine().trim().toUpperCase();

        switch (type) {
            case "SMS":
                factory = new SMSFactory();
                break;
            case "EMAIL":
                factory = new EmailFactory();
                break;
            default:
                System.out.println("Type de famille inconnu");
                return;
        }

        System.out.println("=== Creation des produits pour la famille " + type + " ===");
        Alert alert = factory.creerAlert();
        Rapport rapport = factory.creerRapport();
        Confirmation confirmation = factory.creerConfirmation();

        alert.envoyer();
        rapport.envoyer();
        confirmation.envoyer();
    }
}

