import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {
    public static final String PROD_SERVER = "149.154.167.50:443";
    public static final String APP_HASH = "529e70d628008c6d9927dda6003a3b58";
    public static final Integer APP_ID = 778578;

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());

        Login loginForm = new Login();
        ConfirmationCode confirmationCodeForm = new ConfirmationCode();
        ContactList contactListForm = new ContactList();

        frame.setContentPane(loginForm.getRootPanel());

        TelegramApiBridge bridge = new TelegramApiBridge(PROD_SERVER, APP_ID, APP_HASH);

        loginForm.getContinueButton().addActionListener(e -> {
            if (!loginForm.checkInputtedNumberPhoneCorrect()) {
                JOptionPane.showMessageDialog(
                        loginForm.getRootPanel(),
                        "Номер телефона не корректен",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                AuthCheckedPhone checkedPhone = bridge.authCheckPhone(
                        loginForm.getLoginNumberPhone());
                if (checkedPhone.isRegistered()) {
                    confirmationCodeForm.setPhoneNumberLabel(loginForm.getLoginNumberPhone());


                    AuthSentCode authSentCode = bridge.authSendCode(
                            loginForm.getLoginNumberPhone());

                    frame.setContentPane(confirmationCodeForm.getRootPanel());
                    confirmationCodeForm.setFocusToConfirmCodePasswordField();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                            loginForm.getRootPanel(),
                            "Номер телефона не зарегистрирован",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        confirmationCodeForm.getContinueButton().addActionListener(e -> {
            try {
                confirmationCodeForm.setInputtedConfirmCode();
                if (!confirmationCodeForm.getInputtedConfirmCode().isBlank()) {

                    /*if (!authSentCode.isRegistered()) {
                        JOptionPane.showMessageDialog(
                                loginForm.getRootPanel(),
                                "Неверный код",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }*/

                    AuthAuthorization authAuthorization = bridge.authSignIn(
                            confirmationCodeForm.getInputtedConfirmCode());

                    User user = authAuthorization.getUser();
                    contactListForm.fillAccountName(user.getFirstName() +
                            " " + user.getLastName());

                    ArrayList<UserContact> userContactList = bridge.contactsGetContacts();
                    contactListForm.fillContactList(userContactList);


                    frame.setContentPane(contactListForm.getRootPanel());
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                            confirmationCodeForm.getRootPanel(),
                            "Введите присланный в СМС-сообщении код",
                            "Сообщение",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        frame.setTitle("Javagramm");
        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}



