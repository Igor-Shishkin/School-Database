package GUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

public class CustomMenuUI extends BasicMenuUI {
    public static ComponentUI createUI (JComponent c) {
        return new CustomMenuUI();
    }
}
