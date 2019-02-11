import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader
{
	public static final String 	PROD_SERVER = "149.154.167.50:443";
	public static final String 	APP_HASH 	= "529e70d628008c6d9927dda6003a3b58";
	public static final Integer APP_ID 		= 778578;

	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		TelegramApiBridge bridge = new TelegramApiBridge(PROD_SERVER, APP_ID, APP_HASH);

		System.out.println("Please, type phone number:");
		String phoneNumber = reader.readLine().trim();
		AuthCheckedPhone checkedPhone = bridge.authCheckPhone(phoneNumber);
		System.out.println(checkedPhone.isRegistered());

		AuthSentCode authSentCode = bridge.authSendCode(phoneNumber); //отправка кода
		System.out.println("Have been sent code. Please, type this code:");
		String sentCode = reader.readLine().trim();
		System.out.println("Your code: " + sentCode); //ввод кода

		AuthAuthorization authAuthorization = bridge.authSignIn(sentCode); //пробуем авторизоваться
		User user = authAuthorization.getUser();				//получаем юзера
		System.out.println("You signed in Telegram as " + user.getFirstName() + " " + user.getLastName());
	}
}
