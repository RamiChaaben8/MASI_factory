package factory_method;

public class SMSNotificationFactory extends NotificationFactory{
    public Notification creerNotification() {
        return new SMSNotification();
    }
}
