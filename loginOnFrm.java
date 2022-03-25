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
/*��Ҫ����ģ�飬����GUI������ƺ�������������*/
public class loginOnFrm extends JFrame {

    private JPanel contentPane;
    private  JTextField loginNameTxt;
    private JPasswordField loginPwdTxt;
    private DBUtil dbUtil=new DBUtil();
    private UserDao userDao=new UserDao();
    private Random random=new Random();
    private int s=0;
    public static int finalResult2;
    //������
    public static void main(String[] args) {
              new loginOnFrm().init();
    }
    public void init(){
        //��¼����
        JFrame frame = new JFrame("��¼");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        Panel panel1 = new Panel();
        frame.setContentPane(panel1);
        frame.setBounds(860,500,350,200);
        frame.setLocationRelativeTo(panel1);
        JFrame.setDefaultLookAndFeelDecorated(true);
        panel1.setLayout(null);
        //����
        JLabel ttt = new JLabel("��������");
        ttt.setBounds(130,0,80,20);
        ttt.setFont(new Font("����",Font.PLAIN,20));
        panel1.add(ttt);
        //�û�����
        JLabel loginName = new JLabel("�û���");
        loginName.setBounds(10,20,80,25);
        panel1.add(loginName);
        loginNameTxt = new JTextField();
        loginNameTxt.setBounds(100,20,165,25);
        panel1.add(loginNameTxt);
        //�����
        JLabel loginPwd = new JLabel("����");
        loginPwd.setBounds(10,50,80,25);
        panel1.add(loginPwd);
        loginPwdTxt = new JPasswordField();
        loginPwdTxt.setBounds(100,50,165,25);
        panel1.add(loginPwdTxt);
        //��¼��ť
        JButton button1 = new JButton("��¼");
        button1.setBounds(250/2-40,200/2-12,80,25);
        panel1.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loginActionPerformed(e);
                 frame.dispose();
            }
        });
        //���ð�ť
        JButton button2 = new JButton("����");
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
    //�����¼�����
    private void resetValueActionPerformed(ActionEvent evt) {
        this.loginNameTxt.setText("");
        this.loginPwdTxt.setText("");     
    }
    //��¼�¼�����
    private void loginActionPerformed(ActionEvent evt) {
        String loginName =this.loginNameTxt.getText();
        String loginPwd=new String(this.loginPwdTxt.getPassword());
        if(StringUtil.isEmpty(loginName)){
            JOptionPane.showMessageDialog(null,"�û�������Ϊ��");
            return;
        }
        if(StringUtil.isEmpty(loginPwd)){
            JOptionPane.showMessageDialog(null,"���벻��Ϊ��");
            return;
        }
        User user = new User(loginName,loginPwd);
        Connection conn=null;
        try {
            conn=dbUtil.getConn();
            User currentUser=userDao.login(conn,user);
            if (currentUser!=null){
                JOptionPane.showMessageDialog(null,"��½�ɹ�");
                new fun();
                JiShiQi js1 = new JiShiQi();
                js1.setVisible(true);
                Thread thread1=new Thread(js1);
                thread1.start();
            }
            else {
                JOptionPane.showMessageDialog(null,"�û��������������");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class fun extends JFrame{
        private int score=0;
        public fun()
        {
            //���ⴰ��
            JFrame frame1 = new JFrame("����");
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
            //���ɵ�һ������������Ŀ
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
                            jT_q0.setText("("+String.valueOf(r1) + "" + "+" + "" + String.valueOf(r2) +")"+ "" +"��"+""+String.valueOf(r3)+ "=");

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
                            jT_q0.setText("("+String.valueOf(r1) + "" + "+" + "" + String.valueOf(r2) +")"+ "" +"��"+""+String.valueOf(r3)+ "=");

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
                            jT_q0.setText("("+String.valueOf(r1) + "" + "-" + "" + String.valueOf(r2) +")"+ "" +"��"+""+String.valueOf(r3)+ "=");

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
                            jT_q0.setText("("+String.valueOf(r1) + "" + "-" + "" + String.valueOf(r2) + ")"+"��"+""+String.valueOf(r3)+ "=");

                            break;
                    }
                    break;
                case 3:
                    switch (z) {
                        case 1:
                            result=r1*r2+r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"+"+""+String.valueOf(r3)+ "=");

                            break;
                        case 2:
                            result = r1*r2-r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"-"+""+String.valueOf(r3)+ "=");

                            break;
                        case 3:
                            result=r1*r2*r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"��"+""+String.valueOf(r3)+ "=");

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
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"��"+""+String.valueOf(r3)+ "=");

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
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"+"+""+String.valueOf(r3)+ "=");

                            break;
                        case 2:
                            result = r1/r2-r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"-"+""+String.valueOf(r3)+ "=");

                            break;
                        case 3:
                            result=r1/r2*r3;
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"��"+""+String.valueOf(r3)+ "=");

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
                            jT_q0.setText(String.valueOf(r1) + "" + "��" + "" + String.valueOf(r2) + "" +"��"+""+String.valueOf(r3)+ "=");

                            break;
                    }
                    break;
            }
            jT_q0.setEditable(false);
            jT_q0.setBounds(180,40,221,41);
            jT_q0.setColumns(1);
            jT_q0.setFont(new Font("����",Font.PLAIN,20));
            //�����
            JTextField jTextField_answer = new JTextField();
            jTextField_answer.setColumns(1);
            jTextField_answer.setBounds(300,40,50,41);
            frame1.getContentPane().add(jTextField_answer);
            //�жϿ�
            JTextField jTextField_judge = new JTextField();
            jTextField_judge.setEditable(false);
            jTextField_judge.setColumns(10);
            jTextField_judge.setFont(new Font("����",Font.PLAIN,20));
            jTextField_judge.setBounds(350,40,50,41);
            frame1.getContentPane().add(jTextField_judge);
            panel2.add(jT_q0);
            //��һ�ⰴť
            JButton button_next = new JButton("��һ��");
            button_next.setBounds(350,100,80,25);
            frame1.getContentPane().add(button_next);
            int finalResult = result;//��ȡ��ʽ�𰸸���finalResult
            //����ѭ�����ɺ�19����������
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
                                        jT_q.setText("("+String.valueOf(n1) + "" + "+" + "" + String.valueOf(n2) +")"+ "" +"��"+""+String.valueOf(n3)+ "=");

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
                                        jT_q.setText("("+String.valueOf(n1) + "" + "+" + "" + String.valueOf(n2) +")"+ "" +"��"+""+String.valueOf(n3)+ "=");

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
                                        jT_q.setText("("+String.valueOf(n1) + "" + "-" + "" + String.valueOf(n2) +")"+ "" +"��"+""+String.valueOf(n3)+ "=");

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
                                        jT_q.setText("("+String.valueOf(n1) + "" + "-" + "" + String.valueOf(n2) + ")"+"��"+""+String.valueOf(n3)+ "=");

                                        break;
                                }
                                break;
                            case 3:
                                switch (q) {
                                    case 1:
                                        result2=n1*n2+n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"+"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 2:
                                        result2 = n1*n2-n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n1) + "" +"-"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 3:
                                        result2=n1*n2*n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"��"+""+String.valueOf(n3)+ "=");

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
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"��"+""+String.valueOf(n3)+ "=");

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
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"+"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 2:
                                        result2 = n1/n2-n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"-"+""+String.valueOf(n3)+ "=");

                                        break;
                                    case 3:
                                        result2=n1/n2*n3;
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"��"+""+String.valueOf(n3)+ "=");

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
                                        jT_q.setText(String.valueOf(n1) + "" + "��" + "" + String.valueOf(n2) + "" +"��"+""+String.valueOf(n3)+ "=");

                                        break;
                                }
                                break;
                        }
                        finalResult2=result2;
                        jT_q.setEditable(false);//���һ����һ�ⰴť����Ϊ���ɵ�
                        jT_q.setBounds(180, 40, 120, 41);
                        jT_q.setColumns(1);
                        jT_q.setFont(new Font("����", Font.PLAIN, 20));
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
                        //�ж��û����������ȷ���Ƿ������������÷�
                        if(s==0) {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult))) {
                                JOptionPane.showMessageDialog(null, "�ش���ȷ");
                                score += 5;
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "�ش����");
                            }
                        }
                        else
                            {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult2))) {
                                JOptionPane.showMessageDialog(null, "�ش���ȷ");
                                score += 5;
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "�ش����");
                            }
                        }
                        new test();
                    }
                });
                //��д��ť
                JButton button_re = new JButton("��д");
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
                //����ť
                JButton button_submit = new JButton("����");
                button_submit.setBounds(150,100,80,25);
                frame1.getContentPane().add(button_submit);
                button_submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(s==0) {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult))) {
                                JOptionPane.showMessageDialog(null, "�ش���ȷ");
                                score += 5;
                            }
                            else
                                {
                                JOptionPane.showMessageDialog(null, "�ش����");
                            }
                        }
                        else {
                            if (jTextField_answer.getText().equals(String.valueOf(finalResult2))) {
                                JOptionPane.showMessageDialog(null, "�ش���ȷ");
                                score += 5;
                            }
                            else
                                {
                                JOptionPane.showMessageDialog(null, "�ش����");
                            }
                        }
                        JiShiQi js2 = new JiShiQi();
                        JOptionPane.showMessageDialog(null,"���ĵ÷��ǣ�" + score+"��"+"��ʱ��"+js2.time+"s");
                        js2.time=1;
                        s=0;
                        new  test();
                        score=0;
                    }
                });
        }



    }

}





























































































































































































