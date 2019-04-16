/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import yatzy.logic.*;

/**
 *
 * @author aleksi
 */
public class Gui {
    JFrame frame;
    Gamelogic logic;
    
    public Gui(Gamelogic logic) {
        this.frame = new JFrame();
        this.frame.setSize(500,500);
        this.logic = logic;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void addPlayersScreen() {
        JPanel panelChoosePlayers = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelNamePlayers = new JPanel();
        panelNamePlayers.setLayout(new BoxLayout(panelNamePlayers, BoxLayout.Y_AXIS));
        JLabel labelSelect = new JLabel("Select number of players:");
        String[] options = new String[]{"-","1","2","3","4"};
        JComboBox players = new JComboBox(options);        
        panelChoosePlayers.add(labelSelect);
        panelChoosePlayers.add(players);
        this.frame.add(panelChoosePlayers, BorderLayout.NORTH);
        this.frame.add(panelNamePlayers, BorderLayout.WEST);
        this.frame.setVisible(true);
        
        players.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfPlayers = players.getSelectedIndex();
                logic.playersReset();
                logic.setNumberOfPlayers(numberOfPlayers);
                panelNamePlayers.removeAll();
                frame.revalidate();
                panelNamePlayers.add(new JLabel("Choose player names"));
                for (int i = 1; i <= numberOfPlayers; i++) {
                    panelNamePlayers.add(new JLabel("Player" + i 
                            + ":"));
                    JTextField field = new JTextField("Player" + i);
                    field.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                            field.getPreferredSize().height));
                    panelNamePlayers.add(field);
                    JButton confirm = new JButton("Confirm");
                    panelNamePlayers.add(confirm);                    
                    confirm.addActionListener(new NameListener(field, confirm));
                }
                frame.revalidate();
            }
        });
        

    }
    
    public void chooseFirstPlayerScreen() {
        this.frame.getContentPane().removeAll();
        JPanel panelChoose = new JPanel(new FlowLayout(FlowLayout.LEFT));
        /* create list for a ComboBox */
        String[] playerlist = new String[logic.getNumberOfPlayers() + 1];
        playerlist[0] = "random";
        for (int i = 1; i < playerlist.length; i++) {
            playerlist[i] = logic.getNextPlayer();
        }
        JComboBox cBoxPlayers = new JComboBox(playerlist);        
        JLabel labelChooseFirst = new JLabel("Choose first player:");
        panelChoose.add(labelChooseFirst);
        panelChoose.add(cBoxPlayers);
        
        cBoxPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cBoxPlayers.getSelectedIndex() == 0) {
                    logic.randomizeFirstPlayer();
                } else {
                    logic.setFirstPlayer(cBoxPlayers.getSelectedItem());
                }
                System.out.println("Testing next player: " + logic.peekNextPlayer());
            }
        });
        
        this.frame.add(panelChoose, BorderLayout.CENTER);
        this.frame.revalidate();
        this.frame.repaint();
    }
    
    private class NameListener implements ActionListener {
        private JTextField field;
        private JButton button;
        
        public NameListener(JTextField field, JButton button) {
            this.field = field;
            this.button = button;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            logic.addPlayer(this.field.getText());
            this.button.removeActionListener(this);
            this.button.setText("Added " + this.field.getText());
            if (logic.getNumberOfPlayers() == logic.getPlayerList().size()) {               
                chooseFirstPlayerScreen();
            }
        }
    }    
}

