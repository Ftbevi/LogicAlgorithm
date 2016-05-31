import java.util.ArrayList;

public class Resoluiton {
	ArrayList<String> setencasArquivo = new ArrayList<>();
	ArrayList<ArrayList<Integer>> conjuntosPremissas = new ArrayList<>();
	ArrayList<ArrayList<Integer>> novasClausulas = new ArrayList<>();
	ArrayList<Integer> subconjunto;
	
	public Resoluiton(ArrayList<String> setencas) {
		this.setencasArquivo = setencas;
		gerarConjuntosPremissas();
	}
	
	public void gerarConjuntosPremissas() {
		for (int i = 0; i < this.setencasArquivo.size(); i++) {
			String partes[] = this.setencasArquivo.get(i).split("\\s+");
			if (i > 1) {
				this.subconjunto = new ArrayList<>();
				for (int j = 0; j < partes.length - 1; j++){
					subconjunto.add(Integer.parseInt(partes[j]));
				}
					
				this.conjuntosPremissas.add(this.subconjunto);
			}
		}
		System.out.println("----------------------------------------------");
		System.out.println(conjuntosPremissas);
	}
	
	public void resolutionMethod(){
	 boolean verifica = true;
	 boolean umaVez = false;
		while(verifica || umaVez == false){
			for (int i = 0; i < this.conjuntosPremissas.size() - 1; i++) {
				for (int j = i+1; j < this.conjuntosPremissas.size(); j++){
					this.novasClausulas.add(resolventes(conjuntosPremissas.get(i), conjuntosPremissas.get(j)));
					System.out.println(novasClausulas);
					for (int g = 0; g < this.novasClausulas.size(); g++) 
						if(this.novasClausulas.get(g).size() == 0){
							System.out.println("Sim é Dedutivel !!");
							System.exit(0);
						}
				}
			}
			for (int i = 0; i < novasClausulas.size(); i++) {
				if(conjuntosPremissas.contains(this.novasClausulas.get(i))){
					verifica = false;
				}else{
					umaVez = true;
					conjuntosPremissas.add(novasClausulas.get(i));						
				}
			}	
			System.out.println(novasClausulas+"/n");
			System.out.println(conjuntosPremissas+"/n");
			System.out.println("--------------------");
			System.out.println(conjuntosPremissas+"/n");
			System.out.println("--------------------");
			
		}
		System.out.println(conjuntosPremissas);

	}	
	
	public ArrayList<Integer> resolventes(ArrayList<Integer> clausulaOne, ArrayList<Integer> clausulaTwo){
		ArrayList<Integer> novaClausula = new ArrayList<>();
		for (int i = 0; i < clausulaOne.size(); i++){
			for (int j = 0; j < clausulaTwo.size(); j++){
				if(!(clausulaOne.contains(clausulaTwo.get(j)*(-1))))
					novaClausula.add(clausulaOne.get(i));	
				if(!(clausulaTwo.contains(clausulaOne.get(i)*(-1))))
					novaClausula.add(clausulaTwo.get(j));
			}
		}	
		return novaClausula;
	}	

}
