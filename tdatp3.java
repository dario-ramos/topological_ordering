import java.io.IOException;

public class tdatp3{
	private static Grafo g;
	
	/*
	 * Realiza la carga de los datos del archivo "nomArch" en
	 * el grafo g.
	 */
	public static Grafo parseo(String nomArch){
		TablaHash tablaHash = new TablaHash();
		Grafo grafo = new Grafo();
		ArchivoEntrada entrada = null;
		try{
			entrada = new ArchivoEntrada(nomArch);
		}catch(IOException e){
			System.out.println("ERROR EN LA ENTRADA.");
			System.exit(1);//Sale del programa si no se puede parsear.
		}
		String linea = entrada.readln();
		while(linea !=null){
			int i = 0;
			int iAnt = i;
			while(linea.charAt(i)!= ':')i++; //Busco los ":" siguientes al c�digo de la materia.
			String codMateria = linea.substring(iAnt,i);
			Vertice v = tablaHash.get(codMateria);
			if (v==null){//Si a�n no se ha creado un v�rtice con el c�digo de materia le�do...
				v = new Vertice(codMateria);
				tablaHash.add(v);
			}
			i+=2; //Salteo el " " luego de los ":".
			while (i <= linea.length()){
				iAnt = i;
				while((i<linea.length())&&(linea.charAt(i)!= ','))i++; //Busco la "," siguiente al c�digo de la materia.
				codMateria = linea.substring(iAnt,i);
				if(!(codMateria.length()==0)){
					Vertice ady = tablaHash.get(codMateria);
					if (ady==null){//Si a�n no se ha creado un v�rtice con el c�digo de materia le�do...
						ady = new Vertice(codMateria);
						tablaHash.add(ady);
					}
					v.addAdyacente(ady);
				}
				i++;
			}
			grafo.addVertice(v);
			linea = entrada.readln(); 
		}
		return grafo;
	}
	
	public static void mostrarPila (Pila p,Grafo g,long tiempo)throws eEstructuraVacia{
		ArchivoSalida arch = new ArchivoSalida("con_pila.txt");
		arch.println("--ORDEN TOPOL�GICO GENERADO USANDO UNA PILA--");
		arch.println("---------------------------------------------");
		arch.println("Tiempo insumido: "+tiempo+"ms.");
		arch.println("Cantidad de v�rtices: "+g.getCantVertices()+".");
		arch.println("Cantidad de aristas: "+g.getCantAristas()+".");
		arch.println("---------------------------------------------");
		while(!p.estaVacia()){
			Vertice v = (Vertice)p.desapilar();
			arch.println(v.getElemento());
		}
		arch.println("---------------------------------------------");
		arch.guardarCambios();
	}

	public static void mostrarCola (Cola c,Grafo g, long tiempo)throws eEstructuraVacia{
		ArchivoSalida arch = new ArchivoSalida("con_cola.txt");
		arch.println("--ORDEN TOPOL�GICO GENERADO USANDO UNA COLA--");
		arch.println("---------------------------------------------");
		arch.println("Tiempo insumido: "+tiempo+"ms.");
		arch.println("Cantidad de v�rtices: "+g.getCantVertices()+".");
		arch.println("Cantidad de aristas: "+g.getCantAristas()+".");
		arch.println("---------------------------------------------");
		while(!c.estaVacia()){
			Vertice v = (Vertice)c.desencolar();
			arch.println(v.getElemento());
		}
		arch.println("---------------------------------------------");
		arch.guardarCambios();
	}
	
	public static void main(String[] args) {
		System.out.println("---------------------------------------------------");
		System.out.println("TRABAJO PR�CTICO n�3.");
		System.out.println("INTEGRANTES DEL GRUPO:");
		System.out.println("   >Mat�as Aleardo Mazzei   84.496");
		System.out.println("   >Dar�o Eduardo Ramos     84.885");
		System.out.println("---------------------------------------------------");
		try{
			g = parseo(args[0]);

			long tf,ti;
			//GENERO EL ORDEN TOPOL�GICO USANDO UNA PILA Y LO MUESTRO EN PANTALLA.
			System.out.println("Ordenando topol�gicamente con una pila...");
			ti = System.currentTimeMillis();
			Pila p = g.ordenTopologicoP();
			tf = System.currentTimeMillis();
			mostrarPila(p,g,tf-ti);
			
			//GENERO EL ORDEN TOPOL�GICO USANDO UNA COLA Y LO MUESTRO EN PANTALLA.
			System.out.println("Ordenando topol�gicamente con una cola...");
			ti = System.currentTimeMillis();
			Cola c = g.ordenTopologicoC();
			tf = System.currentTimeMillis();
			mostrarCola(c,g,tf-ti);
			System.out.println("Programa terminado correctamente");
		}catch(eEstructuraVacia e){
			System.err.println("�HUBO UN ERROR!");
			e.printStackTrace();
			System.exit(1);
		}
	}
}