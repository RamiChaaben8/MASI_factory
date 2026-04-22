package factory_abstract;

public class SMSFactory implements NotificationFactory {
    @Override
    public Alert creerAlert() {
        return new SMSAlert();
    }

    @Override
    public Rapport creerRapport() {
        return new SMSRapport();
    }

    @Override
    public Confirmation creerConfirmation() {
        return new SMSConfirmation();
    }
}

