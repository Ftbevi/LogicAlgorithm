import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppSatSolver {

	public static void main(String[] args) throws IOException {
		ArrayList<String> sentencas2 = new ArrayList<>();
		int p = 0;
		Scanner ler = new Scanner(System.in); 
		System.out.printf("Informe o nome do arquivo: \n");
		String nomeArquivo = ler.nextLine();			
		System.out.printf("Informe o algorimo que deseja usar: \n");
		System.out.printf("1 - DPLL e o Problema do Sudoku \n");
		System.out.printf("2 - Tableaux \n");
		System.out.printf("3 - Resoluiton \n"); 
		int opcao = ler.nextInt();
		ler.close();
		try {
			String nome2 = "C:\\Users\\Thiago Beviláqua\\Desktop\\Logica\\"+nomeArquivo;
			FileReader arq2 = new FileReader(nome2);
			BufferedReader lerArq2 = new BufferedReader(arq2);
			String linhas = lerArq2.readLine();
			while (linhas != null) {
				System.out.println(linhas);
				sentencas2.add(p, linhas);
				linhas = lerArq2.readLine();
				p++;
			}
			arq2.close();

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		switch (opcao) {
		case 1:
			escritor();
			Satsolver teste = new Satsolver(sentencas2);
			System.out.println("----------------------------------------------");
			teste.dpll(teste.getConjuntos());
			teste.mostrarClausulas(teste.dpll(teste.getConjuntos()));
			break;
		case 2:	
			Tableaux tableaux = new Tableaux(sentencas2);
			tableaux.arvoreDePremissas.mostrarArvore(tableaux.arvoreDePremissas.raiz);
			break;
		case 3:	
			Resoluiton resoluiton = new Resoluiton(sentencas2);
			resoluiton.resolutionMethod();
			break;	
		default:
			System.out.println("Opção Invalida !!!");
			break;
		}
	}

	public static void escritor() throws IOException {
		StringBuffer out = new StringBuffer("");
		StringBuffer out2 = new StringBuffer("");
		Restri m = new Restri(out2);
		m.SudokuClausulas();
		System.out.println(m.getContadorAtomicas());
		System.out.println(m.getContadorClausulas());
		out2.append(out);
		String path = "C:\\Users\\Thiago Beviláqua\\Desktop\\Logica\\sudoku.txt";
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		buffWrite.append("c comentario\n");
		buffWrite.append("p cnf 128 416" + "\n");
		buffWrite.append(out2);
		buffWrite.close();
	}

}
