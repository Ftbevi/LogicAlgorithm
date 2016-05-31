import java.io.IOException;

public class Restri {
	private final int NUM_VARS = 4;
	private final int SQUARE_SIZE = 4;
	private StringBuffer out;
	private int contadorAtomicas;
	private int contadorClausulas = contadorAtomicas;
	
	public Restri(StringBuffer out2) {
		this.out = out2;
	}
	
	public int getContadorAtomicas() {
		return contadorAtomicas;
	}

	public void setContadorAtomicas(int contadorAtomicas) {
		this.contadorAtomicas = contadorAtomicas;
	}

	public int getContadorClausulas() {
		return contadorClausulas;
	}

	public void setContadorClausulas(int contadorClausulas) {
		this.contadorClausulas = contadorClausulas;
	}

	public void SudokuClausulas() throws IOException {
		// Create all the clauses based on the given constraints
		constraint_11A();// Verifique que cada número deve ocorrer pelo menos
							// uma vez na linha
		constraint_21A(); // Verifica-se de que cada número deve ocorrer pelo
							// menos uma vez na coluna
		constraint_11B(); // valor nao se repete na linha 2
		constraint_21B();// valor nao se repete na coluna 3
		constraint_31A();// cada numero aparece pelo menos uma vez em cada bloco
							// 4
		constraint_41(); // cada casa tem pelo menos uma variavel 1
		constraint_42(); // cada casa nao ha mais que um valor
	}

	// Verifique que cada número deve ocorrer pelo menos uma vez na linha
	// 111 V 121 V 131 V ... V 191
	// 211 V 221 V 231 V ... V 291
	private void constraint_11A() throws IOException {
		for (int k = 1; k <= NUM_VARS; k++) {
			for (int i = 1; i <= SQUARE_SIZE; i++) {
				// Loop interno verifica cada caixa nessa linha
				for (int j = 1; j <= SQUARE_SIZE; j++) {
					out.append(i + "" + j + "" + k + " ");
					this.contadorAtomicas++;
				}
				out.append("0\n");
			}
		}
		
	}

	// Verifica-se de que cada número deve ocorrer pelo menos uma vez na coluna
	// 111 V 211 V 311 V ... V 911
	// 121 V 221 V 321 V ... V 921

	private void constraint_21A() throws IOException {
		for (int k = 1; k <= NUM_VARS; k++) {
			for (int i = 1; i <= SQUARE_SIZE; i++) {
				for (int j = 1; j <= SQUARE_SIZE; j++) {
					out.append(j + "" + i + "" + k + " ");
					this.contadorAtomicas++;
				}
				out.append("0\n");
			}
		}
	}

	// Verifica-se de que cada número deve ocorrer apenas uma vez na linha
	// (-111 V -121) /\ (-111 V -131) /\ ... /\ (-111 V -191)
	// (-121 V -131) /\ (-121 V -141) /\ ... /\ (-121 V -191)

	private void constraint_11B() throws IOException {
		for (int k = 1; k <= NUM_VARS; k++) {
			for (int n = 1; n <= SQUARE_SIZE; n++) {
				for (int j = 1; j <= SQUARE_SIZE; j++) {
					int ptr = 1;
					for (int i = j; i < SQUARE_SIZE; i++) {
						out.append("-" + n + "" + j + "" + k + " ");
						out.append("-" + n + (j + ptr) + k + " 0\n");
						// System.out.println("-" + n + "" + j + "" + k + " ");
						// System.out.println("-" + n + (j + ptr) + k + " 0\n");
						ptr++;
						this.contadorClausulas++;
					}
				}
			}
		}
	}

	// Verifica-se de que cada número deve ocorrer apenas uma vez na coluna
	// (-111 V -211) /\ (-111 V -311) /\ ... /\ (-111 V -911)
	// (-211 V -311 /\ (-211 V -411) /\ ... /\ (-211 V -911)
	private void constraint_21B() throws IOException {
		for (int k = 1; k <= NUM_VARS; k++) {
			for (int n = 1; n <= SQUARE_SIZE; n++) {
				for (int j = 1; j <= SQUARE_SIZE; j++) {
					int ptr = 1;
					for (int i = j; i < SQUARE_SIZE; i++) {
						out.append("-" + j + "" + n + "" + k + " ");
						out.append("-" + (j + ptr) + "" + n + "" + k + " 0\n");
						ptr++;
						this.contadorClausulas++;
					}
				}
			}
		}
	}

	// sub grid Verifique que cada número deve ocorrer pelo menos uma vez em
	// cada
	// 111 V 121 V 131 V 211 V 221 V 231 V 311 V 321 V 331
	private void constraint_31A() throws IOException {
		for (int k = 1; k <= NUM_VARS; k++) {
			for (int m = 1; m <= SQUARE_SIZE; m += 2) {
				for (int n = 1; n <= SQUARE_SIZE; n += 2) {

					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							out.append((m + i) + "" + (n + j) + "" + k + " ");
							this.contadorClausulas++;
						}
					}
					out.append("0\n");
				}
			}
		}
	}

	// Verifique que cada caixa tem pelo menos 1 variável
	// V111 V V112 V V113 V ... V V119
	private void constraint_41() throws IOException {
		for (int i = 1; i <= SQUARE_SIZE; i++) {
			for (int j = 1; j <= SQUARE_SIZE; j++) {
				for (int k = 1; k <= SQUARE_SIZE; k++) {
					out.append(i + "" + j + "" + k + " ");
					this.contadorClausulas++;
				}
				out.append("0\n");
			}
		}
	}

	// Verifique que cada caixa tem no máximo 1 variável
	// (-V111 V -V112) /\ (-V111 V -V112)
	private void constraint_42() throws IOException {
		for (int i = 1; i <= SQUARE_SIZE; i++) {
			for (int j = 1; j <= SQUARE_SIZE; j++) {
				for (int k = 1; k <= NUM_VARS; k++) {
					for (int m = k + 1; m <= SQUARE_SIZE; m++) {
						out.append("-" + i + "" + j + "" + k + " ");
						out.append("-" + i + "" + j + "" + m + " 0\n");
						this.contadorClausulas++;
					}
				}
			}
		}
	}

}
