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
import java.util.*;
import yatzy.logic.domain.*;
import javax.swing.table.*;
import java.io.*;

/**
 *
 * @author aleksi
 */
public class Gui {
    JFrame frame;
    Gamelogic logic;
    Integer rollCounter;
    int turns;
    HashMap<String, Object> components;
    
    public Gui(Gamelogic logic) {
        this.frame = new JFrame();
        this.frame.setSize(1000,470);
        this.frame.setLocationRelativeTo(null);
        this.logic = logic;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rollCounter = 0;
        this.turns = 0;
        this.components = new HashMap<>();
    }
    
    public void addPlayersScreen() {
        this.logic.resetLogic();
        this.turns = 0;
        this.rollCounter = 0;
        this.components.clear();
        
        JPanel panelChoosePlayers = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelNamePlayers = new JPanel();
        panelNamePlayers.setLayout(new BoxLayout(panelNamePlayers, BoxLayout.Y_AXIS));
        JLabel labelSelect = new JLabel("Select number of players:");
        String[] options = new String[]{"-","1","2","3","4"};
        JComboBox players = new JComboBox(options);        
        panelChoosePlayers.add(labelSelect);
        panelChoosePlayers.add(players);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new CloseListener(this.frame));
        components.put("closeButton", closeButton);
        JPanel closePanel = new JPanel(new FlowLayout());
        closePanel.add(closeButton);
        this.frame.add(closePanel, BorderLayout.SOUTH);
        this.frame.add(panelChoosePlayers, BorderLayout.NORTH);
        this.frame.add(panelNamePlayers, BorderLayout.WEST);
        this.frame.setVisible(true);
        
