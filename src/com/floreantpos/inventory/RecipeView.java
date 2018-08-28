/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floreantpos.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class RecipeView extends javax.swing.JFrame {

    String recipe = "";

    public void Search() {
        try {
            String name = jTextField1.getText();
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT ID,NAME,DESCRIPTION,TRANSLATED_NAME,RECEPIE FROM MENU_ITEM WHERE NAME LIKE '%" + name + "%' OR DESCRIPTION LIKE '" + name + "'";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            tblMenuItems.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }
    }

    public void FillTable() {
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT ID,NAME,DESCRIPTION,TRANSLATED_NAME,RECEPIE FROM MENU_ITEM WHERE RECEPIE!=0";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            tblMenuItems.setModel(buildTableModel(rs));
        } catch (SQLException se) {
            System.out.println("Error 1" + se);
        } catch (Exception e) {
            System.out.println("Error 2" + e);
        }
    }

    public void FillTableRecipe() {
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT ID,NAME,DESCRIPTION,TRANSLATED_NAME,RECEPIE FROM MENU_ITEM";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            tblMenuItems.setModel(buildTableModel(rs));
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

    public RecipeView() {
        initComponents();
        FillTable();
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
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenuItems = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRecipeDisplay = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Select MENU ITEM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 370, -1));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 90, -1));

        tblMenuItems.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMenuItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMenuItemsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMenuItems);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 820, 160));

        tblRecipeDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "QUANTITY"
            }
        ));
        jScrollPane2.setViewportView(tblRecipeDisplay);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 820, 220));

        jLabel2.setBackground(new java.awt.Color(160, 208, 204));
        jLabel2.setText("RECIPE DETAILS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 510));

        jButton2.setText("CLOSE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 510, 135, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblMenuItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuItemsMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblMenuItems.getModel();
        int selectedRowIndex = tblMenuItems.getSelectedRow();
        int recipe_id = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());

        DefaultTableModel model1 = (DefaultTableModel) tblRecipeDisplay.getModel();
        int rowCount = model1.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        //Fill package unit
        try {
            Connection con = ConnectionManager.getConnection();
            String sql = "SELECT INVENTORY_ITEM,PERCENTAGE FROM RECEPIE_ITEM WHERE RECEPIE_ID = " + recipe_id + " ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int inventory_id = rs.getInt(1);
                int perc = rs.getInt(2);
                try {
                    String sql1 = "SELECT NAME FROM INVENTORY_ITEM WHERE ID = " + inventory_id + " ";
                    Statement st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery(sql1);
                    while (rs1.next()) {
                        model1.addRow(new Object[]{rs1.getString(1), perc});
                    }
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error 1" + se);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error 2" + e);
                }
            }

        } catch (SQLException se) {
//            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 3" + se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error 4" + e);
        }

    }//GEN-LAST:event_tblMenuItemsMouseClicked

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
            java.util.logging.Logger.getLogger(RecipeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecipeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecipeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecipeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecipeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblMenuItems;
    private javax.swing.JTable tblRecipeDisplay;
    // End of variables declaration//GEN-END:variables
}