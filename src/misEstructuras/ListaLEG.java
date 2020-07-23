package misEstructuras;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import misClases.Mozo;
import misClases.Pedido;
import misClases.Plato;

public class ListaLEG<E extends Mozo> {

    private NodoLEG<E> primero;
    private int talla;

    public ListaLEG() {
        this.primero = null;
        this.talla = 0;
    }

    public NodoLEG<E> getPrimero() {
        return primero;
    }

    public int getTalla() {
        return talla;
    }

    //reglas de negocio
    public void insertarAlInicio(E x) {
        NodoLEG<E> nuevo = new NodoLEG<E>(x);

        nuevo.setSiguiente(primero);
        primero = nuevo;
        talla++;
    }

    public void insertarAlFinal(E x) {
        NodoLEG<E> nuevo = new NodoLEG<E>(x);

        if (primero == null) {
            primero = nuevo;
        } else {
            NodoLEG<E> ptr = primero;
            while (ptr.getSiguiente() != null) {
                ptr = ptr.getSiguiente();
            }
            ptr.setSiguiente(nuevo);
        }
        talla++;
    }

    public void eliminarAlInicio() {
        //precondición: Lista no vacia

        //caso especial
        if (primero.getSiguiente() == null) {
            primero = null;
        } else {
            primero = primero.getSiguiente();
        }
        talla--;
    }

    public void eliminarAlFinal() {
        //precondición: Lista no vacia

        if (primero.getSiguiente() == null) {
            primero = null;
        } else {
            NodoLEG<E> ptr = primero, r = null;
            while (ptr.getSiguiente() != null) {
                r = ptr;
                ptr = ptr.getSiguiente();
            }
            r.setSiguiente(null);
        }
        talla--;
    }

    public String verLista() {
        String cad = "";

        NodoLEG<E> ptr = primero;
        while (ptr != null) {
            cad += ptr.getDato().toString() + "\n";
            ptr = ptr.getSiguiente();
        }
        return cad;
    }

   

    public void agregarMozoXApellido(E x) {
        if (primero == null) {
            insertarAlInicio(x);
        } else {
            NodoLEG<E> ptr = primero, ant = null;
            boolean estado = false;

            while (ptr != null && !estado) {
                if (!ptr.getDato().getApellidos().equalsIgnoreCase(x.getApellidos())) {
                    if (ptr.getDato().getApellidos().compareToIgnoreCase(x.getApellidos()) < 0) {
                        ant = ptr;
                        ptr = ptr.getSiguiente();
                    } else {
                        break;
                    }
                } else {
                    estado = true;
                    JOptionPane.showMessageDialog(null, "Mozo ya existe");
                }
            }

            if (!estado) {
                if (ptr != null) {
                    if (ptr == primero) {
                        insertarAlInicio(x);
                    } else {
                        NodoLEG<E> nuevo = new NodoLEG<>(x);
                        ant.setSiguiente(nuevo);
                        nuevo.setSiguiente(ptr);
                        talla -= -1;
                    }
                } else {
                    insertarAlFinal(x);
                }
            }
        }
    }

    public String retornarPedidos() {
        String cad = "";
        NodoLEG<E> tempN = primero;
        for (int i = 0; i < talla; i++) {
            int b = i + 1;
            cad += "\nMozo N°" + b + " : " + tempN.getDato().getApellidos();
            ArrayCola<Pedido> colita = new ArrayCola<Pedido>();
            //Copiamos la cola del mozo en una cola auxiliar
            while (tempN.getDato().getColaPedidos().colaVacia() == false) {
                colita.encolar(tempN.getDato().getColaPedidos().desencolar());
            }
            int k = 1;
            while (colita.colaVacia() == false) {
                cad += "\nPedido N°" + k + " :";
                k -= -1;
                Pedido refPE = colita.desencolar();
                for (int j = 0; j < refPE.getListaPlatos().length; j++) {
                    int a = j + 1;
                    cad += "\nPlato° " + a + " : " + refPE.getListaPlatos()[j].toString();

                }
                //cad += refPE.toString();
                tempN.getDato().getColaPedidos().encolar(refPE);
                cad += "\n\t";
            }
            tempN = tempN.getSiguiente();
        }
        return cad;
    }

