package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {
    JButton button;
    String pin;
    MiniStatement(String pin){
        this.pin = pin;

        getContentPane().setBackground(new Color(255,204,204));
        setLayout(null);
        setSize(450,650);
        setLocation(20,20);

        JLabel label1  = new JLabel();
        label1.setVerticalAlignment(JLabel.TOP);
        add(label1);
        JScrollPane scrollPane = new JScrollPane(label1);
        scrollPane.setBounds(40,120,350,330);
        add(scrollPane);

        JLabel label2 = new JLabel("MINI STATEMENT");
        label2.setFont(new Font("System",Font.BOLD,18));
        label2.setBounds(130,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setFont(new Font("System",Font.BOLD,16));
        label3.setBounds(20,60,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setFont(new Font("System",Font.BOLD,18));
        label4.setBounds(40,460,300,20);
        add(label4);

        try{
            Conn conn = new Conn();
            ResultSet resultSet = conn.statement.executeQuery("SELECT * FROM login WHERE pin = '"+pin+"'");
            while(resultSet.next()){
                label3.setText("Card Number: "+ resultSet.getString("card_number").substring(0,4)+ "XXXXXXXX" + resultSet.getString("card_number").substring(12));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            StringBuilder sb = new StringBuilder("<html>");

            while (rs.next()){
                sb.append(rs.getString("date")).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("type")).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("amount")).append("<br><br>");

                if(rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            sb.append("</html>");
            label1.setText(sb.toString());

            label4.setText("Your Total Balance is Rs. " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }


        button = new JButton("Exit");
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setBounds(300,500,100,25);
        button.addActionListener(this);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);

    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}





//
//
//
//package bank.management.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//
//public class MiniStatement extends JFrame implements ActionListener {
//
//    JButton button;
//    String pin;
//
//    MiniStatement(String pin){
//        this.pin = pin;
//
//        getContentPane().setBackground(new Color(255,204,204));
//        setLayout(null);
//        setSize(450,650);
//        setLocationRelativeTo(null); // <-- center screen
//
//        JLabel label2 = new JLabel("Your History");
//        label2.setFont(new Font("System",Font.BOLD,18));
//        label2.setBounds(150,20,200,20);
//        add(label2);
//
//        JLabel label3 = new JLabel(); // card number
//        label3.setBounds(20,80,350,20);
//        add(label3);
//
//        JLabel label4 = new JLabel(); // balance
//        label4.setBounds(20,470,350,20);
//        add(label4);
//
//        // main statement label but inside scrollpane
//        JLabel label1 = new JLabel();
//        label1.setVerticalAlignment(JLabel.TOP);
//
//        JScrollPane scrollPane = new JScrollPane(label1);
//        scrollPane.setBounds(20,120,400,330);
//        add(scrollPane);
//
//        try{
//            Conn conn = new Conn();
//            ResultSet rs1 = conn.statement.executeQuery("SELECT * FROM login WHERE pin = '"+pin+"'");
//            while(rs1.next()){
//                label3.setText("Card Number: "+ rs1.getString("card_number").substring(0,4)+ "XXXXXXXX" + rs1.getString("card_number").substring(12));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            int balance = 0;
//            Conn con = new Conn();
//            ResultSet rs = con.statement.executeQuery("select * from bank where pin = '"+pin+"'");
//
//            StringBuilder sb = new StringBuilder("<html>");
//            while (rs.next()){
//                sb.append(rs.getString("date")).append("&nbsp;&nbsp;&nbsp;&nbsp;")
//                        .append(rs.getString("type")).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
//                        .append(rs.getString("amount")).append("<br><br>");
//
//                if(rs.getString("type").equals("Deposit")){
//                    balance += Integer.parseInt(rs.getString("amount"));
//                }else {
//                    balance -= Integer.parseInt(rs.getString("amount"));
//                }
//            }
//            sb.append("</html>");
//            label1.setText(sb.toString());
//
//            label4.setText("Your Total Balance is Rs. " + balance);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        button = new JButton("Exit");
//        button.setBackground(Color.black);
//        button.setForeground(Color.white);
//        button.setBounds(20,520,100,30);
//        button.addActionListener(this);
//        add(button);
//
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        setVisible(false);
//    }
//
//    public static void main(String[] args) {
//        new MiniStatement("");
//    }
//}

