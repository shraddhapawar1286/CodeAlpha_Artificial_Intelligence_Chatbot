import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

class ChatBot {
    private HashMap<String, String> responses;

    public ChatBot() {
        responses = new HashMap<>();

        // FAQ Training Data
        responses.put("hello", "Hello! How can I help you?");
        responses.put("hi", "Hi! Nice to meet you.");
        responses.put("how are you", "I am fine. Thank you!");
        responses.put("what is java", "Java is an Object-Oriented Programming Language.");
        responses.put("who are you", "I am an AI Chatbot created in Java.");
        responses.put("bye", "Goodbye! Have a nice day.");
    }

    public String getResponse(String input) {
        input = input.toLowerCase().trim();

        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                return responses.get(key);
            }
        }

        return "Sorry, I don't understand.";
    }
}

public class AIChatBotGUI extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private ChatBot bot;

    public AIChatBotGUI() {

        bot = new ChatBot();

        setTitle("AI ChatBot");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input Field
        inputField = new JTextField();

        // Send Button
        sendButton = new JButton("Send");

        // Bottom Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        // Add Components
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Events
        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        chatArea.append("Bot: Hello! I am your AI ChatBot.\n\n");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String userText = inputField.getText().trim();

        if (!userText.isEmpty()) {

            chatArea.append("You: " + userText + "\n");

            String response = bot.getResponse(userText);

            chatArea.append("Bot: " + response + "\n\n");

            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AIChatBotGUI());
    }
}