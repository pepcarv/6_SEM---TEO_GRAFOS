package GrafoMatriz;

import PPilha.Pilha;
import FFilaCircular.FilaCircular;

//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; //matriz de adjacência
	
	private boolean marcado[]; // vetor de marcados
	private String letraVet[]; // letras dos vet

	
	// Métodos Públicos
	public TGrafo( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new int [n][n];


	    // alocação do vetor de marcação
	    this.marcado = new boolean[n];
	    // alocação e preenchimento do vet de letras
	    this.letraVet = new String[n];

	    // inicia a matriz com zeros
		for (int i = 0; i< n; i++) {
			for(int j = 0; j< n; j++) {
				this.adj[i][j]=0;
			}
			this.marcado[i] = false;
			this.letraVet[i] = String.valueOf((char)('a' + i));
			//System.out.println("["+i+"]: "+this.letraVet[i]);
		}
	}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w
	public void insereA(int v, int w) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == 0 ){
	        adj[v][w] = 1;
	        m++; // atualiza qtd arestas
	    }
	}




	// Insere uma aresta não dir no Grafo
	// v é adjacente a w e w é adjacente a v
	public void insereANaoDirecionada(int v, int w) {
	    insereA(v, w);
	    insereA(w, v);
	}

	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == 1 ){
	        adj[v][w] = 0;
	        m--; // atualiza qtd arestas
	    }
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] == 1)
	            	System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}

	
	
	
	// DFS E BFS:

	// desmarca todos os nós do grafo; usado antes de iniciar uma nova busca
	private void desmarcaTudo() {
		for (int i = 0; i < n; i++)
			marcado[i] = false;
	}
	// visita
	private void visita(int node) {
		System.out.print(letraVet[node] + " ");
	}





	// Percurso em Profundidade - DFS)
	public void buscaEmProfundidade(int nInicial) {
		desmarcaTudo();
		System.out.print("DFS começando em " + letraVet[nInicial] + ": ");
		bscDFS(nInicial, this.adj, true);
		System.out.println("");
	}




	// Percurso em Largura - BFS
	public void buscaEmLargura(int nInicial) {
		desmarcaTudo();
		System.out.print("BFS começando em " + letraVet[nInicial] + ": ");
		bscBFS(nInicial, this.adj, true);
		System.out.println("");
	}


	// dfs e bfs adaptados (comparados com ultimo commit) para conexidade
	//mesma logic
	private void bscDFS(int nInicial, int[][] matriz, boolean imprimir) {
		Pilha pilha = new Pilha(this.n * this.n + 1); // capacidade segura

		pilha.push(nInicial); //empilha nó inicial n na pilha P

		while (!pilha.isEmpty()) {// Enquanto a pilha P não estiver vazia
			int node = pilha.pop();// n <- pop(P)

			if (!marcado[node]) {// se n ainda não foi visitado
				if (imprimir) visita(node);// O nó n é visitado
				marcado[node] = true;// O nó n é marcado

				for (int adjacente = n - 1; adjacente >= 0; adjacente--) {
					if (matriz[node][adjacente] == 1 && !marcado[adjacente]) { // nó m não marcado e adjacente a n
						pilha.push(adjacente); // O nó m é colocado na pilha P
					}
				}
			}
		}
	}

	private void bscBFS(int nInicial, int[][] matriz, boolean imprimir) {
		FilaCircular fila = new FilaCircular(n); // TAM: qtd de vértices
		int node = nInicial;

		if (imprimir) visita(node);// visita um nó n selecionado
		marcado[node] = true; // marca n
		fila.enqueue(node); // insere n na fila

		while (!fila.qIsEmpty()) { // enquanto a fila não estiver vazia
			node = fila.dequeue(); // retira um elemento da fila F e atribui ao nó n

			for (int adjacente = 0; adjacente < n; adjacente++) {
				if (matriz[node][adjacente] == 1 && !marcado[adjacente]) { // nó m não marcado e adjacente a n
					if (imprimir) visita(adjacente);// m é visitado
					fila.enqueue(adjacente);// m é colocado na fila
					marcado[adjacente] = true;// e m é marcado
				}
			}
		}
	}





	// return de cópia do vetor de marcados
	private boolean[] contVisitadosDFS(int nInicial, int[][] matriz) {
		desmarcaTudo();
		bscDFS(nInicial, matriz, false);
		return marcado.clone();
	}





	// CONEXIDADE

	// C3
	public boolean eh_c3() {
		for (int v = 0; v < n; v++) {
			boolean[] visitados = contVisitadosDFS(v, this.adj);
			int qtdVisitados = 0;
			for (int i = 0; i < n; i++)
				if (visitados[i]) qtdVisitados++;

			if (qtdVisitados != n) {
				return false;
			}
		}
		return true;
	}

	// C2
	public boolean eh_c2() {
		for (int v = 0; v < n; v++) {
			for (int w = 0; w < n; w++) {
				if (v == w) continue;

				boolean[] visitadosDeV = contVisitadosDFS(v, this.adj);

				if (!visitadosDeV[w]) {
					boolean[] visitadosDeW = contVisitadosDFS(w, this.adj);

					if (!visitadosDeW[v]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// C1 / C0 (desconexo)
	// logica dos slides pro desconexo
	public boolean eh_desconexo() {
		int[][] simetrica = new int[n][n];

		//System.out.println("DESCONEX)");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//System.out.println("["+i+"]"+" ["+j+"]: " + simetrica[i][j]);
				if (adj[i][j] == 1 || adj[j][i] == 1) {
					
					simetrica[i][j] = 1;
					simetrica[j][i] = 1;
				}
			}
		}

		boolean[] visitados = contVisitadosDFS(0, simetrica);

		int qtdVisitados = 0;
		for (int i = 0; i < n; i++)
			if (visitados[i]) qtdVisitados++;

		
		
		
		
		
		// se visitou todo mundo, NÃO é desconexo
		return qtdVisitados != n;
	}




	public String testeConexidade() {
		String categoria = "C3";

		if (!eh_c3()) {
			categoria = "C2";

			if (!eh_c2()) {
				categoria = "C0";

				if (!eh_desconexo()) {
					categoria = "C1";
				}
			}
		}

		return categoria;
	}
}