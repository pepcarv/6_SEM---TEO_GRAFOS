package GrafoMatriz;

public class TesteGrafoMatriz {
	public static void main(String args[]) {

		// G10: Grafo direcionado (8 vértices: a,b,c,d,e,f,g,h)
		TGrafo g10 = new TGrafo(8);

		g10.insereA(0, 1); // a -> b
		g10.insereA(0, 2); // a -> c
		g10.insereA(0, 4); // a -> e
		g10.insereA(1, 3); // b -> d
		g10.insereA(1, 4); // b -> e
		g10.insereA(2, 5); // c -> f
		g10.insereA(2, 6); // c -> g
		g10.insereA(3, 7); // d -> h
		g10.insereA(4, 7); // e -> h
		g10.insereA(5, 4); // f -> e
		g10.insereA(5, 6); // f -> g
		g10.insereA(6, 7); // g -> h

		System.out.println("G10 (Grafo direcionado) ");
		g10.show();

		System.out.println("\nBuscas começando em a (G10 - dir)");
		g10.buscaEmProfundidade(0); // DFS a partir de a
		g10.buscaEmLargura(0); // BFS a partir de a

		System.out.println("\nConexidade de G10: " + g10.testeConexidade());

		System.out.println("==================================");
		
		
		
		// G9: Grafo não direcionado (4 vértices: a,b,c,d)
		TGrafo g9 = new TGrafo(4);

		g9.insereANaoDirecionada(0, 1); // a - b
		g9.insereANaoDirecionada(0, 2); // a - c
		g9.insereANaoDirecionada(1, 3); // b - d
		g9.insereANaoDirecionada(2, 3); // c - d

		System.out.println("\n\nG9 (Grafo Não Direcionado)");
		g9.show();

		System.out.println("\nBuscas começando em a (G9 - não dir)");
		g9.buscaEmProfundidade(0); // DFS a partir de a
		g9.buscaEmLargura(0); // BFS a partir de a

		System.out.println("\nConexidade de g9: " + g9.testeConexidade());
	}
}