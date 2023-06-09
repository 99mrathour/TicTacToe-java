package com.tictactoe;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class TicTacToeGUI extends Frame implements ActionListener {
    private Button[][] buttons;
    private boolean isXTurn;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setLayout(new GridLayout(3, 3));

        buttons = new Button[3][3];
        isXTurn = true;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button();
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(300, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();

        if (button.getLabel().equals("")) {
            if (isXTurn) {
                button.setLabel("X");
            } else {
                button.setLabel("O");
            }

            isXTurn = !isXTurn;
        }

        checkGameStatus();
    }

    private void checkGameStatus() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!buttons[row][0].getLabel().equals("") && buttons[row][0].getLabel().equals(buttons[row][1].getLabel())
                    && buttons[row][0].getLabel().equals(buttons[row][2].getLabel())) {
                showWinner(buttons[row][0].getLabel());
                return;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!buttons[0][col].getLabel().equals("") && buttons[0][col].getLabel().equals(buttons[1][col].getLabel())
                    && buttons[0][col].getLabel().equals(buttons[2][col].getLabel())) {
                showWinner(buttons[0][col].getLabel());
                return;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getLabel().equals("") && buttons[0][0].getLabel().equals(buttons[1][1].getLabel())
                && buttons[0][0].getLabel().equals(buttons[2][2].getLabel())) {
            showWinner(buttons[0][0].getLabel());
            return;
        }

        if (!buttons[0][2].getLabel().equals("") && buttons[0][2].getLabel().equals(buttons[1][1].getLabel())
                && buttons[0][2].getLabel().equals(buttons[2][0].getLabel())) {
            showWinner(buttons[0][2].getLabel());
            return;
        }

        // Check for a tie
        boolean isTie = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getLabel().equals("")) {
                    isTie = false;
                    break;
                }
            }
        }

        if (isTie) {
            showMessage("It's a tie!");
            resetGame();
        }
    }

    private void showWinner(String winner) {
        showMessage("Player " + winner + " wins!");
        resetGame();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setLabel("");
            }
        }

        isXTurn = true;
    }

    public static void main(String[] args) {
        TicTacToeGUI game = new TicTacToeGUI();
    }
}
