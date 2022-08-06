package pendulodenewton.Logica;
import pendulodenewton.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * representa la basa de donde salen las cuerdas
 * y controla el las colisiones en el sistema de esferas
 * @author grupo 9
 */
public class Base {
    
    private int largo;
    private int ancho;
    private Cuerda cuerda;
    private int posX;
    private int posY;
    private int Nesferas;
    private int radio;
    private int gravedad;
    private int particion;
    private ArrayList arregloCuerdas;
    private Cuerda[] vector;
    private boolean aux;
    
    /**
     * metodo constructor de clase.
     * @param n numero de esferas.
     * @param r radio de las esferas.
     * @param m masa de las esferas.
     * @param g gravedad de las esferas.
     */
    public Base(int n,int r,int g){
        Nesferas=n;
        radio=r;
        gravedad=g;
        largo=4*radio;
        ancho=20;
        posX=400;
        posY=200;
        aux=false;
        arregloCuerdas= new ArrayList();
    }
    
    /**
     * metodo de dibujo.
     * @param g representa el pincel del panel de dibujo.
     */
    public void paint (Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(posX, posY, largo, ancho);
        
        colision();
        
        for(int i=0;i<arregloCuerdas.size();i=i+1){
            cuerda=(Cuerda)arregloCuerdas.get(i);
            cuerda.paint(g);
        }
        
    }
    
    /**
     * cambia el numero de esferas.
     * @param n numero nuevo de esferas.
     */
    public void cambiarNumero(int n){
        Nesferas=n;
        largo=(int)((Nesferas*radio));
        
        arregloCuerdas.removeAll(arregloCuerdas);
        if(Nesferas>0){
            particion=largo/Nesferas;
            for(int i=posX;i<largo+posX;i=i+particion){
                cuerda=new Cuerda(i,posY+ancho,radio,gravedad);
                arregloCuerdas.add(cuerda);
            }
        }
        
    }
    
    /**
     * 
     * cambia el radio de las esferas.
     * @param r nuevo radio.
     */
    public void cambiarRadio(int r){
        radio=r;
    }
    
    /**
     * cambia la gravedad.
     * @param g nueva gravedad.
     */
    public void cambiarGravedad(int g){
        gravedad=g;
    }
    
    /**
     * mueve la esfera hacia donde señalan las cordenadas x e y.
     * @param x cordenada x del mouse.
     * @param y cordenada y del mouse.
     */
    public void mover(int x,int y){
        int j=arregloCuerdas.size();
        vector = new Cuerda[j];
        for(int i=0;i<j;i=i+1){
            vector[i]=(Cuerda)arregloCuerdas.get(i);
        }
        arregloCuerdas.clear();
        for(int i=0;i<j;i=i+1){
             vector[i].mover(x, y);
             arregloCuerdas.add(vector[i]);
        }
    }
    
    /**
     * selecciona una esfera la cual alguno de sus puntos sea el que señalan las cordenadas x e y cuando se apreta el mouse.
     * @param x cordenada x del mouse.
     * @param y cordenada y del mouse.
     */
    public void select(int x,int y){
        int j=arregloCuerdas.size();
        vector = new Cuerda[j];
        for(int i=0;i<j;i=i+1){
            vector[i]=(Cuerda)arregloCuerdas.get(i);
        }
        arregloCuerdas.clear();
        for(int i=0;i<j;i=i+1){
            if(!aux) vector[i].select(x, y);
            if(vector[i].getSelect()) aux=true;
            arregloCuerdas.add(vector[i]);
        }
    }
    
    /**
     * deselecciona las esferas cuando se suelta el mouse.
     */
    public void diselect(){
        int j=arregloCuerdas.size();
        vector = new Cuerda[j];
        for(int i=0;i<j;i=i+1){
            vector[i]=(Cuerda)arregloCuerdas.get(i);
        }
        arregloCuerdas.clear();
        for(int i=0;i<j;i=i+1){
             vector[i].diselect();
             vector[i].desbloquear();
             arregloCuerdas.add(vector[i]);
        }
        aux=false;
    }
    
