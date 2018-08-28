/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floreantpos.inventory;

import static com.floreantpos.inventory.Locations.buildTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class Items extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

    PreparedStatement p;
    int visible;

    public void FillTable() {
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_ITEM";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // It creates and displays the table
//          JTable table = new JTable(buildTableModel(rs));
            jTable1.setModel(buildTableModel(rs));

        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }
    }
public void Search() {
        try {
            String name = jTextField12.getText(), phone = jTextField11.getText();
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_ITEM WHERE NAME LIKE '%"+name+"%' OR DESCRIPTION LIKE '"+phone+"'";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            // It creates and displays the table
//          JTable table = new JTable(buildTableModel(rs));
            jTable1.setModel(buildTableModel(rs));

        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }
    }
    public void FillCombo() {
        //Fill package unit
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM PACKAGING_UNIT";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString(2));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 2" + e);
        }
        //Fill recipe unit
        try {

            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM RECEPIE_UNIT";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox2.addItem(rs.getString(2));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 2" + e);
        }
        //Fill group unit
        try {

            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_GROUP";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox3.addItem(rs.getString(2));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 2" + e);
        }
        //Fill location unit
        try {

            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_LOCATION";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox4.addItem(rs.getString(2));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 2" + e);
        }
        //Fill vendor ID
        try {

            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_VENDOR";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox5.addItem(rs.getString(2));
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 2" + e);
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);

    }

    public void executeQuery(String query, String message) {
        Connection con = ConnectionManager.getConnection();
        Statement st;
        try {
            st = con.createStatement();
            if ((st.executeUpdate(query)) == 1) {
                JOptionPane.showMessageDialog(this, "Data " + message + " Succesfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Data Not " + message);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void setInitialStatus() {
//        name.disable();
//        short_name.disable();
//        jTextField1.disable();
//        jComboBox1.disable();
//        jButton3.disable();
//        jButton4.disable();
//        jButton2.disable();
    }

    public void enableInitialStatus() {
//
//        name.enable();
//        short_name.enable();
//        jTextField1.enable();
//        jComboBox1.enable();
//        jButton3.enable();
//        jButton4.enable();
//        jComboBox1.enable();
    }

    public Items() {
        initComponents();
        FillTable();
        FillCombo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INVENTORY ITEM");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 487, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, -1));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel1.setText("Name");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, 30));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel2.setText("Bar Code");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, 30));

        jTextField2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 230, -1));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel3.setText("Description");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 230, 70));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel4.setText("Packaging unit");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, 30));

        jComboBox1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 230, -1));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel5.setText("Recipe unit");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, 30));

        jComboBox2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 230, -1));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel6.setText("Recipe unit per package");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 30));

        jTextField3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 230, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel7.setText("Sort order");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, 30));

        jTextField4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 230, -1));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel8.setText("Reorder level");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, 20));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel9.setText("Replenish level");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, 40));

        jTextField5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 230, -1));

        jTextField6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 230, -1));

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel10.setText("Total package");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));

        jTextField7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 50, -1));

        jCheckBox1.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        jCheckBox1.setText("Update recipe balance");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, -1, -1));

        jLabel11.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel11.setText("Total recipe balance ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        jTextField8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 230, -1));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel12.setText("Package purchase price");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 140, 40));

        jComboBox3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jComboBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox3MouseClicked(evt);
            }
        });
        jPanel3.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 110, -1));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel13.setText("Package selling price");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 120, 30));

        jLabel14.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel14.setText("Group");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, -1, 40));

        jTextField9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 230, -1));

        jTextField10.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 230, -1));

        jLabel15.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel15.setText("Location");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 60, -1));

        jComboBox4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 110, -1));

        jLabel16.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel16.setText("Vendor");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, -1, -1));

        jComboBox5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jPanel3.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 110, -1));

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton1.setText("New group");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 110, -1));

        jButton2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton2.setText("New location");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 110, -1));

        jButton3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton3.setText("New vendor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 110, -1));

        jButton4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton4.setText("New Stock Transaction");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 400, 30));

        jButton5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton5.setText("NEW");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 80, 30));

        jButton6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton6.setText("EDIT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 610, 70, 30));

        jButton7.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton7.setText("SAVE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, 70, 30));

        jButton8.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton8.setText("DELETE");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 610, 70, 30));

        jButton9.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton9.setText("CANCEL");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 610, 110, 30));

        jComboBox6.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "False", "True" }));
        jPanel3.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, -1));

        jLabel17.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel17.setText("Visible");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, -1, 30));

        jButton13.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton13.setText("+");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 40, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 440, 690));

        jTable1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 860, 610));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 210, 30));

        jButton12.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton12.setText("Search");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 110, 30));

        jButton10.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton10.setText("Export Items");
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 110, 30));

        jLabel18.setText("Description :");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 30));
        jPanel4.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 10, 230, -1));

        jLabel19.setText("Name :");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 860, 60));

        jButton11.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton11.setText("Full Screen");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1360, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        enableInitialStatus();
        jTextField2.setText("");
        jTextField1.setText("");
        jTextArea1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int item_id = Integer.parseInt(jLabel20.getText());
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit item?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            int recipe_unit_id = 0;
            int item_group_id = 0;
            int item_location_id = 0;
            int item_vendor_id = 0;
            int punit_id = 0;

            String recepie = jComboBox2.getSelectedItem().toString();
            try {
                Connection con = ConnectionManager.getConnection();
                String sql = "SELECT * FROM RECEPIE_UNIT WHERE NAME='" + recepie + "' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    recipe_unit_id = rs.getInt(1);
                }
                jTable1.setModel(buildTableModel(rs));
            } catch (SQLException se) {
                System.out.println("Error 1" + se);
            } catch (Exception e) {
                System.out.println("Error 2" + e);
            }

            String punit_name = jComboBox1.getSelectedItem().toString();
            try {
                Connection con = ConnectionManager.getConnection();
                String sql = "SELECT * FROM PACKAGING_UNIT WHERE NAME='" + punit_name + "' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    punit_id = rs.getInt(1);
                }
                jTable1.setModel(buildTableModel(rs));
            } catch (SQLException se) {
                System.out.println("Error 1" + se);
            } catch (Exception e) {
                System.out.println("Error 2" + e);
            }

            String group = jComboBox3.getSelectedItem().toString();
            try {
                Connection con = ConnectionManager.getConnection();
                String sql = "SELECT * FROM INVENTORY_GROUP WHERE NAME='" + group + "' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    item_group_id = rs.getInt(1);
                }
                jTable1.setModel(buildTableModel(rs));
            } catch (SQLException se) {
                System.out.println("Error 1" + se);
            } catch (Exception e) {
                System.out.println("Error 2" + e);
            }

            String loc = jComboBox4.getSelectedItem().toString();
            try {
                Connection con = ConnectionManager.getConnection();
                String sql = "SELECT * FROM INVENTORY_LOCATION WHERE NAME='" + loc + "' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    item_location_id = rs.getInt(1);
                }
                jTable1.setModel(buildTableModel(rs));
            } catch (SQLException se) {
                System.out.println("Error 1" + se);
            } catch (Exception e) {
                System.out.println("Error 2" + e);
            }

            String vendor = jComboBox5.getSelectedItem().toString();
            try {
                Connection con = ConnectionManager.getConnection();
                String sql = "SELECT * FROM INVENTORY_VENDOR WHERE NAME='" + vendor + "' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    item_vendor_id = rs.getInt(1);
                }
                jTable1.setModel(buildTableModel(rs));
            } catch (SQLException se) {
                System.out.println("Error 1" + se);
            } catch (Exception e) {
                System.out.println("Error 2" + e);
            }
            if (jComboBox6.getSelectedItem().equals("True")) {
                visible = 1;
            } else {
                visible = 0;
            }
            String query = "UPDATE `INVENTORY_ITEM` SET `NAME` = '" + jTextField1.getText() + "', `PACKAGE_BARCODE`='" + jTextField2.getText() + "', `UNIT_PER_PACKAGE`='" + jTextField3.getText() + "', `SORT_ORDER`='" + jTextField4.getText() + "', "
                    + "`PACKAGE_REORDER_LEVEL`='" + jTextField5.getText() + "', `PACKAGE_REPLENISH_LEVEL`='" + jTextField6.getText() + "', `DESCRIPTION`='" + jTextArea1.getText() + "', "
                    + " `TOTAL_UNIT_PACKAGES`='" + jTextField7.getText() + "', `TOTAL_RECEPIE_UNITS`='" + jTextField8.getText() + "', "
                    + "`UNIT_PURCHASE_PRICE`='" + jTextField9.getText() + "', `UNIT_SELLING_PRICE`='" + jTextField10.getText() + "', `VISIBLE`='" + visible + "', `PUNIT_ID`='" + punit_id + "', `RECIPE_UNIT_ID`='" + recipe_unit_id + "', "
                    + "`ITEM_GROUP_ID`='" + item_group_id + "', `ITEM_LOCATION_ID`='" + item_location_id + "', `ITEM_VENDOR_ID`='" + item_vendor_id + "' WHERE ID = " + item_id + "";
            executeQuery(query, "Updates");
            FillTable();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int recipe_unit_id = 0;
        int item_group_id = 0;
        int item_location_id = 0;
        int item_vendor_id = 0;
        int punit_id = 0;

        String recepie = jComboBox2.getSelectedItem().toString();
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM RECEPIE_UNIT WHERE NAME='" + recepie + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                recipe_unit_id = rs.getInt(1);
            }
            jTable1.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }

        String punit_name = jComboBox1.getSelectedItem().toString();
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM PACKAGING_UNIT WHERE NAME='" + punit_name + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                punit_id = rs.getInt(1);
            }
            jTable1.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }

        String group = jComboBox3.getSelectedItem().toString();
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_GROUP WHERE NAME='" + group + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                item_group_id = rs.getInt(1);
            }
            jTable1.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }

        String loc = jComboBox4.getSelectedItem().toString();
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_LOCATION WHERE NAME='" + loc + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                item_location_id = rs.getInt(1);
            }
            jTable1.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }

        String vendor = jComboBox5.getSelectedItem().toString();
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_VENDOR WHERE NAME='" + vendor + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                item_vendor_id = rs.getInt(1);
            }
            jTable1.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }
        if (jComboBox6.getSelectedItem().equals("True")) {
            visible = 1;
        } else {
            visible = 0;
        }
        String query = "INSERT INTO `INVENTORY_ITEM`("
                + "`NAME`, `PACKAGE_BARCODE`, `UNIT_PER_PACKAGE`, `SORT_ORDER`, "
                + "`PACKAGE_REORDER_LEVEL`, `PACKAGE_REPLENISH_LEVEL`, `DESCRIPTION`, "
                + " `TOTAL_UNIT_PACKAGES`, `TOTAL_RECEPIE_UNITS`, "
                + "`UNIT_PURCHASE_PRICE`, `UNIT_SELLING_PRICE`, `VISIBLE`, `PUNIT_ID`, `RECIPE_UNIT_ID`, "
                + "`ITEM_GROUP_ID`, `ITEM_LOCATION_ID`, `ITEM_VENDOR_ID`)"
                + " VALUES ('" + jTextField1.getText() + "','" + jTextField2.getText() + "',"
                + "'" + jTextField3.getText() + "','" + jTextField4.getText() + "','" + jTextField5.getText() + "','" + jTextField6.getText() + "',"
                + "'" + jTextArea1.getText() + "','" + jTextField7.getText() + "','" + jTextField8.getText() + "','" + jTextField9.getText() + "',"
                + "'" + jTextField10.getText() + "','" + visible + "','" + punit_id + "',"
                + "'" + recipe_unit_id + "','" + item_group_id + "','" + item_location_id + "','" + item_vendor_id + "')";
        executeQuery(query, "Inserted");
        FillTable();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        ItemsView temp = new ItemsView();
        temp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        temp.setAlwaysOnTop(true);
        temp.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GroupsAdd gp = new GroupsAdd();
        gp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gp.setAlwaysOnTop(true);
        gp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LocationAdd gp = new LocationAdd();
        gp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gp.setAlwaysOnTop(true);
        gp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        VendorsAdd gp = new VendorsAdd();
        gp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gp.setAlwaysOnTop(true);
        gp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox3MouseClicked

        this.repaint();
    }//GEN-LAST:event_jComboBox3MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        double total = Double.parseDouble(jTextField3.getText()) * Double.parseDouble(jTextField7.getText());
        String temp = Double.toString(total);
        jTextField8.setText(temp);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        StockTransactions stk = new StockTransactions();
        stk.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        ItemsView item = new ItemsView();
        item.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        item.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        jLabel20.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jTextField1.setText(model.getValueAt(selectedRowIndex, 3).toString());
        jTextArea1.setText(model.getValueAt(selectedRowIndex, 10).toString());
        jTextField3.setText(model.getValueAt(selectedRowIndex, 6).toString());
        jTextField5.setText(model.getValueAt(selectedRowIndex, 8).toString());

        jTextField4.setText(model.getValueAt(selectedRowIndex, 7).toString());
        jTextField6.setText(model.getValueAt(selectedRowIndex, 9).toString());

        jTextField7.setText(model.getValueAt(selectedRowIndex, 12).toString());
        jTextField8.setText(model.getValueAt(selectedRowIndex, 13).toString());

        jTextField9.setText(model.getValueAt(selectedRowIndex, 14).toString());
        jTextField10.setText(model.getValueAt(selectedRowIndex, 15).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Search();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
int item_id = Integer.parseInt(jLabel20.getText());
int p = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
       
      if(p == 0){
          String query = "DELETE FROM `INVENTORY_ITEM` WHERE ID = " + item_id + "";
            executeQuery(query, "Deleted");
            FillTable();
      }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Items().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
