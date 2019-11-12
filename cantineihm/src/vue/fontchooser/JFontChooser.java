/* 
 JFontChooser v0.1
 -------------------------------------
 a font chooser component for java.
 provide selection for:
 1. font
 2. font size
 3. font style
 4. text
 5. text color
 -------------------------------------
 Developed By : deepak pk
 Email : deepakpk009@yahoo.in
 -------------------------------------
 This Project is Licensed under LGPL
 -------------------------------------
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package vue.fontchooser;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

/**
 * @author deepak
 * @version v0.1
 */
public class JFontChooser extends javax.swing.JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4596223507101868971L;
	/**
     * the user selection constant for approve option
     */
    public static final int APPROVE_OPTION = 1;
    /**
     * the user selection constant for cancel option
     */
    public static final int CANCEL_OPTION = 0;
    // initilise the current user selection value to cancel option
    private int selectedOption = CANCEL_OPTION;
    // the text color object
    private Color textColor = null;
    // the font name array to store all the system font names
    private String[] fontNames = null;
    // the current font name
    private String fontName = null;
    // the current font size
    private int fontSize = 0;
    // the current font style - PLAIN, BOLD, BOLD AND ITALIC
    private int fontStyle = Font.PLAIN;
    // the current font reference
    private Font font = null;
    // the current fonts folder
    private File dir = null;
    // the font name array to store all the system font names
    private File[] fontFiles = null;

    /**
     * the show method used to display the JFontChooser dialog wit hthe default
     * values
     *
     * @return the user selection value which is either APPROVE_OPTION or
     * CANCEL_OPTION
     */
    public int showTextChooserDialog() {
        // set the default font name which is the first font in the detected list
        if (fontNames != null && fontNames.length > 0) {
            this.fontName = fontNames[0];
            installedfontList.setSelectedValue(this.fontName, true);
        }
        // set the default text foreground color
        this.textColor = Color.black;
        // set the foreground color
        textArea.setForeground(textColor);
        // set the text area background color as the contrast color of the text color
        textArea.setBackground(ContrastColor.getContrastColor(textColor));
        // load the default text
        textArea.setText("This is a sample text");
        // refresh the font and load it to the text area
        refreshFont();
        // set the dialog visible
        this.setVisible(true);
        // on user selection return the selected option
        return selectedOption;
    }

    /**
     * the show method used to display the JFontChooser dialog with specified
     * values
     *
     * @param fontName the default installed font name
     * @param fontStyle the default font style which may be Font.PLAIN,
     * Font.ITALIC or Font.BOLD;
     * @param fontSize the default font size
     * @param textColor the default font foreground color
     * @param text the default text to be displayed
     * @return the user selection value which is either APPROVE_OPTION or
     * CANCEL_OPTION
     */
    public int showTextChooserDialog(String fontName, int fontStyle, int fontSize, Color textColor, String text) {

        // set the specified font name
        if (fontName != null && !fontName.isEmpty()) {
            this.fontName = fontName;
            installedfontList.setSelectedValue(this.fontName, true);
        }

        // set the parameter values to the corresponding objects
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.textColor = textColor;
        // set the foreground color
        textArea.setForeground(textColor);
        // set the text area background color as the contrast color of the text color
        textArea.setBackground(ContrastColor.getContrastColor(textColor));
        // load the default text
        textArea.setText(text);
        // refresh the font and load it to the text area
        refreshFont();
        // set the dialog visible
        this.setVisible(true);
        // on user selection return the selected option
        return selectedOption;
    }

    /**
     * the default constructor which creates the text chooser with default title
     */
    public JFontChooser() {
        this.setTitle("JFontChooser");
        initJFontChooser();
    }

    /**
     * the overloaded constructor which creates the text chooser with the user
     * specified title
     *
     * @param title the title for the JFontChooser dialog window
     */
    public JFontChooser(String title) {
        this.setTitle(title);
        initJFontChooser();
    }

    /**
     * the JFontChooser initiliser method
     */
    private void initJFontChooser() {

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
            java.util.logging.Logger.getLogger(JFontChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFontChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFontChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFontChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // set the dialog modal to application model 
        // as this blocks the parent window execution till this window is closed
        // if not set we would not be able to get the selected value
        this.setModalityType(ModalityType.APPLICATION_MODAL);

        // Font info is obtained from the current graphics environment.
        // this is done first because the font names are to be loaded onto the font list        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get an array of font names (smaller than the number of fonts)
        fontNames = ge.getAvailableFontFamilyNames();

        // here in initComponents the fontList is created with the font names as parameter
        initComponents();
        // set the default text color
        textColor = Color.black;
        textArea.setForeground(textColor);
        // set the contrast color of the text foreground color to the text area background color
        textArea.setBackground(ContrastColor.getContrastColor(textColor));
        // refresh the text area with the new settings
        refreshFont();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        textAreaScrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        boldRadioButton = new javax.swing.JRadioButton();
        okButton = new javax.swing.JButton();
        colorButton = new javax.swing.JButton();
        sizeSpinner = new javax.swing.JSpinner();
        plainRadioButton = new javax.swing.JRadioButton();
        cancelButton = new javax.swing.JButton();
        italicCheckBox = new javax.swing.JCheckBox();
        fontsTabbedPane = new javax.swing.JTabbedPane();
        fontListScrollPane = new javax.swing.JScrollPane();
        installedfontList = new javax.swing.JList<Object>(fontNames);
        uninstalledFontsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        uninstalledFontsList = new javax.swing.JList<Object>();
        selectFontsFolderButton = new javax.swing.JButton();

        buttonGroup.add(plainRadioButton);
        buttonGroup.add(boldRadioButton);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textAreaScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Text"));

        textArea.setColumns(20);
        textArea.setRows(5);
        textAreaScrollPane.setViewportView(textArea);

        boldRadioButton.setText("Bold");
        boldRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldRadioButtonActionPerformed(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        colorButton.setText("Color");
        colorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorButtonActionPerformed(evt);
            }
        });

        sizeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(18), Integer.valueOf(12), null, Integer.valueOf(1)));
        sizeSpinner.setBorder(javax.swing.BorderFactory.createTitledBorder("Size"));
        sizeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sizeSpinnerStateChanged(evt);
            }
        });

        plainRadioButton.setSelected(true);
        plainRadioButton.setText("Plain");
        plainRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plainRadioButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        italicCheckBox.setText("Italic");
        italicCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                italicCheckBoxActionPerformed(evt);
            }
        });

        fontsTabbedPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Font List"));

        installedfontList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                installedfontListValueChanged(evt);
            }
        });
        fontListScrollPane.setViewportView(installedfontList);

        fontsTabbedPane.addTab("Installed", fontListScrollPane);

        uninstalledFontsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                uninstalledFontsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(uninstalledFontsList);

        selectFontsFolderButton.setText("Select Fonts Folder");
        selectFontsFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFontsFolderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout uninstalledFontsPanelLayout = new javax.swing.GroupLayout(uninstalledFontsPanel);
        uninstalledFontsPanel.setLayout(uninstalledFontsPanelLayout);
        uninstalledFontsPanelLayout.setHorizontalGroup(
            uninstalledFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(selectFontsFolderButton, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        uninstalledFontsPanelLayout.setVerticalGroup(
            uninstalledFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uninstalledFontsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(selectFontsFolderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
        );

        fontsTabbedPane.addTab("Un-installed", uninstalledFontsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(colorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(plainRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boldRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(italicCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fontsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fontsTabbedPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colorButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plainRadioButton)
                            .addComponent(boldRadioButton)
                            .addComponent(italicCheckBox)))
                    .addComponent(textAreaScrollPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(639, 417));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void colorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorButtonActionPerformed
        // show and get the color from color chooser
        textColor = JColorChooser.showDialog(rootPane, "Select Text Color", Color.black);
        // set the text area foreground color as the selected color
        textArea.setForeground(textColor);
        // set the text area background color as contrast of the selected color
        textArea.setBackground(ContrastColor.getContrastColor(textColor));
    }//GEN-LAST:event_colorButtonActionPerformed

    private void plainRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plainRadioButtonActionPerformed
        refreshFont();
    }//GEN-LAST:event_plainRadioButtonActionPerformed

    private void boldRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldRadioButtonActionPerformed
        refreshFont();
    }//GEN-LAST:event_boldRadioButtonActionPerformed

    private void italicCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italicCheckBoxActionPerformed
        refreshFont();
    }//GEN-LAST:event_italicCheckBoxActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // set user selection as canceled
        selectedOption = CANCEL_OPTION;
        // set visibility to false
        this.setVisible(false);
        // dispose all graphics resources
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // set the user selection as approval
        selectedOption = APPROVE_OPTION;
        // set the JFontChooser visibility to false
        this.setVisible(false);
        // dispose all screen resources used by the JFontChooser
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void installedfontListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_installedfontListValueChanged
        refreshFont();
    }//GEN-LAST:event_installedfontListValueChanged

    private void sizeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sizeSpinnerStateChanged
        refreshFont();
    }//GEN-LAST:event_sizeSpinnerStateChanged

    private void selectFontsFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFontsFolderButtonActionPerformed
        JFileChooser jfc = new JFileChooser(dir);

        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = jfc.showOpenDialog(jfc);

        if (result == JFileChooser.APPROVE_OPTION) {
            // get the selected folder
            dir = jfc.getSelectedFile();
            // list out the font files within the selected
            // folder
            fontFiles = dir.listFiles(new FontFileFilter());
            // load the font files names to the list view 
            uninstalledFontsList.setListData(dir.list(new FontFileFilter()));
            // set the default font name which is the first font in the detected list
            uninstalledFontsList.setSelectedIndex(0);
            // refresh the font and load it to the text area
            refreshFont();
        }
    }//GEN-LAST:event_selectFontsFolderButtonActionPerformed

    private void uninstalledFontsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_uninstalledFontsListValueChanged
        refreshFont();
    }//GEN-LAST:event_uninstalledFontsListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton boldRadioButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton colorButton;
    private javax.swing.JScrollPane fontListScrollPane;
    private javax.swing.JTabbedPane fontsTabbedPane;
    private javax.swing.JList<Object> installedfontList;
    private javax.swing.JCheckBox italicCheckBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JRadioButton plainRadioButton;
    private javax.swing.JButton selectFontsFolderButton;
    private javax.swing.JSpinner sizeSpinner;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textAreaScrollPane;
    private javax.swing.JList<Object> uninstalledFontsList;
    private javax.swing.JPanel uninstalledFontsPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * method to refersh the text area with the new values
     */
    private void refreshFont() {
        // get the font size from the size spinner
        fontSize = Integer.parseInt(sizeSpinner.getValue().toString());

        // get the font style from the radio buttons selected
        // and the italic check box
        // # corrected in JTextChooser v0.1.4
        if (plainRadioButton.isSelected()) {
            fontStyle = Font.PLAIN;
        } else {
            fontStyle = Font.BOLD;
        }

        if (italicCheckBox.isSelected()) {
            fontStyle = fontStyle + Font.ITALIC;
        }

        // if the user has selected installed fonts panel then
        if (fontsTabbedPane.getSelectedIndex() == 0) {
            // get the selected font from the font list
            if (installedfontList.isSelectionEmpty()) {
                // create the font with the first font name in the font list 
                // and selected style and size
                font = new Font(fontNames[0], fontStyle, fontSize);
            } else {
                // create the font with the selected font name style and size
                fontName = installedfontList.getSelectedValue().toString();
                font = new Font(fontName, fontStyle, fontSize);
            }
        } // if the user has selected fonts from the un-installed panel
        else {
            // process only if there is fonts files selected
            // and there is a valid selection in the list view
            if (fontFiles != null && fontFiles.length > 0
                    && uninstalledFontsList.getSelectedIndex() > -1) {
                try {
                    // get the selected font from the font list
                    // create the font with the selected font name style and size
                    font = Font.createFont(Font.TRUETYPE_FONT, fontFiles[uninstalledFontsList.getSelectedIndex()]);
                } catch (FontFormatException ex) {
                    Logger.getLogger(JFontChooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JFontChooser.class.getName()).log(Level.SEVERE, null, ex);
                }
                font = font.deriveFont(fontStyle, fontSize);
            }
        }
        // set the text area font
        textArea.setFont(font);
    }

    /**
     * getter for the selected text color
     *
     * @return the selected text foreground color
     */
    public Color getSelectedTextColor() {
        return textColor;
    }

    /**
     * getter for returning all the available installed font names
     *
     * @return the list of all available installed font names
     */
    public String[] getFontNames() {
        return fontNames;
    }

    /**
     * getter for getting the selected font name
     *
     * @return the selected font name
     */
    public String getSelectedFontName() {
        return fontName;
    }

    /**
     * getter for selected font
     *
     * @return the selected font
     */
    public Font getSelectedFont() {
        return font;
    }

    /**
     * getter for the selected font size
     *
     * @return the selected font size
     */
    public int getSelectedFontSize() {
        return fontSize;
    }

    /**
     * getter for the selected font style
     *
     * @return the selected font styles which may be Font.PLAIN, Font.ITALIC or
     * Font.BOLD
     */
    public int getSelectedFontStyle() {
        return fontStyle;
    }

    /**
     * getter for returning the user inputted text
     *
     * @return the user inputted text as String
     */
    public String getText() {
        return textArea.getText();
    }
}
