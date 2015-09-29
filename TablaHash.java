public class TablaHash {
	private Vertice[] vertices;
	private int tamanio; //Representa la capacidad actual de la tabla.
	private int cant; //Representa la cantidad de elementos de la tabla.

	public TablaHash(){
		vertices = new Vertice[1000];
		tamanio = 1000;
		cant = 0;
	}

	public TablaHash(int tamanio){
		vertices = new Vertice[tamanio];
		this.tamanio = tamanio;
		cant = 0;
	}

	/*
	 * Devuelve el vertice cuyo c�digo de materia es el dado.
	 * En caso de que no pertenezca a la tabla, devuelve null.
	 * Para hallarlo se aplica el siguiente m�todo:
	 * 1_ Calculo: "a = hashCode (c�digo de la materia)".
	 * 2_ Si la ubicaci�n est� vac�a, devuelvo null.
	 * 3_ Si la ubicaci�n est� ocupada por la materia pedida, la devuelvo.
	 * 4_ En otro caso, sumo uno a "a" y vuelvo al paso 2.
	 */
	public Vertice get(String nombre){
		int cod = hashCode (nombre);
		do{
			if (vertices[cod] == null) return null;
			if (vertices[cod].getElemento().equals(nombre)) return vertices[cod];
			cod++;
		}while (true);
	}

	/*
	 * Agrega el vertice a la tabla.
	 * Para elegir la ubicaci�n en la que se coloca se siguen los siguientes pa-
	 * sos:
	 * 1_ Calculo: "a = hashCode (codigo de la materia)".
	 * 2_ Si la ubicaci�n est� vac�a, lo agrego.
	 * 3_ En otro caso, sumo uno a "a" y vuelvo al paso 2.
	 * Si llevo la tabla a un nivel de ocupaci�n de m�s del 70%, duplico su tama-
	 * �o y reubico todos los elementos.
	 */
	public void add(Vertice i){
		int cod = hashCode (i.getElemento().toString());//El c�digo de materia es una clave.
		while (vertices[cod] != null){
			cod++;
			cod%= tamanio;
		}
		vertices[cod] = i;
		cant++;
		if (cant >= Math.round(tamanio * 0.7f)) reHashear();
	}

/*
 *EN EL PROGRAMA QUE IMPLEMENTAMOS NO ES NECESARIO REMOVER ELEMENTOS.
	public void remove(Individuo i){
		return;
	}
*/

	/*
	 * Devuelve el c�digo de hash de un vertice dado su c�digo de materia.
	 * codigo = c0 * B^n + c1 * B^(n-1) + ... + cn
	 * Donde ci es el i-�simo caracter del nombre (convertido a min�sculas y qui-
	 * t�ndole los espacios que haya antes de la primera inicial o despu�s de la
	 * �ltima letra).
	 * B = 26 (de este modo, si no hubiera restricciones como el tama�o de la ta-
	 * bla de hash o de un m�ximo representable por "int", la asignaci�n del c�-
	 * digo ser�a biun�voca).
	 */ 
	private int hashCode(String nombre){
		//El nombre en min�sculas y sin espacios al comienzo o al final.
		String cad = nombre.trim().toLowerCase();
		int codigo = (int)(nombre.charAt(0));
		for (int i=1;i<cad.length();i++){
			codigo*= 5;
			codigo+= (nombre.charAt(0) - 'a') + 1;
			codigo%= tamanio;
		}
		return codigo;
	}
	/*
	 * Duplica el tamanio de la tabla de hash.
	 * Para logarlo, crea una nueva tabla del doble de tamanio que la actual y
	 * copia todo el contenido de la vieja en ella.
	 */
	private void reHashear(){
		int cantCopiada = 0;
		TablaHash aux = new TablaHash(tamanio * 2);
		for (int i = 0;(i < tamanio)||(cantCopiada < cant); i++)
			if (vertices[i] != null){
				aux.add(vertices[i]);
				cantCopiada++;
			}
		this.vertices = aux.vertices;
	}
}