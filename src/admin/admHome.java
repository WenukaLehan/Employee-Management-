
package admin;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import login.singin;
import java.awt.Cursor;
import javax.swing.JFrame;


public class admHome extends javax.swing.JFrame {

    
    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    int n1,n2;
    
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    public admHome() {
        initComponents();
        view();
        viewA();
        maleM.setActionCommand("Male");
        femaleM.setActionCommand("Female");
        maleA.setActionCommand("Male");
        femaleA.setActionCommand("Female");
        
    }
    
    public static String hash(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    
    private static String generatePwd() {
        Random random = new Random();
        int length = 6;
        String numbers = "0123456789";

        String OTP = "";

        for(int i=0; i<length; i++) {
            int index = random.nextInt(numbers.length());
            OTP += numbers.charAt(index);
        }

        return OTP;
    }
    
    
    public void sendmail(String mail,String msg){
      
        Properties props=new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session=Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("wlghost2022@gmail.com","kkzx dsxn dzeo ebdh");
                }
                }
                );
        
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("wlghost2022@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Successfully Registration");
            message.setText(msg);
            
            Transport.send(message);
            
            
            //JOptionPane.showMessageDialog(null, "OTP sent to your email\n check inbox");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "check email address");
        }
        
    }
    
    public void viewA(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT assistantNo,name,nic,username,phoneNo FROM assistant ");
            rs=pst.executeQuery();
            DefaultTableModel tbModel=(DefaultTableModel)tableA.getModel();
            tbModel.setRowCount(0);
            while(rs.next()){
                String AssistantNo=String.valueOf(rs.getInt("assistantNo"));
                String Name=rs.getString("name");
                String NIC=rs.getString("nic");
                String Username=rs.getString("username");
                String PhoneNo=rs.getString("phoneNo");
                String tbData[]={AssistantNo,Name,NIC,Username,PhoneNo,};
                //DefaultTableModel tblModel=(DefaultTableModel)tb1.getModel();
                tbModel.addRow(tbData);
            }
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
    }
    
    public void view(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT managerNo,name,nic,username,phoneNo FROM manager ");
            rs=pst.executeQuery();
            DefaultTableModel tbModel=(DefaultTableModel)tableM.getModel();
            tbModel.setRowCount(0);
            while(rs.next()){
                String ManagerNo=String.valueOf(rs.getInt("managerNo"));
                String Name=rs.getString("name");
                String NIC=rs.getString("nic");
                String Username=rs.getString("username");
                String PhoneNo=rs.getString("phoneNo");
                String tbData[]={ManagerNo,Name,NIC,Username,PhoneNo,};
                //DefaultTableModel tblModel=(DefaultTableModel)tb1.getModel();
                tbModel.addRow(tbData);
            }
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cut = new javax.swing.JButton();
        mini = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        managerAdd = new image.jpanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        assistantA = new image.jpanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mm = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        addressM = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        nicM = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        phoneM = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        usernameM = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        emailM = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        nameM = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        femaleM = new javax.swing.JRadioButton();
        maleM = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        dobM = new com.toedter.calendar.JDateChooser();
        clearM = new rojerusan.RSMaterialButtonRectangle();
        submitM = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableM = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        previewM = new javax.swing.JTextPane();
        as = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        addressA = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        nicA = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        phoneA = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        usernameA = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        emailA = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        nameA = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        femaleA = new javax.swing.JRadioButton();
        maleA = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        dobA = new com.toedter.calendar.JDateChooser();
        clearA = new rojerusan.RSMaterialButtonRectangle();
        submitA = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableA = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        previewA = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/setting (1).png"))); // NOI18N
        jLabel1.setText("Admin Potral");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 160, 30));

        cut.setBackground(new java.awt.Color(102, 102, 102));
        cut.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        cut.setForeground(new java.awt.Color(255, 255, 255));
        cut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/c.png"))); // NOI18N
        cut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cutMouseExited(evt);
            }
        });
        jPanel1.add(cut, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 30, 30));

        mini.setBackground(new java.awt.Color(102, 102, 102));
        mini.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        mini.setForeground(new java.awt.Color(255, 255, 255));
        mini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/m.png"))); // NOI18N
        mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                miniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                miniMouseExited(evt);
            }
        });
        jPanel1.add(mini, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 30, 30));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Black logo - no background.png"))); // NOI18N
        jLabel7.setText("Colombo Institute of studies");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        jLabel8.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/quit (1).png"))); // NOI18N
        jLabel8.setText("Logout");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 400, 100, 30));

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard (1).png"))); // NOI18N
        jLabel9.setText("Dashboard");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));

        managerAdd.setBackground(new java.awt.Color(102, 102, 255));
        managerAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile (1) (1).png"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        managerAdd.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 90));

        jLabel6.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Add Manager");
        managerAdd.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 30));

        jPanel6.add(managerAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 180, 100));

        assistantA.setBackground(new java.awt.Color(102, 102, 255));
        assistantA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile (1) (1).png"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        assistantA.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 90));

        jLabel5.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Add Assistant");
        assistantA.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));

        jPanel6.add(assistantA, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 180, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow (1).png"))); // NOI18N
        jLabel10.setText("Back..");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("HR Manager Registration");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Gender:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 60, 30));

        addressM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        addressM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addressMKeyReleased(evt);
            }
        });
        jPanel4.add(addressM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 210, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("NIC :");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 40, 30));

        nicM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        nicM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nicMKeyReleased(evt);
            }
        });
        jPanel4.add(nicM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 210, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("PhoneNo:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        phoneM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        phoneM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneMKeyReleased(evt);
            }
        });
        jPanel4.add(phoneM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 210, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Username:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 80, 30));

        usernameM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        usernameM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameMKeyReleased(evt);
            }
        });
        jPanel4.add(usernameM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 210, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Email:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 50, 30));

        emailM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        emailM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailMFocusLost(evt);
            }
        });
        emailM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailMKeyReleased(evt);
            }
        });
        jPanel4.add(emailM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 210, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Address:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 70, 30));

        nameM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        nameM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameMKeyReleased(evt);
            }
        });
        jPanel4.add(nameM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("DOB :");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 50, 30));

        buttonGroup1.add(femaleM);
        femaleM.setForeground(new java.awt.Color(0, 0, 0));
        femaleM.setText("Female");
        jPanel4.add(femaleM, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 70, 30));

        buttonGroup1.add(maleM);
        maleM.setForeground(new java.awt.Color(0, 0, 0));
        maleM.setText("Male");
        jPanel4.add(maleM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 60, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Preview:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 60, 30));

        dobM.setDateFormatString("yyyy-MM-dd");
        dobM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobMFocusLost(evt);
            }
        });
        jPanel4.add(dobM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 210, 30));

        clearM.setBackground(new java.awt.Color(255, 102, 102));
        clearM.setText("Clear");
        clearM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMActionPerformed(evt);
            }
        });
        jPanel4.add(clearM, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 110, 50));

        submitM.setText("Submit");
        submitM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitMActionPerformed(evt);
            }
        });
        jPanel4.add(submitM, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 110, 50));

        tableM.setBorder(new javax.swing.border.MatteBorder(null));
        tableM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ManagerNo", "Name", "NIC", "Username", "PhoneNo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableM);
        if (tableM.getColumnModel().getColumnCount() > 0) {
            tableM.getColumnModel().getColumn(0).setResizable(false);
            tableM.getColumnModel().getColumn(1).setResizable(false);
            tableM.getColumnModel().getColumn(2).setResizable(false);
            tableM.getColumnModel().getColumn(3).setResizable(false);
            tableM.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 640, 130));

        jLabel22.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Full Name:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Registerd Managers:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 140, 30));

        previewM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        previewM.setFocusable(false);
        jScrollPane1.setViewportView(previewM);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 570, 150));

        javax.swing.GroupLayout mmLayout = new javax.swing.GroupLayout(mm);
        mm.setLayout(mmLayout);
        mmLayout.setHorizontalGroup(
            mmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mmLayout.setVerticalGroup(
            mmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", mm);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow (1).png"))); // NOI18N
        jLabel12.setText("Back..");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("HR assistant Registration");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jLabel24.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Gender:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 60, 30));

        addressA.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        addressA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addressAKeyReleased(evt);
            }
        });
        jPanel5.add(addressA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 210, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("NIC :");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 40, 30));

        nicA.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        nicA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nicAKeyReleased(evt);
            }
        });
        jPanel5.add(nicA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 210, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("PhoneNo:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        phoneA.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        phoneA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneAKeyReleased(evt);
            }
        });
        jPanel5.add(phoneA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 210, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Username:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 80, 30));

        usernameA.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        usernameA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameAKeyReleased(evt);
            }
        });
        jPanel5.add(usernameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 210, 30));

        jLabel28.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Email:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 50, 30));

        emailA.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        emailA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailAFocusLost(evt);
            }
        });
        emailA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailAKeyReleased(evt);
            }
        });
        jPanel5.add(emailA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 210, 30));

        jLabel29.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Address:");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 70, 30));

        nameA.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        nameA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameAKeyReleased(evt);
            }
        });
        jPanel5.add(nameA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("DOB :");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 50, 30));

        buttonGroup2.add(femaleA);
        femaleA.setForeground(new java.awt.Color(0, 0, 0));
        femaleA.setText("Female");
        jPanel5.add(femaleA, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 70, 30));

        buttonGroup2.add(maleA);
        maleA.setForeground(new java.awt.Color(0, 0, 0));
        maleA.setText("Male");
        jPanel5.add(maleA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 60, 30));

        jLabel31.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Preview:");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 60, 30));

        dobA.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dobA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 210, 30));

        clearA.setBackground(new java.awt.Color(255, 102, 102));
        clearA.setText("Clear");
        clearA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAActionPerformed(evt);
            }
        });
        jPanel5.add(clearA, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 110, 50));

        submitA.setText("Submit");
        submitA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAActionPerformed(evt);
            }
        });
        jPanel5.add(submitA, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 110, 50));

        tableA.setBorder(new javax.swing.border.MatteBorder(null));
        tableA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ManagerNo", "Name", "NIC", "Username", "PhoneNo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableA);
        if (tableA.getColumnModel().getColumnCount() > 0) {
            tableA.getColumnModel().getColumn(0).setResizable(false);
            tableA.getColumnModel().getColumn(1).setResizable(false);
            tableA.getColumnModel().getColumn(2).setResizable(false);
            tableA.getColumnModel().getColumn(3).setResizable(false);
            tableA.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 640, 130));

        jLabel32.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Full Name:");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 80, 30));

        jLabel33.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Registerd Assistant:");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 140, 30));

        previewA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        previewA.setFocusable(false);
        jScrollPane4.setViewportView(previewA);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 570, 150));

        javax.swing.GroupLayout asLayout = new javax.swing.GroupLayout(as);
        as.setLayout(asLayout);
        asLayout.setHorizontalGroup(
            asLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        asLayout.setVerticalGroup(
            asLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", as);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1000, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1000, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int o= JOptionPane.showConfirmDialog(null, "Are you sure logout?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(o==0){
            singin sing= new singin();
            sing.setVisible(true);
            this.hide();
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        int p=JOptionPane.showConfirmDialog(null , "Are you sure exit?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(p==0){
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    public void clearAs(){
        nameA.setText("");
        nicA.setText("");
        phoneA.setText("");
        usernameA.setText("");
        emailA.setText("");
        addressA.setText("");
        previewA.setText("");
        dobA.setDate(null);
        buttonGroup2.clearSelection();
        submitA.setEnabled(true);
    }
    
    public void clear(){
        nameM.setText("");
        nicM.setText("");
        phoneM.setText("");
        usernameM.setText("");
        emailM.setText("");
        addressM.setText("");
        previewM.setText("");
        dobM.setDate(null);
        buttonGroup1.clearSelection();
        submitM.setEnabled(true);
    }
    
    private void clearMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMActionPerformed
        clear();
        
    }//GEN-LAST:event_clearMActionPerformed

    private void submitMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitMActionPerformed
        if("".equals(nameM.getText()) || "".equals(nicM.getText()) || "".equals(phoneM.getText()) || "".equals(usernameM.getText())
                || "".equals(emailM.getText()) || "".equals(addressM.getText()) || "".equals(dobM.getDate()) || maleM.isSelected()==false && femaleM.isSelected()==false ){
           
            JOptionPane.showMessageDialog(null, "Please fill all !!","Warning",JOptionPane.QUESTION_MESSAGE);
            
        }
        else
        {
            String pwd=generatePwd();
            
           try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn, username, password);
            pst=sqlConn.prepareStatement("insert into manager(name,nic,phoneNo,username,dob,email,gender,address,password)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, nameM.getText());
            pst.setString(2,nicM.getText());
            pst.setString(3,phoneM.getText());
            pst.setString(4,usernameM.getText());
            pst.setString(5,((JTextField)dobM.getDateEditor().getUiComponent()).getText());
            pst.setString(6,emailM.getText());
            pst.setString(7,buttonGroup1.getSelection().getActionCommand());
            pst.setString(8,addressM.getText());
            pst.setString(9,hash(pwd));
            
            pst.executeUpdate();
            
            //jPanel4.setCursor(Cursor.getPredefinedCursor(WAIT_CURSOR));
            
            sendmail(emailM.getText(),"Your Manager Account Has Registerd Successfully!!!\n this is Your Login Info\nUsername: "+usernameM.getText()+"\nPassword:"+pwd+"\n\nThankyou");
            
            JOptionPane.showMessageDialog(this, "Registerd successfully\ncheck manager inbox for password");
            clear();
            view();
            //jPanel4.setCursor(Cursor.getPredefinedCursor(DEFAULT_CURSOR));
            
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Databese Error");
            }
        }
    }//GEN-LAST:event_submitMActionPerformed

    private void emailMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailMFocusLost
        
        
        if(!"".equals(emailM.getText())){
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(emailM.getText()).matches()){
                
                submitM.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Wrong Email ID Please Check !!","Warning",JOptionPane.WARNING_MESSAGE);
                submitM.setEnabled(false);
            } 
        }
    }//GEN-LAST:event_emailMFocusLost

    private void dobMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobMFocusLost
        if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }else{
            previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_dobMFocusLost

    private void addressMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressMKeyReleased
        if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }else{
            previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_addressMKeyReleased

    private void nameMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameMKeyReleased
        if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }else{
            previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_nameMKeyReleased

    private void nicMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nicMKeyReleased
        if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }else{
            previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_nicMKeyReleased

    private void phoneMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneMKeyReleased
        if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }else{
            previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_phoneMKeyReleased

    private void usernameMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameMKeyReleased
        if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }else{
            previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_usernameMKeyReleased

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        int p=JOptionPane.showConfirmDialog(null , "Are you sure exit?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(p==0){
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void addressAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressAKeyReleased
        if(maleA.isSelected()==true ||femaleA.isSelected()==true){
                previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText()+ "\nGender: "+buttonGroup2.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }else{
            previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_addressAKeyReleased

    private void nicAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nicAKeyReleased
        if(maleA.isSelected()==true ||femaleA.isSelected()==true){
                previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText()+ "\nGender: "+buttonGroup2.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }else{
            previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_nicAKeyReleased

    private void phoneAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneAKeyReleased
        if(maleA.isSelected()==true ||femaleA.isSelected()==true){
                previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText()+ "\nGender: "+buttonGroup2.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }else{
            previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_phoneAKeyReleased

    private void usernameAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameAKeyReleased
        if(maleA.isSelected()==true ||femaleA.isSelected()==true){
                previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText()+ "\nGender: "+buttonGroup2.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }else{
            previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_usernameAKeyReleased

    private void emailAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAFocusLost
        if(!"".equals(emailA.getText())){
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(emailA.getText()).matches()){
                
                submitA.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Wrong Email ID Please Check !!","Warning",JOptionPane.WARNING_MESSAGE);
                submitA.setEnabled(false);
            } 
        }
    }//GEN-LAST:event_emailAFocusLost

    private void nameAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameAKeyReleased
        if(maleA.isSelected()==true ||femaleA.isSelected()==true){
                previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText()+ "\nGender: "+buttonGroup2.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }else{
            previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                "\nAddress: "+addressA.getText() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_nameAKeyReleased

    private void clearAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAActionPerformed
        clearAs();
    }//GEN-LAST:event_clearAActionPerformed

    private void submitAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAActionPerformed
        
        if("".equals(nameA.getText()) || "".equals(nicA.getText()) || "".equals(phoneA.getText()) || "".equals(usernameA.getText())
                || "".equals(emailA.getText()) || "".equals(addressA.getText()) || "".equals(dobA.getDate()) || maleA.isSelected()==false && femaleA.isSelected()==false ){
           
            JOptionPane.showMessageDialog(null, "Please fill all !!","Warning",JOptionPane.QUESTION_MESSAGE);
            
        }
        else
        {
            String pwd=generatePwd();
            
           try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn, username, password);
            pst=sqlConn.prepareStatement("insert into assistant(name,nic,phoneNo,username,dob,email,gender,address,password)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, nameA.getText());
            pst.setString(2,nicA.getText());
            pst.setString(3,phoneA.getText());
            pst.setString(4,usernameA.getText());
            pst.setString(5,((JTextField)dobA.getDateEditor().getUiComponent()).getText());
            pst.setString(6,emailA.getText());
            pst.setString(7,buttonGroup2.getSelection().getActionCommand());
            pst.setString(8,addressA.getText());
            pst.setString(9,hash(pwd));
            
            pst.executeUpdate();
            
            //jPanel4.setCursor(Cursor.getPredefinedCursor(WAIT_CURSOR));
            
            sendmail(emailA.getText(),"Your Assistant Account Has Registerd Successfully!!!\n this is Your Login Info\nUsername: "+usernameM.getText()+"\nPassword:"+pwd+"\n\nThankyou");
            
            JOptionPane.showMessageDialog(this, "Registerd successfully\ncheck Assistant inbox for password");
            clearAs();
            viewA();
            //jPanel4.setCursor(Cursor.getPredefinedCursor(DEFAULT_CURSOR));
            
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Databese Error");
            }
        }
        
    }//GEN-LAST:event_submitAActionPerformed

    private void emailAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailAKeyReleased
        if(!"".equals(emailA.getText())){
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(emailA.getText()).matches()){
                
                 if(maleA.isSelected()==true ||femaleA.isSelected()==true){
                    previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                    "\nAddress: "+addressA.getText()+ "\nGender: "+buttonGroup2.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
                }else{
                    previewA.setText("Name: "+nameA.getText()+ "\nNIC: "+nicA.getText()+ "\nPhoneNo: "+phoneA.getText()+ "\nUsername: "+usernameA.getText() +"\nEmail: "+emailA.getText()+
                    "\nAddress: "+addressA.getText() + "\nDOB: "+((JTextField)dobA.getDateEditor().getUiComponent()).getText() );
                }
            }
             
        }
    }//GEN-LAST:event_emailAKeyReleased

    private void emailMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailMKeyReleased
        if(!"".equals(emailM.getText())){
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(emailM.getText()).matches()){
                
                if(maleM.isSelected()==true ||femaleM.isSelected()==true){
                previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                "\nAddress: "+addressM.getText()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
                }else{
                    previewM.setText("Name: "+nameM.getText()+ "\nNIC: "+nicM.getText()+ "\nPhoneNo: "+phoneM.getText()+ "\nUsername: "+usernameM.getText() +"\nEmail: "+emailM.getText()+
                        "\nAddress: "+addressM.getText() + "\nDOB: "+((JTextField)dobM.getDateEditor().getUiComponent()).getText() );
                }
                
                
            }
        }
    }//GEN-LAST:event_emailMKeyReleased

    private void cutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseClicked
        cut.setBackground(Color.red);
        System.exit(1);
    }//GEN-LAST:event_cutMouseClicked

    private void cutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseEntered
        cut.setBackground(Color.red);
    }//GEN-LAST:event_cutMouseEntered

    private void cutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseExited
        cut.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_cutMouseExited

    private void miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseClicked
        mini.setBackground(Color.BLUE);
        this.setExtendedState(JFrame.ICONIFIED);
        //jTextPane1.setBorder(BorderFactory.createLineBorder(Color.black));
    }//GEN-LAST:event_miniMouseClicked

    private void miniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseEntered
        mini.setBackground(Color.BLUE);
    }//GEN-LAST:event_miniMouseEntered

    private void miniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseExited
        mini.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_miniMouseExited

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressA;
    private javax.swing.JTextField addressM;
    private javax.swing.JPanel as;
    private image.jpanel assistantA;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private rojerusan.RSMaterialButtonRectangle clearA;
    private rojerusan.RSMaterialButtonRectangle clearM;
    private javax.swing.JButton cut;
    private com.toedter.calendar.JDateChooser dobA;
    private com.toedter.calendar.JDateChooser dobM;
    private javax.swing.JTextField emailA;
    private javax.swing.JTextField emailM;
    private javax.swing.JRadioButton femaleA;
    private javax.swing.JRadioButton femaleM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton maleA;
    private javax.swing.JRadioButton maleM;
    private image.jpanel managerAdd;
    private javax.swing.JButton mini;
    private javax.swing.JPanel mm;
    private javax.swing.JTextField nameA;
    private javax.swing.JTextField nameM;
    private javax.swing.JTextField nicA;
    private javax.swing.JTextField nicM;
    private javax.swing.JTextField phoneA;
    private javax.swing.JTextField phoneM;
    private javax.swing.JTextPane previewA;
    private javax.swing.JTextPane previewM;
    private rojerusan.RSMaterialButtonRectangle submitA;
    private rojerusan.RSMaterialButtonRectangle submitM;
    private javax.swing.JTable tableA;
    private javax.swing.JTable tableM;
    private javax.swing.JTextField usernameA;
    private javax.swing.JTextField usernameM;
    // End of variables declaration//GEN-END:variables
}
