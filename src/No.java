import java.util.ArrayList;

public class No {
	
	ArrayList<Integer> valor = new ArrayList<>();
	ArrayList<No> filhos = new ArrayList<>() ;
	No pai;
	boolean fechada = false;
	
	public No() {}

	public No(ArrayList<Integer> valor) {
		this.valor = valor;
		this.pai = null;
	}

}
