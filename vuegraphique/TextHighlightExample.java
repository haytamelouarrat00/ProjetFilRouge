package ProjetFilRouge.vuegraphique;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class TextHighlightExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Text Highlight Example");
                JTextArea textArea = new JTextArea(10, 40);
                JScrollPane scrollPane = new JScrollPane(textArea);
                frame.add(scrollPane);

                String text = "This is a sample text that contains the word sample multiple times.";
                textArea.setText(text);

                // Highlight the word "sample"
                Highlighter highlighter = textArea.getHighlighter();
                DefaultHighlighter.DefaultHighlightPainter painter =
                        new DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.YELLOW);

                int index = text.indexOf("sample");
                while (index >= 0) {
                    int endIndex = index + "sample".length();
                    try {
                        highlighter.addHighlight(index, endIndex, painter);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    index = text.indexOf("sample", endIndex);
                }

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}

