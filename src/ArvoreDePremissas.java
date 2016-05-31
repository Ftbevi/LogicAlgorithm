import java.util.ArrayList;

public class ArvoreDePremissas {
	ArrayList<ArrayList<Integer>> premissas = new ArrayList<>();
	ArrayList<ArrayList<Integer>> premissasaux = new ArrayList<>();
	ArrayList<No> noAberto = new ArrayList<>();
	No raiz, aux2, premissaAtual;
	No auxFilho = new No();
	No aux = new No();

	public void encherArvore(){
		premissasaux.addAll(premissas);
		raiz = new No(premissasaux.get(0));
		premissaAtual = raiz;
		premissasaux.remove(0);
		aux = raiz;	
		do{	
			if(!noAberto.isEmpty()){
				aux = noAberto.remove(0);
				premissaAtual = new No(premissasaux.remove(0));
			}
			if(aux.filhos.isEmpty())
				for (int j = 0; j < premissaAtual.valor.size(); j++) {
					No filho = new No();
					filho.valor.add(premissaAtual.valor.get(j));
					filho.pai = aux;
					aux.filhos.add(filho);
				}
			for (int j = 0; j < aux.filhos.size(); j++) {
				auxFilho = aux.filhos.get(j);
				if(!(auxFilho.pai.valor.isEmpty()))
					if(auxFilho.pai.valor.size() == 1)
						if(auxFilho.valor.get(0) == (auxFilho.pai.valor.get(0) * (-1))){
							auxFilho.fechada = true;
						}else
							if(auxFilho.pai != null){
								auxFilho = auxFilho.pai;
							}
			}
			for (int g = 0; g < aux.filhos.size(); g++) {
				auxFilho = aux.filhos.get(g);
				for (int h = 0; h < premissas.size(); h++) 
					if(premissas.get(h).size() == 1)
						if(premissas.get(h).get(0) == (auxFilho.valor.get(0)* (-1)))
							auxFilho.fechada = true;
				if(auxFilho.fechada == false)
					noAberto.add(auxFilho);
			}
		}while(!(noAberto.isEmpty()));
		ArrayList<No> proximoNo = new ArrayList<>();
		proximoNo.add(raiz);
		while (!(proximoNo.isEmpty())) {	
			for (int i = 0; i < proximoNo.size(); i++) {
				aux2 = proximoNo.get(i);
				for (int j = 0; j < aux2.filhos.size(); j++)
					if(aux2.pai == null)
						System.out.println(aux2.valor+" : "+aux2.filhos.get(j).valor);
					else
						System.out.println(aux2.valor+" : "+aux.filhos.get(j).valor);
			}
			proximoNo.addAll(aux2.filhos);
			proximoNo.remove(0);
		}
	}

}
