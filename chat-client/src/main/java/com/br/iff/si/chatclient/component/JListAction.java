package com.br.iff.si.chatclient.component;

import javax.swing.*;
import java.awt.event.*;

public class JListAction extends JList {

    ActionListener al;

    public JListAction() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (al == null)
                    return;

                Object ob[] = getSelectedValues();
                
                if (ob.length > 1)
                    return;

                if (me.getClickCount() == 2) {
                    al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ob[0].toString()));
                    me.consume();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                if (al == null)
                    return;

                Object ob[] = getSelectedValues();

                if (ob.length > 1)
                    return;
                
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ob[0].toString()));
                    ke.consume();
                }
            }
        });
        
        this.setSelectedIndex(0);
    }

    

    public void addActionListener(ActionListener al) {
        this.al = al;
    }
}
