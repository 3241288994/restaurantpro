import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Date;
import java.text.SimpleDateFormat;


public class table_detail extends JFrame{
	private JButton print;
	private JLabel dishNameJLabel, dishPriceJLabel, dishSumPriceJLabel,
		cookJLabel, finishJLabel, timeJLabel, memberDiscountJLabel, j_numberJlabel;
	private ImageIcon backGroundPicture, logoffPicture;
	private JLabel backGroundPictureLabel, seatIndexJLabel, logoffPictureJLabel;
	private int windowsWedth = 900, windowsHeight = 550, indexNumber;
	private String index;
	private String dishName[]= {"麻婆豆腐","水煮肉片","水煮鱼","鱼香肉丝"},
				cookName[]= {"（未烹饪）","（正在烹饪）","（烹饪完成）"},
				finishName[]= {"（未上桌）","（已上桌）"},
				timeName[]= {"（未超时）","（已超时）"};
	private String taste, component, randomNumber;
	SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	Date now = new Date();
	String NowTime= df2.format(now);
	private int[] tempnub= {0,0,0,0};
	public table_detail(int indexNumber) {
		this.indexNumber=indexNumber+1;
		setLayout(null);		
		indexMethod();
		dishMethod();
		logoffPictureMethod();
		buttonMethod();
		backgroundPictureMethod();
		setVisible(true);
		setTitle("餐桌详情");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
     	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2,windowsWedth,windowsHeight);
	}

	void logoffPictureMethod() {
		int x = 50, y = 30;
		logoffPicture = new ImageIcon("017.jpg");
		logoffPicture.setImage(logoffPicture.getImage().getScaledInstance(x,y,Image.SCALE_DEFAULT));
		logoffPictureJLabel = new JLabel(logoffPicture);
		getContentPane().add(logoffPictureJLabel);
		logoffPictureJLabel.setBounds(10,5,x,y);
		logoffPictureJLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event){	
				dispose();
				new reception_table();
			}
			public void mouseEntered(MouseEvent event){	
				setCursor(Cursor.HAND_CURSOR);
			}
			public void mouseExited(MouseEvent event){	
				setCursor(Cursor.DEFAULT_CURSOR );
			}
		});	
	}
	
	void indexMethod() {
			index = String.valueOf(indexNumber);
			seatIndexJLabel = new JLabel("第 "+index+" 桌顾客：");
			seatIndexJLabel.setFont(new Font("楷体",Font.PLAIN,30));
			getContentPane().add(seatIndexJLabel);
			seatIndexJLabel.setBounds(120,50,300,50);			
	}
	String sumprice;
	
	void dishMethod() {
		int price, qwer, asdf, zxcv, uiop;
		int sum = 0;
		for(int i=0;i<4;i++) {
	    	Random r=new Random();
			price = r.nextInt(98);
			
			tempnub[i]=price;
			if(price < 33) {
				taste = "加辣";
			}else if(price > 32 && price < 66){
				taste = "微辣";
			}else {
				taste = "不要辣";
			}
				
			price = r.nextInt(98);
			if(price < 33) {
				component = "大份";
			}else if(price > 32 && price < 66){
				component = "中份";
			}else {
				component = "小份";
			}
									
			dishNameJLabel = new JLabel(dishName[i] + "(" + taste + ")");
	    	dishNameJLabel.setFont(new Font("宋体",Font.PLAIN,17));
	    	getContentPane().add(dishNameJLabel);
	    	dishNameJLabel.setBounds(125,45*i+120,200,25);
	    		
	    	price = (int) (Math.round(30+(r.nextInt(100))*0.4)*(0.7+price*0.003));
	    	randomNumber = String.valueOf(price);
	    	dishPriceJLabel = new JLabel("￥ " + randomNumber);
	    	dishPriceJLabel.setFont(new Font("宋体",Font.PLAIN,17));
	    	getContentPane().add(dishPriceJLabel);
	    	dishPriceJLabel.setBounds(710,45*i+120,100,25);	
	    	
	    	Random w = new Random();
			qwer = w.nextInt(3);
			cookJLabel = new JLabel(cookName[qwer]);
			cookJLabel.setFont(new Font("宋体",Font.PLAIN,15));
	    	getContentPane().add(cookJLabel);
	    	cookJLabel.setBounds(380,45*i+120,95,25);    	
	    	
			if(qwer != 2) {
				finishJLabel = new JLabel(finishName[0]);
			}else {
				Random b = new Random();
				asdf = b.nextInt(3);
				if(asdf < 2) {
					finishJLabel = new JLabel(finishName[1]);
				}else {
					finishJLabel = new JLabel(finishName[0]);
				}
			}
			finishJLabel.setFont(new Font("宋体",Font.PLAIN,15));
	    	getContentPane().add(finishJLabel);
	    	finishJLabel.setBounds(460,45*i+120,80,25);
	    	
	    	Random p = new Random();
			zxcv = p.nextInt(8);
			if(zxcv < 7) {
				timeJLabel = new JLabel(timeName[0]);
			}else {
				timeJLabel = new JLabel(timeName[1]);
			}
			timeJLabel.setFont(new Font("宋体",Font.PLAIN,15));
	    	getContentPane().add(timeJLabel);
	    	timeJLabel.setBounds(530,45*i+120,80,25);
	    	
	    	sum += price;
	    }
		
		Random k = new Random();
		uiop = k.nextInt(16);
		int sum_1;
		String discountNumber = null, j_number = null;
		if(uiop < 1) {
			sum_1 = (int)Math.floor((float)sum*0.3);
			discountNumber = String.valueOf(7);
			j_number = String.valueOf(sum_1);		
		}else if(uiop < 4) {
			sum_1 = (int)Math.floor((float)sum*0.2);
			discountNumber = String.valueOf(8);
			j_number = String.valueOf(sum_1);				
		}else if(uiop < 9) {
			sum_1 = (int)Math.floor((float)sum*0.1);
			discountNumber = String.valueOf(9);
			j_number = String.valueOf(sum_1);				
		}else {
			sum_1 = 0;
			j_number = String.valueOf(sum_1);
			memberDiscountJLabel = new JLabel("该顾客折扣为： 非会员");	
		}
		
		if(uiop < 9) {
			memberDiscountJLabel = new JLabel("该顾客折扣为：              " + discountNumber + " 折");
		}else {
			memberDiscountJLabel = new JLabel("该顾客折扣为：              非会员");	
		}
				
		memberDiscountJLabel.setFont(new Font("宋体",Font.BOLD,18));
    	getContentPane().add(memberDiscountJLabel);
    	memberDiscountJLabel.setBounds(200,340,500,25);
    	
    	j_numberJlabel = new JLabel("- "+j_number);
    	j_numberJlabel.setFont(new Font("宋体",Font.BOLD,18));
    	j_numberJlabel.setForeground(new Color(240,0,0));
    	getContentPane().add(j_numberJlabel);
    	j_numberJlabel.setBounds(720,340,500,25);
    	
    	randomNumber = String.valueOf(sum);
    	dishSumPriceJLabel = new JLabel("￥ " + randomNumber);
    	dishSumPriceJLabel.setFont(new Font("宋体",Font.BOLD,18));
    	getContentPane().add(dishSumPriceJLabel);
    	dishSumPriceJLabel.setBounds(710,300,500,25);
    	
		randomNumber = String.valueOf(sum-sum_1);
		sumprice=randomNumber;
		dishSumPriceJLabel = new JLabel("总计                     "+"￥ " + randomNumber);
		dishSumPriceJLabel.setFont(new Font("宋体",Font.BOLD,18));
    	getContentPane().add(dishSumPriceJLabel);
    	dishSumPriceJLabel.setBounds(471,380,500,25);
	}

	void buttonMethod() {
	    print = new JButton("打印账单发票");
	    print.setBackground(new Color(181,230,29));
	    print.setFont(new Font("宋体",Font.PLAIN,14));
	    print.setForeground(new Color(0,0,0));
	    getContentPane().add(print);
	    print.setBounds(350,455,170,35);

	    // 添加事件监听器
	    print.addActionListener(event -> {
	        // 构造账单内容
	    	String invoiceContent = "账单详情:\n";
	        invoiceContent+="单位：餐饮服务系统\n";
	        invoiceContent+="桌号: " + index + "\n";
	        invoiceContent+="打印日期:"+NowTime+"\n";
	        invoiceContent+="--------------------------------\n";
	        invoiceContent+="消费清单         数量     单价     金额\n";
	        invoiceContent+="--------------------------------\n";
	        invoiceContent += "麻婆豆腐         1         " + String.valueOf(tempnub[0]); 
	    	invoiceContent += "        "+String.valueOf(tempnub[0]); invoiceContent += "\n";
	    	invoiceContent += "水煮肉片         1         " + String.valueOf(tempnub[1]);
	    	invoiceContent += "        " + String.valueOf(tempnub[1]); invoiceContent += "\n";
	    	invoiceContent += "水煮鱼           1         " + String.valueOf(tempnub[2]);
	    	invoiceContent += "        " + String.valueOf(tempnub[2]); invoiceContent += "\n";
	    	invoiceContent += "鱼香肉丝         1         " + String.valueOf(tempnub[3]);
	    	invoiceContent += "        " + String.valueOf(tempnub[3]); invoiceContent += "\n";
	        invoiceContent+="--------------------------------\n";
	        invoiceContent+= "应付金额:            ￥" + sumprice + "\n";
	        
	        // 调用打印功能
	        InvoicePrinter printer = new InvoicePrinter();
	        printer.printInvoice(invoiceContent);
	    });
	}


	
	void backgroundPictureMethod() {
		backGroundPicture = new ImageIcon("C:\\Users\\32412\\Desktop\\软件工程_餐厅点餐系统\\项目\\RestaurantManagementSystem\\007.jpg");
		backGroundPicture.setImage(backGroundPicture.getImage().getScaledInstance(windowsWedth,windowsHeight,Image.SCALE_DEFAULT));
		backGroundPictureLabel = new JLabel(backGroundPicture);
		getContentPane().add(backGroundPictureLabel);
		backGroundPictureLabel.setBounds(0,0,windowsWedth,windowsHeight);
	}

	public static void main(String[] args) {
		new table_detail(25);
	}
	
}
