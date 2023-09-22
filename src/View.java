import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {

    private JFrame jFrame;
    private JButton menu1,menu2,menu3;

    private JMenuBar jMenuBar;

    public View() {
        ImageIcon backgroundImage = new ImageIcon("menu.jpg");
        jFrame = new JFrame(" Morse ");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1200, 720);
        jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        menu1 = new JButton(" ПЕРЕВОДЧИК ");
        menu1.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu1.setPreferredSize(new Dimension(300,100));
        menu1.setMinimumSize(new Dimension(300,100));
        menu1.setMaximumSize(new Dimension(300,100));
//        menu1.setBackground(new Color(0, 0, 0, 0));
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Translator translator = new Translator();
                translator.display();
            }
        });
        menu2 = new JButton("ИЗУЧЕНИЕ АЗБУКИ МОРЗЕ ");
        menu2.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu2.setPreferredSize(new Dimension(300,100));
        menu2.setMinimumSize(new Dimension(300,100));
        menu2.setMaximumSize(new Dimension(300,100));
//        menu2.setBackground(new Color(0, 0, 0, 0));
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                School school = new School();
                school.display();
            }
        });

        menu3 = new JButton("НАРАЩИВАНИЕ СКОРОСТИ ПРИЕМА");
        menu3.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu3.setPreferredSize(new Dimension(300,100));
        menu3.setMinimumSize(new Dimension(300,100));
        menu3.setMaximumSize(new Dimension(300,100));
//        menu3.setBackground(new Color(0, 0, 0, 0));
        menu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Speed speed = new Speed();
                speed.display();
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalStrut(40));
        panel.add(menu1);
        panel.add(Box.createVerticalStrut(100));
        panel.add(menu2);
        panel.add(Box.createVerticalStrut(100));
        panel.add(menu3);
        panel.add(Box.createVerticalStrut(10));
        panel.add(Box.createVerticalGlue());


        jFrame.add(panel);


        jFrame.setVisible(true);

        jFrame.setContentPane(panel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                View view = new View();
                view.display();
            }
        });
    }


    public void display() {
        jFrame.setVisible(true);
    }
}
class BackgroundPanel extends JPanel {
    private ImageIcon background;

    public BackgroundPanel(ImageIcon background) {
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Отрисовываем фоновое изображение
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
