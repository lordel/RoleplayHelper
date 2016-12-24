package com.roleplay.utils;

import java.awt.*;

@Deprecated public abstract class BackgroundColor {
    public static void setColor(Container cont, int choice){
        switch(choice+1){
            case 1:
                cont.setBackground(new Color(255, 239, 55));
                break;
            case 2:
                cont.setBackground(new Color(0, 102, 204));
                break;
            case 3:
                cont.setBackground(new Color(0, 102, 0));
                break;
            case 4:
                cont.setBackground(new Color(204, 0, 0));
                cont.setForeground(new Color(255,255,255));
                break;
            case 5:
                cont.setBackground(new Color(0, 0, 0));
                cont.setForeground(new Color(255,255,255));
                break;
            case 6:
                cont.setBackground(new Color(96, 98, 97));
                cont.setForeground(new Color(255,255,255));
                break;
            case 7:
                cont.setBackground(new Color(76, 0, 153));
                cont.setForeground(new Color(255,255,255));
                break;
            case 8:
                cont.setBackground(new Color(102, 51, 0));
                cont.setForeground(new Color(255,255,255));
                break;
        }
    }
}
