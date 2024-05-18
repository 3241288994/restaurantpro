import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.*;

public class RegisterFrame extends JFrame {
    private JLabel accountLabel, passwordLabel, confirmPasswordLabel;
    private JTextField accountInput;
    private JPasswordField passwordInput, confirmPasswordInput;
    private JButton registerButton, cancelButton;
    private int windowsWidth = 450, windowsHeight = 350;
    private Map<String, String> userAccounts;

    public RegisterFrame(Map<String, String> userAccounts) {
        this.userAccounts = userAccounts;
        setLayout(null);
        inputFields();
        actionButtons();
        setVisible(true);
        setTitle("注册");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
    }

    private void inputFields() {
        accountLabel = new JLabel("账号：");
        passwordLabel = new JLabel("密码：");
        confirmPasswordLabel = new JLabel("确认密码：");

        accountLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        confirmPasswordLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

        accountInput = new JTextField();
        passwordInput = new JPasswordField();
        confirmPasswordInput = new JPasswordField();

        int x = 60, y = 50;
        getContentPane().add(accountLabel);
        accountLabel.setBounds(x, y, 120, 22);
        getContentPane().add(accountInput);
        accountInput.setBounds(x + 130, y, 200, 22);

        y += 50;
        getContentPane().add(passwordLabel);
        passwordLabel.setBounds(x, y, 120, 22);
        getContentPane().add(passwordInput);
        passwordInput.setBounds(x + 130, y, 200, 22);

        y += 50;
        getContentPane().add(confirmPasswordLabel);
        confirmPasswordLabel.setBounds(x, y, 120, 22);
        getContentPane().add(confirmPasswordInput);
        confirmPasswordInput.setBounds(x + 130, y, 200, 22);
    }

    private void actionButtons() {
        registerButton = new JButton("注册");
        cancelButton = new JButton("取消");

        registerButton.setBackground(new Color(120, 170, 240));
        registerButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        registerButton.setForeground(new Color(250, 250, 250));

        cancelButton.setBackground(new Color(120, 170, 240));
        cancelButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        cancelButton.setForeground(new Color(250, 250, 250));

        int x = 100, y = 220;
        getContentPane().add(registerButton);
        registerButton.setBounds(x, y, 80, 30);
        getContentPane().add(cancelButton);
        cancelButton.setBounds(x + 160, y, 80, 30);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = accountInput.getText();
                String password = new String(passwordInput.getPassword());
                String confirmPassword = new String(confirmPasswordInput.getPassword());

                if (account.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "账号和密码不能为空！", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else if (userAccounts.containsKey(account)) {
                    JOptionPane.showMessageDialog(null, "该账号已存在，请使用不同的账号！", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else {
                    userAccounts.put(account, password);
                    JOptionPane.showMessageDialog(null, "注册成功！");
                    dispose(); // 关闭注册窗口
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // 取消注册并关闭窗口
            }
        });
    }
}
