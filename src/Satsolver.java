import java.util.ArrayList;

public class Satsolver {
	private ArrayList<String> linhasArquivo = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> conjuntos = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> conjuntoAuxiliar = new ArrayList<>();
	private ArrayList<Integer> conjuntoDasAtomicas = new ArrayList<>();
	ArrayList<Integer> subconjunto;
	ArrayList<Integer> valoresLogicos = new ArrayList<>();
	private int atomicas, clausulas;
	int literal = 0;
	int atomicaInclude;
	int atomicaIncludeNegada = 0;
	boolean Verifica;

	public Satsolver(ArrayList<String> linhasArquivo) {
		this.linhasArquivo = linhasArquivo;
		gerarConjuntos();
	}

	public ArrayList<String> getLinhasArquivo() {
		return linhasArquivo;
	}

	public void setLinhasArquivo(ArrayList<String> linhasArquivo) {
		this.linhasArquivo = linhasArquivo;
	}

	public ArrayList<ArrayList<Integer>> getConjuntos() {
		return conjuntos;
	}

	public void setConjuntos(ArrayList<ArrayList<Integer>> conjuntos) {
		this.conjuntos = conjuntos;
	}

	public ArrayList<Integer> getSubconjunto() {
		return subconjunto;
	}

	public void setSubconjunto(ArrayList<Integer> subconjunto) {
		this.subconjunto = subconjunto;
	}

	public int getAtomicas() {
		return atomicas;
	}

	public void setAtomicas(int atomicas) {
		this.atomicas = atomicas;
	}

	public int getClausulas() {
		return clausulas;
	}

	public void setClausulas(int clausulas) {
		this.clausulas = clausulas;
	}

	public void gerarConjuntos() {
		for (int i = 0; i < this.linhasArquivo.size(); i++) {
			String partes[] = this.linhasArquivo.get(i).split("\\s+");
			if (i == 1) {
				this.atomicas = Integer.parseInt(partes[2]);
				this.clausulas = Integer.parseInt(partes[3]);
			}
			if (i > 1) {
				this.subconjunto = new ArrayList<>();
				for (int j = 0; j < partes.length - 1; j++) {
					if(!(conjuntoDasAtomicas.contains(Integer.parseInt(partes[j]))) 
							&& !(conjuntoDasAtomicas.contains(Integer.parseInt(partes[j])*-1))){
						this.conjuntoDasAtomicas.add(Integer.parseInt(partes[j]));
					}
					subconjunto.add(Integer.parseInt(partes[j]));
				}
				this.conjuntos.add(this.subconjunto);
			}
		}
		System.out.println("conjunto das atomicas");
		System.out.println(conjuntoDasAtomicas);
		System.out.println("conjuntoDasAtomicas size: "+conjuntoDasAtomicas.size());
		conjuntoAuxiliar.addAll(conjuntos);
		// Mostrando partes das linhas
		System.out.println("----------------------------------------------");
		System.out.println(conjuntos);
	}

	public ArrayList<Integer> dpll(ArrayList<ArrayList<Integer>> conjuntos) {
		atomicaIncludeNegada = atomicaInclude * -1;
		for (int i = 0; i < conjuntos.size(); i++) {
			if (conjuntos.get(i).isEmpty()) {
				if (Verifica == false) {
					Verifica = true;
					conjuntos.clear();
					conjuntos.addAll(conjuntoAuxiliar);
					ArrayList<Integer> atomicaSelecionada = new ArrayList<>();
					atomicaSelecionada.add(atomicaIncludeNegada);
					conjuntos.add(atomicaSelecionada);
					dpll(conjuntos);
				} else {
					System.out.println("Não é Satisfativel!");
					System.exit(0);
				}
			}
		}

		simplifica(conjuntos);
		if (conjuntos.isEmpty()) {
			System.out.println("Formula Satisfativel!");
		} else {
			if (!(this.conjuntoDasAtomicas.isEmpty())) {
				atomicaInclude = this.conjuntoDasAtomicas.remove(literal);
				valoresLogicos.add(atomicaInclude);
				ArrayList<Integer> atomicaSelecionada = new ArrayList<>();
				atomicaSelecionada.add(atomicaInclude);
				conjuntos.add(atomicaSelecionada);
				dpll(conjuntos);
				literal++;
			}
		}
		return valoresLogicos;
	}

	public ArrayList<ArrayList<Integer>> simplifica(ArrayList<ArrayList<Integer>> conjuntos) {
		propaga(conjuntos);
		return conjuntos;
	}

	public void propaga(ArrayList<ArrayList<Integer>> conjuntos) {
		int unitaria;
		for (int i = 0; i < conjuntos.size(); i++) {
			if (conjuntos.get(i).size() == 1) {
				unitaria = conjuntos.get(i).get(0);
				System.out.println("Atomica "+unitaria+" foi propagada!!");
				excluirClausulas(unitaria, conjuntos);
			}
		}
	}

	public void excluirClausulas(int unitaria, ArrayList<ArrayList<Integer>> conjuntos) {
		int unitariaNegativa = unitaria * -1;
		for (int i = 0; i < conjuntos.size(); i++) {
			if (conjuntos.get(i).contains(unitaria)) {
				System.out.println("Posição: "+i+" Clausula "+conjuntos.get(i)+" foi removida!!");
				conjuntos.remove(i);
				i--;
			}
		}
		for (int k = 0; k < conjuntos.size(); k++) {
			if (conjuntos.get(k).contains(unitariaNegativa)) {
				for (int k2 = 0; k2 < conjuntos.get(k).size(); k2++) {
					if (conjuntos.get(k).get(k2) == unitariaNegativa){
						System.out.println("Atômica "+conjuntos.get(k).get(k2)+" foi removida da clausula "+conjuntos.get(k));
						conjuntos.get(k).remove(k2);
						System.out.println(conjuntos.get(k));
					}
				}
			}
		}
	}

	public void mostrarClausulas(ArrayList<Integer> valor) {
		System.out.println(valor);
	}

}
