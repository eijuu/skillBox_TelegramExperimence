import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
    private JPanel rootPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel logoPanel;
    private JPanel logoPicPanel;
    private JLabel logoLabel;
    private JPanel inputPhonePanel;
    private JFormattedTextField inputPhoneFormattedTextField;
    private JLabel inputPhoneLabel;
    private JButton continueButton;

    private String loginNumberPhone;

    public Login() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        inputPhoneLabel.setText("<html>Введите код страны и номер<BR>" +
                "вашего мобильного телефона</html>");
        inputPhoneFormattedTextField.setPreferredSize(new Dimension(200, 30));
        inputPhoneFormattedTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    continueButton.doClick();
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void setLoginNumberPhone() {
        loginNumberPhone = clearPhoneNumber(inputPhoneFormattedTextField.getText());
    }

    public String getLoginNumberPhone() {
        return loginNumberPhone;
    }

    public boolean checkInputtedNumberPhoneCorrect() {
        setLoginNumberPhone();
        return loginNumberPhone.matches("(7|8)\\d{10}");
    }

    private String clearPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("\\D+", "");
    }

    public JButton getContinueButton() {
        return continueButton;
    }
}