        players.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfPlayers = players.getSelectedIndex();
                logic.resetLogic();
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
                int componentCount = panelChoose.getComponentCount();
                if (componentCount < 3) {
                    JButton startGame = new JButton("Start Game");
                    
                    startGame.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gameScreen();
                        }
                    });
                    panelChoose.add(startGame);
                    frame.revalidate();
                }              
                if (cBoxPlayers.getSelectedIndex() == 0) {
                    logic.randomizeFirstPlayer();
                } else {
                    logic.setFirstPlayer(cBoxPlayers.getSelectedItem());
                }
            }
        });
        JButton closeButton = (JButton) components.get("closeButton");
        JPanel closePanel = new JPanel(new FlowLayout());
        closePanel.add(closeButton);
        
        this.frame.add(closePanel, BorderLayout.SOUTH);
        this.frame.add(panelChoose, BorderLayout.NORTH);
        this.frame.revalidate();
        this.frame.repaint();
    }
    
    public void gameScreen() {
        this.frame.getContentPane().removeAll();
        
        JPanel playerLayout = new JPanel();
        playerLayout.setLayout(new BoxLayout(playerLayout, BoxLayout.Y_AXIS));        
        Player player = this.logic.getPlayer(this.logic.peekNextPlayer());
        JLabel currentPlayerLabel = new JLabel("Current player: " + player.getName());
        currentPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        String[] columns = {"Combination", "Score"};
        JTable scoreTable = new JTable(player.getScorecard().getScoreArray(), columns);
        scoreTable.setMaximumSize(new Dimension(130, 500));
        TableColumn column1 = scoreTable.getColumnModel().getColumn(0);
        column1.setMaxWidth(100);
        column1.setPreferredWidth(100);
        TableColumn column2 = scoreTable.getColumnModel().getColumn(1);
        column2.setMaxWidth(20);
        column2.setPreferredWidth(20);
        scoreTable.setLocation(2, 10);
        
        JPanel lowerInterfacePanel = new JPanel(new BorderLayout());
        JTextArea promptArea = new JTextArea(player.getName() + " will start the"
                + " game. Press [Roll] to begin.\n(press [Rules] for more information)");
        JPanel lowerButtonsPanel = new JPanel(new FlowLayout());
        JButton rollButton = new JButton("Roll");
        JLabel scoringChoiceLabel = new JLabel("Select combination to score: ");
        JComboBox scoringChoiceBox = new JComboBox();
        JButton confirmScoringButton = new JButton("Score selected combination");
        confirmScoringButton.addActionListener(new EndTurnListener(components));
        JButton rulesButton = new JButton("Rules");
        rulesButton.addActionListener(new RulesListener());
        rulesButton.setHorizontalAlignment(JButton.RIGHT);
        lowerInterfacePanel.add(promptArea, BorderLayout.CENTER);
        lowerInterfacePanel.add(lowerButtonsPanel, BorderLayout.SOUTH);
        lowerButtonsPanel.add(rollButton);
        lowerButtonsPanel.add(scoringChoiceLabel);
        lowerButtonsPanel.add(scoringChoiceBox);
        lowerButtonsPanel.add(confirmScoringButton);
        lowerButtonsPanel.add(rulesButton);
        scoringChoiceLabel.setVisible(false);        
        scoringChoiceBox.setVisible(false);
        confirmScoringButton.setVisible(false);

        scoringChoiceBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmScoringButton.setVisible(true);
        
            }
        });
        
        JPanel dicePanel = new JPanel();
        GridBagLayout diceBag = new GridBagLayout();
        dicePanel.setLayout(diceBag);
        GridBagConstraints diceConstraints = new GridBagConstraints();
        diceConstraints.insets = new Insets(20, 20, 20, 20);
        JToggleButton[] diceButtons = new JToggleButton[5];        
        for (int i = 0; i < 5; i++) {
            diceButtons[i] = new JToggleButton();
            diceButtons[i].setMinimumSize(new Dimension(100, 100));
            diceButtons[i].setPreferredSize(new Dimension(100, 100));
            diceButtons[i].setFont(new Font("Arial", Font.BOLD, 70));
            if (rollCounter == 0) {
                diceButtons[i].setVisible(false);
            }
            dicePanel.add(diceButtons[i], diceConstraints);
        }      
                
        int numberOfPlayers = this.logic.getNumberOfPlayers();
        ArrayList<String> playerNames = this.logic.getPlayerArray();
        ArrayList<Component> playerDisplay = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            playerDisplay.add(new JButton(playerNames.get(i)));
            playerDisplay.add(new JLabel("Score: " + this.logic.getPlayer(playerNames.get(i)).getScore()));
            playerLayout.add(playerDisplay.get(i * 2));
            playerLayout.add(playerDisplay.get(i * 2 + 1));
        }
        
        components.put("promptArea", promptArea);
        components.put("playerDisplay", playerDisplay);
        components.put("playerNames", playerNames);
        components.put("currentPlayerLabel", currentPlayerLabel);
        components.put("diceButtons", diceButtons);
        components.put("scoreTable", scoreTable);        
        components.put("rollButton", rollButton);
        components.put("scoringChoiceLabel", scoringChoiceLabel);
        components.put("scoringChoiceBox", scoringChoiceBox);
        components.put("confirmScoringButton", confirmScoringButton);
        
        rollButton.addActionListener(new RollListener(components));
        
        JButton closeButton = (JButton) components.get("closeButton");
        lowerButtonsPanel.add(closeButton);
        
        this.frame.add(currentPlayerLabel, BorderLayout.NORTH);
        this.frame.add(dicePanel, BorderLayout.CENTER);
        this.frame.add(lowerInterfacePanel, BorderLayout.SOUTH);
        this.frame.add(scoreTable, BorderLayout.WEST);
        this.frame.add(playerLayout, BorderLayout.EAST);
        this.frame.revalidate();
        this.frame.repaint();
    }
    
    public void endScreen() {
        this.frame.getContentPane().removeAll();
        Player[] winners = this.logic.getWinningOrder();
        JButton closeButton = (JButton) components.get("closeButton");
        JButton newGameButton = new JButton("New game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                addPlayersScreen();
            }
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(closeButton);
        JLabel winnerLabel = new JLabel("Congratulations " + winners[winners.length - 1].getName()
                + ", you have won the game with " + winners[winners.length - 1].getScore() 
                + " points!");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        winnerLabel.setForeground(Color.GREEN);
        JPanel scoreScreenPanel = new JPanel();
        scoreScreenPanel.setLayout(new BoxLayout(scoreScreenPanel, BoxLayout.Y_AXIS));
        scoreScreenPanel.add(winnerLabel);
        for (int i = winners.length - 2; i >= 0; i--) {
            scoreScreenPanel.add(new Label((winners.length - i) + ". " 
                    + winners[i].getName() + ", " + winners[i].getScore() + " points"));
        }
        this.frame.add(scoreScreenPanel, BorderLayout.CENTER);
        this.frame.add(buttonsPanel, BorderLayout.SOUTH);
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
    
    private class EndTurnListener implements ActionListener {
        private HashMap<String, Object> components;
        
        public EndTurnListener(HashMap<String, Object> components) {
            this.components = components;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            rollCounter = 0;
            turns++;
            Player player = logic.getPlayer(logic.peekNextPlayer());
            int[] result = new int[5];
            JToggleButton[] diceButtons = (JToggleButton[]) components.get("diceButtons");
            for (int i = 0; i < 5; i++) {
                result[i] = Integer.parseInt(diceButtons[i].getText());
                diceButtons[i].setSelected(false);
                diceButtons[i].setVisible(false);
            }
            JComboBox scoringChoiceBox = (JComboBox) components.get("scoringChoiceBox");
            scoringChoiceBox.setVisible(false);
            JLabel scoringChoiceLabel = (JLabel) components.get("scoringChoiceLabel");
            scoringChoiceLabel.setVisible(false);
            JButton confirmScoringButton = (JButton) components.get("confirmScoringButton");
            confirmScoringButton.setVisible(false);
            JButton rollButton = (JButton) components.get("rollButton");
            rollButton.setVisible(true);
            JTextArea promptArea = (JTextArea) components.get("promptArea");

            
            String choice = scoringChoiceBox.getSelectedItem().toString();
            choice = choice.substring(0, choice.indexOf("."));
            player.getScorecard().addScore(result, Integer.parseInt(choice));
            
            ArrayList<String> playerNames = (ArrayList<String>) components.get("playerNames");
            ArrayList<Component> playerDisplay = (ArrayList<Component>) components.get("playerDisplay");
            JLabel scoreLabel = (JLabel) playerDisplay.get(playerNames.indexOf(player.getName()) * 2 + 1);
            scoreLabel.setText("Score: " + player.getScore());
            
            JTable scoreTable = (JTable) components.get("scoreTable");
            frame.remove(scoreTable);
            components.remove("scoreTable");
            
            logic.getNextPlayer();
            Player nextPlayer = logic.getPlayer(logic.peekNextPlayer());
            promptArea.setText("Your turn " + nextPlayer.getName() + ", press [Roll] to roll the dice");            
            
            JLabel currentPlayerLabel = (JLabel) components.get("currentPlayerLabel");
            currentPlayerLabel.setText("Current player: " + nextPlayer.getName());
            
            String[][] scoreArray = nextPlayer.getScorecard().getScoreArray();
            
            String[] columns = {"Combination", "Score"};
            scoreTable = new JTable(nextPlayer.getScorecard().getScoreArray(), columns);
            scoreTable.setMaximumSize(new Dimension(130, 500));
            TableColumn column1 = scoreTable.getColumnModel().getColumn(0);
            column1.setMaxWidth(100);
            column1.setPreferredWidth(100);
            TableColumn column2 = scoreTable.getColumnModel().getColumn(1);
            column2.setMaxWidth(20);
            column2.setPreferredWidth(20);
            components.put("scoreTable", scoreTable);
            
            frame.add(scoreTable, BorderLayout.WEST);
            
            frame.revalidate();
            frame.repaint();
            
            if (turns >= (logic.getPlayerList().size() * 15)) {
                endScreen();
            }
        }
    }

    private class RollListener implements ActionListener {
        private JToggleButton[] diceButtons;
        private JLabel scoringChoiceLabel;
        private JComboBox scoringChoiceBox;
        private JButton rollButton;
        private JTextArea promptArea;
        
        public RollListener(HashMap<String, Object> components) {
            this.diceButtons = (JToggleButton[]) components.get("diceButtons");
            this.scoringChoiceLabel = (JLabel) components.get("scoringChoiceLabel");
            this.scoringChoiceBox = (JComboBox) components.get("scoringChoiceBox");
            this.rollButton = (JButton) components.get("rollButton");
            this.promptArea = (JTextArea) components.get("promptArea"); 
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            rollCounter++;
            Player player = logic.getPlayer(logic.peekNextPlayer());
            for (int i = 0; i < diceButtons.length; i++) {
                diceButtons[i].setVisible(true);
                if (!diceButtons[i].isSelected() && rollCounter < 4) {
                    diceButtons[i].setText(Integer.toString(logic.rollOnce()));
                }
            }
            this.promptArea.setText("Click the dice you want to keep and press "
                    + "[Roll] to roll again (" + (3 - rollCounter) + " rolls "
                            + "remaining)\nOR\n + Choose a combination from the "
                            + "drop down menu and press [Score selected combination]\n"
                            + "Note: turns can not be skipped, you must make a choice");
            
            int[] result = new int[5];
            for (int i = 0; i < result.length; i++) {
                result[i] = Integer.parseInt(this.diceButtons[i].getText());
            }
            this.scoringChoiceBox.removeAllItems();
            Scorecard scorecard = player.getScorecard();
            String[][] scoreArray = scorecard.getScoreArray();
            for (int i = 0; i < 16; i++) {
                if (scoreArray[i][1].equals("-") && i != 6) {
                    int score = scorecard.countScore(result, i + 1);
                    this.scoringChoiceBox.addItem((i + 1) + ". " + scoreArray[i][0] + " +" + score);
                }
            }
            
            this.scoringChoiceLabel.setVisible(true);
            this.scoringChoiceBox.setVisible(true);
            frame.revalidate();
            frame.repaint();
            if (rollCounter >= 3) {
                this.rollButton.setVisible(false);
            } 
        }
    }
    
    private class RulesListener implements ActionListener {
        
        public RulesListener() {            
        }
        
        @Override        
        public void actionPerformed(ActionEvent e) {
            JFrame rulesFrame = new JFrame();
            rulesFrame.setAlwaysOnTop(true);
            rulesFrame.setSize(600, 450);
            rulesFrame.setLocationRelativeTo(frame);
            rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JTextArea rulesTextArea = new JTextArea();
            try {
                rulesTextArea.read(new InputStreamReader(getClass().getClassLoader()
                        .getResourceAsStream("rules/yatzyRules.txt")), null);
            } catch (Exception ex) {
                rulesTextArea.setText("Error while loading rules text");
            }
            rulesTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(rulesTextArea);
            JButton closeButton = new JButton("Close");
            closeButton.setPreferredSize(new Dimension(80, 30));
            closeButton.addActionListener(new CloseListener(rulesFrame));
            JPanel closePanel = new JPanel(new FlowLayout());
            closePanel.add(closeButton);
            rulesFrame.getContentPane().add(scrollPane);
            rulesFrame.add(closePanel, BorderLayout.SOUTH);  
            rulesFrame.setVisible(true);
            rulesFrame.repaint();
        }
    }
    
    private class CloseListener implements ActionListener {
        private JFrame disposableFrame;
        
        public CloseListener(JFrame disposableFrame) {
            this.disposableFrame = disposableFrame;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            this.disposableFrame.dispose();
        }
    }
}

