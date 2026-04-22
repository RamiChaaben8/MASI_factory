package factory_abstract;

public class SMSRapport implements Rapport {
    @Override
    public void envoyer() {
        System.out.println("Envoi d'un rapport SMS");
    }
}

