package lgsw;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Lego extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> objectList;

    public Lego() {
        setTitle("Object List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        objectList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(objectList);

        add(scrollPane, BorderLayout.CENTER);

        readObjectsFromFile("objects.txt");
    }

    private void readObjectsFromFile(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String price = parts[1];
                String id = parts[2];
                listModel.addElement(name + ", Price: " + price + ", ID: " + id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Lego objectListUI = new Lego();
            objectListUI.setVisible(true);
        });
    }
}