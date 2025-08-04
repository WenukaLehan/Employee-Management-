
package login;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class frogot_password extends javax.swing.JFrame {

    
    private static final String usernamed="root";
    private static final String passwordd="";
    private static final String dataConnd="jdbc:mysql://127.0.0.1:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    String table;
    
    public frogot_password() {
        initComponents();
        con_pass.setEchoChar((char)0);
        pass.setEchoChar((char)0);
    }
    
    public void set(String userN,String tableN,String actor){
        user.setForeground(new Color(0,0,0));
        user.setText(userN);
        table=tableN;
        topic.setText("Frogot "+actor+" Password...");
        
    }
    
    public static int password_strenght(String pwd){
        int score=0;
        int lenght= pwd.length();
        
        if(lenght<10){score=1;}
        if(lenght==10){score=2;}
        if(lenght>10){score=3;}
        if(pwd.matches("(?=.*[a-z]).*")){score++;}
        if(pwd.matches("(?=.*[A-Z]).*")){score++;}
        if(pwd.matches("(?=.*[0-9]).*")){score++;}
        if(pwd.matches("(?=.*[!@#$%&^*]).*")){score++;}
        
        return score;
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
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        topic = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        sh = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sh1 = new javax.swing.JLabel();
        con_pass = new javax.swing.JPasswordField();
        cp = new javax.swing.JLabel();
        n = new javax.swing.JLabel();
        e = new javax.swing.JLabel();
        p = new javax.swing.JLabel();
        warn = new javax.swing.JLabel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        submit = new rojerusan.RSMaterialButtonRectangle();
        val_emal = new javax.swing.JLabel();
        pass_warn = new javax.swing.JLabel();
        user_warn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topic.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        topic.setForeground(new java.awt.Color(255, 255, 255));
        topic.setText("Frogot Your Password...");
        jPanel2.add(topic, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        user.setBackground(new java.awt.Color(153, 153, 255));
        user.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        user.setForeground(new java.awt.Color(110, 110, 110));
        user.setText("Enter Username");
        user.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userFocusLost(evt);
            }
        });
        jPanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 240, 40));

        sh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hidden.png"))); // NOI18N
        sh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                shMouseReleased(evt);
            }
        });
        jPanel2.add(sh, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 20, 20));

        pass.setBackground(new java.awt.Color(153, 153, 255));
        pass.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        pass.setForeground(new java.awt.Color(110, 110, 110));
        pass.setText("Enter Password");
        pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passFocusLost(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passKeyReleased(evt);
            }
        });
        jPanel2.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 240, 40));

        jLabel6.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        email.setBackground(new java.awt.Color(153, 153, 255));
        email.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        email.setForeground(new java.awt.Color(110, 110, 110));
        email.setText("Enter Email");
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 240, 40));

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirm Password:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        sh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hidden.png"))); // NOI18N
        sh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sh1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sh1MouseReleased(evt);
            }
        });
        jPanel2.add(sh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 20, 20));

        con_pass.setBackground(new java.awt.Color(153, 153, 255));
        con_pass.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 16)); // NOI18N
        con_pass.setForeground(new java.awt.Color(110, 110, 110));
        con_pass.setText("Enter Password");
        con_pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                con_passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                con_passFocusLost(evt);
            }
        });
        con_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                con_passKeyReleased(evt);
            }
        });
        jPanel2.add(con_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 240, 40));

        cp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lock.png"))); // NOI18N
        jPanel2.add(cp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 40, 40));

        n.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile (1).png"))); // NOI18N
        jPanel2.add(n, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 40, 40));

        e.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/message.png"))); // NOI18N
        jPanel2.add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 40, 40));

        p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lock.png"))); // NOI18N
        jPanel2.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 40, 40));

        warn.setForeground(new java.awt.Color(204, 0, 0));
        jPanel2.add(warn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, -1, -1));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle2.setText("cancle");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 130, 50));

        submit.setBackground(new java.awt.Color(255, 102, 51));
        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        jPanel2.add(submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 130, 50));

        val_emal.setForeground(new java.awt.Color(230, 0, 0));
        jPanel2.add(val_emal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));
        jPanel2.add(pass_warn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        user_warn.setForeground(new java.awt.Color(220, 0, 0));
        jPanel2.add(user_warn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 500));

        setSize(new java.awt.Dimension(400, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userFocusGained

        if(user.getText().equals("Enter Username")){
            user.setText("");
            user.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_userFocusGained

    private void userFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userFocusLost

        if("".equals(user.getText())){
            user.setForeground(new Color(110,110,110));
            user.setText("Enter Username");
            user_warn.setText("Username cannot be null");
            submit.setEnabled(false);
        }else{
            user_warn.setText("");
        }
    }//GEN-LAST:event_userFocusLost

    private void shMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shMousePressed
        URL imageURL = singin.class.getResource("/image/view.png");
        ImageIcon scaleicon= new ImageIcon(imageURL);
        sh.setIcon(scaleicon);

        pass.setEchoChar((char)0);
    }//GEN-LAST:event_shMousePressed

    private void shMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shMouseReleased
        URL imageURL = singin.class.getResource("/image/hidden.png");
        ImageIcon scaleicon= new ImageIcon(imageURL);
        sh.setIcon(scaleicon);

        if(!"Enter Password".equals(pass.getText())){
            pass.setEchoChar('*');
        }
    }//GEN-LAST:event_shMouseReleased

    private void passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFocusGained

        if(pass.getText().equals("Enter Password")){
            pass.setText("");
            pass.setForeground(new Color(0,0,0));
            pass.setEchoChar('*');
        }
    }//GEN-LAST:event_passFocusGained

    private void passFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFocusLost

        if(pass.getText().equals("")){
            pass.setText("Enter Password");
            pass.setForeground(new Color(110,110,110));
            pass.setEchoChar((char)0);
            pass_warn.setForeground(new Color(220,0,0));
            pass_warn.setText("Password cannot be Null!");
            submit.setEnabled(false);
        }
    }//GEN-LAST:event_passFocusLost

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        if(email.getText().equals("Enter Email")){
            email.setText("");
            email.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_emailFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        if("".equals(email.getText())){
            email.setForeground(new Color(110,110,110));
            email.setText("Enter Email");
            val_emal.setText("Email ID Cann't be null");
            submit.setEnabled(false);
        }else{
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(email.getText()).matches()){
                val_emal.setText("");
                submit.setEnabled(true);
            }else{
                val_emal.setText("Email ID Not Valide");
                submit.setEnabled(false);
            } 
        }
    }//GEN-LAST:event_emailFocusLost

    private void sh1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sh1MousePressed
        URL imageURL = singin.class.getResource("/image/view.png");
        ImageIcon scaleicon= new ImageIcon(imageURL);
        sh1.setIcon(scaleicon);

        con_pass.setEchoChar((char)0);
    }//GEN-LAST:event_sh1MousePressed

    private void sh1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sh1MouseReleased
        URL imageURL = singin.class.getResource("/image/hidden.png");
        ImageIcon scaleicon= new ImageIcon(imageURL);
        sh1.setIcon(scaleicon);

        if(!"Enter Password".equals(con_pass.getText())){
            con_pass.setEchoChar('*');
        }
    }//GEN-LAST:event_sh1MouseReleased

    private void con_passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_con_passFocusGained
        if(con_pass.getText().equals("Enter Password")){
            con_pass.setText("");
            con_pass.setForeground(new Color(0,0,0));
            con_pass.setEchoChar('*');
        }
    }//GEN-LAST:event_con_passFocusGained

    private void con_passFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_con_passFocusLost
        if(con_pass.getText().equals("")){
            con_pass.setText("Enter Password");
            con_pass.setForeground(new Color(110,110,110));
            con_pass.setEchoChar((char)0);
        }
    }//GEN-LAST:event_con_passFocusLost

    private void con_passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_con_passKeyReleased
        if(!pass.getText().equals(con_pass.getText())){
            warn.setForeground(new Color(230,0,0));
            warn.setText("Password not match!!");
            submit.setEnabled(false);
        } else {
            warn.setForeground(new Color(0,220,0));
            warn.setText("Password matched!");
            submit.setEnabled(true);
        }
    }//GEN-LAST:event_con_passKeyReleased

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        singin sing= new singin();
        sing.setVisible(true);
        this.hide();
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=(Connection) DriverManager.getConnection(dataConnd,usernamed,passwordd);
            pst=sqlConn.prepareStatement("SELECT username,password,email FROM "+table+" WHERE username=?");
            pst.setString(1, user.getText());
            //pst.executeQuery();
            rs=pst.executeQuery();
            
            if(rs.next()== false){
                JOptionPane.showMessageDialog(null, "Username not found!!!");
            }else{
                if(rs.getString("email").equals(email.getText())){
                    sqlConn.close();
                    Class.forName("com.mysql.jdbc.Driver");
                    sqlConn=(Connection) DriverManager.getConnection(dataConnd,usernamed,passwordd);
                    pst=sqlConn.prepareStatement("UPDATE "+table+" SET password=? WHERE username=?");
                    pst.setString(2, user.getText());
                    pst.setString(1, hash(pass.getText()));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Password Changed Successfully..");
                    this.hide();
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Username and email doesn't match!!!");
                }
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database Error");
        }
        
    }//GEN-LAST:event_submitActionPerformed

    private void passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyReleased
        int x=password_strenght(pass.getText());
        if(x<5){
            pass_warn.setForeground(new Color(220,0,0));
            pass_warn.setText("weak password");
            submit.setEnabled(false);
        }else if(x==5){
            pass_warn.setForeground(new Color(240,240,0));
            pass_warn.setText("medium password");
            submit.setEnabled(true);
        }else if(x>5){
            pass_warn.setForeground(new Color(0,220,0));
            pass_warn.setText("strong password");
            submit.setEnabled(true);
        }
    }//GEN-LAST:event_passKeyReleased

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
            java.util.logging.Logger.getLogger(frogot_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frogot_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frogot_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frogot_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frogot_password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField con_pass;
    private javax.swing.JLabel cp;
    private javax.swing.JLabel e;
    public javax.swing.JTextField email;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel n;
    private javax.swing.JLabel p;
    public javax.swing.JPasswordField pass;
    private javax.swing.JLabel pass_warn;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JLabel sh;
    private javax.swing.JLabel sh1;
    private rojerusan.RSMaterialButtonRectangle submit;
    private javax.swing.JLabel topic;
    public javax.swing.JTextField user;
    private javax.swing.JLabel user_warn;
    private javax.swing.JLabel val_emal;
    private javax.swing.JLabel warn;
    // End of variables declaration//GEN-END:variables
}
