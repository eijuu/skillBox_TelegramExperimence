import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContactList {
    private JPanel rootPanel;
    private JPanel northPanel;
    private JPanel logoPanel;
    private JPanel accountPanel;
    private JPanel westPanel;
    private JPanel searchUserPanel;
    private JPanel contactListPanel;
    private JPanel userAddPanel;
    private JPanel centerPanel;
    private JPanel userMessagePanel;
    private JPanel inputMessagePanel;
    private JPanel messagesPanel;
    private JLabel accountName;


    public ContactList() {
        contactListPanel.setLayout(new BoxLayout(contactListPanel, BoxLayout.Y_AXIS));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void fillAccountName(String accountName) {
        this.accountName.setText(accountName);
    }

    public void fillContactList(ArrayList<UserContact> userContactArrayList) {
        int contactCount = userContactArrayList.size();
        for (int i = 0; i < contactCount; i++) {
            UserContact contact = userContactArrayList.get(i);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JLabel label = new JLabel();
            label.setText(contact.getFirstName() + " " + contact.getLastName());
            panel.add(label);
            contactListPanel.add(panel);
        }

    }

}
