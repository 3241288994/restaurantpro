import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class customer_order extends JFrame {
    private JPanel dishPanel;
    private JScrollPane dishScrollPane;
    private ImageIcon dishPicture, logoPicture;
    private JCheckBox[] choices;
    private JButton myOrder, placeOrder, settleAccount;
    private JLabel logo, taglineLabel;
    private String[] dishName = {"东坡肘子", "夫妻肺片", "宫保鸡丁", "辣子鸡丁", "麻婆豆腐", "水煮肉片", "水煮鱼", "鱼香肉丝",
            "拔丝山药", "葱烧海参", "黄焖鸡块", "木须肉", "三丝鱼翅", "糖醋里脊", "糖醋鲤鱼", "一品豆腐",
            "八宝红鲟饭", "白雪鸡", "佛跳墙", "福州鱼丸", "荔枝肉", "闽南咸饭", "兴化米粉", "漳州卤面",
            "黄桥烧饼", "叫花鸡", "南京盐水鸭", "松鼠桂鱼", "苏式焖肉", "糖醋排骨", "无锡排骨", "扬州炒饭",
            "剁椒鱼头", "姜辣蛇", "腊味合蒸", "辣椒炒肉", "永州血鸭", "姊妹团子", "组庵豆腐", "组庵鱼翅",
            "阿一鲍鱼", "潮州卤味", "挂炉烧鹅", "广州文昌鸡", "红烧乳鸽", "梅菜扣肉", "蜜汁叉烧", "明炉烤乳猪",
            "杭椒牛柳", "杭州卤鸭", "红烧狮子头", "龙井虾仁", "梅菜肉饼", "西湖醋鱼", "蟹炒年糕", "油焖笋",
            "拔丝芋头", "红烧臭鳜鱼", "黄山炖鸽", "徽州毛豆腐", "火腿炖甲鱼", "麻辣小龙虾", "霉豆渣", "腌鲜鳜鱼"};
    private String file, dishPrice, path = "C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\src\\八大菜系\\", filetype = ".jpg";
    private int dishNumber = 64;
    private List<Dish> selectedDishes;

    public customer_order() {
        setLayout(new BorderLayout());
        setTitle("Delicious");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        headerPanel();
        dishPanel();
        buttonPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void headerPanel() {
        JPanel header = new JPanel(new BorderLayout());

        logoPicture = new ImageIcon("002.jpg");
        logoPicture.setImage(logoPicture.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH));
        logo = new JLabel(logoPicture);
        header.add(logo, BorderLayout.WEST);

        taglineLabel = new JLabel("舌尖上的美味，给你不一样的美食享受！");
        taglineLabel.setFont(new Font("华文行楷", Font.PLAIN, 25));
        taglineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(taglineLabel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
    }

    private void dishPanel() {
        dishPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        choices = new JCheckBox[dishNumber];
        selectedDishes = new ArrayList<>();

        for (int i = 0; i < dishNumber; i++) {
            file = path + dishName[i] + filetype;
            dishPicture = new ImageIcon(file);
            dishPicture.setImage(dishPicture.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            JLabel dishPictureLabel = new JLabel(dishPicture);

            Random r = new Random();
            int price = (int) Math.round(30 + (r.nextInt(100)) * 0.4);
            dishPrice = String.valueOf(price);

            JCheckBox checkBox = new JCheckBox(dishName[i] + " - ￥" + dishPrice);
            checkBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            checkBox.addActionListener(new DishSelectionHandler(dishName[i], price));

            JPanel dishItem = new JPanel(new BorderLayout());
            dishItem.add(dishPictureLabel, BorderLayout.CENTER);
            dishItem.add(checkBox, BorderLayout.SOUTH);

            dishPanel.add(dishItem);
            choices[i] = checkBox;
        }

        dishScrollPane = new JScrollPane(dishPanel);
        dishScrollPane.setPreferredSize(new Dimension(900, 400));
        add(dishScrollPane, BorderLayout.CENTER);
    }

    private void buttonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        myOrder = new JButton("我的订单");
        myOrder.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        myOrder.setBackground(new Color(120, 170, 240));
        myOrder.addActionListener(e -> new customer_myOrder(selectedDishes));
        buttonPanel.add(myOrder);

        placeOrder = new JButton("下 单");
        placeOrder.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        placeOrder.setBackground(new Color(0, 240, 80));
        placeOrder.addActionListener(e -> placeOrderAction());
        buttonPanel.add(placeOrder);

        settleAccount = new JButton("结 账");
        settleAccount.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        settleAccount.setBackground(new Color(240, 130, 20));
        settleAccount.addActionListener(e -> {
            try {
                new customer_settleAccounts(selectedDishes, 0, 0);
            } catch (Exception ex) {
                System.err.println("报错了报错了报错了报错了。。。。");
            }
        });
        buttonPanel.add(settleAccount);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void placeOrderAction() {
        String tableNumberStr = JOptionPane.showInputDialog(
                null,
                "请输入座位号 (1-40)：",
                "选择座位号",
                JOptionPane.PLAIN_MESSAGE
        );

        try {
            int tableNumber = Integer.parseInt(tableNumberStr);

            if (tableNumber < 1 || tableNumber > 40) {
                JOptionPane.showMessageDialog(null, "请输入有效的座位号 (1-40)！");
                return;
            }

            // 设置所有已选菜品为已下单状态
            for (Dish dish : selectedDishes) {
                dish.setOrdered(true);
            }

            // 打开或刷新“我的订单”界面
            new customer_myOrder(selectedDishes).refreshOrderList();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "请输入数字格式的座位号！");
        }
    }

    private class DishSelectionHandler implements ActionListener {
        private final String name;
        private final int price;

        public DishSelectionHandler(String name, int price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            if (checkbox.isSelected()) {
                String[] options = {"加辣", "微辣", "不要辣"};
                String spiciness = (String) JOptionPane.showInputDialog(null,
                        "选择辣度：",
                        "辣度选择",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (spiciness != null) {
                    Dish dish = new Dish(name, price);
                    dish.setSpiciness(spiciness);
                    selectedDishes.add(dish);
                } else {
                    checkbox.setSelected(false);
                }
            } else {
                selectedDishes.removeIf(dish -> dish.getName().equals(name));
            }
        }
    }

    public static void main(String[] args) {
        new customer_order();
    }
}

class Dish {
    private String name;
    private int price;
    private boolean isOrdered;
    private String spiciness;

    public Dish(String name, int price) {
        this.name = name;
        this.price = price;
        this.isOrdered = false;
        this.spiciness = "";
    }

    // Getters and setters
    public String getName() {
        return name + " - " + spiciness;
    }

    public int getPrice() {
        return price;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean isOrdered) {
        this.isOrdered = isOrdered;
    }

    public void setSpiciness(String spiciness) {
        this.spiciness = spiciness;
    }
}
