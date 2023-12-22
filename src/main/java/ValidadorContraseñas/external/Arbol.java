package ValidadorContraseñas.external;

public class Arbol {
    public Nodo raiz;
    public Arbol(){
      this.raiz = null;

    }

    public void insertarNodo(String info){
        Nodo n = new Nodo();
        n.info = new String(info);
         if(this.raiz == null){
            this.raiz = n;
           // System.out.print("Insertó raiz: "+ n.info +"\n");
        }else {
             Nodo aux = raiz;
            // System.out.print("Aux info: " + aux.info+ "\n");
             while (aux != null) {
                 n.padre = aux;
                 if (n.info.compareTo(aux.info) >= 0) {
                     aux = aux.derecha;
                 } else
                     aux = aux.izquierda;
             }
             //System.out.print("Encontro donde colgarlo \n");
          //  System.out.print("Aux info: " + aux.info+ "\n");
             if(n.info.compareTo(n.padre.info) < 0){
                // System.out.print(n.info.compareTo(n.padre.info) + "\n");
                 n.padre.izquierda = n;
                // System.out.print("lo colgo a izquierda \n");
             }

             else {
                 n.padre.derecha = n;
              //   System.out.print("lo colgo a derecha\n");
             }
         }
    }

    public void recorrer(Nodo n){
        if(n != null){
            recorrer(n.izquierda);
            System.out.print("Contraseña debil: " + n.info + "\n");
            recorrer(n.derecha);
        }
    }
}