    public String pedidosAños() {
        String cad = "";
        NodoLEG<E> tempN = primero;
        NodoLEG<E> mayor = tempN;
        while (tempN.getSiguiente() != null) {
            if (mayor.getDato().getAñosExperiencia() < tempN.getDato().getAñosExperiencia()) {
                mayor = tempN;
            }
            tempN = tempN.getSiguiente();
        }
        cad += mayor.getDato().toString();

        ArrayCola<Pedido> colita = new ArrayCola<Pedido>();
        //Copiamos la cola del mozo en una cola auxiliar
        while (mayor.getDato().getColaPedidos().colaVacia() == false) {
            colita.encolar(mayor.getDato().getColaPedidos().desencolar());
        }
        int k = 1;
        while (colita.colaVacia() == false) {

            cad += "\n   Pedido N°" + k + " :";
            k -= -1;
            Pedido refPE = new Pedido();
            refPE = colita.desencolar();

            for (int j = 0; j < refPE.getListaPlatos().length; j++) {
                int c = j + 1;
                cad += "\nPlato n° " + c + " " + refPE.getListaPlatos()[j].toString();
            }
            cad += "\n\t Monto de Pago es: " + refPE.getMontoPago();
            mayor.getDato().getColaPedidos().encolar(refPE);
            cad += "\n\t";
        }

        return cad;
    }

    public double calcularPedidos() {
        NodoLEG<E> tempN = primero;
        double monto = 0.0;
        while (tempN != null) {
            tempN.getDato().calcularMontoRecaudado();
            monto += tempN.getDato().getMontoRecaudado();
            tempN = tempN.getSiguiente();
        }
        return monto;
    }

    public void agregarPedidoAleatorio(Pedido p) {

        Random rd = new Random();
        int valor = rd.nextInt(talla) + 1;
        int i = 0;

        NodoLEG<E> ptr = primero;

        NodoLEG<E> elegido = ptr;

        while (ptr != null && i < valor) {
            ptr = ptr.getSiguiente();
            elegido = ptr;

        }

        ArrayCola<Pedido> colita = elegido.getDato().getColaPedidos();
        colita.encolar(p);
    }

    public NodoLEG<E> seleccionarMozoAleatorio() {
        NodoLEG<E> ptr = primero;
        Random randito = new Random();
        int aleatorio = randito.nextInt(talla);
        for (int i = 0; i < aleatorio; i++) {
            ptr = ptr.getSiguiente();
        }
        return ptr;
    }

    public void modificarMozo(String apellido, Pedido pedido) {
        NodoLEG<E> ptr = primero;
        boolean estado = true;
        while (ptr.getSiguiente() != null && estado == true) {
            if (ptr.getDato().getApellidos().equalsIgnoreCase(apellido)) {
                ptr.getDato().encolarPedido(pedido);
                estado = false;
            }
            ptr = ptr.getSiguiente();
        }
        if (estado == true) {
            ptr.getDato().encolarPedido(pedido);
        }
    }

    public NodoLEG<E> obtenerMozoApe(String apellido) {
        NodoLEG<E> ptr = primero;
        boolean estado = false;

        while (ptr != null && !estado) {
            if (ptr.getDato().getApellidos().equalsIgnoreCase(apellido)) {
                estado = true;
            } else {

                ptr = ptr.getSiguiente();

            }

        }
        if (estado == false) {
            JOptionPane.showMessageDialog(null, "Ingrese un apellido válido");
        }

        return ptr;
    }

    public ArrayList<Pedido> obtenerPedidos(NodoLEG<E> x) {

        ArrayList<Pedido> listita = new ArrayList<Pedido>();

        while (x.getDato().getColaPedidos().colaVacia() == false) {
            listita.add(x.getDato().getColaPedidos().desencolar());

        }
        for (Pedido pedido : listita) {
            x.getDato().getColaPedidos().encolar(pedido);
        }

        return listita;
    }

    public Plato[] obtenerPlatos(ArrayList<Pedido> p, NodoLEG<E> x) {

        ArrayList<Pedido> listita = obtenerPedidos(x);

        Plato[] listaP = new Plato[4];
        for (int i = 0; i < listita.size(); i++) {
            listaP = listita.get(i).getListaPlatos();
        }

        return listaP;
    }

    //Aca se pierden los pedidos
    public double calcularMontoRecaudacion(NodoLEG<E> x) {
        NodoLEG<E> ptr = primero;
        double monto = 0;
        //while (ptr != null) {
        while ( //!ptr.getDato().getColaPedidos().colaVacia()
                !x.getDato().getColaPedidos().colaVacia()) {
            Pedido valo = x.getDato().getColaPedidos().desencolar();
            valo.calcularMontoPago();
            monto += valo.getMontoPago();

        }
        ptr = ptr.getSiguiente();
        //}
        return monto;
    }

}
