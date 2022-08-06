package pendulodenewton.Logica;
import pendulodenewton.*;
import java.awt.*;
import javax.swing.*;

/**
 * representa a las esferas del pendulo de Newton
 * controla la fisica de las esferas
 * @author grupo 9
 */
public class Esfera {
    
    private int radio;
    private int posX;
    private int posY;
    private int inicialX;
    private int inicialY;
    private Cuerda anclaje;
    private int gravedad;
    private double velX;
    private double aceleracionX;
    private boolean seleccionado;
    private boolean bloqueado;
    private static double pick;
    private static double orientacion;
    
    /**
     * metodo constructor de la clase Esfera.
     * @param x posicion x del centro de la esfera.
     * @param y posicion y del centro de la esfera.
     * @param r radio de la esfera.
     * @param m masa de la esfera.
     * @param g gravedad.
     * @param a puntero al objeto cuerda que sostiene a la esfera.
     */
    public Esfera(int x, int y,int r,int g,Cuerda a){
        posX=x;
        posY=y;
        inicialX=x;
        inicialY=y;
        radio=r;
        gravedad=g;
        anclaje=a;
        velX=0;
        seleccionado=false;
        bloqueado=false;
        pick=0;
    }
    
    /**
     * metodo de dibujo.
     * @param g representa el pincel del panel de dibujo.
     */
    public void paint(Graphics g){
        if(!seleccionado && !bloqueado){
            fisica();
            movimiento();
            moverFisica(posX,posY);
        }
        
        if(velX>0){
            orientacion=1;
        }
        if(velX<0){
            orientacion=-1;
        }
        
        System.out.println(velX + " " + anclaje.getAngulo());
        
        g.setColor(Color.GREEN);
        g.fillOval(posX-radio/2, posY-radio/2, radio, radio);
    }
    
    /**
     * selecciona una esfera la cual alguno de sus puntos sea el que señalan las cordenadas x e y cuando se apreta el mouse.
     * @param x cordenada x del mouse.
     * @param y cordenada y del mouse.
     */
    public void select(int x,int y){
        double distancia=Math.sqrt(Math.pow(x-posX, 2)+Math.pow(y-posY, 2));
        if(distancia<=radio/2){
            seleccionado=true;
            velX=0;
        }
    }
    
    /**
     * deselecciona las esferas cuando se suelta el mouse.
     */
    public void diselect(){
        if(seleccionado){
            seleccionado=false;
            aceleracionX=0;
            velX=0;
        }
    }
    
    /**
     * mueve la esfera hacia donde señalan las cordenadas x e y.
     * @param x cordenada x del mouse.
     * @param y cordenada y del mouse.
     */
    public void mover(int x,int y){
        if(seleccionado){
            if(y<anclaje.getY()) y=anclaje.getY();
            
            double distancia=Math.sqrt(Math.pow(x-anclaje.getX(), 2)+Math.pow(y-anclaje.getY(), 2));
            float vectX=x-anclaje.getX();
            float vectY=y-anclaje.getY();

            vectX=vectX/(float)distancia;
            vectY=vectY/(float)distancia;

            vectX=vectX*anclaje.getLargo();
            vectY=vectY*anclaje.getLargo();
        
            posX=(int)(vectX)+anclaje.getX();
            posY=(int)(vectY)+anclaje.getY();
        }
        
        anclaje.moverAnclaje(posX, posY);
    }
    
    /**
     * corrige la posicion de la esfera para mantener el largo de la cuerda.
     * @param x cordenada x actual de la esfera.
     * @param y cordenada Y actual de la esfera.
     */
    public void moverFisica(int x,int y){
        if(!seleccionado){
            double distancia=Math.sqrt(Math.pow(x-anclaje.getX(), 2)+Math.pow(y-anclaje.getY(), 2));
            float vectX=x-anclaje.getX();
            float vectY=y-anclaje.getY();

            vectX=vectX/(float)distancia;
            vectY=vectY/(float)distancia;

            vectX=vectX*anclaje.getLargo();
            vectY=vectY*anclaje.getLargo();
        
            posX=(int)(vectX)+anclaje.getX();
            posY=(int)(vectY)+anclaje.getY();
        }
        
        anclaje.moverAnclaje(posX, posY);
    }
    
    /**
     * aplica la fisica a la esfera en el eje x.
     */
    public void fisica(){
        
        if(anclaje.getAngulo()==1.5707963267948966 || anclaje.getAngulo()==-1.5707963267948966){
            posY=posY+5;
        }
        
       aceleracionX=-gravedad*Math.sin(anclaje.getAngulo());
       
       velX+=aceleracionX;
       
       if(aceleracionX==0 && pick==0){
           pick=Math.abs(velX);
       }
       
       if(pick!=0 && Math.abs(velX)>pick){
           if(velX<0){
               velX=-pick;
           }
           if(velX>0){
               velX=pick;
           }
       }
       
       
    }
    
    /**
     * mueve la esfera no seleccionada en relacion a su velocidad actual.
     */
    public void movimiento(){
        double distanciaX= velX*0.045;
        
        posX+=(int)distanciaX;
        
    }
    
    public boolean getSelect(){
        return seleccionado;
    }
    
    /**
     * le da velocidad a una esfera que fue chocada.
     * @param vel velocidad que se le pasa a la esfera.
     */
    public void acelerar(double vel){
        this.velX=vel;
    }
    
    /**
     * detiene una esfera que choco.
     * @return la velocidad que tenia la esfera antes de detenerse.
     */
    public double detener(){
        double aux=velX;
        velX=0;
        posX=inicialX;
        posY=inicialY;
        return aux;
    }
    
    public double getVel(){
        return velX;
    }
    
    public void setVel(double v){
        velX=v;
        bloqueado=true;
    }
    
    public void setX(int x){
        posX=x;
    }
    
    public void setY(int y){
        posY=y;
    }
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public double getOrientacion(){
        return orientacion;
    }
    
    /**
     * desbloqua la esfera.
     */
    public void desbloquear(){
        bloqueado=false;
    }
    
}

