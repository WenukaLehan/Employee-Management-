
package manager;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class edit extends javax.swing.JFrame {

    
    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    int n1,n2;
    
    String emailN,managerN,nicS,nameS;
    
    public edit() {
        initComponents();
        male.setActionCommand("Male");
        female.setActionCommand("Female");
    }
    
    public void load(String nic1,String name1,String mangr){
        managerN=mangr;
        nicS=nic1;
        nameS=name1;
        topic.setText(name1+"(change)");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT * FROM employe WHERE nic="+nic1);
            //pst.setString(1, nic1);
            rs=pst.executeQuery();
            if(rs.next()){
                emailN=rs.getString("email");
                String gender=rs.getString("gender");
                name.setText(rs.getString("name"));
                nic.setText(rs.getString("nic"));
                phone.setText(rs.getString("phoneNo"));
                dob.setText(rs.getString("DOB"));
                email.setText(rs.getString("email"));
                address.setText(rs.getString("address"));
                dep.setText(rs.getString("department"));
                des.setText(rs.getString("designation"));
                
                if(gender.equals("Male")){
                    male.setSelected(true);
                    
                }else if(gender.equals("Female")){
                    female.setSelected(true);
                }
                
            }
            
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex+"\nDatabase Connection Error","error",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        mini = new javax.swing.JButton();
        cut = new javax.swing.JButton();
        topic = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        des = new image.TextField();
        name = new image.TextField();
        nic = new image.TextField();
        dob = new image.TextField();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        textField6 = new image.TextField();
        address = new image.TextField();
        phone = new image.TextField();
        dep = new image.TextField();
        email = new image.TextField();
        update = new rojerusan.RSMaterialButtonRectangle();
        reset = new rojerusan.RSMaterialButtonRectangle();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("editP"); // NOI18N
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

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setText("Name:");
        jTextField2.setFocusable(false);
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 28));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.setText("NIC:");
        jTextField3.setFocusable(false);
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, 28));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(0, 0, 0));
        jTextField4.setText("DOB:");
        jTextField4.setFocusable(false);
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 28));

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(0, 0, 0));
        jTextField9.setText("Email:");
        jTextField9.setFocusable(false);
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 28));

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(0, 0, 0));
        jTextField11.setText("Address:");
        jTextField11.setFocusable(false);
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, 28));

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(255, 255, 255));
        jTextField15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(0, 0, 0));
        jTextField15.setText("PhoneNo:");
        jTextField15.setFocusable(false);
        jPanel1.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, 28));

        jTextField21.setEditable(false);
        jTextField21.setBackground(new java.awt.Color(255, 255, 255));
        jTextField21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField21.setForeground(new java.awt.Color(0, 0, 0));
        jTextField21.setText("Department:");
        jTextField21.setFocusable(false);
        jPanel1.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, 28));

        jTextField19.setEditable(false);
        jTextField19.setBackground(new java.awt.Color(255, 255, 255));
        jTextField19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField19.setForeground(new java.awt.Color(0, 0, 0));
        jTextField19.setText("Designation:");
        jTextField19.setFocusable(false);
        jPanel1.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, -1, 28));

        jTextField13.setEditable(false);
        jTextField13.setBackground(new java.awt.Color(255, 255, 255));
        jTextField13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(0, 0, 0));
        jTextField13.setText("Gender:");
        jTextField13.setFocusable(false);
        jPanel1.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, 28));

        des.setForeground(new java.awt.Color(0, 0, 0));
        des.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        des.setShadowColor(new java.awt.Color(0, 0, 255));
        jPanel1.add(des, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 200, 38));

        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        name.setShadowColor(new java.awt.Color(0, 0, 255));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 200, 38));

        nic.setForeground(new java.awt.Color(0, 0, 0));
        nic.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jPanel1.add(nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 200, 38));

        dob.setForeground(new java.awt.Color(0, 0, 0));
        dob.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        dob.setShadowColor(new java.awt.Color(0, 0, 255));
        jPanel1.add(dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 200, 38));

        buttonGroup1.add(male);
        male.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        male.setForeground(new java.awt.Color(0, 0, 0));
        male.setText("Male");
        jPanel1.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 75, -1, -1));

        buttonGroup1.add(female);
        female.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        female.setForeground(new java.awt.Color(0, 0, 0));
        female.setText("Female");
        jPanel1.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 75, -1, -1));

        textField6.setEditable(false);
        textField6.setEnabled(false);
        textField6.setFocusable(false);
        jPanel1.add(textField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 200, 35));

        address.setForeground(new java.awt.Color(0, 0, 0));
        address.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        address.setShadowColor(new java.awt.Color(0, 0, 255));
        jPanel1.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 200, 38));

        phone.setForeground(new java.awt.Color(0, 0, 0));
        phone.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        phone.setShadowColor(new java.awt.Color(0, 0, 255));
        jPanel1.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 200, 38));

        dep.setForeground(new java.awt.Color(0, 0, 0));
        dep.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        dep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depActionPerformed(evt);
            }
        });
        jPanel1.add(dep, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 200, 38));

        email.setForeground(new java.awt.Color(0, 0, 0));
        email.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 200, 38));

        update.setBackground(new java.awt.Color(255, 102, 51));
        update.setText("update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 160, 50));

        reset.setBackground(new java.awt.Color(255, 102, 51));
        reset.setText("reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 160, 50));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editE (1).gif"))); // NOI18N
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

    private void miniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseEntered
        mini.setBackground(Color.BLUE);
    }//GEN-LAST:event_miniMouseEntered

    private void miniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseExited
        mini.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_miniMouseExited

    private void miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseClicked
        mini.setBackground(Color.BLUE);
        this.setExtendedState(JFrame.ICONIFIED);
       
    }//GEN-LAST:event_miniMouseClicked

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

    private void depActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        load(nicS,nameS,managerN);
    }//GEN-LAST:event_resetActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("UPDATE employe SET name=?,nic=?,department=?,designation=?,address=?,DOB=?,email=?,gender=?,phoneNo=? WHERE nic="+nicS);
            pst.setString(1, name.getText());
            pst.setString(2, nic.getText());
            pst.setString(3, dep.getText());
            pst.setString(4, des.getText());
            pst.setString(5, address.getText());
            pst.setString(6, dob.getText());
            pst.setString(7, email.getText());
            pst.setString(8, buttonGroup1.getSelection().getActionCommand());
            pst.setString(9, phone.getText());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee Updated Successfully");
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_updateActionPerformed

    
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
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new edit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private image.TextField address;
    private javax.swing.JLabel background;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cut;
    private image.TextField dep;
    private image.TextField des;
    private image.TextField dob;
    private image.TextField email;
    private javax.swing.JRadioButton female;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JRadioButton male;
    private javax.swing.JButton mini;
    private image.TextField name;
    private image.TextField nic;
    private image.TextField phone;
    private rojerusan.RSMaterialButtonRectangle reset;
    private image.TextField textField6;
    private javax.swing.JLabel topic;
    private rojerusan.RSMaterialButtonRectangle update;
    // End of variables declaration//GEN-END:variables
}
