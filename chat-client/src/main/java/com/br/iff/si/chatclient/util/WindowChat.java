package com.br.iff.si.chatclient.util;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class WindowChat {
    
    private String windowTitle;
    private JPanel jPanel;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane;

    public WindowChat(String windowTitle) {
        this.windowTitle = windowTitle;
        
        this.jTextArea = new JTextArea();
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setEditable(false);
        
        this.jScrollPane = new JScrollPane(this.jTextArea);
        this.jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.jPanel = new JPanel();
        this.jPanel.setLayout(new BorderLayout());
        this.jPanel.add(this.jScrollPane, BorderLayout.CENTER);
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public void setjTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }
}
