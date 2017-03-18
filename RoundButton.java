import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;
public class RoundButton extends JButton {
 
  public RoundButton(Icon icon) {
    super(icon);
    setBorderPainted(false);
    setFocusPainted(false);
    setContentAreaFilled(false);
  }
 
  /**
   * détermine si le point (x, y) est à l'intérieur de l'icône circulaire
   */
  public boolean contains(int x, int y) {
    Dimension size = getSize();
    float x0 = size.width / 2F;
    float y0 = size.height / 2F;
 
    Icon icon = getIcon();
    float w = icon.getIconWidth() / 2F;
    float h = icon.getIconHeight() / 2F;
 
    return (x - x0) * (x - x0) + (y - y0) * (y - y0) <= w * h;
  }
}