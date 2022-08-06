package pendulodenewton.Grafica;
import pendulodenewton.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


/**
 * clase Ventana crea la ventana donde se dibujan
 * los paneles, botones y cuadros de texto
 * @author grupo 9
 */
public class Ventana extends JFrame{
    
    private PanelDibujo dp;
    private IngresaTexto numero;
    private IngresaTexto radio;
    private IngresaTexto gravedad;
    
    
    /**
     * metodo constructor de ventana
     */
    public Ventana(){
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        dp= new PanelDibujo();
        this.add(dp,BorderLayout.CENTER);
        
        JPanel interfaz=new JPanel();
        
        this.add(interfaz,BorderLayout.EAST);
        interfaz.setLayout(new GridLayout(8,1));
        
        numero=new IngresaTexto(10);
        radio=new IngresaTexto(10);
        gravedad=new IngresaTexto(10);
        BotonPausa pausa=new BotonPausa("pausa");
        BotonIngresar inicio=new BotonIngresar("incio");
        TextoDeArea numero_esfera= new TextoDeArea("Numero de esferas:",1,1);
        TextoDeArea radio_esfera = new TextoDeArea("Radio de esferas:",1,1);
        TextoDeArea gravedad_esfera = new TextoDeArea("Gravedad:",1,1);
        interfaz.add(numero_esfera);        
        interfaz.add(numero);
        interfaz.add(radio_esfera);
        interfaz.add(radio);
        interfaz.add(gravedad_esfera);
        interfaz.add(gravedad);
        interfaz.add(pausa);
        interfaz.add(inicio);
        
        this.setSize(1800,600);
        this.setVisible(true);
        
    }
    
    private class IngresaTexto extends JTextField{
        
        public IngresaTexto(int x){
            super(x);
        }
        
        /**
         * permite obtener el numero escrito en los cuadros de texto
         * @return el numero escrito en el cuadro
         */
        public int obtenerNumero(){
            
            int value=0;
                    
            try{
                value = Integer.parseInt(getText());
            }catch(NumberFormatException e){
            }
            return value;
        }
    }
    
    private class BotonPausa extends JButton implements ActionListener{
        public BotonPausa(String nom){
            super(nom);
            this.addActionListener(this);
        }
        
        /**
         * transmite la accion de pausa al panel de dibujo
         * @param e representa la acccion de pulsar el boton pausa.
         */
        public void actionPerformed(ActionEvent e){
           dp.ponerPausa();
       }
    }
    
    private class BotonIngresar extends JButton implements ActionListener{
        public BotonIngresar(String nom){
            super(nom);
            this.addActionListener(this);
        }
        
        /**
         * transmite la accion de iniciar la simulacion
         * @param e representa la acccion de pulsar el boton inicio.
         */
        public void actionPerformed(ActionEvent e){
            if(numero.obtenerNumero()>0 && radio.obtenerNumero()>0 && gravedad.obtenerNumero()>0){
                dp.cambiarRadio(radio.obtenerNumero());
                dp.cambiarGravedad(gravedad.obtenerNumero());
            
            dp.cambiarNumero(numero.obtenerNumero());
            }
       }
    }
    
     private class TextoDeArea extends TextArea{
        
        public TextoDeArea(String s,int x, int y){
            super.setText(s);
            super.setColumns(y);
            super.setRows(x);
            super.setEditable(false);
        }
    }
    
}
