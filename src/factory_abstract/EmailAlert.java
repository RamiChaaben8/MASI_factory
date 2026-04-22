package factory_abstract;

public class EmailAlert implements Alert {
    @Override
    public void envoyer() {
        System.out.println("Envoi d'une alerte Email");
    }
}

