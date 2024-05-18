import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class customer_login extends JFrame {
    private ImageIcon loginPicture;
    private JLabel loginPictureLabel, accountJLabel, passwordJLabel;
    private JTextField accountInput;
    private JPasswordField passwordInput;
    private JButton loginButton, registerButton, visitorLoginButton;
    private int windowsWidth = 900, windowsHeight = 560;

    private Map<String, String> userAccounts;

    public customer_login() {
        setLayout(null);
        initializeUserAccounts();
        inputMethod();
        buttonMethod();
        backgroundPictureMethod();
        setVisible(true);
        setTitle("登录");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
    }

    private void initializeUserAccounts() {
        userAccounts = new HashMap<>();
        userAccounts.put("user1", "123456");
    }

    void inputMethod() {
        accountJLabel = new JLabel("会员账号：");
        passwordJLabel = new JLabel("会员密码：");
        accountJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passwordJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        accountJLabel.setForeground(Color.WHITE);
        passwordJLabel.setForeground(Color.WHITE);
        accountInput = new JTextField();
        accountInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        passwordInput = new JPasswordField();
        passwordInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        int x = 310, y = 230;
        getContentPane().add(accountJLabel);
        accountJLabel.setBounds(x, y, 100, 20);
        getContentPane().add(accountInput);
        accountInput.setBounds(x + 110, y, 180, 30);
        getContentPane().add(passwordJLabel);
        passwordJLabel.setBounds(x, y + 50, 100, 20);
        getContentPane().add(passwordInput);
        passwordInput.setBounds(x + 110, y + 50, 180, 30);
    }

    void buttonMethod() {
        loginButton = createButton("登录", 310, 350, 90, 35);
        registerButton = createButton("注册", 410, 350, 90, 35);
        visitorLoginButton = createButton("游客登录", 510, 350, 110, 35);

        visitorLoginButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                dispose();
                new customer_order();
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                String account = accountInput.getText();
                String password = new String(passwordInput.getPassword());

                if (userAccounts.containsKey(account) && userAccounts.get(account).equals(password)) {
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    dispose();
                    new customer_order();
                } else {
                    JOptionPane.showMessageDialog(null, "账号或密码错误，请重试！", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                new RegisterFrame(userAccounts);
            }
        });
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(new Color(80, 150, 220));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("微软雅黑", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 130, 200), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        button.setBounds(x, y, width, height);
        getContentPane().add(button);
        return button;
    }

    void backgroundPictureMethod() {
        loginPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\001.jpg");
        loginPicture.setImage(loginPicture.getImage().getScaledInstance(windowsWidth, windowsHeight, Image.SCALE_DEFAULT));
        loginPictureLabel = new JLabel(loginPicture);
        loginPictureLabel.setOpaque(true);
        loginPictureLabel.setBackground(new Color(0, 0, 0, 150));
        getContentPane().add(loginPictureLabel);
        loginPictureLabel.setBounds(0, 0, windowsWidth, windowsHeight);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new customer_login());
    }
}

