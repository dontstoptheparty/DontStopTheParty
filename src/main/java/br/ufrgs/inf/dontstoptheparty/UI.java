package br.ufrgs.inf.dontstoptheparty;


import javax.swing.*;
import java.awt.*;

public class UI{

    public UI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("aqui desgraçado");
        JLabel label = new JLabel("Digite o texto abaixo:");

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,10));
        panel.setLayout(new GridLayout(0,1));
        panel.add(label);
        panel.add(button);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Dont Stop The Party");
        frame.pack();
        frame.setVisible(true);
    }






    public static void main(String[] args) {
        new UI();

    }
}