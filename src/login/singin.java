
package login;

import admin.admHome;
import assistent.AssisHome;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import manager.managHome;


public class singin extends javax.swing.JFrame {

    boolean adm=false;
    boolean mang=false;
    boolean assis=false;
    
    String table,user1;
    
    private static final String usernamed="root";
    private static final String passwordd="";
    private static final String dataConnd="jdbc:mysql://localhost:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    
    public singin() {
        initComponents();
        pass.setEchoChar((char)0);
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

        jPanel1 = new javax.swing.JPanel();
        jpanel2 = new image.jpanel();
        jLabel6 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        sh = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        na = new javax.swing.JLabel();
        cut = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        assistant = new javax.swing.JLabel();
        manager = new javax.swing.JLabel();
        admin = new javax.swing.JLabel();
        assimg = new javax.swing.JLabel();
        glowAs = new javax.swing.JLabel();
        manimg = new javax.swing.JLabel();
        glowM = new javax.swing.JLabel();
        adimg = new javax.swing.JLabel();
        glowA = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel2.setBackground(new java.awt.Color(102, 102, 255));
        jpanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username:");
        jpanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

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
        jpanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, 40));

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        jpanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("WELCOME AGAIN...");
        jpanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/frog.png"))); // NOI18N
        jLabel5.setText("Frogot Password?..");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jpanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(255, 102, 102));
        rSMaterialButtonRectangle1.setText("login");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jpanel2.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 240, 50));

        sh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hidden.png"))); // NOI18N
        sh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                shMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                shMouseReleased(evt);
            }
        });
        jpanel2.add(sh, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 20, 20));

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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        jpanel2.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 240, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile (1).png"))); // NOI18N
        jpanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 40, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lock.png"))); // NOI18N
        jpanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 40, 40));

        na.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        na.setForeground(new java.awt.Color(255, 255, 255));
        jpanel2.add(na, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jPanel1.add(jpanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 390, 430));

        cut.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        cut.setForeground(new java.awt.Color(0, 0, 0));
        cut.setText("X");
        cut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cutMouseClicked(evt);
            }
        });
        jPanel1.add(cut, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, -1, -1));

        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Black logo - no background.png"))); // NOI18N
        jLabel7.setText("Colombo Institute of studies");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("Login as:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 110, 30));

        assistant.setFont(new java.awt.Font("Segoe UI Emoji", 3, 14)); // NOI18N
        assistant.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(assistant, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 75, -1));

        manager.setFont(new java.awt.Font("Segoe UI Emoji", 3, 14)); // NOI18N
        manager.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(manager, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 75, -1));

        admin.setFont(new java.awt.Font("Segoe UI Emoji", 3, 14)); // NOI18N
        admin.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 60, -1));

        assimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/secretary (1).png"))); // NOI18N
        assimg.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        assimg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        assimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                assimgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                assimgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                assimgMouseExited(evt);
            }
        });
        jPanel1.add(assimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 100, 100));

        glowAs.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        glowAs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(glowAs, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 100, 100));

        manimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manager (1).png"))); // NOI18N
        manimg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manimgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manimgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manimgMouseExited(evt);
            }
        });
        jPanel1.add(manimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 100, 100));

        glowM.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        glowM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(glowM, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 100, 100));

        adimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/admin (1).png"))); // NOI18N
        adimg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adimgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adimgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adimgMouseExited(evt);
            }
        });
        jPanel1.add(adimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 100, 100));

        glowA.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        glowA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(glowA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 100, 100));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/a (1).gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        setSize(new java.awt.Dimension(1000, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cutMouseClicked
       System.exit(0);
    }//GEN-LAST:event_cutMouseClicked

    private void adimgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adimgMouseEntered
        admin.setText("ADMIN");
    }//GEN-LAST:event_adimgMouseEntered

    private void adimgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adimgMouseExited
       if(!adm){
        admin.setText("");
       }
    }//GEN-LAST:event_adimgMouseExited

    private void manimgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manimgMouseEntered
        manager.setText("MANAGER");
    }//GEN-LAST:event_manimgMouseEntered

    private void manimgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manimgMouseExited
         if(!mang){
             manager.setText("");
         }
    }//GEN-LAST:event_manimgMouseExited

    private void assimgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assimgMouseEntered
         assistant.setText("ASSISTANT");
    }//GEN-LAST:event_assimgMouseEntered

    private void assimgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assimgMouseExited
       if(!assis){
           assistant.setText("");
       }
    }//GEN-LAST:event_assimgMouseExited

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
        }

    }//GEN-LAST:event_userFocusLost

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
        }

    }//GEN-LAST:event_passFocusLost

    private void shMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shMousePressed
        URL imageURL = singin.class.getResource("/image/view.png");
        ImageIcon icon= new ImageIcon(imageURL);
        sh.setIcon(icon);
        
        pass.setEchoChar((char)0);
    }//GEN-LAST:event_shMousePressed

    private void shMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shMouseReleased
        URL imageURL = singin.class.getResource("/image/hidden.png");
        ImageIcon icon= new ImageIcon(imageURL);
        sh.setIcon(icon);
        
        if(!"Enter Password".equals(pass.getText())){
            pass.setEchoChar('*');
        } 
    }//GEN-LAST:event_shMouseReleased

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
       
        if(adm || mang || assis){
            String pwd1;
            if(adm){
                 pwd1=(pass.getText());
            }else{
                 pwd1=hash(pass.getText());
            }
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn=(Connection) DriverManager.getConnection(dataConnd,usernamed,passwordd);
                pst=sqlConn.prepareStatement("SELECT username,password FROM "+table+" WHERE username=?");

                pst.setString(1,user.getText());
                rs=pst.executeQuery();
                if(rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Username not Found");

                }
                else{

                    if( pwd1.equals(rs.getString("password"))){

                        user1=user.getText();
                        URL imageURL = singin.class.getResource("/image/done.png");
                        ImageIcon icon= new ImageIcon(imageURL);
                        Image img=icon.getImage();
                        Image scaleimg= img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon doneic= new ImageIcon(scaleimg);
                        JOptionPane.showMessageDialog(this, "Login Successfull","Done",JOptionPane.INFORMATION_MESSAGE,doneic);
                        if(adm==true){
                            admHome ad= new admHome();
                            ad.setVisible(true);
                            this.hide();
                        }
                        if(mang==true){
                            managHome mn= new managHome();
                            mn.setVisible(true);
                            mn.mngAdd(user1);
                            this.hide();
                        }
                        if(assis==true){
                            AssisHome as= new AssisHome();
                            as.setVisible(true);
                            as.assAdd(user1);
                            this.hide();
                        }

                    }else{
                        JOptionPane.showMessageDialog(this, " incorrect Password ","Wrong!",JOptionPane.ERROR_MESSAGE);
                    } 
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "database error","Error!!!",JOptionPane.ERROR_MESSAGE);

            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please Select Actor😡😠","Warning!!!",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void adimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adimgMouseClicked
        adm=true;
        mang=false;
        assis=false;
    
        user1="Admin";
        na.setText("Login To Your Admin Account...");
        
        table="admin";
        
        URL imageURL = singin.class.getResource("/image/glows.jpeg");
        ImageIcon icon= new ImageIcon(imageURL);
        glowA.setIcon(icon);
        glowM.setIcon(null);
        glowAs.setIcon(null);
        
        admin.setText("ADMIN");
        manager.setText("");
        assistant.setText("");
        
    }//GEN-LAST:event_adimgMouseClicked

    private void manimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manimgMouseClicked
        adm=false;
        mang=true;
        assis=false;
    
        user1="Manager";
        na.setText("Login To Your Manager Account...");
        
        table="manager";
        
        URL imageURL = singin.class.getResource("/image/glows.jpeg");
        ImageIcon icon= new ImageIcon(imageURL);
        glowA.setIcon(null);
        glowM.setIcon(icon);
        glowAs.setIcon(null);
        
        admin.setText("");
        manager.setText("MANAGER");
        assistant.setText("");
    }//GEN-LAST:event_manimgMouseClicked

    private void assimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assimgMouseClicked
        adm=false;
        mang=false;
        assis=true;
    
        user1="Assistant";
        na.setText("Login To Your Assistant Account...");
        
        table="assistant";
        
        URL imageURL = singin.class.getResource("/image/glows.jpeg");
        ImageIcon icon= new ImageIcon(imageURL);
        glowA.setIcon(null);
        glowM.setIcon(null);
        glowAs.setIcon(icon);
        
        admin.setText("");
        manager.setText("");
        assistant.setText("ASSISTANT");
    }//GEN-LAST:event_assimgMouseClicked

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(adm || mang || assis){
                String pwd1;
                if(adm){
                     pwd1=(pass.getText());
                }else{
                     pwd1=hash(pass.getText());
                }
                try{
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn=(Connection) DriverManager.getConnection(dataConnd,usernamed,passwordd);
                pst=sqlConn.prepareStatement("SELECT username,password FROM "+table+" WHERE username=?");

                pst.setString(1,user.getText());
                rs=pst.executeQuery();
                if(rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Username not Found");

                }
                else{

                    if(pwd1 == null ? (rs.getString("password")) == null : pwd1.equals(rs.getString("password"))){

                        URL imageURL = singin.class.getResource("/image/done.png");
                        ImageIcon icon= new ImageIcon(imageURL);
                        Image img=icon.getImage();
                        Image scaleimg= img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon doneic= new ImageIcon(scaleimg);
                        JOptionPane.showMessageDialog(this, "Login Successfull","Done",JOptionPane.INFORMATION_MESSAGE,doneic);
                        if(adm==true){
                            admHome ad= new admHome();
                            ad.setVisible(true);
                            this.hide();
                        }
                        if(mang==true){
                            managHome mn= new managHome();
                            mn.setVisible(true);
                            mn.mngAdd(user1);
                            this.hide();
                        }
                        if(assis==true){
                            AssisHome as= new AssisHome();
                            as.setVisible(true);
                            as.assAdd(user1);
                            this.hide();
                        }

                    }else{
                        JOptionPane.showMessageDialog(this, " incorrect Password ","Wrong!",JOptionPane.ERROR_MESSAGE);
                    } 
                }

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this, "database error","Error!!!",JOptionPane.ERROR_MESSAGE);

                }
            }
            else{
            JOptionPane.showMessageDialog(this, "Please Select Actor😡😠","Warning!!!",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_passKeyPressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if(adm || mang || assis){
            if(!adm){
                frogot_password fp= new frogot_password();
                fp.setVisible(true);
                fp.set(user.getText(), table,user1);
            }
            else{
                JOptionPane.showMessageDialog(this, "You Can't change admin password!","Warning!!!",JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Please Select Actor😡😠","Warning!!!",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jLabel5MouseClicked

    
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
            java.util.logging.Logger.getLogger(singin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(singin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(singin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(singin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new singin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adimg;
    private javax.swing.JLabel admin;
    private javax.swing.JLabel assimg;
    private javax.swing.JLabel assistant;
    private javax.swing.JLabel cut;
    private javax.swing.JLabel glowA;
    private javax.swing.JLabel glowAs;
    private javax.swing.JLabel glowM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private image.jpanel jpanel2;
    private javax.swing.JLabel manager;
    private javax.swing.JLabel manimg;
    private javax.swing.JLabel na;
    private javax.swing.JPasswordField pass;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private javax.swing.JLabel sh;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

   
}
