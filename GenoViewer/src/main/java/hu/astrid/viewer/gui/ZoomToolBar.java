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
 * ZoomToolBar.java
 *
 * Created on 2010.06.17., 13:36:53
 */

package hu.astrid.viewer.gui;

import hu.astrid.viewer.Viewer;

/**
 *
 * @author Szuni
 */
public class ZoomToolBar extends javax.swing.JToolBar {
		
	private static final long serialVersionUID = 1L;

    /** Creates new form ZoomToolBar */
    public ZoomToolBar() {
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

        zoomOutButton = new javax.swing.JButton();
        zoomInButton = new javax.swing.JButton();
        actualZoomButton = new javax.swing.JButton();

        zoomOutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hu/astrid/viewer/gui/zoomicons/zoomout.png"))); // NOI18N
        zoomOutButton.setToolTipText(Viewer.getLabelResources().getString("toolbarIconZoomOut")); // NOI18N
        zoomOutButton.setFocusable(false);
        zoomOutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomOutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        zoomOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutButtonActionPerformed(evt);
            }
        });

        zoomInButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hu/astrid/viewer/gui/zoomicons/zoomin.png"))); // NOI18N
        zoomInButton.setToolTipText(Viewer.getLabelResources().getString("toolbarIconZoomIn")); // NOI18N
        zoomInButton.setFocusable(false);
        zoomInButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomInButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        zoomInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInButtonActionPerformed(evt);
            }
        });

        actualZoomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hu/astrid/viewer/gui/zoomicons/actualsize.png"))); // NOI18N
        actualZoomButton.setToolTipText(Viewer.getLabelResources().getString("toolbarIconActualSize")); // NOI18N
        actualZoomButton.setFocusable(false);
        actualZoomButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualZoomButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualZoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualZoomButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zoomOutButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoomInButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actualZoomButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(zoomOutButton)
            .addComponent(zoomInButton)
            .addComponent(actualZoomButton)
        );
    }// </editor-fold>//GEN-END:initComponents

	private void zoomOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutButtonActionPerformed
		Viewer.getMainWindow().zoomOutActionPerformed(evt);
	}//GEN-LAST:event_zoomOutButtonActionPerformed

	private void zoomInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInButtonActionPerformed
		Viewer.getMainWindow().zoomInActionPerformed(evt);
	}//GEN-LAST:event_zoomInButtonActionPerformed

	private void actualZoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualZoomButtonActionPerformed
		Viewer.getMainWindow().zoomDefaultActionPerformed(evt);
	}//GEN-LAST:event_actualZoomButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualZoomButton;
    private javax.swing.JButton zoomInButton;
    private javax.swing.JButton zoomOutButton;
    // End of variables declaration//GEN-END:variables

	public void	refreshI18N() {
        zoomOutButton.setToolTipText(Viewer.getLabelResources().getString("toolbarIconZoomOut")); // NOI18N
        zoomInButton.setToolTipText(Viewer.getLabelResources().getString("toolbarIconZoomIn")); // NOI18N
        actualZoomButton.setToolTipText(Viewer.getLabelResources().getString("toolbarIconActualSize")); // NOI18N
	}
}
