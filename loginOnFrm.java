package com.first.view;

import com.first.dao.UserDao;
import com.first.model.User;
import com.first.utils.DBUtil;
import com.first.utils.StringUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Random;
/*主要代码模块，用于GUI界面设计和生成四则运算*/
public class loginOnFrm extends JFrame {

    private JPanel contentPane;
    private  JTextField loginNameTxt;
    private JPasswordField loginPwdTxt;
    private DBUtil dbUtil=new DBUtil();
    private UserDao userDao=new UserDao();
    private Random random=new Random();
    private int s=0;
    public static int finalResult2;
    //主函数
    public static void main(String[] args) {
              new loginOnFrm().init();
    }
    public void init(){
        //登录窗口
        JFrame frame = new JFrame("登录");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        Panel panel1 = new Panel();
        frame.setContentPane(panel1);
        frame.setBounds(860,500,350,200);
        frame.setLocationRelativeTo(panel1);
        JFrame.setDefaultLookAndFeelDecorated(true);
        panel1.setLayout(null);
        //标题
        JLabel ttt = new JLabel("四则运算");
        ttt.setBounds(130,0,80,20);
        ttt.setFont(new Font("宋体",Font.PLAIN,20));
        panel1.add(ttt);
        //用户名框
        JLabel loginName = new JLabel("用户名");
        loginName.setBounds(10,20,80,25);
        panel1.add(loginName);
        loginNameTxt = new JTextField();
        loginNameTxt.setBounds(100,20,165,25);
        panel1.add(loginNameTxt);
        //密码框
        JLabel loginPwd = new JLabel("密码");
        loginPwd.setBounds(10,50,80,25);
        panel1.add(loginPwd);
        loginPwdTxt = new JPasswordField();
        loginPwdTxt.setBounds(100,50,165,25);
        panel1.add(loginPwdTxt);
        //登录按钮
        JButton button1 = new JButton("登录");
        button1.setBounds(250/2-40,200/2-12,80,25);
        panel1.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loginActionPerformed(e);
                 frame.dispose();
            }
        });
        //重置按钮
        JButton button2 = new JButton("重置");
        button2.setBounds(470/2-40,200/2-12,80,25);
        panel1.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                resetValueActionPerformed(a);
            }
        });
        frame.setResizable(false);
  }
    //重置事件处理
    private void resetValueActionPerformed(ActionEvent evt) {
        this.loginNameTxt.setText("");
        this.loginPwdTxt.setText("");     
    }
    //登录事件处理
    private void loginActionPerformed(ActionEvent evt) {
        String loginName =this.loginNameTxt.getText();
        String loginPwd=new String(this.loginPwdTxt.getPassword());
        if(StringUtil.isEmpty(loginName)){
            JOptionPane.showMessageDialog(null,"用户名不能为空");
            return;
        }
        if(StringUtil.isEmpty(loginPwd)){
            JOptionPane.showMessageDialog(null,"密码不能为空");
            return;
        }
        User user = new User(loginName,loginPwd);
        Connection conn=null;
        try {
            conn=dbUtil.getConn();
            User currentUser=userDao.login(conn,user);
            if (currentUser!=null){
                JOptionPane.showMessageDialog(null,"登陆成功");
                new fun();
                JiShiQi js1 = new JiShiQi();
                js1.setVisible(true);
                Thread thread1=new Thread(js1);
                thread1.start();
            }
            else {
                JOptionPane.showMessageDialog(null,"用户名或者密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class fun extends JFrame{
        private int score=0;
        public fun()
        {
            //答题窗口
            JFrame frame1 = new JFrame("答题");
            frame1.setVisible(true);
            frame1.pack();
            frame1.setBounds(400,300,600,300);
            frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Panel panel2 = new Panel();
            frame1.setContentPane(panel2);
            frame1.setLocationRelativeTo(null);
            JFrame.setDefaultLookAndFeelDecorated(true);
            panel2.setLayout(null);
            Random r = new Random();
            JTextField jT_q0 = new JTextField();
            //生成第一个四则运算题目
            int t;
            int y = r.nextInt(5);
            int z = r.nextInt(5);
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(20);
            int r3 = r.nextInt(20);
            int result = 0;
            int result1=0;
            switch (y) {
                case 1:
                    switch (z) {
                        case 1:
                            result=r1+r2+r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "+" + "" + String.valueOf(r2) + "" +"+"+""+String.valueOf(r3)+ "=");

                            break;
                        case 2:
                            result = r1+r2-r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "+" + "" + String.valueOf(r2) + "" +"-"+""+String.valueOf(r3)+ "=");

                            break;
                        case 3:
                            result=(r1+r2)*r3;
                            jT_q0.setText("("+String.valueOf(r1) + "" + "+" + "" + String.valueOf(r2) +")"+ "" +"×"+""+String.valueOf(r3)+ "=");

                            break;
                        default:
                            result1=r1+r2;
                            if(r3==0)
                            {
                                r3++;
                            }
                            if(result1<r3){
                                t=result1;
                                result1=r3;
                                r3=t;
                            }
                            while (result1%r3!=0){
                                r1++;
                                result1=r1+r2;
                            }
                            result=(r1+r2)/r3;
                            jT_q0.setText("("+String.valueOf(r1) + "" + "+" + "" + String.valueOf(r2) +")"+ "" +"÷"+""+String.valueOf(r3)+ "=");

                            break;
                    }
                    break;
                case 2:
                    switch (z) {
                        case 1:
                            result=r1-r2+r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "-" + "" + String.valueOf(r2) + "" +"+"+""+String.valueOf(r3)+ "=");

                            break;
                        case 2:
                            result = r1-r2-r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "-" + "" + String.valueOf(r2) + "" +"-"+""+String.valueOf(r3)+ "=");

                            break;
                        case 3:
                            result=(r1-r2)*r3;
                            jT_q0.setText("("+String.valueOf(r1) + "" + "-" + "" + String.valueOf(r2) +")"+ "" +"×"+""+String.valueOf(r3)+ "=");

                            break;
                        default:
                            result1=r1-r2;
                            if(r3==0)
                            {
                                r3++;
                            }
                            if(result1<r3){
                                t=result1;
                                result1=r3;
                                r3=t;
                            }
                            while (result1%r3!=0){
                                r1++;
                                result1=r1-r2;
                            }
                            result=(r1-r2)/r3;
                            jT_q0.setText("("+String.valueOf(r1) + "" + "-" + "" + String.valueOf(r2) + ")"+"÷"+""+String.valueOf(r3)+ "=");

                            break;
                    }
                    break;
                case 3:
                    switch (z) {
                        case 1:
                            result=r1*r2+r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "×" + "" + String.valueOf(r2) + "" +"+"+""+String.valueOf(r3)+ "=");

                            break;
                        case 2:
                            result = r1*r2-r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "×" + "" + String.valueOf(r2) + "" +"-"+""+String.valueOf(r3)+ "=");

                            break;
                        case 3:
                            result=r1*r2*r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "×" + "" + String.valueOf(r2) + "" +"×"+""+String.valueOf(r3)+ "=");

                            break;
                        default:
                            result1=r1*r2;
                            if(r3==0)
                            {
                                r3++;
                            }
                            if(result1<r3){
                                t=result1;
                                result1=r3;
                                r3=t;
                            }
                            while (result1%r3!=0){
                                r1++;
                                result1=r1*r2;
                            }
                            result=r1*r2/r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "×" + "" + String.valueOf(r2) + "" +"÷"+""+String.valueOf(r3)+ "=");

                            break;
                    }
                    break;
                default:
                    if(r2==0)
                    {
                        r2++;
                    }
                    if(r1<r2)
                    {
                        t=r1;
                        r1=r2;
                        r2=t;
                    }
                    while (r1%r2!=0)
                    {
                        r1++;
                    }
                    switch (z) {
                        case 1:
                            result=r1/r2+r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "÷" + "" + String.valueOf(r2) + "" +"+"+""+String.valueOf(r3)+ "=");

                            break;
                        case 2:
                            result = r1/r2-r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "÷" + "" + String.valueOf(r2) + "" +"-"+""+String.valueOf(r3)+ "=");

                            break;
                        case 3:
                            result=r1/r2*r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "÷" + "" + String.valueOf(r2) + "" +"×"+""+String.valueOf(r3)+ "=");

                            break;
                        default:
                            result1=r1/r2;
                            if(r3==0)
                            {
                                r3++;
                            }
                            if(result1<r3){
                                t=result1;
                                result1=r3;
                                r3=t;
                            }
                            while (result1%r3!=0){
                                r3--;
                                result1=r1/r2;
                            }
                            result=r1/r2/r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "÷" + "" + String.valueOf(r2) + "" +"÷"+""+String.valueOf(r3)+ "=");

                            break;
                    }
                    break;
            }
            jT_q0.setEditable(false);
            jT_q0.setBounds(180,40,221,41);
            jT_q0.setColumns(1);
            jT_q0.setFont(new Font("宋体",Font.PLAIN,20));
            //答题框
            JTextField jTextField_answer = new JTextField();
            jTextField_answer.setColumns(1);
            jTextField_answer.setBounds(300,40,50,41);
            frame1.getContentPane().add(jTextField_answer);
            //判断框
            JTextField jTextField_judge = new JTextField();
            jTextField_judge.setEditable(false);
            jTextField_judge.setColumns(10);
            jTextField_judge.setFont(new Font("宋体",Font.PLAIN,20));
            jTextField_judge.setBounds(350,40,50,41);
            frame1.getContentPane().add(jTextField_judge);
            panel2.add(jT_q0);
            //下一题按钮
            JButton button_next = new JButton("下一题");
            button_next.setBounds(350,100,80,25);
            frame1.getContentPane().add(button_next);
            int finalResult = result;//获取算式答案赋给finalResult
            //利用循环生成后19题四则运算
            class test {
                public test()
                {
                    s++;
                    if (s<19) {
                        button_next.setEnabled(true);
                        JTextField jT_q = new JTextField();
                        int t1;
                        int p = r.nextInt(5);
                        int q = r.nextInt(5);
                        int n1 = r.nextInt(100);
                        int n2 = r.nextInt(20);
                        int n3 = r.nextInt(20);
                        int result2 = 0;
                        int result3=0;
                        switch (p) {
                            case 1:
                                switch (q) {
                                    case 1:
                                        result2=n1+n2+n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "+" + "" + String.valueOf(n2) + "" +"+"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 2:
                                        result2 = n1+n2-n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "+" + "" + String.valueOf(n2) + "" +"-"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 3:
                                        result2=(n1+n2)*n3;
                                        jT_q.setText("("+String.valueOf(n1) + "" + "+" + "" + String.valueOf(n2) +")"+ "" +"×"+""+String.valueOf(n3)+ "=");

                                        break;
                                    default:
                                        result3=n1+n2;
                                        if(n3==0)
                                        {
                                            n3++;
                                        }
                                        if(result3<n3){
                                            t1=result3;
                                            result3=n3;
                                            n3=t1;
                                        }
                                        while (result3%n3!=0){
                                            n1++;
                                            result3=n1+n2;
                                        }
                                        result2=(n1+n2)/n3;
                                        jT_q.setText("("+String.valueOf(n1) + "" + "+" + "" + String.valueOf(n2) +")"+ "" +"÷"+""+String.valueOf(n3)+ "=");

                                        break;
                                }
                                break;
                            case 2:
                                switch (q) {
                                    case 1:
                                        result2=n1-n2+n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "-" + "" + String.valueOf(n2) + "" +"+"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 2:
                                        result2 = n1-n2-n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "-" + "" + String.valueOf(n2) + "" +"-"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 3:
                                        result2=(n1-n2)*n3;
                                        jT_q.setText("("+String.valueOf(n1) + "" + "-" + "" + String.valueOf(n2) +")"+ "" +"×"+""+String.valueOf(n3)+ "=");

                                        break;
                                    default:
                                        result3=n1-n2;
                                        if(n3==0)
                                        {
                                            n3++;
                                        }
                                        if(result3<n3){
                                            t1=result3;
                                            result3=n3;
                                            n3=t1;
                                        }
                                        while (result3%n3!=0){
                                            n1++;
                                            result3=n1-n2;
                                        }
                                        result2=(n1-n2)/n3;
                                        jT_q.setText("("+String.valueOf(n1) + "" + "-" + "" + String.valueOf(n2) + ")"+"÷"+""+String.valueOf(n3)+ "=");

                                        break;
                                }
                                break;
                            case 3:
                                switch (q) {
                                    case 1:
                                        result2=n1*n2+n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "×" + "" + String.valueOf(n2) + "" +"+"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 2:
                                        result2 = n1*n2-n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "×" + "" + String.valueOf(n1) + "" +"-"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 3:
                                        result2=n1*n2*n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "×" + "" + String.valueOf(n2) + "" +"×"+""+String.valueOf(n3)+ "=");

                                        break;
                                    default:
                                        result3=n1*n2;
                                        if(n3==0)
                                        {
                                            n3++;
                                        }
                                        if(result3<n3){
                                            t1=result3;
                                            result3=n3;
                                            n3=t1;
                                        }
                                        while (result3%n3!=0){
                                            n1++;
                                            result3=n1*n2;
                                        }
                                        result2=n1*n2/n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "×" + "" + String.valueOf(n2) + "" +"÷"+""+String.valueOf(n3)+ "=");

                                        break;
                                }
                                break;
                            default:
                                if(n2==0)
                                {
                                    n2++;
                                }
                                if(n1<n2)
                                {
                                    t1=n1;
                                    n1=n2;
                                    n2=t1;
                                }
                                while (n1%n2!=0&&n2!=0)
                                {
                                    n1++;
                                }
                                switch (q) {
                                    case 1:
                                        result2=n1/n2+n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "÷" + "" + String.valueOf(n2) + "" +"+"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 2:
                                        result2 = n1/n2-n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "÷" + "" + String.valueOf(n2) + "" +"-"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 3:
                                        result2=n1/n2*n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "÷" + "" + String.valueOf(n2) + "" +"×"+""+String.valueOf(n3)+ "=");

                                        break;
                                    default:
                                        result3=n1/n2;
                                        if(n3==0)
                                        {
                                            n3++;
                                        }
                                        if(result3<n3){
                                            t1=result3;
                                            result3=n3;
                                            n3=t1;
                                        }
                                        while (result3%n3!=0){
                                            n3--;
                                            result3=n1/n2;
                                        }
                                        result2=n1/n2/n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "÷" + "" + String.valueOf(n2) + "" +"÷"+""+String.valueOf(n3)+ "=");

                                        break;
                                }
                                break;
                        }
                        finalResult2=result2;
                        jT_q.setEditable(false);//最后一题下一题按钮设置为不可点
                        jT_q.setBounds(180, 40, 120, 41);
                        jT_q.setColumns(1);
                        jT_q.setFont(new Font("宋体", Font.PLAIN, 20));
                        panel2.add(jT_q);
                    }
                    else {
                        button_next.setEnabled(false);
                    }
                        jTextField_answer.setText("");

                }
            }
            button_next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //判断用户输入答案与正确答案是否相符，并计算得分
                        if(s==0) {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult))) {
                                JOptionPane.showMessageDialog(null, "回答正确");
                                score += 5;
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "回答错误");
                            }
                        }
                        else
                            {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult2))) {
                                JOptionPane.showMessageDialog(null, "回答正确");
                                score += 5;
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "回答错误");
                            }
                        }
                        new test();
                    }
                });
                //重写按钮
                JButton button_re = new JButton("重写");
                button_re.setBounds(250,100,80,25);
                frame1.getContentPane().add(button_re);
                button_re.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        score=0;
                        s=0;
                        jTextField_answer.setText("");
                        JiShiQi js1 = new JiShiQi();
                        js1.time=1;
                        s=0;
                        new  test();
                    }
                });
                //交卷按钮
                JButton button_submit = new JButton("交卷");
                button_submit.setBounds(150,100,80,25);
                frame1.getContentPane().add(button_submit);
                button_submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(s==0) {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult))) {
                                JOptionPane.showMessageDialog(null, "回答正确");
                                score += 5;
                            }
                            else
                                {
                                JOptionPane.showMessageDialog(null, "回答错误");
                            }
                        }
                        else {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult2))) {
                                JOptionPane.showMessageDialog(null, "回答正确");
                                score += 5;
                            }
                            else
                                {
                                JOptionPane.showMessageDialog(null, "回答错误");
                            }
                        }
                        JiShiQi js2 = new JiShiQi();
                        JOptionPane.showMessageDialog(null,"您的得分是：" + score+"。"+"用时："+js2.time+"s");
                        js2.time=1;
                        s=0;
                        new  test();
                        score=0;
                    }
                });
        }



    }

}





























































































































































































