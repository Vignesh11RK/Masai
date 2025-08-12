//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        EventManager subject=new EventManager();
        subject.register(new EmailNotifier("vigneshrk3001@gmail.com"));
        subject.register(new SmsNotifier("7715826632"));
        subject.notifyObservers("New Policy created: POL011");



    }
}