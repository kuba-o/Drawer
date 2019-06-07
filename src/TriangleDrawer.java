import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.*;

public class TriangleDrawer extends JFrame{

  private int height = 533;
  private int width = 600;
  private int padding = 50;
  private int edge = width - 2 * padding;
  private double lineDivision = 50;

  private Line2D rightLine = new Line2D.Float(width - padding, height - padding, padding + edge / 2, padding); 
  private Line2D leftLine  = new Line2D.Float(padding + edge / 2, padding, padding, height - padding); 
  private Line2D bottomLine = new Line2D.Float(padding, height - padding, width - padding, height - padding); 
  
  
  //square
  private Point2D pointA = new Point2D.Double(50, 450);
  private Point2D pointB = new Point2D.Double(50, 50);
  private Point2D pointC = new Point2D.Double(450, 50);
  private Point2D pointD = new Point2D.Double(450, 450);
  
  private Line2D lineAB = new Line2D.Double(pointA, pointB);
  private Line2D lineBC = new Line2D.Double(pointB, pointC);
  private Line2D lineCD = new Line2D.Double(pointC, pointD);
  private Line2D lineDA = new Line2D.Double(pointD, pointA);
  
  
  public TriangleDrawer(){
    setSize(width, height);
    setBackground(Color.BLACK);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  public void paint(Graphics g)  {
    g.setColor(Color.WHITE);
//    drawLine(bottomLine, g);
//    drawLine(leftLine, g);
//    drawLine(rightLine, g);
//
//    ArrayList<Point2D> pointsOnBottomLine = getPointsOnLine(bottomLine);
//    ArrayList<Point2D> pointsOnLeftLine = getPointsOnLine(leftLine);
//    ArrayList<Point2D> pointsOnRightLine = getPointsOnLine(rightLine);
//    
//    drawConnections(pointsOnBottomLine, pointsOnLeftLine, g);
//    drawConnections(pointsOnBottomLine, pointsOnRightLine, g);
//    drawConnections(pointsOnRightLine, pointsOnLeftLine, g);
    
    drawLine(lineAB, g);
    drawLine(lineBC, g);
    drawLine(lineCD, g);
    drawLine(lineDA, g);
    
    ArrayList<Point2D> pointsOnLineAB = getPointsOnLine(lineAB);
    ArrayList<Point2D> pointsOnLineBC = getPointsOnLine(lineBC);
    ArrayList<Point2D> pointsOnLineCD = getPointsOnLine(lineCD);
    ArrayList<Point2D> pointsOnLineDA = getPointsOnLine(lineDA);
    
    drawConnections(pointsOnLineAB, pointsOnLineCD, g);
    drawConnections(pointsOnLineBC, pointsOnLineDA, g);
    
    drawConnections(pointsOnLineAB, pointsOnLineBC, g, Color.BLACK);
    drawConnections(pointsOnLineBC, pointsOnLineCD, g, Color.BLACK);
    drawConnections(pointsOnLineCD, pointsOnLineDA, g, Color.BLACK);
    drawConnections(pointsOnLineDA, pointsOnLineAB, g, Color.BLACK);
    
  }

  private void drawConnections(ArrayList<Point2D> pointsOnLineAB, ArrayList<Point2D> pointsOnLineBC, Graphics g, Color black) {
    g.setColor(black);
    drawConnections(pointsOnLineAB, pointsOnLineBC, g);
  }

  public void drawLine(Line2D line, Graphics g){
    Graphics2D g2 = (Graphics2D) g;
    g2.draw(line);
  }
  
  public void drawConnections(ArrayList<Point2D> firstLinePoints, ArrayList<Point2D> secondLinePoints, Graphics g){
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < lineDivision; i++){
      g2.draw(new Line2D.Double(firstLinePoints.get(i), secondLinePoints.get(i)));
    }
  }

  public void drawConnections(ArrayList<Point2D> firstLinePoints, ArrayList<Point2D> secondLinePoints,
                              Graphics g, Color firstColor, Color secondColor){
    GradientPaint gp = new GradientPaint(firstLinePoints.get(0), firstColor, secondLinePoints.get(0), secondColor);
    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(gp);
    for (int i = 0; i < lineDivision; i++){
      g2.draw(new Line2D.Double(firstLinePoints.get(i), secondLinePoints.get(i)));
    }
  }
  
  public ArrayList<Point2D> getPointsOnLine(Line2D line){
    ArrayList<Point2D> pointList = new ArrayList<>();
    Point2D firstPoint = line.getP1();
    Point2D secondPoint = line.getP2();
    double xDiff = secondPoint.getX() - firstPoint.getX();
    double yDiff = secondPoint.getY() - firstPoint.getY();
    
    for (int i = 0; i < lineDivision; i++){
      pointList.add(new Point2D.Double(firstPoint.getX() + xDiff * i / lineDivision, firstPoint.getY() + yDiff * i / lineDivision));
    }
    
    
    return pointList;
  }
}