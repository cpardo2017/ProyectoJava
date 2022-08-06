package pendulodenewton.Logica;
import pendulodenewton.*;
import java.awt.*;
import javax.swing.*;

import java.awt.Polygon;

/**
 *representa las cuerdas de las cuelgan las esferas,
 *transmite propiedades de las esferas a la base
 * @author grupo 9
 */
public class Cuerda {
    
    private Esfera pelota;
    private int largo;
    private int p1x;
    private int p2x;
    private int p1y;
    private int p2y;
    private int radio;
    private int gravedad;
    private double angulo;
    
    /**
     * metodo constructor de la cuerda.
     * @param posX posicion x del extremo superior de la cuerda.
     * @param posY posicion y del extremo superior de la cuerda.
     * @param r radio de la esfera que sostiene la cuerda.
     * @param m masa de la esfera que sostiene la cuerda.
     * @param g g es la gravedad.
     */
    public Cuerda(int posX, int posY,int r, int g){
        largo=100+r/2;
        p1x=posX;
        p1y=posY;
        p2x=p1x;
        p2y=p1y+largo;
        radio=r;
        gravedad=g;
        angulo=angulo=Math.asin((p2x-p1x)/largo);
        pelota=new Esfera(p1x,p1y+largo,radio,gravedad,this);
    }
    
    /**
     * metodo de dibujo.
     * @param g representa el pincel del panel de dibujo.
     */
    public void paint(Graphics g){
      
        g.setColor(Color.BLACK);
        g.drawLine(p1x, p1y, p2x, p2y);
        pelota.paint(g);
        
    }
    
    /**
     * mueve la esfera hacia donde señalan las cordenadas x e y.
     * @param x cordenada x del mouse.
     * @param y cordenada y del mouse.
     */
    public void mover(int x, int y){
        pelota.mover(x, y);
    }
    
    /**
     * mueve la el extremo inferior de la cuerda.
     * @param x cordenada x de la esfera.
     * @param y cordenada y de la esfera
     */
    public void moverAnclaje(int x,int y){
        p2x=x;
        p2y=y;
        angulo=Math.asin(((double)(p2x)-(double)(p1x))/(double)(largo));
    }
    
    public int getX(){
        return p1x;
    }
    
    public int getY(){
        return p1y;
    }
    
    public int getLargo(){
        return largo;
    }
    
    /**
     * selecciona una esfera la cual alguno de sus puntos sea el que señalan las cordenadas x e y cuando se apreta el mouse.
     * @param x cordenada x del mouse.
     * @param y cordenada y del mouse.
     */
    public void select(int x,int y){
        pelota.select(x,y);
    }
    
    /**
     * deselecciona las esferas cuando se suelta el mouse.
     */
    public void diselect(){
        pelota.diselect();
    }
    
    public boolean getSelect(){
        return pelota.getSelect();
    }
    
    public double getAngulo(){
        return angulo;
    }
    
    /**
     * le da velocidad a una esfera que fue chocada.
     * @param vel velocidad que se le pasa a la esfera.
     */
    public void acelerar(double vel){
        pelota.acelerar(vel);
    }
    
    /**
     * detiene una esfera que choco.
     * @return la velocidad que tenia la esfera antes de detenerse.
     */
    public double detener(){
        angulo=0;
        return pelota.detener();
    }
    
    public double getVel(){
        return pelota.getVel();
    }
    
    public void setVel(double v){
        pelota.setVel(v);
    }
    
    public void setX(int x){
        pelota.setX(x);
    }
    
    public void setY(int y){
        pelota.setY(y);
    }
    
    public int getX2(){
        return pelota.getX();
    }
    
    public int getY2(){
        return pelota.getY();
    }
    
    /**
     * activa el metodo desbloquear de las esfera.
     */
    public void desbloquear(){
        pelota.desbloquear();
    }
    
    public double getOrientacion(){
        return pelota.getOrientacion();
    }
}