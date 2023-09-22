
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.table.TableColumn;


public class Speed {

    private static List<Clip> soundClips = new ArrayList<>();
    JCheckBox checkBox3, checkBox2, checkBox1;
    int w, y, r, t;

    int pmz;
    int pmg;

    boolean stopTranslation;
    private JFrame jFrame;
    String inputText = "";

    DefaultTableModel tableModel;
    String[][] data;


    public Speed() {
        jFrame = new JFrame("Наращивание скорости");
        jFrame.setSize(1200, 720);


        String[] columnNames = {"Группа", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", " 10 "};
        data = new String[][]{{"0x", "", "", "", "", "", "", "", "", "", ""}};
        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };


        JTable table = new JTable(tableModel);

        table.getTableHeader().getColumnModel().getColumn(0).setResizable(false);
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMaxWidth(50); // Ширина столбца #


        table.getTableHeader().setReorderingAllowed(false);


        JScrollPane scrollPane = new JScrollPane(table);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));


        String[] dropdownOptions = {"Русский", "Латинский"};
        String[] dropdownOptions1 = {"Буквенный", "Цифровой", "Буквенно-Цифрвовй", "Разделительные и служебные", "Смешанный"};
        String[] dropdownOptions2 = {"Полный", "Сокращенный"};
        String[] dropdownOptions3 = {"Повторение знаков", "Повторение групп", "Обычный текст"};
        String[] dropdownOptions4 = {" 3 ", " 5 ", " 7 ", " 9 "};
        String[] dropdownOptions5 = {" 3 ", " 5 ", " 7 ", " 9 "};
        String[] dropdownOptions6 = {"0.5", "0.6", "0.7", "0.8", "0.9", "1.0", "1.1", "1.0", "1.2", "1.3", "1.4", "1.5"};
        JComboBox dropdowns = new JComboBox(dropdownOptions);
        settingsPanel.add(new JLabel(" Язык :"));
        settingsPanel.add(dropdowns);


        JComboBox dropdowns1 = new JComboBox(dropdownOptions1);
        settingsPanel.add(new JLabel(" Формат :"));
        settingsPanel.add(dropdowns1);

        JComboBox dropdowns2 = new JComboBox(dropdownOptions2);
        settingsPanel.add(new JLabel("Набор знаков :"));

        settingsPanel.add(dropdowns2);

        JComboBox dropdowns3 = new JComboBox(dropdownOptions3);
        settingsPanel.add(new JLabel("Сложность текста :"));
        settingsPanel.add(dropdowns3);

        JComboBox dropdowns4 = new JComboBox(dropdownOptions4);
        settingsPanel.add(new JLabel(" Пауза между знаками :"));
        settingsPanel.add(dropdowns4);

        if (dropdowns4.equals("3")) {
            pmz = 3;

        } else if (dropdowns4.equals("5")) {
            pmz = 5;
        } else if (dropdowns4.equals("7")) {
            pmz = 7;
        } else {
            pmz = 9;
        }

        JComboBox dropdowns5 = new JComboBox(dropdownOptions5);
        settingsPanel.add(new JLabel(" Пауза между группами :"));
        settingsPanel.add(dropdowns5);

        if (dropdowns5.equals("3")) {
            pmg = 3;

        } else if (dropdowns5.equals("5")) {
            pmg = 5;
        } else if (dropdowns5.equals("7")) {
            pmg = 7;
        } else {
            pmg = 9;
        }

        JComboBox dropdowns6 = new JComboBox(dropdownOptions6);
        settingsPanel.add(new JLabel(" Сжатость знака :"));
        settingsPanel.add(dropdowns6);

        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                component.setBackground(Color.WHITE);
                return component;
            }
        };
        dropdowns6.setRenderer(renderer);
        dropdowns5.setRenderer(renderer);
        dropdowns4.setRenderer(renderer);
        dropdowns3.setRenderer(renderer);
        dropdowns2.setRenderer(renderer);
        dropdowns1.setRenderer(renderer);
        dropdowns.setRenderer(renderer);


        JTextField numberField1 = new JTextField(10);
        JTextField numberField2 = new JTextField(10);
        settingsPanel.add(new JLabel("Количество групп:"));
        settingsPanel.add(numberField1);

        JButton applyButton = new JButton("Применить");
        applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(Box.createHorizontalStrut(25));
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = numberField1.getText();
                try {
                    int numGroups = Integer.parseInt(input);

                    // Очищаем текущие строки в таблице
                    tableModel.setRowCount(0);

                    for (int i = 1; i <= numGroups; i++) {
                        String[] newRow = new String[columnNames.length];
                        newRow[0] = i + "x";
                        tableModel.addRow(newRow);
                    }

                    tableModel.fireTableDataChanged();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(jFrame, "Введите корректное количество групп.");
                }
            }
        });
        settingsPanel.add(applyButton);

        settingsPanel.add(new JLabel("Скорость передачи(групп\\мин):"));
        settingsPanel.add(numberField2);

        JButton applyButton1 = new JButton("Применить");
        applyButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(Box.createRigidArea(new Dimension(50, 10)));
        settingsPanel.add(applyButton1);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        settingsPanel.add(Box.createRigidArea(new Dimension(50, 8)));
        settingsPanel.add(slider, BorderLayout.CENTER);
        settingsPanel.add(new JLabel("Тональноть"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("             ▶️            ");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTranslation = false;
                translateToMorseCodeInBackground();
            }
        });

        JButton button2 = new JButton("              ||              ");
        JButton button3 = new JButton("            ■              ");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTranslation = true;
            }
        });
        button1.setBackground(Color.green);
        button2.setBackground(Color.yellow);
        button3.setBackground(Color.RED);

        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonRowPanel = new JPanel();
        buttonRowPanel.setLayout(new BoxLayout(buttonRowPanel, BoxLayout.X_AXIS));

        buttonRowPanel.add(button1);
        buttonRowPanel.add(button2);
        buttonRowPanel.add(button3);
        settingsPanel.add(Box.createRigidArea(new Dimension(50, 20)));

        settingsPanel.add(buttonRowPanel, BorderLayout.CENTER);


        JButton generateButton = new JButton("Генерация");
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         boolean checkbox1Selected = checkBox1.isSelected();
         boolean checkbox2Selected = checkBox2.isSelected();

         if (checkbox2Selected && !checkbox1Selected) {
             int rowCount = tableModel.getRowCount();
             Random random = new Random();
             for (int row = 0; row < rowCount; row++) {
                 for (int col = 1; col < columnNames.length; col++) {
                     StringBuilder randomChars = new StringBuilder();

                     // Generate exactly 5 characters for each cell
                     for (int i = 0; i < 5; i++) {

                         if (y == 0) {
                             if (w == 0) {
                                 randomChars.append((char) (random.nextInt(32) + 1072)); // Russian letter
                             } else {
                                 randomChars.append((char) (random.nextInt(26) + 97)); // Latin letter
                             }
                         } else if (y == 2) {
                             randomChars.append(random.nextInt(10)); // Digit
                         } else if (y == 3) {
                             if (w == 1) {
                                 if (random.nextBoolean()) {
                                     randomChars.append((char) (random.nextInt(32) + 1072)); // Russian letter
                                 } else {
                                     randomChars.append(random.nextInt(10)); // Digit
                                 }
                             } else {
                                 if (random.nextBoolean()) {
                                     randomChars.append((char) (random.nextInt(26) + 97)); // Latin letter
                                 } else {
                                     randomChars.append(random.nextInt(10)); // Digit
                                 }
                             }
                         } else if (y == 4) {
                             String specialCharacters = "!@#$%^&*()_+-={}[]|;:'\",.<>?/";
                             int randomIndex = random.nextInt(specialCharacters.length());
                             randomChars.append(specialCharacters.charAt(randomIndex));
                         } else if (y == 5) {
                             String allowedCharacters = (w == 2) ?
                                     "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-={}[]|;:'\",.<>?/" :
                                     "абвгдеёжзиклмнопрстуцфхчщэюяъь0123456789!@#$%^&*()_+-={}[]|;:'\",.<>?/";
                             int length = 1;

                             for (int b = 0; b < length; b++) {
                                 int randomIndex = random.nextInt(allowedCharacters.length());
                                 randomChars.append(allowedCharacters.charAt(randomIndex));
                             }
                         }
                     }

                     // Set the generated characters in the cell
                     tableModel.setValueAt(randomChars.toString(), row, col);
                 }
             }
             JOptionPane.showMessageDialog(jFrame, "Generation completed.");
         } else {
             JOptionPane.showMessageDialog(jFrame, "Select the automatic input method.");
         }
            }
        });
        settingsPanel.add(generateButton);
        JButton clearbutton = new JButton("                                         Очистить таблицу                                          ");
        clearbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(Box.createRigidArea(new Dimension(50, 60)));// Выравнивание по центру
        clearbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = tableModel.getRowCount();
                int columnCount = tableModel.getColumnCount();

                for (int row = 0; row < rowCount; row++) {
                    for (int column = 1; column < columnCount; column++) {
                        tableModel.setValueAt("", row, column);
                    }
                }
            }
        });

        settingsPanel.add(clearbutton);

        //
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Установите вертикальное расположение

        JButton button4 = new JButton(" ADD   ");
        button4.setBackground(Color.green);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // Устанавливаем фильтр для выбора определенных типов файлов (например, только текстовых файлов)
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".rdm", "rdm");
                fileChooser.setFileFilter(filter);

                // Открываем диалог выбора файла
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // Получаем выбранный файл
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                        // Создаем Scanner для считывания файла
                        Scanner scanner = new Scanner(selectedFile, "Windows-1251");

                        // Создаем ArrayList для хранения строк
                        ArrayList<String> lines = new ArrayList<>();

                        // Считываем и добавляем строки из файла в массив
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            lines.add(line);
                        }

                        // Закрываем Scanner после чтения файла
                        scanner.close();

                        // Создаем новый массив для хранения отфильтрованных строк
                        ArrayList<String> filteredLines = new ArrayList<>();


                        int rowCount = tableModel.getRowCount();
                        int columnCount = tableModel.getColumnCount();

                        for (int row = 0; row < rowCount; row++) {
                            for (int column = 1; column < columnCount; column++) {
                                tableModel.setValueAt("", row, column);
                            }
                        }
                        tableModel.setRowCount(0);


                        for (int i = 0; i < 14; i++) {
                            lines.remove(0);
                        }
                        int columns = 11;

                        // Заполняем таблицу данными из ArrayList
                        int row = -1;
                        for (int i = 0; i < lines.size(); i++) {
                            if (i % columns == 0) {
                                row++;
                                tableModel.addRow(new Object[columns]);
                            }
                            tableModel.setValueAt(lines.get(i), row, i % columns);
                        }


                        // Выводим содержимое отфильтрованного массива строк в консоль
                        for (String line : filteredLines) {

                        }
                    } catch (FileNotFoundException w) {
                        w.printStackTrace();
                    }
                } else {
                    System.out.println("Выбор файла отменен");
                }


            }
        });
        panel.add(Box.createRigidArea(new Dimension(50, 15)));
        JButton button5 = new JButton(" SAVE ");
        button5.setBackground(Color.yellow);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    filePath += ".rdm";

                    try (FileOutputStream fos = new FileOutputStream(filePath);
                         OutputStreamWriter osw = new OutputStreamWriter(fos, "Cp1251")) {

                        // Добавляем строку "жжж=" в начало файла
                        osw.write("жжж=");
                        osw.write("\r\n");

                        // Добавляем числа 11 и 3
                        osw.write(column.toString());
                        osw.write("\r\n");
                        osw.write("3");
                        osw.write("\r\n");

                        // Добавляем строку "Группа"
                        osw.write("Группа");
                        osw.write("\r\n");


                        for (int i = 1; i <= 10; i++) {
                            String strI = String.valueOf(i);
                            osw.write(strI);
                            osw.write("\r\n");
                        }


                        // Перебираем данные из tableModel и сохраняем их в файл
                        int rowCount = tableModel.getRowCount();
                        int columnCount = tableModel.getColumnCount();
                        for (int row = 0; row < rowCount; row++) {
                            for (int col = 0; col < columnCount; col++) {
                                Object value = tableModel.getValueAt(row, col);
                                osw.write(value.toString());
                                osw.write("\r\n"); // Сохраняем каждую ячейку на новой строке
                            }
                        }
                        System.out.println("Данные сохранены в файл " + filePath);
                    } catch (IOException z) {
                        z.printStackTrace();
                    }
                } else {
                    System.out.println("Отменено пользователем.");
                }
            }
        });
        JButton button6 = new JButton(" LOOK ");
        button6.setBackground(Color.orange);

        panel.add(Box.createVerticalStrut(10));
        panel.add(button4);
        panel.add(Box.createVerticalStrut(25));
        panel.add(button5);
        panel.add(Box.createVerticalStrut(25));
        panel.add(button6);


        panel.add(Box.createVerticalStrut(350));
        checkBox1 = new JCheckBox(" Ручной ");
        panel.add(new JLabel(" Ввод радиограмм:"));
        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkBox2.setSelected(false); // Если checkBox1 выбран, выключаем checkBox2
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    checkBox1.setSelected(true); // Если попытались выключить checkBox1, включаем его обратно
                }
            }
        });
        panel.add(checkBox1);

        checkBox2 = new JCheckBox("Автоматический");
        panel.add(new JLabel(""));
        checkBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkBox1.setSelected(false); // Если checkBox2 выбран, выключаем checkBox1
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    checkBox2.setSelected(true); // Если попытались выключить checkBox2, включаем его обратно
                }
            }
        });
        panel.add(checkBox2);

        checkBox3 = new JCheckBox("Короткий ноль");
        panel.add(new JLabel(""));
        panel.add(checkBox3);
        //


        // Используем BoxLayout для размещения элементов справа от таблицы
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(settingsPanel, BorderLayout.NORTH);
        rightPanel.add(generateButton, BorderLayout.SOUTH);


        // Добавление компонентов на главную панель
        jFrame.getContentPane().setLayout(new BorderLayout());
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        jFrame.getContentPane().add(rightPanel, BorderLayout.EAST);
        jFrame.getContentPane().add(panel, BorderLayout.WEST);

        // Отображение окна
        jFrame.setVisible(true);
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

        int rowCount = tableModel.getRowCount(); // Получаем количество строк
        int columnCount = tableModel.getColumnCount(); // Получаем количество столбцов

