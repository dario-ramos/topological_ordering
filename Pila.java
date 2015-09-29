
public class Pila {
	private Nodo tope;
	
	public Pila(){
		tope = null;
	}
	public void apilar(Object nuevoElemento){		
		Nodo nuevo = new Nodo(nuevoElemento);
		nuevo.setSiguiente(tope);
		tope = nuevo;		
	}
	public Object desapilar() throws eEstructuraVacia{
		if (tope == null) throw (new eEstructuraVacia());
		else{
			Object primero = tope.getElemento();
			Nodo aux = tope;
			tope = aux.getSiguiente();
			aux = null;
			return primero;			
		}		
	}
	public boolean estaVacia(){
		return (tope == null);
	}
	public void vaciar() throws eEstructuraVacia{
		if (tope == null) throw (new eEstructuraVacia());
		while (tope!=null){
			Nodo aux = tope;
			tope = aux.getSiguiente();
			aux = null;				
		}
	}
}
