import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class customer_myOrder extends JFrame {
    private JPanel orderJPanel;
    private JScrollPane orderJScrollPane;
    private List<Dish> dishes;

    public customer_myOrder(List<Dish> selectedDishes) {
        this.dishes = selectedDishes;
        setLayout(null);
        orderJPanelMethod();
        orderJScrollPaneMethod();

        setTitle("我的订单");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    void orderJPanelMethod() {
        orderJPanel = new JPanel();
        orderJPanel.setLayout(null);
        orderJPanel.setBackground(new Color(245, 245, 245));
        orderJPanel.setBorder(new LineBorder(Color.GRAY, 1, true));

        for (int i = 0; i < dishes.size(); i++) {
            JLabel dishLabel = new JLabel(dishes.get(i).getName() + (dishes.get(i).isOrdered() ? " - 已下单" : ""));
            dishLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
            dishLabel.setForeground(new Color(50, 50, 50));
            orderJPanel.add(dishLabel);
            dishLabel.setBounds(20, 40 * i + 20, 300, 30);

            JLabel priceLabel = new JLabel("￥ " + dishes.get(i).getPrice());
            priceLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
            priceLabel.setForeground(new Color(50, 50, 50));
            orderJPanel.add(priceLabel);
            priceLabel.setBounds(320, 40 * i + 20, 100, 30);
        }

        // 设置面板的首选尺寸和内边距
        orderJPanel.setPreferredSize(new Dimension(500, dishes.size() * 40 + 40));
        orderJPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    void orderJScrollPaneMethod() {
        orderJScrollPane = new JScrollPane(orderJPanel);
        orderJScrollPane.setBorder(new LineBorder(Color.GRAY, 1));
        orderJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
        getContentPane().add(orderJScrollPane);
        orderJScrollPane.setBounds(20, 20, 540, 320);
    }

    public void refreshOrderList() {
        getContentPane().removeAll();
        orderJPanelMethod();
        orderJScrollPaneMethod();
        validate();
        repaint();
    }

    public static void main(String[] args) {
        // Example to test customer_myOrder separately
        List<Dish> exampleDishes = List.of(new Dish("Example Dish 1", 35), new Dish("Example Dish 2", 42));
        new customer_myOrder(exampleDishes);
    }
}
