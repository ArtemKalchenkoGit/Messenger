import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;

public class MenuWindow extends JFrame{

    MenuWindow(){
        createAndShowMainPage();
    }
    public String name;

    String history = "<html>";

    private final JFrame frame = new JFrame("Main Page");

    private final JPanel name_panel = new JPanel();
    private final JPanel main_panel = new JPanel();
    private final JPanel button_panel = new JPanel();

    private final JLabel label_name = new JLabel("""
                <html>
                    <font color = white size = 12>Your name: </font>
                </html>
                """);

    private final JTextField name_field = new JTextField();

    Button submit_button = new Button("Submit");

    public void createAndShowMainPage() {
        //Create and set up the window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();

        name_field.setColumns(12);

        submit_button.setPreferredSize(new Dimension(80,40));
        submit_button.addActionListener(new submitButtonClick());

        //name panel
        name_panel.setBackground(new Color(0x2162AF));
        name_panel.add(label_name);
        name_panel.add(name_field);

        //button panel
        button_panel.setBackground(new Color(0x134D8D));
        button_panel.add(submit_button);

        //main panel
        main_panel.add(name_panel);
        main_panel.add(button_panel);
        main_panel.setLayout(new BoxLayout(main_panel,BoxLayout.PAGE_AXIS));

        //frame
        frame.setPreferredSize(new Dimension(300 ,200));
        content.add(main_panel);
        frame.setResizable(false);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    private final JTextField messageField = new JTextField();
    private final JFrame chat_frame = new JFrame();
    private final JLabel historyOut = new JLabel();
    private void createChats(){
        JPanel mainPanel = new JPanel();
        JPanel textWriter = new JPanel();
        JPanel textSender = new JPanel();

        textSender.setPreferredSize(new Dimension(500,40));
        textSender.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Send here"));
        textSender.setBackground(new Color(0x3886D3));
        textSender.setLayout(new BoxLayout(textSender,BoxLayout.PAGE_AXIS));

        JScrollPane scrollablePane = new JScrollPane(historyOut);
        scrollablePane.createVerticalScrollBar();
        scrollablePane.setBackground(new Color(0x3D98EA));

        messageField.setColumns(40);

        Button sendMessage = new Button("Send");
        sendMessage.addActionListener(new sendMessage());

        chat_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chat_frame.setResizable(false);

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(500,500));

        scrollablePane.setPreferredSize(new Dimension(500,370));
        scrollablePane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),"Read here")
        );
        scrollablePane.getViewport().setBackground(new Color(0x7BC1FC));

        textWriter.setPreferredSize(new Dimension(500,40));
        textWriter.setBackground(new Color(0x2188EA));
        textWriter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Write here"));

        textWriter.add(messageField);
        textSender.add(sendMessage);
        mainPanel.add(scrollablePane);
        mainPanel.add(textWriter);
        mainPanel.add(textSender);
        chat_frame.add(mainPanel);

        chat_frame.pack();
        chat_frame.setVisible(true);
    }

    private class sendMessage implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!messageField.getText().equals("")) {
                history += "<font size=5 color=blue>" + name + "<br></font>" +
                        "<font size=4 color=white>" + messageField.getText() + "<br></font>";
                messageField.setText("");
                historyOut.setText(history);
            }
        }
    }
    private class submitButtonClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!name_field.getText().equals("")) {
                name = name_field.getText();
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                createChats();
            }
        }
    }
}
