package pendulodenewton.Grafica;
import pendulodenewton.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.Timer;

/**
 * crea el lienzo donde se dibujara el proyecto
 * @author grupo 9
 */
public class PanelDibujo extends JPanel implements ActionListener,MouseListener, MouseMotionListener{
   
    private pendulodenewton.Logica.Base base;
    private Timer t;
    private Image offImage;
    private Graphics offGraphics;
    private Dimension offDimension;
    private boolean pausa;
    private int Nesferas;
    private int radio;
    private int gravedad;
    
    /**
     * metodo constructor de PanelDibujo.
     */
    public PanelDibujo(){
        Nesferas=0;
        radio=0;
        gravedad=0;
        base=new pendulodenewton.Logica.Base(Nesferas,radio,gravedad);
        t=new Timer(45,null);
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        t.addActionListener(this);
        t.start();
        pausa=false;
    }
    
    /**
     * metodo de dibujo.
     * @param g representa el pincel del panel de dibujo.
     */
    public void paintComponent (Graphics g){
        t_update(g);
    }
    
    /** 
     * es el doble buffer.
     * @param g representa el pincel del panel de dibujo.
     */
    public void t_update(Graphics g){
        Dimension d=getSize();
        if(offGraphics == null || d.width != offDimension.width || d.height != offDimension.height){
            offDimension=d;
            offImage=createImage(d.width,d.height);
            offGraphics=offImage.getGraphics();
        }
        
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        
        base.paint(offGraphics);
        
        g.drawImage(offImage,0,0,this);
    }
    
    /**
     * es el metodo que se encarga de controlar que la pantalla se pinte cada segundo.
     * @param ea representa el pasar del timer.
     */
    public void actionPerformed(ActionEvent ea){
        super.repaint();
    }
    
    /**
     * para el timer.
     */
    public void ponerPausa(){
        if(pausa){
            pausa=false;
            t.start();
        }
        else{
            pausa=true;
            t.stop();
        }
    }
    /**
     * cambia el numero de esferas.
     * @param n numero nuevo de esferas.
     */
    public void cambiarNumero(int n){
        base.cambiarNumero(n);
    }
    
    /**
     * cambia el radio de las esferas.
     * @param r nuevo radio.
     */
    public void cambiarRadio(int r){
        base.cambiarRadio(r);
    }
    
    /**
     * cambia la gravedad.
     * @param g nueva gravedad.
     */
    public void cambiarGravedad(int g){
        base.cambiarGravedad(g);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
      
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    /**
     * controla todo lo que pase cuando se apreta el mouse.
     * @param me accion de presiona un boton del mouse.
     */
    public void mousePressed(MouseEvent me) {
        base.select(me.getX(),me.getY());
    }
    
    /**
     * controla todo lo que pase cuando se suelta un boton del mouse.
     * @param me accion de soltar un boton del mouse.
     */
    public void mouseReleased(MouseEvent me) {
        base.diselect();
    }
    
    /**
     * controla todo lo que pase cuando presiona y arrastra el mouse.
     * @param me accion de presionar y arrastrar el mouse.
     */
    public void mouseDragged(MouseEvent me) {
        base.mover(me.getX(),me.getY());
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
