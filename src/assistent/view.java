
package assistent;

import manager.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class view extends javax.swing.JFrame {

    
    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    int n1,n2;
    
    String emailN,assistantN;
    
    public view() {
        initComponents();
        
    }
    
    
    public void load(String nic1,String name1,String assista){
        assistantN=assista;
        topic.setText(name1+"(view)");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT * FROM employe WHERE nic="+nic1);
            //pst.setString(1, nic1);
            rs=pst.executeQuery();
            if(rs.next()){
                emailN=rs.getString("email");
                id.setText(String.valueOf(rs.getInt("empID")));
                gender.setText(rs.getString("gender"));
                name.setText(rs.getString("name"));
                nic.setText(rs.getString("nic"));
                phone.setText(rs.getString("phoneNo"));
                dob.setText(rs.getString("DOB"));
                email.setText(rs.getString("email"));
                address.setText(rs.getString("address"));
                dep.setText(rs.getString("department"));
                des.setText(rs.getString("designation"));
                epf.setText(rs.getString("epfNo"));
            }
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex+"\nDatabase Connection Error","error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void sendmail(String mail,String msg,String object){
      
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
            message.setSubject(object);
            message.setText(msg);
            
            Transport.send(message);
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "check email address");
        }
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        mini = new javax.swing.JButton();
        cut = new javax.swing.JButton();
        topic = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        dob = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        nic = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        gender = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        epf = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        des = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        dep = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgmail = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(mini, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 30, 30));

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
        jPanel2.add(cut, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 30, 30));

        topic.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        topic.setForeground(new java.awt.Color(0, 0, 0));
        topic.setText("Wenuka lehan ");
        jPanel2.add(topic, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Black logo - no background.png"))); // NOI18N
        jLabel7.setText("Colombo Institute of studies");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setText("EmpID:");
        jTextField1.setFocusable(false);
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 28));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setText("Name:");
        jTextField2.setFocusable(false);
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 28));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.setText("NIC:");
        jTextField3.setFocusable(false);
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 28));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(204, 204, 204));
        jTextField4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(0, 0, 0));
        jTextField4.setText("DOB:");
        jTextField4.setFocusable(false);
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 28));

        dob.setEditable(false);
        dob.setBackground(new java.awt.Color(204, 204, 204));
        dob.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        dob.setForeground(new java.awt.Color(0, 0, 0));
        dob.setFocusable(false);
        jPanel1.add(dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 220, 28));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(255, 255, 255));
        id.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        id.setForeground(new java.awt.Color(0, 0, 0));
        id.setFocusable(false);
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 220, 28));

        name.setEditable(false);
        name.setBackground(new java.awt.Color(204, 204, 204));
        name.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setFocusable(false);
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 220, 28));

        nic.setEditable(false);
        nic.setBackground(new java.awt.Color(255, 255, 255));
        nic.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        nic.setForeground(new java.awt.Color(0, 0, 0));
        nic.setFocusable(false);
        jPanel1.add(nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 220, 28));

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(0, 0, 0));
        jTextField9.setText("Email:");
        jTextField9.setFocusable(false);
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 28));

        email.setEditable(false);
        email.setBackground(new java.awt.Color(255, 255, 255));
        email.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        email.setForeground(new java.awt.Color(0, 0, 0));
        email.setFocusable(false);
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 220, 28));

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(204, 204, 204));
        jTextField11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(0, 0, 0));
        jTextField11.setText("Address:");
        jTextField11.setFocusable(false);
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 28));

        address.setEditable(false);
        address.setBackground(new java.awt.Color(204, 204, 204));
        address.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        address.setForeground(new java.awt.Color(0, 0, 0));
        address.setFocusable(false);
        jPanel1.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 220, 28));

        jTextField13.setEditable(false);
        jTextField13.setBackground(new java.awt.Color(255, 255, 255));
        jTextField13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(0, 0, 0));
        jTextField13.setText("Gender:");
        jTextField13.setFocusable(false);
        jPanel1.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 28));

        gender.setEditable(false);
        gender.setBackground(new java.awt.Color(255, 255, 255));
        gender.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        gender.setForeground(new java.awt.Color(0, 0, 0));
        gender.setFocusable(false);
        jPanel1.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 220, 28));

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(204, 204, 204));
        jTextField15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(0, 0, 0));
        jTextField15.setText("PhoneNo:");
        jTextField15.setFocusable(false);
        jPanel1.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 28));

        phone.setEditable(false);
        phone.setBackground(new java.awt.Color(204, 204, 204));
        phone.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        phone.setForeground(new java.awt.Color(0, 0, 0));
        phone.setFocusable(false);
        jPanel1.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 220, 28));

        jTextField17.setEditable(false);
        jTextField17.setBackground(new java.awt.Color(255, 255, 255));
        jTextField17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(0, 0, 0));
        jTextField17.setText("EPFNo:");
        jTextField17.setFocusable(false);
        jPanel1.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 28));

        epf.setEditable(false);
        epf.setBackground(new java.awt.Color(255, 255, 255));
        epf.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        epf.setForeground(new java.awt.Color(0, 0, 0));
        epf.setFocusable(false);
        jPanel1.add(epf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 220, 28));

        jTextField19.setEditable(false);
        jTextField19.setBackground(new java.awt.Color(204, 204, 204));
        jTextField19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField19.setForeground(new java.awt.Color(0, 0, 0));
        jTextField19.setText("Designation:");
        jTextField19.setFocusable(false);
        jPanel1.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 28));

        des.setEditable(false);
        des.setBackground(new java.awt.Color(204, 204, 204));
        des.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        des.setForeground(new java.awt.Color(0, 0, 0));
        des.setFocusable(false);
        jPanel1.add(des, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 220, 28));

        jTextField21.setEditable(false);
        jTextField21.setBackground(new java.awt.Color(255, 255, 255));
        jTextField21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField21.setForeground(new java.awt.Color(0, 0, 0));
        jTextField21.setText("Department:");
        jTextField21.setFocusable(false);
        jPanel1.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, 28));

        dep.setEditable(false);
        dep.setBackground(new java.awt.Color(255, 255, 255));
        dep.setFont(new java.awt.Font("Segoe UI Black", 0, 13)); // NOI18N
        dep.setForeground(new java.awt.Color(0, 0, 0));
        dep.setFocusable(false);
        jPanel1.add(dep, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 220, 28));

        msgmail.setBackground(new java.awt.Color(255, 255, 255));
        msgmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.black, java.awt.Color.darkGray), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.black, java.awt.Color.darkGray)));
        jScrollPane1.setViewportView(msgmail);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 280, 110));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gmail (1).png"))); // NOI18N
        jLabel1.setText("Send MSG:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, -1, -1));

        send.setBackground(new java.awt.Color(255, 204, 102));
        send.setFont(new java.awt.Font("Segoe Print", 1, 13)); // NOI18N
        send.setForeground(new java.awt.Color(0, 0, 0));
        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        jPanel1.add(send, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, -1, -1));

        jScrollPane2.setEnabled(false);
        jScrollPane2.setFocusable(false);
        jScrollPane2.setVerifyInputWhenFocusTarget(false);

        jTextPane2.setEditable(false);
        jScrollPane2.setViewportView(jTextPane2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 100, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/empV (1).gif"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(800, 430));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void cutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseClicked
        cut.setBackground(Color.red);
        this.hide();
    }//GEN-LAST:event_cutMouseClicked

    private void cutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseEntered
        cut.setBackground(Color.red);
    }//GEN-LAST:event_cutMouseEntered

    private void cutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseExited
        cut.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_cutMouseExited

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        sendmail(emailN,msgmail.getText(),"Massage Frome Manager "+assistantN+"\nColombo institute of studies.");
        JOptionPane.showMessageDialog(null, "successfully sent...");
    }//GEN-LAST:event_sendActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JLabel background;
    private javax.swing.JButton cut;
    private javax.swing.JTextField dep;
    private javax.swing.JTextField des;
    private javax.swing.JTextField dob;
    private javax.swing.JTextField email;
    private javax.swing.JTextField epf;
    private javax.swing.JTextField gender;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton mini;
    private javax.swing.JTextPane msgmail;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nic;
    private javax.swing.JTextField phone;
    private javax.swing.JButton send;
    private javax.swing.JLabel topic;
    // End of variables declaration//GEN-END:variables
}
