import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AsciiArtViewer {

    public static void main(String[] args) {
        // Specify the path to your ASCII art text file
        String filePath = "medieval-girl.txt";

        // Read ASCII art from the file
        StringBuilder asciiArt = new StringBuilder();
        final int[] dimensions = new int[2]; // Array to hold rows and columns

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                asciiArt.append(line).append("\n");
                dimensions[0]++; // Increment rows
                dimensions[1] = Math.max(dimensions[1], line.length()); // Update max columns
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading the file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if any ASCII art was read
        if (dimensions[0] == 0) {
            JOptionPane.showMessageDialog(null, "No ASCII art found in the file.", "Empty File", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Display the ASCII art in a GUI
        SwingUtilities.invokeLater(() -> createAndShowGUI(asciiArt.toString(), dimensions[0], dimensions[1]));
    }

    private static void createAndShowGUI(String asciiArt, int rows, int columns) {
        JFrame frame = new JFrame("ASCII Art Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create a JTextArea to display the ASCII art
        JTextArea textArea = new JTextArea(asciiArt);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Monospaced font for ASCII art
        textArea.setEditable(false);
        textArea.setLineWrap(false); // Disable line wrap for ASCII art
        textArea.setCaretPosition(0); // Scroll to the top

        // Add a JScrollPane to allow scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Display the number of rows and columns in the title bar
        frame.setTitle("ASCII Art Viewer - Rows: " + rows + ", Columns: " + columns);
        
        // Center the window on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
