
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Example {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACe3cf895452395389d2125500d672fc3d";
    public static final String AUTH_TOKEN = "your_auth_token";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+15558675310"),
                new com.twilio.type.PhoneNumber("+15017122661"),
                "This is the ship that made the Kessel Run in fourteen parsecs?")
            .create();

        System.out.println(message.getSid());
    }
}