// Создаем двумерный массив для хранения данных
        Object[][] data1 = new Object[rowCount][columnCount];

// Проходим по всем строкам и столбцам и извлекаем данные
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                data1[row][column] = tableModel.getValueAt(row, column);
            }
        }


        for (int row = 0; row < data1.length; row++) {
            for (int column = 1; column < data1[row].length; column++) {
                // Добавляем значение из массива к строке inputText
                inputText += data1[row][column].toString();

                // Добавляем пробел или другой разделитель между элементами, если нужно
                inputText += " ";
            }
        }
        System.out.println(inputText);

        Clip clip = null;

        for (char c : inputText.toCharArray()) {
            if (stopTranslation) {
                break;
            }

            if (c == ' ') {
                Thread.sleep(30 * pmg); // Пауза между словами
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
                    Thread.sleep(50); // Пауза между точками и чертами внутри символа
                }
                Thread.sleep(30 * pmz); // Пауза между символами
            }

        }
        inputText = "";
    }

    private Map<Character, String> morseCodeMap;

    {
        morseCodeMap = new HashMap<>();
        // Английский алфавит
        morseCodeMap.put('a', ".-");
        morseCodeMap.put('b', "-...");
        morseCodeMap.put('c', "-.-.");
        morseCodeMap.put('d', "-..");
        morseCodeMap.put('e', ".");
        morseCodeMap.put('f', "..-.");
        morseCodeMap.put('g', "--.");
        morseCodeMap.put('h', "....");
        morseCodeMap.put('i', "..");
        morseCodeMap.put('j', ".---");
        morseCodeMap.put('k', "-.-");
        morseCodeMap.put('l', ".-..");
        morseCodeMap.put('m', "--");
        morseCodeMap.put('n', "-.");
        morseCodeMap.put('o', "---");
        morseCodeMap.put('p', ".--.");
        morseCodeMap.put('q', "--.-");
        morseCodeMap.put('r', ".-.");
        morseCodeMap.put('s', "...");
        morseCodeMap.put('t', "-");
        morseCodeMap.put('u', "..-");
        morseCodeMap.put('v', "...-");
        morseCodeMap.put('w', ".--");
        morseCodeMap.put('x', "-..-");
        morseCodeMap.put('y', "-.--");
        morseCodeMap.put('z', "--..");

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

        morseCodeMap.put('а', ".-");
        morseCodeMap.put('б', "-...");
        morseCodeMap.put('в', ".--");
        morseCodeMap.put('г', "--.");
        morseCodeMap.put('д', "-..");
        morseCodeMap.put('е', ".");
        morseCodeMap.put('ж', "...-");
        morseCodeMap.put('з', "--..");
        morseCodeMap.put('и', "..");
        morseCodeMap.put('й', ".---");
        morseCodeMap.put('к', "-.-");
        morseCodeMap.put('л', ".-..");
        morseCodeMap.put('м', "--");
        morseCodeMap.put('н', "-.");
        morseCodeMap.put('о', "---");
        morseCodeMap.put('п', ".--.");
        morseCodeMap.put('р', ".-.");
        morseCodeMap.put('с', "...");
        morseCodeMap.put('т', "-");
        morseCodeMap.put('у', "..-");
        morseCodeMap.put('ф', "..-.");
        morseCodeMap.put('х', "....");
        morseCodeMap.put('ц', "-.-.");
        morseCodeMap.put('ч', "---.");
        morseCodeMap.put('ш', "----");
        morseCodeMap.put('щ', "--.-");
        morseCodeMap.put('ъ', ".--.-.");
        morseCodeMap.put('ы', "-.--");
        morseCodeMap.put('ь', "-..-");
        morseCodeMap.put('э', "..-..");
        morseCodeMap.put('ю', "..--");
        morseCodeMap.put('я', ".-.-");
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