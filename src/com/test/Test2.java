package com.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        //设置界面宽高
        jFrame.setSize(603, 680);
        //界面标题
        jFrame.setTitle("事件演示");
        //界面置顶
        jFrame.setAlwaysOnTop(true);
        //界面居中
        jFrame.setLocationRelativeTo(null);
        //设置游戏关闭模式
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置格式
        jFrame.setLayout(null);

        //按钮对象
        JButton jtb = new JButton("点击");
        //设置位置和宽高
        jtb.setBounds(0, 0, 100, 50);
        //添加动作监听 动作监听（鼠标左键点击或空格）
        //jtb.addActionListener(new MyActionListener());

        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击");
            }
        });

        //添加按钮到界面中
        jFrame.getContentPane().add(jtb);

        jFrame.setVisible(true);
    }
}
