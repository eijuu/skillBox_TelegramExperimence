import javax.swing.*;
import java.awt.*;

public class ConfirmationCode {
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JPanel logoPanel;
    private JPanel logoPicPanel;
    private JLabel logoLabel;
    private JLabel inputPhoneLabel;
    private JPanel inputPhonePanel;
    private JPasswordField confirmCodePasswordField;
    private JButton continueButton;
    private JLabel phoneNumberLabel;

    private String inputtedConfirmCode;

    public ConfirmationCode() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        inputPhoneLabel.setText("<html>На данный номер телефона было отправлено<BR>" +
                "SMS-сообщение с кодом подтверждения.<BR>" +
                "Пожалуйста, введите этот код в поле ниже:</html>");
        confirmCodePasswordField.setPreferredSize(new Dimension(100, 30));
    }

    public void setInputtedConfirmCode() {
        inputtedConfirmCode = new String(confirmCodePasswordField.getPassword());
    }

    public String getInputtedConfirmCode() {
        return inputtedConfirmCode;
    }

    public void setPhoneNumberLabel(String phoneNumber) {
        phoneNumberLabel.setText(phoneNumber);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getContinueButton() {
        return continueButton;
    }
}
