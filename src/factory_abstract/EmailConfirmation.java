package factory_abstract;

public class EmailConfirmation implements Confirmation {
    @Override
    public void envoyer() {
        System.out.println("Envoi d'une confirmation Email");
    }
}

