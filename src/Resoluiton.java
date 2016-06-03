import java.util.ArrayList;

public class Resoluiton {
	ArrayList<String> setencasArquivo = new ArrayList<>();
	ArrayList<ArrayList<Integer>> conjuntosPremissas = new ArrayList<>();
	ArrayList<ArrayList<Integer>> novasClausulas = new ArrayList<>();
	ArrayList<Integer> subconjunto;

	public Resoluiton(ArrayList<String> setencas) {
		setencasArquivo = setencas;
		gerarConjuntosPremissas();
	}

	public void gerarConjuntosPremissas() {
		for (int i = 0; i < setencasArquivo.size(); i++) {
			String partes[] = setencasArquivo.get(i).split("\\s+");
			if (i > 1) {
				subconjunto = new ArrayList<>();
				for (int j = 0; j < partes.length - 1; j++){
					subconjunto.add(Integer.parseInt(partes[j]));
				}	
				conjuntosPremissas.add(subconjunto);
			}
		}
		System.out.println("----------------------------------------------");
	}

	public void resolutionMethod(){
		ArrayList<ArrayList<Integer>> diferentes = new ArrayList<>();
		do{
			diferentes.clear();
			for (int i = 0; i < conjuntosPremissas.size(); i++){
				for (int j5 = 1; j5 < conjuntosPremissas.size(); j5++){
					novasClausulas.add(resolventes(conjuntosPremissas.get(i), conjuntosPremissas.get(j5)));
				}		
			}
			for (int g = 0; g < this.novasClausulas.size(); g++) {
				if(novasClausulas.get(g).size() == 0){
					System.out.println("Sim é Dedutivel !!");
					System.exit(0);
				}
				if(!(conjuntosPremissas.contains(novasClausulas.get(g)))){
					diferentes.add(novasClausulas.get(g));
				}
			}
			novasClausulas.clear();
			conjuntosPremissas.addAll(diferentes);
		}while(!(diferentes.isEmpty()));
		System.out.println("Não é Dedutivel !!");
	}	

	public ArrayList<Integer> resolventes(ArrayList<Integer> clausulaOne, ArrayList<Integer> clausulaTwo){
		ArrayList<Integer> novaClausula = new ArrayList<>();
		ArrayList<Integer> clausula = new ArrayList<>();
		ArrayList<Integer> clausulaRemovida = new ArrayList<>();
		Integer clausulaNegada;
		if((clausulaOne.size() > clausulaTwo.size())|| (clausulaOne.size() == clausulaTwo.size())){
			clausula.addAll(clausulaOne);
			for (int j = 0; j < clausulaTwo.size(); j++){
				clausulaNegada = clausulaTwo.get(j)* (-1);
				if((clausula.contains(clausulaNegada))){
					for (int i = 0; i < clausula.size(); i++){
						if(clausula.get(i) == clausulaNegada){
							System.out.println((clausulaTwo.get(j) * (-1)));
							clausula.remove(i);
							clausulaRemovida.add(i);
						}
						if((clausula.contains(clausulaTwo.get(j)))){
							clausula.add(clausulaTwo.get(j));
						}		
					}
				}else{
					if(!(clausula.contains(clausulaTwo.get(j))) & !(clausulaRemovida.contains(clausulaTwo.get(j))))
						clausula.add(clausulaTwo.get(j));
				}
			}
		}else{
			clausula.addAll(clausulaTwo);
			for (int j = 0; j < clausulaOne.size(); j++){
				clausulaNegada = clausulaOne.get(j)* (-1);
				if((clausula.contains(clausulaNegada))){
					for (int i = 0; i < clausula.size(); i++){
						if(clausula.get(i) == clausulaNegada){
							System.out.println((clausulaOne.get(j) * (-1)));
							clausula.remove(i);
							clausulaRemovida.add(i);
						}else{
							if(!(clausula.contains(clausulaOne.get(j))) & !(clausulaRemovida.contains(clausulaOne.get(j))))
								clausula.add(clausulaOne.get(j));
						}
					}
				}if(!(clausula.contains(clausulaOne.get(j))))
					clausula.add(clausulaOne.get(j));
			}
		}			
		novaClausula.addAll(clausula);
		return novaClausula;
	}

}
