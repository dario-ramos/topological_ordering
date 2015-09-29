import java.util.ArrayList;
import java.util.List;

public class Vertice {
	private Object elemento; //Elemento representado por este vértice.
	private ArrayList adyacentes; //Vértices adyacentes a este.
	// Utilizado en el algoritmo de Orden topológico con cola.
	// En realidad, no representa al concepto formal de grado de entrada ya que cambia
	// a lo largo de la ejecución del algoritmo. 
	private int grEntrada;
	private boolean listado; //Utilizado por los algoritmos de orden topológico. 
	
	public Vertice(Object elemento){
		super();
		this.elemento = elemento;
		adyacentes = new ArrayList();
		grEntrada = 0;
		listado = false;
	}
	public void addAdyacente(Vertice ady){
		adyacentes.add(ady);
		ady.incGrEntrada();
	}
	public void incGrEntrada(){
		grEntrada++;
	}
	public void decGrEntrada(){
		grEntrada--;
		grEntrada = Math.max(0,grEntrada); //Para que no ocurra que grEntrada < 0.	
	}
	public void setListado(boolean listado){
		this.listado = listado;
	}
	public int getGrEntrada(){
		return grEntrada;
	}
	public List getAdyacentes(){
		return adyacentes;
	}
	public Object getElemento(){
		return elemento;
	}
	public boolean isListado(){
		return listado;
	}
}