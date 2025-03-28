package principal;

import comandos_sql.CreateDataBaseSQL;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Martendal
 */
public class FramePrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        
        txtpnSQLEditor.setText(        
            "create table abc.xpto (xa int, xb char(20), xc float);\n" +
            "insert into abc.xpto (xa, xb) values (1, 'abc');\n" +
            "select * from abc.xpto;"
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtpnSQLEditor = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSQLResults = new javax.swing.JTable();
        pnlControls = new javax.swing.JPanel();
        btnRunSQL = new javax.swing.JButton();
        btnCreateDataBase = new javax.swing.JButton();
        edtNomeBase = new javax.swing.JTextField();
        lblNomeBase = new javax.swing.JLabel();
        edtXml = new javax.swing.JTextField();
        btXml = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName(""); // NOI18N

        txtpnSQLEditor.setName(""); // NOI18N
        jScrollPane2.setViewportView(txtpnSQLEditor);

        tblSQLResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSQLResults.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSQLResults.setName(""); // NOI18N
        jScrollPane3.setViewportView(tblSQLResults);

        pnlControls.setName(""); // NOI18N

        btnRunSQL.setText("Executar");
        btnRunSQL.setName(""); // NOI18N
        btnRunSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunSQLActionPerformed(evt);
            }
        });

        btnCreateDataBase.setText("Create DataBase");
        btnCreateDataBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateDataBaseActionPerformed(evt);
            }
        });

        lblNomeBase.setText("Nome do Banco de Dados");
        lblNomeBase.setName(""); // NOI18N

        edtXml.setText("Insira aqui o caminho do arquivo xml para inserir");
        edtXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtXmlActionPerformed(evt);
            }
        });

        btXml.setText("Inserir xml");
        btXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXmlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlsLayout = new javax.swing.GroupLayout(pnlControls);
        pnlControls.setLayout(pnlControlsLayout);
        pnlControlsLayout.setHorizontalGroup(
            pnlControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRunSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edtXml, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btXml, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(lblNomeBase)
                .addGap(10, 10, 10)
                .addComponent(edtNomeBase, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnCreateDataBase, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlControlsLayout.setVerticalGroup(
            pnlControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRunSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateDataBase, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNomeBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeBase)
                    .addGroup(pnlControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btXml, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtXml)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(pnlControls, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunSQLActionPerformed
        if (ExecutarSQL.Executar(txtpnSQLEditor.getText(), tblSQLResults)) {
            System.out.println("SUCESSO - SQL executado com sucesso");
        } else {
            System.out.println("ERRO - Não foi possível executar o comando SQL");
            //Exibir mensagem de erro ("Não foi possível executar o comando SQL")
        }
    }//GEN-LAST:event_btnRunSQLActionPerformed

    private void btnCreateDataBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateDataBaseActionPerformed
        if (CreateDataBaseSQL.CreateDataBase(edtNomeBase.getText())) {
            System.out.println("SUCESSO - Base de Dados criada com sucesso");
        } else {
            System.out.println("ERRO - Não foi possível criar o Banco de Dados");
            //Exibir mensagem de erro ("Não foi possível criar o Banco de Dados")
        }        
    }//GEN-LAST:event_btnCreateDataBaseActionPerformed

    private void edtXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtXmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtXmlActionPerformed

    private void btXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXmlActionPerformed
        if (ExecutarXML.Executar(edtXml.getText())) {
            JOptionPane.showMessageDialog(null, "SUCESSO - XML executado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "ERRO - Não foi possível executar comandos do XML, verifique se o arquivo"
                    + " se encontra no caminho especificado.");
            //Exibir mensagem de erro ("Não foi possível executar o comando SQL")
        }
    }//GEN-LAST:event_btXmlActionPerformed

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
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btXml;
    private javax.swing.JButton btnCreateDataBase;
    private javax.swing.JButton btnRunSQL;
    private javax.swing.JTextField edtNomeBase;
    private javax.swing.JTextField edtXml;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNomeBase;
    private javax.swing.JPanel pnlControls;
    private javax.swing.JTable tblSQLResults;
    private javax.swing.JTextPane txtpnSQLEditor;
    // End of variables declaration//GEN-END:variables
}
