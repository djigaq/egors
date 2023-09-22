import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {

    private static List<Clip> soundClips = new ArrayList<>();
    private JTextArea textArea;
    private JButton translateButton;
    private JButton stopButton;

    private JFrame jFrame;
    boolean stopTranslation = false;

    public Translator() {
        jFrame = new JFrame("Переводчик");
        jFrame.setSize(747, 720);
        jFrame.setVisible(true);
        jFrame.setVisible(true);

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.white, 20));
        textArea.setFont(new Font("Arial", Font.PLAIN, 55));
        JScrollPane scrollPane = new JScrollPane(textArea);

        translateButton = new JButton("Перевести");
        translateButton.setFont(new Font("Arial", Font.BOLD, 16));
        translateButton.setForeground(Color.WHITE);
        translateButton.setBackground(Color.GREEN);
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTranslation = false;
                translateToMorseCodeInBackground();
            }
        });

        stopButton = new JButton("   Стоп    ");
        stopButton.setFont(new Font("Arial", Font.BOLD, 16));
        stopButton.setForeground(Color.WHITE);
        stopButton.setBackground(Color.RED);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTranslation = true;
            }
        });

        Image img = Toolkit.getDefaultToolkit().getImage("morse.png");
        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);

        jFrame.add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(translateButton);
        bottomPanel.add(stopButton);

        jFrame.add(centerPanel, BorderLayout.CENTER);
        jFrame.add(bottomPanel, BorderLayout.SOUTH);

    }


    private void translateToMorseCodeInBackground() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                translateToMorseCode();
                return null;
            }
        };

        worker.execute();
    }


    private void translateToMorseCode() throws InterruptedException {
        String inputText = textArea.getText().toUpperCase();
        System.out.println("PNH");
        Clip clip = null;

        for (char c : inputText.toCharArray()) {
            if (stopTranslation) {
                break;
            }

            if (c == ' ') {
                Thread.sleep(1000); // Пауза между словами
            } else if (morseCodeMap.containsKey(c)) {
                String morseCode = morseCodeMap.get(c);
                for (char morseChar : morseCode.toCharArray()) {
                    if (stopTranslation) {
                        break;
                    }

                    if (morseChar == '.') {
                        clip = playSound("e.wav");
                    } else if (morseChar == '-') {
                        clip = playSound("t.wav");
                    }
                    Thread.sleep(200); // Пауза между точками и чертами внутри символа
                }
                Thread.sleep(500); // Пауза между символами
            }
        }
    }

    private Map<Character, String> morseCodeMap;
    {
        morseCodeMap = new HashMap<>();
        // Английский алфавит
        morseCodeMap.put('A', ".-");
        morseCodeMap.put('B', "-...");
        morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-..");
        morseCodeMap.put('E', ".");
        morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--.");
        morseCodeMap.put('H', "....");
        morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---");
        morseCodeMap.put('K', "-.-");
        morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--");
        morseCodeMap.put('N', "-.");
        morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--.");
        morseCodeMap.put('Q', "--.-");
        morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "...");
        morseCodeMap.put('T', "-");
        morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-");
        morseCodeMap.put('W', ".--");
        morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--");
        morseCodeMap.put('Z', "--..");

        // Цифры
        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");

        // Русский алфавит
        morseCodeMap.put('А', ".-");
        morseCodeMap.put('Б', "-...");
        morseCodeMap.put('В', ".--");
        morseCodeMap.put('Г', "--.");
        morseCodeMap.put('Д', "-..");
        morseCodeMap.put('Е', ".");
        morseCodeMap.put('Ж', "...-");
        morseCodeMap.put('З', "--..");
        morseCodeMap.put('И', "..");
        morseCodeMap.put('Й', ".---");
        morseCodeMap.put('К', "-.-");
        morseCodeMap.put('Л', ".-..");
        morseCodeMap.put('М', "--");
        morseCodeMap.put('Н', "-.");
        morseCodeMap.put('О', "---");
        morseCodeMap.put('П', ".--.");
        morseCodeMap.put('Р', ".-.");
        morseCodeMap.put('С', "...");
        morseCodeMap.put('Т', "-");
        morseCodeMap.put('У', "..-");
        morseCodeMap.put('Ф', "..-.");
        morseCodeMap.put('Х', "....");
        morseCodeMap.put('Ц', "-.-.");
        morseCodeMap.put('Ч', "---.");
        morseCodeMap.put('Ш', "----");
        morseCodeMap.put('Щ', "--.-");
        morseCodeMap.put('Ъ', ".--.-.");
        morseCodeMap.put('Ы', "-.--");
        morseCodeMap.put('Ь', "-..-");
        morseCodeMap.put('Э', "..-..");
        morseCodeMap.put('Ю', "..--");
        morseCodeMap.put('Я', ".-.-");
    }

    private static Clip playSound(String fileName) {
        try {
            File audioFile = new File(fileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            soundClips.add(clip);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void display() {
        jFrame.setVisible(true);
    }
}
