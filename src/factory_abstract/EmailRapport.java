package factory_abstract;

public class EmailRapport implements Rapport {
    @Override
    public void envoyer() {
        System.out.println("Envoi d'un rapport Email");
    }
}

