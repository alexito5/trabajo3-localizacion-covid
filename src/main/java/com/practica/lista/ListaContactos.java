package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
public void insertarNodoTemporal(PosicionPersona p) {
    NodoTemporal aux = lista, ant = null;
    boolean encontrado = false;
    while (aux != null && !encontrado) {
        if (aux.getFecha().compareTo(p.getFechaPosicion()) == 0) {
            encontrado = true;
            NodoPosicion npActual = aux.getListaCoordenadas();
            NodoPosicion npAnt = null;
            while (npActual != null) {
                if (npActual.getCoordenada().equals(p.getCoordenada())) {
                    npActual.setNumPersonas(npActual.getNumPersonas() + 1);
                    return;
                } else {
                    npAnt = npActual;
                    npActual = npActual.getSiguiente();
                }
            }
            NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
            if (aux.getListaCoordenadas() == null)
                aux.setListaCoordenadas(npNuevo);
            else
                npAnt.setSiguiente(npNuevo);
        } else if (aux.getFecha().compareTo(p.getFechaPosicion()) > 0) {
            break;
        }
        ant = aux;
        aux = aux.getSiguiente();
    }
    if (!encontrado) {
        NodoTemporal nuevo = new NodoTemporal();
        nuevo.setFecha(p.getFechaPosicion());
        NodoPosicion npActual = nuevo.getListaCoordenadas();
        NodoPosicion npAnt = null;
        while (npActual != null) {
            if (npActual.getCoordenada().equals(p.getCoordenada())) {
                npActual.setNumPersonas(npActual.getNumPersonas() + 1);
                return;
            } else {
                npAnt = npActual;
                npActual = npActual.getSiguiente();
            }
        }
        NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
        if (nuevo.getListaCoordenadas() == null)
            nuevo.setListaCoordenadas(npNuevo);
        else
            npAnt.setSiguiente(npNuevo);
        if (ant != null) {
            nuevo.setSiguiente(aux);
            ant.setSiguiente(nuevo);
        } else {
            nuevo.setSiguiente(lista);
            lista = nuevo;
        }
        this.size++;
    }
}


	
	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + nodo.getNumPersonas();
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + 1;
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	@Override
	public String toString() {
		String cadena="";
		int cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" +  aux.getFecha().getHora().toString() + " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}
	
	
	
}
