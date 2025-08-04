
package assistent;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import login.singin;
import assistent.cell.TableActionCellEditor;
import assistent.cell.TableActionCellRender;
import assistent.cell.TableActionEvent;
import javax.swing.JFrame;


public class AssisHome extends javax.swing.JFrame {

    String assis;
    
    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    int n1,n2;
    
    boolean f1 =false;
    
    public AssisHome() {
        initComponents();
    }
    
    public void assAdd(String user1) {

        assis=user1;
    }
    
    
    public void cell(){
        
        TableActionEvent event;
        event = new TableActionEvent() {
            
            @Override
            public void onView(int row) {
                view vd= new view();
                DefaultTableModel model=(DefaultTableModel)searchT.getModel();
                String num=String.valueOf(model.getValueAt(searchT.getSelectedRow(), 1));
                String nicc=String.valueOf(model.getValueAt(searchT.getSelectedRow(), 2));
                vd.setVisible(true);
                vd.load(nicc, num,assis);

                
            }
            
            @Override
            public void onView2(){
                inf.setForeground(new Color(0,0,255));
                inf.setText("View Data");
            }
            
            @Override
            public void onView3() {
            
                inf.setText("");
            }
            
        };
        searchT.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        searchT.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
        searchT.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.CENTER);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
            });
        
        
        
    }

    
    
    
    public void depdesViewS(){
        try{
            f1=true;
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT depName FROM department ");
            rs=pst.executeQuery();
            depS.removeAllItems();
            depS.addItem("All");
            while(rs.next()){
                
                String dep=rs.getString("depName");
                depS.addItem(dep);
            }
            pst.close();
            
            
            pst=sqlConn.prepareStatement("SELECT desName FROM designation ");
            rs=pst.executeQuery();
            desS.removeAllItems();
            desS.addItem("All");
            while(rs.next()){
                
                String des=rs.getString("desName");
                desS.addItem(des);
            }
            pst.close();
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR_MESSAGE);
        }
        f1=false;
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
    public void search(String str){
         DefaultTableModel tblModel=(DefaultTableModel)searchT.getModel();
         TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(tblModel);
         searchT.setRowSorter(trs);
         trs.setRowFilter(RowFilter.regexFilter(str));
        }
    
     public void loadAll(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT * FROM employe ");
            rs=pst.executeQuery();
            DefaultTableModel tbModel=(DefaultTableModel)searchT.getModel();
            tbModel.setRowCount(0);
            while(rs.next()){
                String ID=String.valueOf(rs.getInt("empID"));
                String name=rs.getString("name");
                String nic=rs.getString("nic");
                String dep=rs.getString("department");
                String des=rs.getString("designation");
                String epf=rs.getString("epfNo");
                String phone=rs.getString("phoneNo");
                String tbData[]={ID,name,nic,phone,des,dep,epf};
                tbModel.addRow(tbData);
            }
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        assistantD = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        searchT = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        Bname = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        Bepf = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        desS = new javax.swing.JComboBox<>();
        depS = new javax.swing.JComboBox<>();
        searchB = new javax.swing.JButton();
        inf = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("assistant");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/setting (1).png"))); // NOI18N
        jLabel1.setText("Assistant Potral");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 180, 30));

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
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

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

        jLabel6.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Search Employee");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managerAdd.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 180, 30));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile (1) (1).png"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        managerAdd.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 90));

        jPanel6.add(managerAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 190, 100));

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

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow (1).png"))); // NOI18N
        jLabel33.setText("Back..");
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel34.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("search employees");
        jPanel12.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        searchT.setAutoCreateRowSorter(true);
        searchT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "EmpID", "Name", "NIC", "PhoneNo", "Designation", "Department", "EPF No", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        searchT.setCellSelectionEnabled(true);
        searchT.setRowHeight(34);
        searchT.setSelectionBackground(new java.awt.Color(102, 102, 255));
        searchT.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(searchT);

        jPanel11.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 960, 280));

        jLabel41.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("By Name:");
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 130, 30));

        Bname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        Bname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BnameKeyReleased(evt);
            }
        });
        jPanel11.add(Bname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 210, 30));

        jLabel42.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("By EPF No:");
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 130, 30));

        Bepf.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        Bepf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BepfKeyReleased(evt);
            }
        });
        jPanel11.add(Bepf, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 210, 30));

        jLabel43.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("By Department:");
        jPanel11.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 130, 30));

        jLabel44.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("By Designation:");
        jPanel11.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 130, 30));

        desS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        desS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desSActionPerformed(evt);
            }
        });
        jPanel11.add(desS, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 210, 30));

        depS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        depS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depSActionPerformed(evt);
            }
        });
        jPanel11.add(depS, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 210, 30));

        searchB.setBackground(new java.awt.Color(0, 0, 255));
        searchB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchB.setText("Load All");
        searchB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        searchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBActionPerformed(evt);
            }
        });
        jPanel11.add(searchB, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, -1, -1));

        inf.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        inf.setText("jLabel4");
        jPanel11.add(inf, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, -1, -1));

        javax.swing.GroupLayout assistantDLayout = new javax.swing.GroupLayout(assistantD);
        assistantD.setLayout(assistantDLayout);
        assistantDLayout.setHorizontalGroup(
            assistantDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(assistantDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(assistantDLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        assistantDLayout.setVerticalGroup(
            assistantDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
            .addGroup(assistantDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(assistantDLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", assistantD);

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

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        jTabbedPane1.setSelectedIndex(1);
        cell();
        loadAll();
        depdesViewS();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int o= JOptionPane.showConfirmDialog(null, "Are you sure logout?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(o==0){
            singin sing= new singin();
            sing.setVisible(true);
            this.hide();
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        jTabbedPane1.setSelectedIndex(0);
        
    }//GEN-LAST:event_jLabel33MouseClicked

    private void BnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BnameKeyReleased
        search(Bname.getText());
    }//GEN-LAST:event_BnameKeyReleased

    private void BepfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BepfKeyReleased
        search(Bepf.getText());
    }//GEN-LAST:event_BepfKeyReleased

    private void desSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desSActionPerformed
        if(f1){

        }else{
            if(desS.getSelectedItem().toString()=="All"){
                search("");
            }else{
                search(desS.getSelectedItem().toString());
            }

        }
    }//GEN-LAST:event_desSActionPerformed

    private void depSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depSActionPerformed

        if(f1){

        }else{
            if(depS.getSelectedItem().toString()=="All"){
                search("");
            }else{
                search(depS.getSelectedItem().toString());
            }

        }

    }//GEN-LAST:event_depSActionPerformed

    private void searchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBActionPerformed

        search("");
        loadAll();
        depdesViewS();
    }//GEN-LAST:event_searchBActionPerformed

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
            java.util.logging.Logger.getLogger(AssisHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssisHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssisHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssisHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssisHome().setVisible(true);
            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bepf;
    private javax.swing.JTextField Bname;
    private javax.swing.JPanel assistantD;
    private javax.swing.JButton cut;
    private javax.swing.JComboBox<String> depS;
    private javax.swing.JComboBox<String> desS;
    private javax.swing.JLabel inf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private image.jpanel managerAdd;
    private javax.swing.JButton mini;
    private javax.swing.JButton searchB;
    private javax.swing.JTable searchT;
    // End of variables declaration//GEN-END:variables
}
