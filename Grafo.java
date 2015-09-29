import java.util.ArrayList;
import java.util.Iterator;

public class Grafo {
	private ArrayList listaAdy;
	
	public Grafo(){
		super();
		listaAdy = new ArrayList();
	}
	public void addVertice(Vertice v){
		listaAdy.add(v);
	}
	public Object getVertice(int i) throws eEstructuraVacia{
		if (i>=listaAdy.size()) throw new eEstructuraVacia();
		return (((Vertice)listaAdy.get(i)).getElemento());
	}
	public int getCantAristas(){
		Iterator it = listaAdy.iterator();
		int cant = 0;
		while(it.hasNext()){
			Vertice v = (Vertice)it.next();
			cant+= v.getAdyacentes().size(); 
		}
		return cant;
	}
	public int getCantVertices(){
		return listaAdy.size();
	}
	/*----------------------------------------------------------------------------
	 * Ordena topológicamente el grafo utilizando una cola en
	 * la implementación del algoritmo.
	 */
	public Cola ordenTopologicoC() throws eEstructuraVacia{
		Cola c = new Cola();
		Cola retorno = new Cola();
		//Marca todos los vértices como "no listado".
		for (int i=0;i<listaAdy.size();i++) ((Vertice)listaAdy.get(i)).setListado(false);
		//Agrego todos los vértices con grado de entrada igual a cero a la cola.
		for(int i=0;i<listaAdy.size();i++){
			Vertice v = (Vertice)listaAdy.get(i);
			if(v.getGrEntrada()==0){
				c.encolar(v);
				v.setListado(true);
			}
		}
		while(!c.estaVacia()){
			Vertice v = (Vertice)c.desencolar();
			Iterator it = v.getAdyacentes().iterator();
			retorno.encolar(v);
			while(it.hasNext()){
				//Recorro todos los vértices de v, decrementando su "grado de entrada"
				//y encolándolos, de ser necesario.
				Vertice ady = (Vertice)it.next();
				ady.decGrEntrada();
				if((ady.getGrEntrada()==0)&&(!ady.isListado())){
					c.encolar(ady);
					ady.setListado(true);
				}
			}
		}
		return retorno;
	}
	/*----------------------------------------------------------------------------
	 * Ordena topológicamente el grafo utilizando una pila en
	 * la implementación del algoritmo.
	 */
	public Pila ordenTopologicoP(){
		Pila p = new Pila();
		//Marca todos los vértices como "no listado".
		for (int i=0;i<listaAdy.size();i++) ((Vertice)listaAdy.get(i)).setListado(false);
		//Recorre el grafo, listando sus vértices.
		for(int i=0;i<listaAdy.size();i++){
			Vertice v = (Vertice)listaAdy.get(i);
			if (!v.isListado())
				apilarNodo(v,p);
		}
		return p;
	}
	/*
	 * Recorre todos los vértices adyacentes a v apilándolos (si no han sido apilados) y,
	 * a continuación, apila a v.
	 * El procedimiento es recursivo.
	 */
	private void apilarNodo(Vertice v, Pila p){
		v.setListado(true);
		Iterator it = v.getAdyacentes().iterator();
		while(it.hasNext()){
			Vertice ady = (Vertice)it.next();
			if(!ady.isListado()) apilarNodo(ady,p);
		}
		p.apilar(v);
	}
}