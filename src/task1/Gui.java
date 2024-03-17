package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private JTextField classNameField;
    private JTextArea descriptionArea;

    public Gui() {
        super("Опис типу");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel classNameLabel = new JLabel("Ім'я класу:");
        classNameField = new JTextField(10);

        JButton getDescButton = new JButton("Отримати опис");
        getDescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = classNameField.getText();
                try {
                    String description = Reflection.getFullDescription(className);
                    descriptionArea.setText(description);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(Gui.this, "Клас не знайдено!", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        descriptionArea = new JTextArea(21, 20);
        descriptionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);

        panel.add(classNameLabel, BorderLayout.WEST);
        panel.add(classNameField, BorderLayout.CENTER);
        panel.add(getDescButton, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui();
            }
        });
    }
}
