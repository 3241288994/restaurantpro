# restaurantpro
环境配置
1. 安装 JDK
确保已安装 JDK 20.0.1，可以从 Oracle官网 下载。安装后配置环境变量：

2. 安装 Eclipse
从 Eclipse官网 下载并安装最新版本的 Eclipse IDE for Java Developers。

3. 配置 MySQL 数据库
下载并安装 MySQL：
从 MySQL官网 下载 MySQL Installer 并安装。

4. 配置 Eclipse 项目
新建 Java 项目：

打开 Eclipse，点击 File -> New -> Java Project，项目名为 RestaurantManagementSystem。
导入源代码：

将提供的 Java 源代码文件（.java 文件）导入到 src 文件夹中。
添加 MySQL JDBC 驱动：

下载 MySQL Connector/J (JDBC 驱动) 从 MySQL官网，下载 .zip 文件并解压。
在 Eclipse 中，右键点击项目 -> Properties -> Java Build Path -> Libraries -> Add External JARs，选择解压后的 mysql-connector-java-xx.x.xx.jar 文件。
使用说明
1. 运行项目
在 Eclipse 中，找到主类（例如：customer_completeMeal，customer_login，customer_order 等），右键点击主类文件，选择 Run As -> Java Application。

2. 项目功能介绍
customer_completeMeal：
该界面显示支付成功后的评价星星系统，用户可以点击星星进行评价。
用户可以点击“打印纸质账单和发票”按钮打印账单，点击“退出系统”按钮返回登录界面。
customer_login：
该界面提供用户登录和注册功能。
输入正确的账号和密码可以登录到点餐界面，点击“游客登录”按钮以游客身份登录。
customer_order：
该界面展示所有菜品，用户可以选择菜品并添加到订单。
点击“我的订单”查看已选菜品，点击“下单”按钮进行下单，点击“结账”按钮结账。
customer_settleAccounts：
该界面展示用户订单详情并提供支付选项。
用户可以选择余额扣除支付，并进行反馈。
reception_login 和 reception_administrator：
该界面提供管理员和服务员的登录功能。
管理员登录后可以查看今日营业额和阶段营业额，并查看餐桌状态。
服务员登录后可以查看餐桌状态，点击具体餐桌查看详细订单信息。
table_detail：
该界面显示选定餐桌的详细订单信息，并提供打印账单发票功能。

3. 注意事项
所有图片文件需放置在相应的路径中。。
数据库连接配置需根据实际数据库配置进行修改，确保 DB_URL，USER 和 PASS 配置正确。
所有路径和配置需根据实际情况进行调整。
