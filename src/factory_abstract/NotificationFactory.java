package factory_abstract;

public interface NotificationFactory {
    Alert creerAlert();
    Rapport creerRapport();
    Confirmation creerConfirmation();
}

