import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crosses extends JPanel {
    static JFrame frame;
    static List<JButton> fieldButtons;
    static JLabel infoLabel;

    static String[] gameField;
    static String turn;
    static String winner;
    static int cellValue;


    public static void main(String[] args) {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Crosses crosses = new Crosses();
        frame.setContentPane(crosses);
        frame.setVisible(true);

        fieldButtons = new ArrayList<>();
        infoLabel = new JLabel("sts");

        gameField = new String[9];
        turn = "O";
        winner = null;
        cellValue = 0;

        emptyField();
    }

    static void emptyField() {
        frame.setLayout(null);
        infoLabel.setBounds(-10, 230, 316, 200);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(infoLabel);
        int xInc;
        int yInc = -100;

        for (int i = 0; i < 9; i++) {
            gameField[i] = "empty";
        }

        for (int y = 0; y < 3; y++) {
            xInc = 0;
            yInc = yInc + 100;

            for (int x = 0; x < 3; x++) {
                fieldButtons.add(new JButton(""));

                fieldButtons.get(cellValue).setBounds(xInc, yInc, 100, 100);
                frame.add(fieldButtons.get(cellValue));

                fieldButtons.get(cellValue).addActionListener(
                        new ActionListener() {
                            final int value = cellValue;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (winner == null && gameField[value].equals("empty")) {
                                    gameField[value] = turn;
                                    fieldButtons.get(value).setText(turn);

                                    if (turn.equals("X")) {
                                        turn = "O";
                                    } else {
                                        turn = "X";
                                    }
                                    winOrNot();
                                } else if (winner != null) {
                                    infoLabel.setText("Игра окончена! " + winner + " победили!");
                                } else {
                                    infoLabel.setText("Эта клетка занята!");
                                }
                            }
                        });
                cellValue++;
                xInc = xInc + 100;
            }
            frame.setSize(316, 400);
            frame.setResizable(false);
        }
    }

    static void winOrNot() {
        winner = checkWinner();
        if (winner != null) {
            if (winner.equalsIgnoreCase("draw")) {
                infoLabel.setText("Ничья");
            } else {
                infoLabel.setText("Игра окончена! " + winner + " победили!");
            }
        }
    }
    static private String checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> gameField[0] + gameField[1] + gameField[2];
                case 1 -> gameField[3] + gameField[4] + gameField[5];
                case 2 -> gameField[6] + gameField[7] + gameField[8];
                case 3 -> gameField[0] + gameField[3] + gameField[6];
                case 4 -> gameField[1] + gameField[4] + gameField[7];
                case 5 -> gameField[2] + gameField[5] + gameField[8];
                case 6 -> gameField[6] + gameField[4] + gameField[2];
                case 7 -> gameField[0] + gameField[4] + gameField[8];
                default -> null;
            };
            if (line.equals("XXX")) {
                return "Крестики";
            } else if (line.equals("OOO")) {
                return "Нолики";
            }

        }
        for (int i = 0; i < 9; i++) {
            if (Arrays.asList(gameField).contains("empty")) {
                break;
            } else if (i == 8) {
                return "draw";
            }
        }
        infoLabel.setText("Ходят " + turn);
        return null;
    }
}
