import java.util.ArrayList;

public class ArvoreDePremissas {
	ArrayList<ArrayList<Integer>> premissas = new ArrayList<>();
	ArrayList<ArrayList<Integer>> premissasaux = new ArrayList<>();
	ArrayList<No> noAberto = new ArrayList<>();
	No raiz, aux2, premissaAtual;
	No auxFilho = new No();
	No aux = new No();
	boolean mudou = false;

	public void encherArvore(){
		premissasaux.addAll(premissas);
		raiz = new No(premissasaux.get(0));
		premissaAtual = raiz;
		premissasaux.remove(0);
		aux = raiz;	
		do{
			if(noAberto.size() > 0){
				for (int i = 0; i < noAberto.size(); i++) {
					for (int j = 0; j < premissasaux.size(); j++){
						aux = noAberto.remove(i);
						if((premissasaux.get(j).size() == 1)){
							premissasaux.remove(0);
						}else{
							premissaAtual = new No(premissasaux.remove(0));	
						}
						i--;
						gerarFilhos(aux);
						break;
					}	
				}
			}else{
				gerarFilhos(aux);
			}
			if(noAberto.size() == 0 && premissasaux.isEmpty()){
				System.out.println("Não é Detutivel !!");
				System.exit(0);
			}		
			fecharNo(aux);	
		}while(!(noAberto.isEmpty()));
	}
	
	public void gerarFilhos(No aux){
		if(aux.filhos.isEmpty()){
			for (int h = 0; h < premissaAtual.valor.size(); h++) {
				No filho = new No();
				filho.valor.add(premissaAtual.valor.get(h));
				filho.pai = aux;
				aux.filhos.add(filho);
			}
		}
	}
	
	public void fecharNo(No raiz){
		aux = raiz;
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
			if(auxFilho.fechada == false && !(noAberto.contains(auxFilho)))
				noAberto.add(auxFilho);
		}
	}
	
	public void mostrarArvore(No raiz){
		System.out.println("É Detutivel !!");
		ArrayList<No> proximoNo = new ArrayList<>();
		proximoNo.add(raiz);
		while (!(proximoNo.isEmpty())) {	
			aux2 = proximoNo.remove(0);
			for (int i = 0; i < aux2.filhos.size(); i++) {
				proximoNo.add(aux2.filhos.get(i));
				if(aux2.valor == raiz.valor)
					System.out.println(
							"Raiz "+raiz.valor+" : "+aux2.filhos.get(i).valor+" "+aux2.filhos.get(i).fechada);
				else
					System.out.println(
							"Pai "+aux2.valor+" : "+aux2.filhos.get(i).valor+" "+aux2.filhos.get(i).fechada);
			}	
		}	
	}

}
