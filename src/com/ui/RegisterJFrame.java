package com.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame(){
        //设置界面大小
        this.setSize(488, 500);
        //界面标题
        this.setTitle("拼图 注册");
        //界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //界面可见
        this.setVisible(true);
    }
}
