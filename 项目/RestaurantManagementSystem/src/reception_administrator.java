import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class reception_administrator extends JFrame {
    private ImageIcon backGroundPicture, logoffPicture, memberPicture;
    private JLabel backGroundPictureLabel, todayTurnoverJLabel, memberPictureJLabel, symbolJLabel, sumTurnoverJLabel, logoffPictureJLabel;
    private JComboBox<String> yearFront, yearBehind, monthFront, monthBehind, dayFront, dayBehind;
    private JButton searchButton;
    private final int windowWidth = 900, windowHeight = 550;

    private String[] years = {"", "2015", "2016", "2017", "2018", "2019"},
                     months = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"},
                     days = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
                             "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
                             "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    public reception_administrator() {
        setLayout(null);
        todayTurnoverMethod();
        sumTurnoverMethod();
        logoffPictureMethod();
        memberMethod();
        backgroundPictureMethod();

        setTitle("欢迎您！管理员");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((width - windowWidth) / 2, (height - windowHeight) / 2, windowWidth, windowHeight);
        setVisible(true);
    }

    private void todayTurnoverMethod() {
        String turnover = String.format("%.2f", (float)(2000000 + new Random().nextInt(999999)) / 100);
        todayTurnoverJLabel = new JLabel("今日营业额为： ￥" + turnover);
        todayTurnoverJLabel.setFont(new Font("宋体", Font.PLAIN, 25));
        todayTurnoverJLabel.setBounds(125, 120, 400, 25);
        getContentPane().add(todayTurnoverJLabel);
    }

    private void sumTurnoverMethod() {
        yearFront = new JComboBox<>(years);
        yearBehind = new JComboBox<>(years);
        monthFront = new JComboBox<>(months);
        monthBehind = new JComboBox<>(months);
        dayFront = new JComboBox<>(days);
        dayBehind = new JComboBox<>(days);

        yearFront.setBounds(110, 420, 52, 25);
        monthFront.setBounds(202, 420, 40, 25);
        dayFront.setBounds(284, 420, 40, 25);
        yearBehind.setBounds(440, 420, 52, 25);
        monthBehind.setBounds(532, 420, 40, 25);
        dayBehind.setBounds(614, 420, 40, 25);

        getContentPane().add(yearFront);
        getContentPane().add(monthFront);
        getContentPane().add(dayFront);
        getContentPane().add(yearBehind);
        getContentPane().add(monthBehind);
        getContentPane().add(dayBehind);

        symbolJLabel = new JLabel("——");
        symbolJLabel.setFont(new Font("宋体", Font.BOLD, 24));
        symbolJLabel.setBounds(370, 422, 50, 25);
        getContentPane().add(symbolJLabel);

        searchButton = new JButton("查找");
        searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        searchButton.setBounds(705, 417, 80, 30);
        searchButton.setBackground(new Color(200, 200, 200));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> updateSumTurnoverLabel());
        getContentPane().add(searchButton);

        sumTurnoverJLabel = new JLabel("阶段营业额为：");
        sumTurnoverJLabel.setFont(new Font("宋体", Font.PLAIN, 25));
        sumTurnoverJLabel.setBounds(125, 260, 400, 25);
        getContentPane().add(sumTurnoverJLabel);
    }

    private void updateSumTurnoverLabel() {
        sumTurnoverJLabel.setText("阶段营业额为： ￥845,963.25");
    }

    private void memberMethod() {
        memberPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\018.jpg");
        memberPicture.setImage(memberPicture.getImage().getScaledInstance(130, 171, Image.SCALE_SMOOTH));
        memberPictureJLabel = new JLabel(memberPicture);
        memberPictureJLabel.setBounds(575, 115, 130, 171);
        memberPictureJLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        memberPictureJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
                new reception_login();
            }
        });
        getContentPane().add(memberPictureJLabel);
    }

    private void logoffPictureMethod() {
        logoffPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\016.jpg");
        logoffPicture.setImage(logoffPicture.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        logoffPictureJLabel = new JLabel(logoffPicture);
        logoffPictureJLabel.setBounds(5, windowHeight - 67, 25, 25);
        logoffPictureJLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoffPictureJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
                new reception_login();
            }
        });
        getContentPane().add(logoffPictureJLabel);
    }

    private void backgroundPictureMethod() {
        backGroundPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\007.jpg");
        backGroundPicture.setImage(backGroundPicture.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH));
        backGroundPictureLabel = new JLabel(backGroundPicture);
        backGroundPictureLabel.setBounds(0, 0, windowWidth, windowHeight);
        getContentPane().add(backGroundPictureLabel);
    }

    public static void main(String[] args) {
        new reception_administrator();
    }
}
