package com.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //管理数据的二维数组
    int [][] data = new int[4][4];

    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义变量，记录当前展示图片的路径
    String path = "G:\\cs\\Java\\puzzlegame\\image\\girl\\girl1";

    int [][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //记录步数
    int step = 0;

    //创建菜单下的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame(){

        //初始化界面
        InitJFrame();

        //初始化菜单
        InitJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片（根据初始化的数据）
        InitImage();

        //设置界面可见
        this.setVisible(true);
    }


    //初始化数据
    private void initData() {
        //1.定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2.打乱数组中的数据的顺序
        //遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取到随机索引
            int index = r.nextInt(tempArr.length);
            //拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //4.给二维数组添加数据
        //遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组当中
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void InitImage() {
/*      //创建ImageIcon对象
        ImageIcon icon = new ImageIcon("E:\\cs\\Java\\puzzlegame\\image\\animal\\animal3\\1.jpg");
        //创建JLabel对象（管理容器）
        JLabel jLabel1 = new JLabel(icon);
        //指定图片位置
        jLabel1.setBounds(0, 0, 105, 105);

        //把容器添加到界面中
        //this.add(jLabel);
        this.getContentPane().add(jLabel1);*/

        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if(victory()){
            //显示胜利图标
            JLabel winJLabel = new JLabel(new ImageIcon("G:\\cs\\Java\\puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数:" + step);
        stepCount.setBounds(50,50,100,20);
        this.getContentPane().add(stepCount);

        //外循环 --- 把内循环重复执行了4次。
        //按二维数组顺序添加图片
        for (int i = 0; i < 4; i++) {
            //内循环 --- 表示在一行添加4张图片
            for (int j = 0; j < 4; j++) {
                //获取当前要加载图片的序号
                int num = data[i][j];
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path + "\\\\" + num + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(1));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //先加载的图片在上方，后加载的图片在下方
        ImageIcon bg = new ImageIcon("G:\\cs\\Java\\puzzlegame\\image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    //初始化界面
    private void InitJFrame(){
        //设置界面宽高
        this.setSize(603, 680);
        //界面标题
        this.setTitle("拼图游戏单机版 v1.0");
        //界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置格式
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    //初始化菜单
    private void InitJMenuBar(){
        //创建菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上的两个选项对象（功能， 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        //将选项条目关联到选项之中
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将菜单里的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中的图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "\\all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //加载背景图片
            ImageIcon bg = new ImageIcon("G:\\cs\\Java\\puzzlegame\\image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    //松开按键的时候调用的方法
    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能执行移动代码
        if(victory()){
            return;//结束方法
        }

        //对上下左右进行判断
        //左 37 上 38 右 39 下 40
        int code = e.getKeyCode();
        if(code == 37){
            System.out.println("向左移动");
            if(y == 3){
                return;
            }
            //空白方块右方的数字往左移动
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            //每移动一次 计数器自增一次
            step++;
            //调用方法按照最新的数字加载图片
            InitImage();
        }else if(code == 38){
            System.out.println("向上移动");
            if(x == 3){
                //空白方块已在最下方
                return;
            }
            //空白方块下方的数字往上移动
            //空白方块下方数字赋值给空白方块
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            //调用方法按照最新的数字加载图片
            InitImage();
        }else if(code == 39){
            System.out.println("向右移动");
            if(y == 0){
                return;
            }
            //空白方块左方的数字往右移动
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            //调用方法按照最新的数字加载图片
            InitImage();
        }else if(code == 40){
            System.out.println("向下移动");
            if(x == 0){
                return;
            }
            //空白方块上方的数字往下移动
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            //调用方法按照最新的数字加载图片
            InitImage();
        }else if(code == 65){
            InitImage();
        }else if(code == 87){
            data = new int [][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            InitImage();
        }
    }

    //判断data数组中数据是否和win中数据相同
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            //data[i]表示一维数组
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取被点击的条目对象
        Object obj = e.getSource();
        //判断
        if(obj == replayItem){
            System.out.println("重新游戏");

            //计步器清零
            step = 0;
            //再次打乱二维数组中的数据
            initData();
            //重新加载图片
            InitImage();


        }else if(obj == reloginItem){
            System.out.println("重新登录");
            //关闭当前游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("公众号");

            //创建一个弹窗对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象
            JLabel jLabel = new JLabel(new ImageIcon("G:\\cs\\Java\\puzzlegame\\image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344, 344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭这无法操作下面的窗口
            jDialog.setModal(true);
            //显示弹框
            jDialog.setVisible(true);
        }
    }
}