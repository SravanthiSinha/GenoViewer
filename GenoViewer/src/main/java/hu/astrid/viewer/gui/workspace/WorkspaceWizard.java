/*
 * This file is part of GenoViewer.
 *
 * GenoViewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GenoViewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GenoViewer.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WorkspaceWizard.java
 *
 * Created on 2010.06.29., 9:54:18
 */
package hu.astrid.viewer.gui.workspace;

import hu.astrid.viewer.Viewer;
import java.awt.Font;
import java.io.File;
import java.text.MessageFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author OTTO
 */
public class WorkspaceWizard extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
//
//	private String workspacePath;
//	private String workspaceName;
	private static final Logger logger = Logger.getLogger(WorkspaceWizard.class);

	/** Creates new form WorkspaceWizard */
	public WorkspaceWizard() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workspaceLocation = new javax.swing.JFileChooser();
        workspaceNameTextField = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        selectFilePathButton = new javax.swing.JButton();
        instructionLabel = new javax.swing.JLabel();
        workspacePathLabel = new javax.swing.JLabel();

        workspaceLocation.setAcceptAllFileFilterUsed(false);
        workspaceLocation.setCurrentDirectory(new File("."));
        workspaceLocation.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        workspaceLocation.setSelectedFile(new File("."));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Viewer.getLabelResources().getString("createWorkspaceTitle")); // NOI18N
        setModal(true);
        setResizable(false);

        workspaceNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                workspaceNameTextFieldKeyReleased(evt);
            }
        });

        confirmButton.setText(Viewer.getLabelResources().getString("ok")); // NOI18N
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(Viewer.getLabelResources().getString("cancel")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        selectFilePathButton.setText(Viewer.getLabelResources().getString("path")); // NOI18N
        selectFilePathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFilePathButtonActionPerformed(evt);
            }
        });

        instructionLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        instructionLabel.setText(Viewer.getLabelResources().getString("giveWorkspaceName")); // NOI18N

        workspacePathLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        workspacePathLabel.setText(workspaceLocation.getSelectedFile().getAbsolutePath());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workspacePathLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(workspaceNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(instructionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectFilePathButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workspaceNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectFilePathButton))
                .addGap(18, 18, 18)
                .addComponent(workspacePathLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void selectFilePathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFilePathButtonActionPerformed
		int option = workspaceLocation.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			workspacePathLabel.setText(workspaceLocation.getSelectedFile().getPath() + System.getProperty("file.separator") + workspaceNameTextField.getText());
		}
	}//GEN-LAST:event_selectFilePathButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		this.dispose();
	}//GEN-LAST:event_cancelButtonActionPerformed

	private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
		if (workspaceNameTextField.getText().length() > 0 && workspacePathLabel.getText().length() > 0) {
			try {
				Viewer.getController().createWorkspace(workspacePathLabel.getText());
			} catch (IllegalStateException ex) {
				JOptionPane.showMessageDialog(Viewer.getMainWindow(), MessageFormat.format(Viewer.getLabelResources().getString("workspaceDirExists"), workspacePathLabel.getText()), Viewer.getLabelResources().getString("dialogTitleWarning"), JOptionPane.WARNING_MESSAGE);
				return;
			}
			this.dispose();
		} else {
			workspaceNameTextField.setText(Viewer.getLabelResources().getString("necessary"));
			workspaceNameTextField.setFont(new Font("tahoma", Font.ITALIC, 12));
		}
	}//GEN-LAST:event_confirmButtonActionPerformed

	private void workspaceNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workspaceNameTextFieldKeyReleased
		workspacePathLabel.setText(workspaceLocation.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator") + workspaceNameTextField.getText());
	}//GEN-LAST:event_workspaceNameTextFieldKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JButton selectFilePathButton;
    private javax.swing.JFileChooser workspaceLocation;
    private javax.swing.JTextField workspaceNameTextField;
    private javax.swing.JLabel workspacePathLabel;
    // End of variables declaration//GEN-END:variables
}