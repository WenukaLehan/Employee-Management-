
package manager;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import login.singin;
import manager.cell.TableActionCellEditor;
import manager.cell.TableActionCellRender;
import manager.cell.TableActionEvent;


public class managHome extends javax.swing.JFrame {

    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/employee";
    
    Connection sqlConn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    int n1,n2;
    
    boolean f1 =false,bool=true;
    
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    String manager;
    
   
    public managHome() {
        initComponents();
        maleE.setActionCommand("Male");
        femaleE.setActionCommand("Female");
    }
    
    
    
    public void cell(){
        
        TableActionEvent event;
        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                edit ed= new edit();
                DefaultTableModel model=(DefaultTableModel)searchT.getModel();
                String num=String.valueOf(model.getValueAt(searchT.getSelectedRow(), 1));
                String nicc=String.valueOf(model.getValueAt(searchT.getSelectedRow(), 2));
                ed.setVisible(true);
                ed.load(nicc, num, manager);
                
            }

            @Override
            public void onDelete(int row) {
                String mal=null;
                int p=JOptionPane.showConfirmDialog(null, "confirm delete", "confirm", JOptionPane.YES_NO_OPTION);
                if(p==0){
                    DefaultTableModel model=(DefaultTableModel)searchT.getModel();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        sqlConn=DriverManager.getConnection(dataConn,username,password);
                        pst=sqlConn.prepareStatement("SELECT * FROM employe WHERE nic=?");
                        pst.setString(1, String.valueOf(model.getValueAt(searchT.getSelectedRow(), 2)));
                        rs=pst.executeQuery();
                        if(rs.next()){
                            mal=rs.getString("email");
                        }
                        pst.close();
                        pst=sqlConn.prepareStatement("DELETE FROM employe WHERE nic=?");
                        pst.setString(1, String.valueOf(model.getValueAt(searchT.getSelectedRow(), 2)));
                        pst.executeUpdate();
                        pst.close();
                        
                        
                        sendmail(mal,"Your Acount has been deleted from colombo institute of studiws","Your Account has been deleted");

                        JOptionPane.showMessageDialog(null, "Employee Removed  Successfully");


                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Database Error");

                    }

                    if (searchT.isEditing()) {
                        searchT.getCellEditor().stopCellEditing();
                    }
                    model.removeRow(row);
                }
            }

            @Override
            public void onView(int row) {
                view vd= new view();
                DefaultTableModel model=(DefaultTableModel)searchT.getModel();
                String num=String.valueOf(model.getValueAt(searchT.getSelectedRow(), 1));
                String nicc=String.valueOf(model.getValueAt(searchT.getSelectedRow(), 2));
                vd.setVisible(true);
                vd.load(nicc, num, manager);
                
            }
            
            @Override
            public void onView2(){
                inf.setForeground(new Color(0,0,255));
                inf.setText("View Data");
            }
            
            @Override
            public void onEdit2(){
                inf.setForeground(new Color(0,255,0));
                inf.setText("Update Details");
            }
            
            @Override
            public void onDelete2(){
                inf.setForeground(new Color(255,0,0));
                inf.setText("Remove Employee");
            }

            @Override
            public void onView3() {
            
                inf.setText("");
            }

            @Override
            public void onDelete3() {
            
                inf.setText("");
            }

            @Override
            public void onEdit3() {
            
                inf.setText("");
            }
            
        };
        searchT.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        searchT.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
        searchT.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
        
    }

    
    
    public void sendmail(String mail,String msg,String sub){
        
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
            message.setSubject(sub);
            message.setText(msg);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "message sent");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    public void search(String str){
         DefaultTableModel tblModel=(DefaultTableModel)searchT.getModel();
         TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(tblModel);
         searchT.setRowSorter(trs);
         trs.setRowFilter(RowFilter.regexFilter(str));
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
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
        f1=false;
    }
    
    public void viewED(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT depName FROM department ");
            rs=pst.executeQuery();
            while(rs.next()){
                
                String dep=rs.getString("depName");
                depE.addItem(dep);
            }
            pst.close();
            
            pst=sqlConn.prepareStatement("SELECT desName FROM designation ");
            rs=pst.executeQuery();
            while(rs.next()){
                
                String des=rs.getString("desName");
                desE.addItem(des);
            }
            pst.close();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
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
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
    }
    
    public void viewDep(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT depNo,depName,location FROM department ");
            rs=pst.executeQuery();
            DefaultTableModel tbModel=(DefaultTableModel)tableDep.getModel();
            tbModel.setRowCount(0);
            while(rs.next()){
                String depNoT=String.valueOf(rs.getInt("depNo"));
                String depNameT=rs.getString("depName");
                String locationT=rs.getString("location");
                String tbData[]={depNoT,depNameT,locationT};
                tbModel.addRow(tbData);
            }
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
    }
    
    public void viewDes(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT desID,desName,type FROM designation ");
            rs=pst.executeQuery();
            DefaultTableModel tbModel=(DefaultTableModel)tableDes.getModel();
            tbModel.setRowCount(0);
            while(rs.next()){
                String ID=String.valueOf(rs.getInt("desID"));
                String name=rs.getString("desName");
                String type=rs.getString("type");
                String tbData[]={ID,name,type};
                tbModel.addRow(tbData);
            }
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
    }
    
    public void clearE(){
        nameE.setText("");
        nicE.setText("");
        phoneE.setText("");
        emailE.setText("");
        addressE.setText("");
        previewE.setText("");
        dobE.setDate(null);
        buttonGroup1.clearSelection();
        submitE.setEnabled(true);
    }
    
    public void clearDep(){
        depName.setText("");
        depLoc.setText("");
        
    }
    
    public void clearDes(){
        desName.setText("");
        desType.setSelectedIndex(0);
        
    }
    
    public String epfGenarate(String dep){
        String ID=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst=sqlConn.prepareStatement("SELECT * FROM employe ORDER BY empID DESC LIMIT 1");
            rs=pst.executeQuery();
            
            if(rs.next()){
                String num=String.valueOf(1+rs.getInt("empID"));
                ID = "E/"+dep.substring(0, 2)+"/"+num;
                
            }else{
                
                ID = "E/"+dep.substring(0, 2)+"/01";
            }
            
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Database Connection Error","error",JOptionPane.ERROR);
        }
        return ID;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cut = new javax.swing.JButton();
        mini = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        home = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addemplo = new image.jpanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addDepa = new image.jpanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addDesigna = new image.jpanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        searchEmp = new image.jpanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        addEmployee = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        addressE = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        nicE = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        phoneE = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        emailE = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        nameE = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        femaleE = new javax.swing.JRadioButton();
        maleE = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        dobE = new com.toedter.calendar.JDateChooser();
        clearE = new rojerusan.RSMaterialButtonRectangle();
        submitE = new rojerusan.RSMaterialButtonRectangle();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        previewE = new javax.swing.JTextPane();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        depE = new javax.swing.JComboBox<>();
        desE = new javax.swing.JComboBox<>();
        addDep = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        depLoc = new javax.swing.JTextField();
        depName = new javax.swing.JTextField();
        clearDep = new rojerusan.RSMaterialButtonRectangle();
        submitDep = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDep = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        addDesig = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        desName = new javax.swing.JTextField();
        clearDes = new rojerusan.RSMaterialButtonRectangle();
        submitDes = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDes = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        desType = new javax.swing.JComboBox<>();
        search = new javax.swing.JPanel();
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
        setTitle("manager");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/project-management (1).png"))); // NOI18N
        jLabel1.setText("Manager Potral");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 180, 30));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Black logo - no background.png"))); // NOI18N
        jLabel7.setText("Colombo Institute of studies");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

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
        jLabel9.setText("Manager Dashboard");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, 30));

        addemplo.setBackground(new java.awt.Color(102, 102, 255));
        addemplo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Add Employee");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addemplo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile (1) (1).png"))); // NOI18N
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        addemplo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 90));

        jPanel6.add(addemplo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 190, 100));

        addDepa.setBackground(new java.awt.Color(102, 102, 255));
        addDepa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/network (1).png"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        addDepa.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 90));

        jLabel5.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Add Department");
        addDepa.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 180, 30));

        jPanel6.add(addDepa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 190, 100));

        addDesigna.setBackground(new java.awt.Color(102, 102, 255));
        addDesigna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/design-thinking (1).png"))); // NOI18N
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        addDesigna.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 90));

        jLabel12.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Add Designation");
        addDesigna.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 180, 40));

        jPanel6.add(addDesigna, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 190, 100));

        searchEmp.setBackground(new java.awt.Color(102, 102, 255));
        searchEmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/research (1).png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        searchEmp.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 90));

        jLabel14.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Search Employee");
        searchEmp.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 30));

        jPanel6.add(searchEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 190, 100));

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", home);

        addEmployee.setMinimumSize(new java.awt.Dimension(1000, 500));
        addEmployee.setPreferredSize(new java.awt.Dimension(1000, 500));
        addEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow (1).png"))); // NOI18N
        jLabel15.setText("Back..");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("New employee registation");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Gender:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 70, 30));

        addressE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        addressE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addressEKeyReleased(evt);
            }
        });
        jPanel4.add(addressE, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 260, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("NIC :");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 60, 30));

        nicE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        nicE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nicEKeyReleased(evt);
            }
        });
        jPanel4.add(nicE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 260, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("PhoneNo:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 70, 30));

        phoneE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        phoneE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneEKeyReleased(evt);
            }
        });
        jPanel4.add(phoneE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 260, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Email:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 60, 30));

        emailE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        emailE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailEFocusLost(evt);
            }
        });
        emailE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailEKeyReleased(evt);
            }
        });
        jPanel4.add(emailE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 260, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Department:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 90, 30));

        nameE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        nameE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameEKeyReleased(evt);
            }
        });
        jPanel4.add(nameE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 260, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("DOB :");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 60, 30));

        buttonGroup1.add(femaleE);
        femaleE.setForeground(new java.awt.Color(0, 0, 0));
        femaleE.setText("Female");
        jPanel4.add(femaleE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 70, 30));

        buttonGroup1.add(maleE);
        maleE.setForeground(new java.awt.Color(0, 0, 0));
        maleE.setText("Male");
        jPanel4.add(maleE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 60, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Preview:");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, -1, 30));

        dobE.setDateFormatString("yyyy-MM-dd");
        dobE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobEFocusLost(evt);
            }
        });
        jPanel4.add(dobE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 260, 30));

        clearE.setBackground(new java.awt.Color(255, 102, 102));
        clearE.setText("Clear");
        clearE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearEActionPerformed(evt);
            }
        });
        jPanel4.add(clearE, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 110, 50));

        submitE.setText("Submit");
        submitE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitEActionPerformed(evt);
            }
        });
        jPanel4.add(submitE, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 110, 50));

        jLabel25.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Full Name:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        previewE.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        previewE.setFocusable(false);
        jScrollPane1.setViewportView(previewE);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 450, 160));

        jLabel26.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Address:");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 70, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Designation:");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 90, 30));

        jPanel4.add(depE, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 260, 30));

        jPanel4.add(desE, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 260, 30));

        addEmployee.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 440));

        jTabbedPane1.addTab("tab2", addEmployee);

        addDep.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow (1).png"))); // NOI18N
        jLabel20.setText("Back..");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel28.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("ADD department");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jLabel30.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Location:");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 70, 30));

        depLoc.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel5.add(depLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 210, 30));

        depName.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel5.add(depName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 210, 30));

        clearDep.setBackground(new java.awt.Color(255, 102, 102));
        clearDep.setText("Clear");
        clearDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearDepActionPerformed(evt);
            }
        });
        jPanel5.add(clearDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 110, 50));

        submitDep.setText("Submit");
        submitDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitDepActionPerformed(evt);
            }
        });
        jPanel5.add(submitDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 110, 50));

        tableDep.setBorder(new javax.swing.border.MatteBorder(null));
        tableDep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DepNo", "DepName", "Location"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableDep);
        if (tableDep.getColumnModel().getColumnCount() > 0) {
            tableDep.getColumnModel().getColumn(0).setHeaderValue("Des No");
            tableDep.getColumnModel().getColumn(1).setHeaderValue("Name");
            tableDep.getColumnModel().getColumn(2).setHeaderValue("Type");
        }

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 300, 110));

        jLabel37.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Dep Name:");
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 80, 30));

        jLabel38.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Registerd Department:");
        jPanel5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 140, 30));

        addDep.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 465));

        jTabbedPane1.addTab("tab3", addDep);

        addDesig.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow (1).png"))); // NOI18N
        jLabel29.setText("Back..");
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel31.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("ADD designation");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jLabel32.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Designation Type:");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 130, 30));

        desName.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel9.add(desName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 210, 30));

        clearDes.setBackground(new java.awt.Color(255, 102, 102));
        clearDes.setText("Clear");
        clearDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearDesActionPerformed(evt);
            }
        });
        jPanel9.add(clearDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 110, 50));

        submitDes.setText("Submit");
        submitDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitDesActionPerformed(evt);
            }
        });
        jPanel9.add(submitDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 110, 50));

        tableDes.setBorder(new javax.swing.border.MatteBorder(null));
        tableDes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Des No", "Name", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableDes);
        if (tableDes.getColumnModel().getColumnCount() > 0) {
            tableDes.getColumnModel().getColumn(0).setHeaderValue("Des No");
            tableDes.getColumnModel().getColumn(1).setHeaderValue("Name");
            tableDes.getColumnModel().getColumn(2).setHeaderValue("Type");
        }

        jPanel9.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 300, 110));

        jLabel39.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Designation Name:");
        jPanel9.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 130, 30));

        jLabel40.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Registerd Designation:");
        jPanel9.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 150, 30));

        desType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "hard", "easy", "meadium", "skillfull" }));
        jPanel9.add(desType, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 210, 30));

        addDesig.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 465));

        jTabbedPane1.addTab("tab4", addDesig);

        search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        searchT.setRowHeight(36);
        searchT.setSelectionBackground(new java.awt.Color(102, 102, 255));
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
        jPanel11.add(inf, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, -1, -1));

        search.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 465));

        jTabbedPane1.addTab("tab5", search);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1000, 500));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1000, 470));

        setSize(new java.awt.Dimension(1000, 474));
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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        jTabbedPane1.setSelectedIndex(2);
        viewDep();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        jTabbedPane1.setSelectedIndex(1);
        viewED();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        jTabbedPane1.setSelectedIndex(3);
        viewDes();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        jTabbedPane1.setSelectedIndex(4);
        cell();
        loadAll();
        depdesViewS();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        int p=JOptionPane.showConfirmDialog(null , "Are you sure exit?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(p==0){
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void addressEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressEKeyReleased
        if(maleE.isSelected()==true ||femaleE.isSelected()==true){
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText()+"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }else{
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText() +"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_addressEKeyReleased

    private void nicEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nicEKeyReleased
        if(maleE.isSelected()==true ||femaleE.isSelected()==true){
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText()+"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }else{
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText() +"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_nicEKeyReleased

    private void phoneEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneEKeyReleased
        if(maleE.isSelected()==true ||femaleE.isSelected()==true){
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText()+"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }else{
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText() +"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_phoneEKeyReleased

    private void emailEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailEFocusLost

        if(!"".equals(emailE.getText())){
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(emailE.getText()).matches()){

                submitE.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Wrong Email ID Please Check !!","Warning",JOptionPane.WARNING_MESSAGE);
                submitE.setEnabled(false);
            }
        }
    }//GEN-LAST:event_emailEFocusLost

    private void emailEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailEKeyReleased
        if(!"".equals(emailE.getText())){
            Pattern pattern= Pattern.compile(emailRegex);
            if(pattern.matcher(emailE.getText()).matches()){

                if(maleE.isSelected()==true ||femaleE.isSelected()==true){
                    previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                        "\nAddress: "+addressE.getText()+"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
                }else{
                    previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                        "\nAddress: "+addressE.getText() +"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
                }

            }
        }
    }//GEN-LAST:event_emailEKeyReleased

    private void nameEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameEKeyReleased
        if(maleE.isSelected()==true ||femaleE.isSelected()==true){
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText()+"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }else{
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText() + "\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+"\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_nameEKeyReleased

    private void dobEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobEFocusLost
        if(maleE.isSelected()==true ||femaleE.isSelected()==true){
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText()+"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nGender: "+buttonGroup1.getSelection().getActionCommand() + "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }else{
            previewE.setText("Name: "+nameE.getText()+ "\nNIC: "+nicE.getText()+ "\nPhoneNo: "+phoneE.getText() +"\nEmail: "+emailE.getText()+
                "\nAddress: "+addressE.getText() +"\nDepartment: "+depE.getSelectedItem().toString()+"\nDesignation: "+desE.getSelectedItem().toString()+ "\nDOB: "+((JTextField)dobE.getDateEditor().getUiComponent()).getText() );
        }
    }//GEN-LAST:event_dobEFocusLost

    private void clearEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearEActionPerformed
        clearE();

    }//GEN-LAST:event_clearEActionPerformed

    private void submitEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEActionPerformed
        if("".equals(nameE.getText()) || "".equals(nicE.getText()) || "".equals(phoneE.getText()) 
            || "".equals(emailE.getText()) || "".equals(addressE.getText()) || "".equals(dobE.getDate()) || maleE.isSelected()==false && femaleE.isSelected()==false ){

            JOptionPane.showMessageDialog(null, "Please fill all !!","Warning",JOptionPane.QUESTION_MESSAGE);

        }
        else
        {
            String epf= epfGenarate(depE.getSelectedItem().toString());
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn=DriverManager.getConnection(dataConn, username, password);
                pst=sqlConn.prepareStatement("insert into employe(name,nic,phoneNo,dob,email,gender,address,epfNo,designation,department)values(?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, nameE.getText());
                pst.setString(2,nicE.getText());
                pst.setString(3,phoneE.getText());
                pst.setString(4,((JTextField)dobE.getDateEditor().getUiComponent()).getText());
                pst.setString(5,emailE.getText());
                pst.setString(6,buttonGroup1.getSelection().getActionCommand());
                pst.setString(7,addressE.getText());
                pst.setString(8,epf);
                pst.setString(9,desE.getSelectedItem().toString());
                pst.setString(10,depE.getSelectedItem().toString());
                
                pst.executeUpdate();
                sendmail(emailE.getText(),"You are registerd to COLOMBO INSTITUTE OF STUDIES as employee\nYou are working on "+depE.getSelectedItem().toString()+" \nwith this job role "+desE.getSelectedItem().toString()+"\n\nEPF Nomber is:-"+epf+"\n\nthanks for joingin us work good...","Successfully Registerd...");

                JOptionPane.showMessageDialog(this, "Registerd successfully\nepf number has sent to email..");
                clearE();
                

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_submitEActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        int p=JOptionPane.showConfirmDialog(null , "Are you sure exit?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(p==0){
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void clearDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearDepActionPerformed
        clearDep();

    }//GEN-LAST:event_clearDepActionPerformed

    private void submitDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitDepActionPerformed
        if("".equals(depName.getText()) || "".equals(depLoc.getText()) ){

            JOptionPane.showMessageDialog(null, "Please fill all !!","Warning",JOptionPane.QUESTION_MESSAGE);

        }
        else
        {
            

            try{
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn=DriverManager.getConnection(dataConn, username, password);
                pst=sqlConn.prepareStatement("insert into department(depName,location)values(?,?)");
                pst.setString(1, depName.getText());
                pst.setString(2,depLoc.getText());
                

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Department Added Successfully");
                clearDep();
                viewDep();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Databese Error");
            }
        }
    }//GEN-LAST:event_submitDepActionPerformed

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        int p=JOptionPane.showConfirmDialog(null , "Are you sure exit?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(p==0){
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jLabel29MouseClicked

    private void clearDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearDesActionPerformed
        clearDes();
    }//GEN-LAST:event_clearDesActionPerformed

    private void submitDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitDesActionPerformed
        if("".equals(desName.getText()) ){

            JOptionPane.showMessageDialog(null, "Please fill all !!","Warning",JOptionPane.QUESTION_MESSAGE);

        }
        else
        {
            

            try{
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn=DriverManager.getConnection(dataConn, username, password);
                pst=sqlConn.prepareStatement("insert into designation(desName,type)values(?,?)");
                pst.setString(1, desName.getText());
                pst.setString(2,desType.getSelectedItem().toString());
                

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Designation Added  Successfully");
                clearDes();
                viewDes();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Databese Error");
            }
        }
    }//GEN-LAST:event_submitDesActionPerformed

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        int p=JOptionPane.showConfirmDialog(null, "Are You sure exit?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(p==0){
            jTabbedPane1.setSelectedIndex(0);
        }
        
    }//GEN-LAST:event_jLabel33MouseClicked

    private void searchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBActionPerformed

        search("");
        loadAll();
        depdesViewS();
    }//GEN-LAST:event_searchBActionPerformed

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

    private void BnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BnameKeyReleased
        search(Bname.getText());
    }//GEN-LAST:event_BnameKeyReleased

    private void BepfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BepfKeyReleased
        search(Bepf.getText());
    }//GEN-LAST:event_BepfKeyReleased

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
            java.util.logging.Logger.getLogger(managHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(managHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(managHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(managHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new managHome().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bepf;
    private javax.swing.JTextField Bname;
    private javax.swing.JPanel addDep;
    private image.jpanel addDepa;
    private javax.swing.JPanel addDesig;
    private image.jpanel addDesigna;
    private javax.swing.JPanel addEmployee;
    private image.jpanel addemplo;
    private javax.swing.JTextField addressE;
    private javax.swing.ButtonGroup buttonGroup1;
    private rojerusan.RSMaterialButtonRectangle clearDep;
    private rojerusan.RSMaterialButtonRectangle clearDes;
    private rojerusan.RSMaterialButtonRectangle clearE;
    private javax.swing.JButton cut;
    private javax.swing.JComboBox<String> depE;
    private javax.swing.JTextField depLoc;
    private javax.swing.JTextField depName;
    private javax.swing.JComboBox<String> depS;
    private javax.swing.JComboBox<String> desE;
    private javax.swing.JTextField desName;
    private javax.swing.JComboBox<String> desS;
    private javax.swing.JComboBox<String> desType;
    private com.toedter.calendar.JDateChooser dobE;
    private javax.swing.JTextField emailE;
    private javax.swing.JRadioButton femaleE;
    private javax.swing.JPanel home;
    private javax.swing.JLabel inf;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton maleE;
    private javax.swing.JButton mini;
    private javax.swing.JTextField nameE;
    private javax.swing.JTextField nicE;
    private javax.swing.JTextField phoneE;
    private javax.swing.JTextPane previewE;
    private javax.swing.JPanel search;
    private javax.swing.JButton searchB;
    private image.jpanel searchEmp;
    private javax.swing.JTable searchT;
    private rojerusan.RSMaterialButtonRectangle submitDep;
    private rojerusan.RSMaterialButtonRectangle submitDes;
    private rojerusan.RSMaterialButtonRectangle submitE;
    private javax.swing.JTable tableDep;
    private javax.swing.JTable tableDes;
    // End of variables declaration//GEN-END:variables

    public void mngAdd(String user1) {

        manager=user1;
    }
}