    /**
     * controla las colisiones entre esferas.
     */
    public void colision(){
        
        for(int i=0;i<arregloCuerdas.size()-1;i=i+1){

            Cuerda pelota1=(Cuerda)arregloCuerdas.get(i);
            Cuerda pelota2=(Cuerda)arregloCuerdas.get(i+1);
            
            if(Math.abs(pelota1.getX2()-pelota2.getX2())<radio){
                if(pelota1.getSelect() || pelota2.getSelect()){
                    if(!pelota1.getSelect()){
                        pelota1.setVel(0);
                        pelota1.setX(pelota2.getX2()-radio);
                        pelota1.setY(pelota2.getY2());
                        
                        int j=1;
                        
                        Cuerda pelotaAux1=pelota1;
                        
                        while(i-j>=0){
                            Cuerda pelotaAux2=(Cuerda)arregloCuerdas.get(i-j);
                            if(Math.abs(pelotaAux1.getX2()-pelotaAux2.getX2())<radio){
                                pelotaAux2.setVel(0);
                                pelotaAux2.setX(pelota2.getX2()-radio*(j+1));
                                pelotaAux2.setY(pelota2.getY2());
                                j++;
                                pelotaAux1=pelotaAux2;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    if(!pelota2.getSelect()){
                        pelota2.setVel(0);
                        pelota2.setX(pelota1.getX2()+radio);
                        pelota2.setY(pelota1.getY2());
                        
                        int j=1;
                        
                        Cuerda pelotaAux1=pelota2;
                        
                        while(i+1+j<arregloCuerdas.size()){
                            Cuerda pelotaAux2=(Cuerda)arregloCuerdas.get(i+1+j);
                            if(Math.abs(pelotaAux1.getX2()-pelotaAux2.getX2())<radio){
                                pelotaAux2.setVel(0);
                                pelotaAux2.setX(pelota1.getX2()+radio*(j+1));
                                pelotaAux2.setY(pelota1.getY2());
                                j++;
                                pelotaAux1=pelotaAux2;
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
                
            }
        }
        
        int tam=arregloCuerdas.size()-1;
        int der=0;
        boolean aux2;
        if(tam>0){
            Cuerda pelota1=(Cuerda)arregloCuerdas.get(0);
            Cuerda pelota2=(Cuerda)arregloCuerdas.get(tam);
            
            if(!pelota1.getSelect() && !pelota2.getSelect()){
                
                if(pelota1.getOrientacion()>0){
                    der=1;
                }
                
                if(pelota1.getOrientacion()<0){
                    der=2;
                }
                
                boolean aux=true;
                
                if(der==2){
                    for(int i=0;i<arregloCuerdas.size()-1;i=i+1){
                        boolean comprobante;
                        pelota1=(Cuerda)arregloCuerdas.get(i+1);
                        pelota2=(Cuerda)arregloCuerdas.get(i);
                        if(!pelota1.getSelect() && !pelota2.getSelect()){
                            if(Math.abs(pelota1.getX2()-pelota2.getX2())<radio && !pelota1.getSelect() && !pelota2.getSelect()){
                                comprobante=false;
                    
                                if(pelota1.getVel()==0 && pelota2.getVel()!=0){
                                    pelota1.acelerar(pelota2.detener());
                                    comprobante=true;
                                }
                            
                                if(pelota2.getVel()==0 && pelota1.getVel()!=0 && !comprobante) {
                                    pelota2.acelerar(pelota1.detener());
                                }
                            }
                        }
                        
                        //else{break;}
                    }
                    aux=false;
                }
                
                //int i=arregloCuerdas.size()-1;i>=3;i=i-1
        
                if(der==1 && aux){
                    for(int i=arregloCuerdas.size()-1;i>0;i=i-1){
                        boolean comprobante;
                        pelota1=(Cuerda)arregloCuerdas.get(i-1);
                        pelota2=(Cuerda)arregloCuerdas.get(i);
                        
                        if(!pelota2.getSelect() && !pelota1.getSelect()){
                                if(Math.abs(pelota2.getX2()-pelota1.getX2())<radio){
                                comprobante=false;
                                
                                if(pelota2.getVel()==0 && pelota1.getVel()!=0) {
                                    pelota2.acelerar(pelota1.detener());
                                    comprobante=true;
                                }
                    
                                if(pelota1.getVel()==0 && pelota2.getVel()!=0 && !comprobante){
                                    pelota1.acelerar(pelota2.detener());
                                }
                                
                            }
                        }
                        
                        //else{break;}
                    }
                }
            }
        }
    }
        
}
   
