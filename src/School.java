import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class School {
    private JFrame jFrame;
    private static Clip clip = null;
    public School() {
//        SwingUtilities.invokeLater(() -> {
        jFrame = new JFrame("Освоение азбуки морзе");
        jFrame.setSize(1200, 720);
//            jFrame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));

        // Добавляем фоновое изображение
        ImageIcon background = new ImageIcon("morse.jpg"); // Замените "background.jpg" на путь к вашему изображению
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new BorderLayout());
        jFrame.setContentPane(backgroundLabel);

        JButton button = new JButton(" Е Т И А М Н ");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЕТИАМН.wav"); //
                }
            }
        });
        panel.add(button);

        JButton button1 = new JButton(" С У Д К ");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("СУДК.wav"); //
                }
            }
        });
        panel.add(button1);

        JButton button2 = new JButton(" В Р Г О ");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ВРГО.wav"); //
                }
            }
        });
        panel.add(button2);

        JButton button3 = new JButton(" Ч Ш ");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЧШ.wav"); //
                }
            }
        });
        panel.add(button3);

        JButton button4 = new JButton(" Х Ж ");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ХЖ.wav"); //
                }
            }
        });
        panel.add(button4);

        JButton button5 = new JButton(" Б Ь ");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("БЬ.wav"); //
                }
            }
        });
        panel.add(button5);

        JButton button6 = new JButton(" Й П ");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЙП.wav"); //
                }
            }
        });
        panel.add(button6);

        JButton button7 = new JButton("З Щ ");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЗЩ.wav"); //
                }
            }
        });
        panel.add(button7);

        JButton button8 = new JButton(" Ц Ы ");
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЦЫ.wav"); //
                }
            }
        });
        panel.add(button8);

        JButton button9 = new JButton(" Л Я ");
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЛЯ.wav"); //
                }
            }
        });
        panel.add(button9);

        JButton button10 = new JButton(" Ю Ф ");
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("ЮФ.wav"); //
                }
            }
        });
        panel.add(button10);

        JButton button11 = new JButton("Э ");
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("Э.wav"); //
                }
            }
        });
        panel.add(button11);

        JButton button12 = new JButton("1 2 3 4 5 ");
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("12345.wav"); //
                }
            }
        });
        panel.add(button12);

        JButton button13 = new JButton("6 7 8 9 0 ");
        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("67890.wav"); //
                }
            }
        });
        panel.add(button13);

        JButton button14 = new JButton("Все цифры ");
        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("всецифры.wav"); //
                }
            }
        });
        panel.add(button14);

        JButton button15 = new JButton("Все буквы ");
        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); // Останавливаем музыку, если она уже играет
                } else {
                    playSound("всебуквы.wav"); //
                }
            }
        });
        panel.add(button15);


        backgroundLabel.add(panel, BorderLayout.CENTER);

        jFrame.setVisible(true);
//    } );
    }

    // Метод для воспроизведения аудио
    public static void playSound(String soundFile) {
        try {
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void display() {
        jFrame.setVisible(true);
    }
}