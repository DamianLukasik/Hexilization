/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexilization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author DayanDey
 */
public class Heks {
    
    private ArrayList<String> sąsiad;
    private Point location;
    private String id;
    private static int wielkosc=50;    
    
    Heks()
    {
        location = new Point(0,0);     
        id="";
        sąsiad = new ArrayList<String>();
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
    }
    
    Heks(Point pkt, String id_)
    {
        location = pkt;
        id=id_;
        sąsiad = new ArrayList<String>();
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
        sąsiad.add("");
    }
    
    public void rysuj(javax.swing.JPanel p)
    {
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        
        File imageFile = new File("src/hexilization/Heks.png");
        try
        {
            BufferedImage image = ImageIO.read(imageFile);
     //       g.drawImage(image, location.x, location.y, p);  
            int a = (int)((image.getData().getHeight()/2)-(wielkosc/2));            
            image = resizeImage(image,wielkosc,wielkosc); 
            location.x+=a;
            location.y+=a;
            g.drawImage(image, location.x, location.y, p);            
            
        }
        catch (IOException e) 
        {
            System.err.println("Blad odczytu obrazka");
	}
    }
    
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) throws IOException {  
        BufferedImage resizedImage = new BufferedImage(width, height,  BufferedImage.TYPE_INT_ARGB);  
        Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        return resizedImage;  
    }  
    
    public void set_location(int x, int y)
    {
        location = new Point(x,y);
    }
    
    public void set_location(Point pkt)
    {
        location = pkt;
    }
    
    public Point set_location()
    {
        return location;
    }
    
    public void set_id(String id_n)
    {
        id=id_n;
    }
    
    public String get_id()
    {
        return id;
    }
    
    public void set_neightbour(String id_n,int nr)
    {
        sąsiad.set(nr, id_n);
    }
    
    public String get_neightbour(int nr)
    {
        return sąsiad.get(nr);
    }

    void set_neightbour(Heks h, int id_n) 
    {
        sąsiad.set(id_n,h.get_id());
        
        Point pkt = new Point();
        
        pkt.y=(int) (location.y);
        pkt.x=(int) ((int) (location.x)+(wielkosc*0.9));
        
        double[] pt = {pkt.x, pkt.y};
        AffineTransform.getRotateInstance(Math.toRadians(60*(id_n+1)), location.x, location.y)
          .transform(pt, 0, pt, 0, 1); // specifying to use this double[] to hold coords
        pkt.x = (int) (pt[0]-(wielkosc/2));
        pkt.y = (int) (pt[1]-(wielkosc/2));
        
             
      //  pkt.x = (int) (location.x + (pkt.x-location.x)*Math.cos(rad) - ( pkt.y-location.y)*Math.sin(rad));
      //  pkt.y = (int) (location.y + (pkt.x-location.x)*Math.sin(rad) + ( pkt.y-location.y)*Math.cos(rad));
        
        
        h.set_location(pkt);
    }
}
