package com.company;

import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new MyPanel());
        this.setBounds(100,100,500,500);
        this.setVisible(true);
    }
}
