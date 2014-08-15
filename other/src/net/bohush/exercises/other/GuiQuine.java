package net.bohush.exercises.other;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GuiQuine extends JFrame {

    public GuiQuine() {
        String[] listing = {
            "package net.bohush.exercises.other;",
            "",
            "import java.awt.Font;",
            "import javax.swing.JFrame;",
            "import javax.swing.JTextArea;",
            "",
            "public class GuiQuine extends JFrame {",
            "",
            "    public GuiQuine() {",
            "        String[] listing = {",
            "            };",
            "        JTextArea jTextArea = new JTextArea();",
            "        jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));",
            "        for (int i = 0; i < 10; i++)",
            "            jTextArea.append(listing[i] + System.lineSeparator());",
            "        for (String listing1 : listing) {",
            "            jTextArea.append(String.valueOf(new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}));",
            "            jTextArea.append((char)34 + listing1 + (char)34 + ',' + System.lineSeparator());",
            "        }",
            "        for (int i = 10; i < listing.length; i++)",
            "            jTextArea.append(listing[i] + System.lineSeparator());",
            "        add(jTextArea);",
            "    }",
            "",
            "    public static void main(String[] args) {",
            "        JFrame frame = new GuiQuine();",
            "        frame.setTitle(frame.getClass().getSimpleName());",
            "        frame.pack();",
            "        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);",
            "        frame.setLocationRelativeTo(null);",
            "        frame.setVisible(true);",
            "    }",
            "}",
            };
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        for (int i = 0; i < 10; i++)
            jTextArea.append(listing[i] + System.lineSeparator());
        for (String listing1 : listing) {
            jTextArea.append(String.valueOf(new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}));
            jTextArea.append((char)34 + listing1 + (char)34 + ',' + System.lineSeparator());
        }
        for (int i = 10; i < listing.length; i++)
            jTextArea.append(listing[i] + System.lineSeparator());
        add(jTextArea);
    }

    public static void main(String[] args) {
        JFrame frame = new GuiQuine();
        frame.setTitle(frame.getClass().getSimpleName());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
