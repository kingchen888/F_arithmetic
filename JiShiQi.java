package com.first.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * 功能：生成计时器
 * 作者：陈爱鑫
 * 日期：2022年03月21日
 */
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

    public class JiShiQi extends JFrame implements Runnable{
        private JPanel timePanel;
        private JLabel timeLabel;
        private JLabel displayArea;
        private String DEFAULT_TIME_FORMAT = "ss";
        private int ONE_SECOND = 1000;
        public static int time;
        public JiShiQi(){
            timePanel = new JPanel();
            timeLabel = new JLabel("用时： ");
            displayArea = new JLabel();
            timePanel.add(timeLabel);
            timePanel.add(displayArea);
            this.add(timePanel);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setSize(new Dimension(200,100));
            this.setBounds(900, 100, 200, 100);
            /*第一个参数改组件在JFrame中的x坐边
            第二个参数改组件在JFrame中的y坐标
            第三个参数改组件在JFrame中的组件宽度
            第四个参数改组件在JFrame中的组件高度*/
        }
        public  void run(){
            while(true){
                SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);/*设置日期格式*/
                displayArea.setText(time+"s");
                try{
                    time++;
                    Thread.sleep(ONE_SECOND);
                }catch(Exception e)
                {
                    displayArea.setText("Error!!!");
                }
            }
        }
    }


