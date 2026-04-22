package factory_abstract;

public class SMSConfirmation implements Confirmation {
    @Override
    public void envoyer() {
        System.out.println("Envoi d'une confirmation SMS");
    }
}

