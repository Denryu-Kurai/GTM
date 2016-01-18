package Modelo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

// @author Alfonso Arcos
public class Mascaras extends javax.swing.JFrame {

    public void mascaraDNI(final JTextField a) {
        for (int i = 0; i < a.getKeyListeners().length; i++) {
            a.removeKeyListener(a.getKeyListeners()[i]);
        }

        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (a.getText().length() < 8) {
                    if (!Character.isDigit(c)) {
                        getToolkit().beep();
                        e.consume();
                    }
                } else if (a.getText().length() == 8) {
                    if (Character.isDigit(c)) {
                        getToolkit().beep();
                        e.consume();
                    }
                } else {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void mascaraString(final JTextField a, final int tam) {
        for (int i = 0; i < a.getKeyListeners().length; i++) {
            a.removeKeyListener(a.getKeyListeners()[i]);
        }

        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(a.getText().length() < tam && !Character.isDigit(c))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void mascaraStringNum(final JTextField a, final int tam) {
        for (int i = 0; i < a.getKeyListeners().length; i++) {
            a.removeKeyListener(a.getKeyListeners()[i]);
        }

        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(a.getText().length() < tam)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void mascaraInt(final JTextField a, final int tam) {
        for (int i = 0; i < a.getKeyListeners().length; i++) {
            a.removeKeyListener(a.getKeyListeners()[i]);
        }

        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || a.getText().length() > tam - 1) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void mascaraDouble(final JTextField a, final int tam) {
        for (int i = 0; i < a.getKeyListeners().length; i++) {
            a.removeKeyListener(a.getKeyListeners()[i]);
        }

        a.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isDigit(c) || a.getText().length() > tam - 1) && (c != '.' || a.getText().contains("."))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }
}
