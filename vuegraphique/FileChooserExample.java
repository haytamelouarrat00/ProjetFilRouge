package ProjetFilRouge.vuegraphique;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class FileChooserExample extends JFrame {

    private JTextArea textArea;

    public FileChooserExample() {
        super("File Chooser Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new OpenListener());
        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(FileChooserExample.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new FileChooserExample();
    }
}

