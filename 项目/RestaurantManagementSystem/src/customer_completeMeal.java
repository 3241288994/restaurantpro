import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class customer_completeMeal extends JFrame implements MouseListener {
    private JButton print, exit;
    private JLabel successfulPaymentJLabel, backGroundJLabel, evaluateJLabel, evaluateResultJLabel;
    private JLabel[] evaluateStarsJLabel = new JLabel[5];
    private String[] evaluateResult = {"非常差", "差", "一般", "好", "非常好"};
    private ImageIcon successfulPaymentPicture, starPicture;
    private ImageIcon backGroundPicture;
    private int windowsWedth = 660, windowsHeight = 520;
    private JPanel mainPanel; 

    public customer_completeMeal() {
        // 设置布局和主面板样式
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(null);
        mainPanel = new JPanel(null); // 初始化 mainPanel
        mainPanel.setOpaque(false);

        // 支付成功图片
        successfulPaymentPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\006.jpg");
        successfulPaymentPicture.setImage(successfulPaymentPicture.getImage().getScaledInstance(440, 150, Image.SCALE_DEFAULT));
        successfulPaymentJLabel = new JLabel(successfulPaymentPicture);
        mainPanel.add(successfulPaymentJLabel);
        successfulPaymentJLabel.setBounds(150, 40, 440, 150);

        // 评价标题
        evaluateJLabel = new JLabel("说出我的评价：");
        evaluateJLabel.setFont(new Font("宋体", Font.BOLD, 18));
        mainPanel.add(evaluateJLabel);
        evaluateJLabel.setBounds(30, 220, 200, 25);

        // 评价星星
        for (int i = 0; i < 5; i++) {
            starPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\008.jpg");
            starPicture.setImage(starPicture.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            evaluateStarsJLabel[i] = new JLabel(starPicture);
            mainPanel.add(evaluateStarsJLabel[i]);
            evaluateStarsJLabel[i].setBounds(80 * i + 125, 280, 70, 70);
            evaluateStarsJLabel[i].addMouseListener(this);
        }

        // 打印和退出按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttonPanel.setOpaque(false);
        print = new JButton("打印纸质账单和发票");
        print.setBackground(new Color(181, 230, 29));
        print.setFont(new Font("宋体", Font.PLAIN, 14));
        print.setForeground(Color.BLACK);
        buttonPanel.add(print);
        print.addActionListener(e -> dispose());

        exit = new JButton("退出系统");
        exit.setBackground(new Color(153, 217, 234));
        exit.setFont(new Font("宋体", Font.PLAIN, 14));
        exit.setForeground(Color.BLACK);
        buttonPanel.add(exit);
        exit.addActionListener(e -> {
            dispose();
            new customer_login();
        });

        buttonPanel.setBounds(120, 415, 420, 35);
        mainPanel.add(buttonPanel);

        // 背景图片
        backGroundPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\007.jpg");
        backGroundPicture.setImage(backGroundPicture.getImage().getScaledInstance(windowsWedth, windowsHeight, Image.SCALE_DEFAULT));
        backGroundJLabel = new JLabel(backGroundPicture);
        getLayeredPane().add(backGroundJLabel, Integer.valueOf(Integer.MIN_VALUE));
        backGroundJLabel.setBounds(0, 0, windowsWedth, windowsHeight);

        // 添加主面板
        setContentPane(mainPanel);
        mainPanel.setBounds(0, 0, windowsWedth, windowsHeight);

        // 窗口配置
        setTitle("用餐完成");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        setVisible(true);
    }

    // 处理星星评价的鼠标事件
    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < 5; i++) {
            if (e.getSource() == evaluateStarsJLabel[i]) {
                for (int j = 0; j < 5; j++) {
                    if (j < i + 1) {
                        starPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\009.jpg");
                    } else {
                        starPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\008.jpg");
                    }
                    starPicture.setImage(starPicture.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
                    evaluateStarsJLabel[j].setIcon(starPicture);
                }

                if (evaluateResultJLabel != null) {
                    evaluateResultJLabel.setText(evaluateResult[i]);
                } else {
                    evaluateResultJLabel = new JLabel(evaluateResult[i]);
                    evaluateResultJLabel.setFont(new Font("楷体", Font.ITALIC, 17));
                    evaluateResultJLabel.setBounds(530, 310, 200, 25);
                    mainPanel.add(evaluateResultJLabel);
                }

                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.HAND_CURSOR);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public static void main(String[] args) {
        new customer_completeMeal();
    }
}
