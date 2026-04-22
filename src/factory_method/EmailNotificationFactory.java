package factory_method;

public class EmailNotificationFactory extends NotificationFactory{
    public Notification creerNotification() {
        return new EmailNotification();
    }
}
