import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainClass {
  public static void main(String[] args) {
    TriangleDrawer triangleDrawer = new TriangleDrawer();


    BufferedImage image = new BufferedImage(triangleDrawer.getWidth(), triangleDrawer.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = image.createGraphics();
    triangleDrawer.paint(graphics2D);
    try {
      ImageIO.write(image,"jpeg", new File("/Users/jostrowicki/IdeaProjects/TriangleDrawer/src/photo.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}
