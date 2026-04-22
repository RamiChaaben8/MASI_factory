package factory_abstract;

public class SMSAlert implements Alert {
    @Override
    public void envoyer() {
        System.out.println("Envoi d'une alerte SMS");
    }
}

