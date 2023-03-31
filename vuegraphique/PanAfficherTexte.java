package ProjetFilRouge.vuegraphique;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PanAfficherTexte extends JPanel {

    private JTextArea textArea;
    public PanAfficherTexte(File file) {
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Read the contents of the file into the text area
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(scrollPane);
        this.add(textArea);
        
    }
}
