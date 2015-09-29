public class Nodo {
	private Object elemento;
	private Nodo siguiente;
	
	public Nodo(Object dato){
		elemento = dato;
		siguiente = null;
	}	
	public Object getElemento(){
		return elemento;
	}
	public Nodo getSiguiente(){
		return siguiente;
	}
	public void setSiguiente(Nodo sig){
		siguiente = sig;
	}
}