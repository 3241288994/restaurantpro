import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class reception_login extends JFrame {
    private ImageIcon loginPicture;
    private JLabel loginPictureLabel, accountJLabel, passwordJLabel, sloganJLabel;
    private JTextField accountInput;
    private JPasswordField passwordInput;
    private JButton loginButton, visitorLoginButton;
    private int windowsWidth = 900;
    private int windowsHeight = 560;

    public reception_login() {
        setLayout(null);
        inputMethod();
        buttonMethod();
        backgroundPictureMethod();
        setVisible(true);
        setTitle("欢迎上班");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
    }

    void inputMethod() {
        int x = 52, y = 480;
        sloganJLabel = new JLabel("新的一天也要元气满满哟！");
        sloganJLabel.setFont(new Font("楷体", Font.BOLD, 36));
        sloganJLabel.setForeground(new Color(80, 80, 80));
        getContentPane().add(sloganJLabel);
        sloganJLabel.setBounds(180, 50, 800, 50);

        accountJLabel = new JLabel("账号：");
        accountJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        accountJLabel.setForeground(new Color(30, 30, 30));
        getContentPane().add(accountJLabel);
        accountJLabel.setBounds(x, y, 110, 20);

        passwordJLabel = new JLabel("密码：");
        passwordJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passwordJLabel.setForeground(new Color(30, 30, 30));
        getContentPane().add(passwordJLabel);
        passwordJLabel.setBounds(x + 260, y, 110, 20);

        accountInput = new JTextField();
        getContentPane().add(accountInput);
        accountInput.setBounds(x + 110, y, 130, 25);

        passwordInput = new JPasswordField();
        getContentPane().add(passwordInput);
        passwordInput.setBounds(x + 370, y, 130, 25);
    }

    void buttonMethod() {
        int x = 590, y = 480;
        loginButton = new JButton("管理员登录");
        styleButton(loginButton, x, y - 2, new Color(70, 130, 180));
        loginButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                if (validateLogin("admin", new String(passwordInput.getPassword()), "admin", "123456")) {
                    dispose();
                    new reception_administrator(); // Open admin dashboard
                } else {
                    JOptionPane.showMessageDialog(null, "管理员账号或密码错误！");
                }
            }
        });

        visitorLoginButton = new JButton("服务员登录");
        styleButton(visitorLoginButton, x + 130, y - 2, new Color(34, 139, 34));
        visitorLoginButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                if (validateLogin(accountInput.getText(), new String(passwordInput.getPassword()), "waiter", "123456")) {
                    dispose();
                    new reception_table(); // Open table view for waiters
                } else {
                    JOptionPane.showMessageDialog(null, "服务员账号或密码错误！");
                }
            }
        });
    }

    private void styleButton(JButton button, int x, int y, Color hoverColor) {
        button.setBackground(Color.WHITE);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        button.setForeground(new Color(0, 0, 0));
        getContentPane().add(button);
        button.setBounds(x, y, 108, 25);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(0, 0, 0));
            }
        });
    }

    private boolean validateLogin(String enteredUsername, String enteredPassword, String correctUsername, String correctPassword) {
        return enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword);
    }

    void backgroundPictureMethod() {
        loginPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\010.jpg");
        loginPicture.setImage(loginPicture.getImage().getScaledInstance(windowsWidth, windowsHeight, Image.SCALE_DEFAULT));
        loginPictureLabel = new JLabel(loginPicture);
        getContentPane().add(loginPictureLabel);
        loginPictureLabel.setBounds(0, 0, windowsWidth, windowsHeight);
    }

    public static void main(String[] args) {
        new reception_login();
    }
}
