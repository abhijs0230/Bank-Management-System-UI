package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1,label2,label3;
    JTextField textField1;
    JPasswordField passwordField2;
    JButton button1,button2,button3;


    Login(){
        super("Bank Management System");


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,10,100,100);
        add(image);


        ImageIcon ii1=new ImageIcon(ClassLoader.getSystemResource("icons/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(650,350,100,100);
        add(iimage);


        label1 = new JLabel("WELCOME TO OUR ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde", Font.BOLD,30));
        label1.setBounds(250,100,400,100);
        add(label1);

        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Ralway",Font.BOLD,20));
        label2.setForeground(Color.white);
        label2.setBounds(100,200,200,30);
        add(label2);

        textField1=new JTextField(15);
        textField1.setBounds(250,200,250,30);
        textField1.setFont(new Font("Arial",Font.BOLD,18));
        add(textField1);


        label3 =new JLabel("PIN: ");
        label3.setFont(new Font("Ralway",Font.BOLD,20));
        label3.setForeground(Color.white);
        label3.setBounds(100,250,200,30);
        add(label3);

        passwordField2 = new JPasswordField(15);
        passwordField2.setBounds(250,250,250,30);
        passwordField2.setFont(new Font("Arial",Font.BOLD,18));
        add(passwordField2);


        button1=new JButton("SIGN IN");
        button1.setFont(new Font("Arial",Font.BOLD,15));
        button1.setForeground(Color.white);
        button1.setBackground(Color.black);
        button1.setBounds(260,300,100,30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("SIGN UP");
        button2.setFont(new Font("Arial",Font.BOLD,15));
        button2.setForeground(Color.white);
        button2.setBackground(Color.black);
        button2.setBounds(250,350,250,30);
        button2.addActionListener(this);
        add(button2);


        button3 = new JButton("CLEAR");
        button3.setFont(new Font("Arial",Font.BOLD,15));
        button3.setForeground(Color.white);
        button3.setBackground(Color.black);
        button3.setBounds(380,300,100,30);
        button3.addActionListener(this);
        add(button3);



        ImageIcon iii1=new ImageIcon(ClassLoader.getSystemResource("icons/backbg.png"));
        Image iii2 = iii1.getImage().getScaledInstance(850,500, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel iiimage = new JLabel(iii3);
        iiimage.setBounds(0,0,850,500);
        add(iiimage);



        setLayout(null);
        setSize(850,500);
        setLocation(400,200);
//        setUndecorated(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() ==button1){
                Conn c = new Conn();
                String cardno = textField1.getText();
                String pin = passwordField2.getText();
                String q = "SELECT * FROM login WHERE card_number ='"+cardno+"' and pin = '"+pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    setVisible(false);
                    new main_class(pin);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN!!!");
                }

            }else if(e.getSource()==button2){
                setVisible(false);
                new Signup();
            }else{
                textField1.setText("");
                passwordField2.setText("");
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
    new Login();
}
}