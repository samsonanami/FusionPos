/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floreantpos.inventory;

import static com.floreantpos.inventory.Units.buildTableModel;
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
public class Locations extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

    int visible = 0;

    PreparedStatement p;

    public Locations() {
        initComponents();
        FillCombo();
        FillTable();
    }

    public void FillCombo() {
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_WAREHOUSE";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString(2));
            }

            // It creates and displays the table
//          JTable table = new JTable(buildTableModel(rs));
            jTable1.setModel(buildTableModel(rs));

        } catch (SQLException se) {
//            this.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 1" + se);
        } catch (Exception e) {
//            this.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 2" + e);
        }
    }

    public void FillTable() {
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_LOCATION";
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 570, 259));

        jLabel1.setText("Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, -1, -1));

        jLabel2.setText("Sort Order");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, -1, -1));

        jLabel3.setText("Warehouse");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, -1, -1));

        jCheckBox1.setText("Visible");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 190, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, 190, -1));

        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 170, -1));

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 180, -1, -1));

        jButton2.setText("NEW");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 80, -1));

        jButton3.setText("EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 300, 80, -1));

        jButton4.setText("CANCEL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 300, 90, -1));

        jButton5.setText("SAVE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 80, -1));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jButton6.setText("DELETE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 90, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 90, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1040, 340));

        jLabel4.setText("Item Location");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        WarehouseAdd whadd = new WarehouseAdd();
        whadd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        whadd.setAlwaysOnTop(true);
        whadd.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int wr_id = 0;
        String warehouse = jComboBox1.getSelectedItem().toString();
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM INVENTORY_WAREHOUSE WHERE NAME='" + warehouse + "' ";
            PreparedStatement p = con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                wr_id = rs.getInt(1);
            }
            jTable1.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }

        String query = "INSERT INTO `INVENTORY_LOCATION`(`NAME`, `SORT_ORDER`, `VISIBLE`, `WAREHOUSE_ID`) "
                + "VALUES('" + jTextField1.getText() + "','" + jTextField2.getText() + "','" + visible + "','" + wr_id + "') ";
        executeQuery(query, "inserted");
        FillTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update record?", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            String query = "UPDATE `INVENTORY_LOCATION` SET `NAME`='" + jTextField1.getText() + "',`SORT_ORDER`='" + jTextField2.getText() + "',"
                    + "`VISIBLE`='" + visible + "' WHERE `ID`='" + Integer.parseInt(jTextField3.getText()) + "'";
            executeQuery(query, "Updated");
            FillTable();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        visible = 1;
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        jTextField3.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jTextField1.setText(model.getValueAt(selectedRowIndex, 1).toString()); 
        jTextField2.setText(model.getValueAt(selectedRowIndex, 2).toString());  
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            String query = "DELETE FROM `INVENTORY_LOCATION` WHERE ID='" + Integer.parseInt(jTextField3.getText()) + "'";
            executeQuery(query, "Deleted");
            FillTable();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Locations().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
