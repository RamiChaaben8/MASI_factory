package factory_abstract;

public class EmailFactory implements NotificationFactory {
    @Override
    public Alert creerAlert() {
        return new EmailAlert();
    }

    @Override
    public Rapport creerRapport() {
        return new EmailRapport();
    }

    @Override
    public Confirmation creerConfirmation() {
        return new EmailConfirmation();
    }
}

