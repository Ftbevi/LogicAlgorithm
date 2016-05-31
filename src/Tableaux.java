import java.util.ArrayList;

public class Tableaux {
	ArrayList<String> sentencasArquivo = new ArrayList<String>();	
	private ArrayList<ArrayList<Integer>> conjuntosPremissas = new ArrayList<>();
	ArrayList<Integer> subconjunto;
	ArvoreDePremissas arvoreDePremissas = new ArvoreDePremissas();
	
	public Tableaux(ArrayList<String> sentencas) {
		this.sentencasArquivo = sentencas;
		gerarConjuntosPremissas();
		arvoreDePremissas.encherArvore();
	}

	public void gerarConjuntosPremissas() {
		for (int i = 0; i < this.sentencasArquivo.size(); i++) {
			String partes[] = this.sentencasArquivo.get(i).split("\\s+");
			if (i > 1) {
				this.subconjunto = new ArrayList<>();
				for (int j = 0; j < partes.length - 1; j++) {
					subconjunto.add(Integer.parseInt(partes[j]));
				}
				this.arvoreDePremissas.premissas.add(this.subconjunto);
				this.conjuntosPremissas.add(this.subconjunto);
			}
		}
		System.out.println("----------------------------------------------");
		System.out.println(this.arvoreDePremissas.premissas);
		
	}

}
