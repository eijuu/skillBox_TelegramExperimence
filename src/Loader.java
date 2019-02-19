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
	public static final String 	PROD_SERVER 	= "149.154.167.50:443";
	public static final String	APP_HASH 	= "529e70d628008c6d9927dda6003a3b58";
	public static final Integer	APP_ID 		= 778578;

	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String phoneNumber;
		while (true)
		{
			System.out.println("============================");
			System.out.println("Please, type phone number:");
			phoneNumber = cleanNumberPhone(reader.readLine().trim());
			if (checkNumberPhoneCorrect(phoneNumber)) 
			{
				break;
			} 
			else
			{
				System.out.println("Phone number isn't correct!");
			}
		}

		System.out.println("Phone number is correct! Your phone number: " + phoneNumber);
		System.out.println("============================");

		TelegramApiBridge bridge = new TelegramApiBridge(PROD_SERVER, APP_ID, APP_HASH);
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

	private static String cleanNumberPhone(String phone)
	{
		/**
		 * реализовать очистку номера телефона, вводимого из консоли, от лишних символов, чтобы его можно было вводить не только в формате
		 *
		 * 79091234567, но и с использованием лишних символов, например:
		 * +7 909 123-­45-­67
		 * +7 (909) 1234567
		 * 7­-909-­123-­45-­67
		 */
		return phone.replaceAll("\\D+", "");
	}

	private static boolean checkNumberPhoneCorrect(String phone)
	{
		return phone.matches("(7|8)\\d{10}");
	}
}



