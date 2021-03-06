package implementations.combate;

/**
 * @author - Ot�vio Vansetti Miranda e Lucca Maia Bollani
 * 
 * Sistema de Combate para RPG Cyberpunk, DeltaNexus
 * 
 * Classe inicial/organizadora de jogadores
 * 
 */

import java.util.ArrayList;
import java.util.Random;
import implementations.personagens.AbsPersonagem;

public class CEngine {

	public static void CombatEngine (ArrayList<AbsPersonagem> Herois, ArrayList<AbsPersonagem> Viloes) { // recebe um ArrayList com herois e outro com viloes
		ArrayList<AbsPersonagem> listaH = new ArrayList<AbsPersonagem> (); // lista que vai conter os herois ordenados
		ArrayList<AbsPersonagem> listaV = new ArrayList<AbsPersonagem> (); // lista que vai conter os viloes ordenados
		ArrayList<AbsPersonagem> listaI = new ArrayList<AbsPersonagem> (); // lista que vai conter a ordem de jogada
		Random random = new Random(); // gerador de numeros randomicos
		int contL; // Contador do vetor da Lista
		boolean flag; // Flag para parar o While abaixo
		
		try {
			for (AbsPersonagem H: Herois) { // percorre o AL de herois
				H.iniciativa = (H.agilidade + H.sorte) / 20 * (random.nextInt(9)+1); // calcula a iniciativa de cada um
				flag = true;
				contL = 0;
				
				listaH.add(H.tipo - 1, H);
				
				// se a lista esta vazia, adiciona na primeira posicao
				if(listaI.isEmpty()) {
					
					listaI.add(contL, H);
					flag = false;
				}
				 
				while (flag && contL < listaI.size()) {	// roda ate o fim da lista ou uma modificacao	
					// se a iniciativa do personagem atual for maior que alguma ja na lista, insere em sua posicao
					if (H.iniciativa > listaI.get(contL).iniciativa) {
						listaI.add(contL, H);
						flag = false; // e sai do loop
					}
					else contL++; // ou passa pro proximo
					
					// se chegar ao fim da lista, adiciona ao fim
					if(contL == listaI.size()) {
						listaI.add(contL, H);
						flag = false;
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("erro: " + e);
		}
		
		// faz o mesmo para os viloes
		try {
			for (AbsPersonagem V: Viloes) {
				V.iniciativa = (V.agilidade + V.sorte) / 20 * (random.nextInt(9)+1);
				flag = true;
				contL = 0;
				
				listaV.add(V.tipo - 1, V);
				
				if(listaI.isEmpty()) {
					
					listaI.add(contL, V);
					flag = false;
				}
				
				while (flag && contL < listaI.size()) {					
					if (V.iniciativa > listaI.get(contL).iniciativa) {
						listaI.add(contL, V);
						flag = false;
					}
					else contL++;
					
					if(contL == listaI.size()) {
						listaI.add(contL, V);
						flag = false;
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("erro: " + e);   
		}
		
		for(int contPrint = 0; contPrint < listaI.size(); contPrint++) {
			System.out.print(listaI.get(contPrint).nome + " ");
			System.out.println(listaI.get(contPrint).iniciativa);
		}
		System.out.println("");
		for(int contPrint = 0; contPrint < listaH.size(); contPrint++) {
			System.out.print(listaH.get(contPrint).nome + " ");
			System.out.println(listaH.get(contPrint).iniciativa);
		}
		System.out.println("");
		for(int contPrint = 0; contPrint < listaV.size(); contPrint++) {
			System.out.print(listaV.get(contPrint).nome + " ");
			System.out.println(listaV.get(contPrint).iniciativa);
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// roda Jogada de CRodada
		CRodada.Jogada(listaH, listaV, listaI);
	}
}

