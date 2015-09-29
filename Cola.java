public class Cola {
	private Nodo pri;
	private Nodo ult;
	
	public Cola(){
		pri = null;
		ult = null;
	}
	public void encolar(Object nuevoElemento){		
		Nodo nuevo = new Nodo(nuevoElemento);
		if (pri == null){
			pri = nuevo;
			ult = nuevo;
		}
		else{
			ult.setSiguiente(nuevo);
			ult = nuevo;
		}
	}
	public Object desencolar() throws eEstructuraVacia{
		if (pri == null) throw (new eEstructuraVacia());		
		Object primero = pri.getElemento();
		Nodo aux = pri;
		pri = aux.getSiguiente();
		if (pri == null) ult = null;
		aux = null;			
		return primero;				
	}
	public boolean estaVacia(){
		return ((pri == null) && (ult ==null));
	}
	public void vaciar() throws eEstructuraVacia{		
		if (pri == null) throw (new eEstructuraVacia());		
		while (pri!=null){
			Nodo aux = pri;
			pri = aux.getSiguiente();
			aux = null;				
		}
		ult = null;		
	}
}