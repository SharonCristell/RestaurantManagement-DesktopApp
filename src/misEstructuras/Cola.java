package misEstructuras;

public interface Cola <E>{
    void encolar(E x);
    E desencolar();
    E frenteC();                    // método consultor
    boolean colaVacia();
    boolean colaLlena();   
}
