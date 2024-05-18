import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class customer_settleAccounts extends JFrame {
    private final List<Dish> selectedDishes;
    private int totalPrice = 0;
    private ImageIcon AlipayQR_CodePicture, WeChatQR_CodePicture, backGroundPicture;
    private JLabel AlipayQR_CodeJLabel, WeChatQR_CodeJLabel, AlipayJLabel, WeChatJLabel;

    private int balance;
    private int userId;

    public customer_settleAccounts(List<Dish> selectedDishes, int userId, int balance) throws ClassNotFoundException, SQLException {
        //从数据库拿钱
        Class.forName(JDBC_DRIVER);//注册驱动 mysql5之后的驱动jar包可以省略这个步骤
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        String sql = "select * from balance where id=1 and user_id=1";
        Statement statement = conn.createStatement();
//        int i = statement.executeUpdate(sql);//i表示 如果是DML语句，则为影响的行数
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            userId = resultSet.getInt(2);
            balance = resultSet.getInt(3);

        }
        System.out.println("当前用户id为：" + userId);
        System.out.println("当前用户的余额为：" + balance);
        //关闭连接
        statement.close();
        conn.close();
        // 保留已点的菜品到当前订单
        this.selectedDishes = new ArrayList<>();
        for (Dish dish : selectedDishes) {
            if (dish.isOrdered()) {
                this.selectedDishes.add(dish);
            }
        }
        this.userId = userId;
        this.balance = balance;
        calculateTotal();
        setupUI();
    }

    private void calculateTotal() {
        for (Dish dish : selectedDishes) {
            totalPrice += dish.getPrice();
        }
    }

    private void setupUI() {
        int windowWidth = 700;
        int windowHeight = 650;
        setLayout(null);

        try {
            displayBill();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setupButtons();
        setupQRCode();
        setupBackground(windowWidth, windowHeight);

        setTitle("结账");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - windowWidth) / 2, (screenSize.height - windowHeight) / 2, windowWidth, windowHeight);
        setVisible(true);
    }

    private void displayBill() throws Exception {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(70, 40, 540, 220);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Dish dish : selectedDishes) {
            JLabel dishLabel = new JLabel(dish.getName() + " - ￥" + dish.getPrice());
            dishLabel.setFont(new Font("宋体", Font.BOLD, 16));
            panel.add(dishLabel);
        }

        JLabel totalLabel = new JLabel("总计: ￥" + totalPrice);

        totalLabel.setFont(new Font("宋体", Font.BOLD, 18));
        panel.add(Box.createVerticalStrut(10));
        panel.add(totalLabel);

        getContentPane().add(panel);
    }

    private void setupButtons() {
        JButton balanceButton = new JButton("余额扣除");
        balanceButton.setBounds(150, 275, 170, 35);
        balanceButton.setBackground(new Color(120, 170, 240));
        balanceButton.setFont(new Font("宋体", Font.PLAIN, 14));
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(balanceButton, "余额扣除成功！");
                /////////////////////////
                /////////////////////////
                /////////////////////////
                /////////////////////////
                balance = balance - totalPrice;
                //更新数据库余额
                try {
                    Class.forName(JDBC_DRIVER);//注册驱动 mysql5之后的驱动jar包可以省略这个步骤
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String sql = "update balance set money= ? where user_id= ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
//                    Statement statement = conn.createStatement();
                    preparedStatement.setDouble(1, balance);
                    preparedStatement.setInt(2, userId);
                    int i = preparedStatement.executeUpdate();
                    if (i < 1) {
                        throw new RuntimeException("更新用户余额失败");
                    }
                    System.out.println("当前用户id为：" + userId);
                    System.out.println("当前用户的余额为：" + balance);
                    //关闭连接
                    preparedStatement.close();
                    conn.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }

                /////////////////////////
                /////////////////////////
                /////////////////////////
                ///////////////////////
                /////////////////////////
                //////////////////////////
                /////////////////////////
            }
        });
        getContentPane().add(balanceButton);

        //todo
        JButton balanceQueryButton = new JButton("余额查询");
        balanceQueryButton.setBounds(380, 275, 170, 35);
        balanceQueryButton.setBackground(new Color(160, 120, 240));
        balanceQueryButton.setFont(new Font("宋体", Font.PLAIN, 14));
        balanceQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(balanceQueryButton, balance);
            }
        });
        getContentPane().add(balanceQueryButton);

        JButton feedbackButton = new JButton("反馈问题");
        feedbackButton.setBounds(280, 575, 100, 25);
        feedbackButton.setBackground(new Color(197, 113, 108));
        feedbackButton.setFont(new Font("宋体", Font.PLAIN, 14));
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    String s = JOptionPane.showInputDialog("请输入您的问题：");
                    if (s == null) {
                        break;
                    } else if (s.isEmpty()) {
                        JOptionPane.showMessageDialog(feedbackButton, "您的输入非法，请重试！");
                    } else {
                        /////////////////////////
                        /////////////////////////
                        /////////////////////////
                        /////////////////////////

                        //更新用户的反馈信息
                        try {
                            Class.forName(JDBC_DRIVER);//注册驱动 mysql5之后的驱动jar包可以省略这个步骤
                            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            String sql = "insert into feedback(userId, message) values (?,?)";
                            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setInt(1, userId);
                            preparedStatement.setString(2, s);
                            int i = preparedStatement.executeUpdate();
                            if (i < 1) {
                                throw new RuntimeException("更新用户余额失败");
                            }
                            //关闭连接
                            preparedStatement.close();
                            conn.close();
                        } catch (ClassNotFoundException | SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        /////////////////////////
                        /////////////////////////
                        /////////////////////////
                        ///////////////////////
                        /////////////////////////
                        //////////////////////////
                        /////////////////////////
                        JOptionPane.showMessageDialog(feedbackButton, "我们已经收到您的反馈，会尽快处理问题！");
                        break;
                    }
                }


            }
        });
        getContentPane().add(feedbackButton);
    }

    private void setupQRCode() {
        AlipayQR_CodePicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\003.jpg");
        WeChatQR_CodePicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\004.jpg");

        AlipayQR_CodeJLabel = new JLabel(new ImageIcon(AlipayQR_CodePicture.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        WeChatQR_CodeJLabel = new JLabel(new ImageIcon(WeChatQR_CodePicture.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));

        AlipayQR_CodeJLabel.setBounds(230, 360, 130, 130);
        WeChatQR_CodeJLabel.setBounds(380, 360, 130, 130);

        getContentPane().add(AlipayQR_CodeJLabel);
        getContentPane().add(WeChatQR_CodeJLabel);

        AlipayJLabel = new JLabel("支付宝");
        AlipayJLabel.setBounds(250, 500, 80, 25);
        AlipayJLabel.setFont(new Font("宋体", Font.BOLD, 14));
        getContentPane().add(AlipayJLabel);

        WeChatJLabel = new JLabel("微信");
        WeChatJLabel.setBounds(400, 500, 50, 25);
        WeChatJLabel.setFont(new Font("宋体", Font.BOLD, 14));
        getContentPane().add(WeChatJLabel);
    }

    private void setupBackground(int width, int height) {
        backGroundPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\005.jpg");
        JLabel backGroundJLabel = new JLabel(new ImageIcon(backGroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        backGroundJLabel.setBounds(0, 0, width, height);
        getContentPane().add(backGroundJLabel);
    }

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ruan_jian_gong_cheng";
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Dish> exampleDishes = List.of(
                new Dish("麻婆豆腐", 35),
                new Dish("水煮肉片", 45),
                new Dish("水煮鱼", 55),
                new Dish("鱼香肉丝", 40)
        );
        Class.forName(JDBC_DRIVER);//注册驱动 mysql5之后的驱动jar包可以省略这个步骤
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        String sql = "select * from balance where id=1 and user_id=1";
        Statement statement = conn.createStatement();
//        int i = statement.executeUpdate(sql);//i表示 如果是DML语句，则为影响的行数
        ResultSet resultSet = statement.executeQuery(sql);
        int userId = 0;
        int balance = 0;
        while (resultSet.next()) {
            userId = resultSet.getInt(2);
            balance = resultSet.getInt(3);

        }
        System.out.println("当前用户id为：" + userId);
        System.out.println("当前用户的余额为：" + balance);
        //关闭连接
        statement.close();
        conn.close();
        new customer_settleAccounts(exampleDishes, userId, balance);


    }
}
