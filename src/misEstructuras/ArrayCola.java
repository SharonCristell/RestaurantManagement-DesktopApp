package misEstructuras;

import javax.swing.JOptionPane;

public class ArrayCola <E> implements Cola<E>{
    private static final int TAM=6;
    private E[]arC;
    private int frente,finalc;

    public ArrayCola() {
        arC=(E[]) new Object[TAM];
        
        frente=-1;
        finalc=-1;        
    }
    
    //reglas de negocio
    public boolean colaVacia(){
        return (frente==-1 && finalc==-1);
    }
    
    public boolean colaLlena(){
        return ((frente==0 && finalc==TAM-1) || frente==finalc+1);
    }
    
    public void encolar(E x){        
        if(colaLlena()==false){
            //caso especial
            if(colaVacia()==true){
                frente=0;
                finalc=0;
            }else if(finalc==TAM-1){
                finalc=0;
            }else{
                finalc++;
            }
            arC[finalc]=x;            
        }else{
            JOptionPane.showMessageDialog(null, "Cola Llena");
        }       
    }
    
    public E frenteC(){
        return arC[frente];
    }
    public E desencolar(){
        E elemento=arC[frente];
        
        if(frente==finalc){ //cuando hay un solo elemento
            frente=-1;
            finalc=-1;
        }else if(frente==TAM-1){
            frente=0;
        }else{
            frente++;
        }
        return elemento;        
    }
    public void eliminarUltimo(){
        ArrayCola<E> cAux=new ArrayCola<E>();
        
        while (!colaVacia()){
            E x=desencolar();
            
            if(!colaVacia()){
                cAux.encolar(x);
            }            
        }
        while(!cAux.colaVacia()){
            encolar(cAux.desencolar());
        }       
    }
    
    public String verColaDeDatos(){
        String cad="";
        
        ArrayCola<E> cAux=new ArrayCola<E>();
        
        while(!colaVacia()){
            E valor=desencolar();
            cad=cad+valor +"  ";
            cAux.encolar(valor);
        }
        
        while(!cAux.colaVacia()){
            encolar(cAux.desencolar());
        }       
        return cad;
    }
    
    
    
    
}
