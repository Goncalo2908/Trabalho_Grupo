package Trabalho_Grupo;
import java.util.Scanner;

public class DD_1261357_1261084 {

    // Descrição:
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nomes = ler_matrizesStrings(sc);
        String[] redes_sociais = ler_matrizesStrings(sc);
        int[][] minutos = ler_minutos(sc, nomes.length, redes_sociais.length);
        imprimir_tabela(nomes, redes_sociais, minutos);
        imprimir_totais(nomes, minutos);
        sc.close();
    }

    //Descrição: Ler Listas Strings (Nomes e Redes Sociais)
    public static String[] ler_matrizesStrings(Scanner sc) {
        String linha = sc.nextLine();
        String[] elementos = linha.split(",");
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = elementos[i].trim();
        }
        return elementos;
    }

    //Descrição: Ler Matriz dos Minutos (uma linha por pessoa e uma coluna por rede social)
    public static int[][] ler_minutos(Scanner sc, int numero_pessoas, int numero_redes) {
        int[][] minutos = new int[numero_pessoas][numero_redes];
        for (int linha = 0; linha < minutos.length; linha++) {
            for (int coluna = 0; coluna < minutos[linha].length; coluna++) {
                minutos[linha][coluna] = sc.nextInt();
            }
        }
        return minutos;
    }

    //Descrição: Imprimir todos os dados em formato de tabela
    public static void imprimir_tabela(String[] nomes, String[] redes, int[][] minutos) {
        System.out.println("b) Social Media Usage (minutes/day)");
        System.out.printf("%-12s", "");
        for (int j = 0; j < redes.length; j++) {
            System.out.printf("%-12s", redes[j]);
        }
        System.out.println();
        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("%-12s", nomes[i]);
            for (int j = 0; j < redes.length; j++) {
                System.out.printf("%-12d", minutos[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //Descrição: Calcular total de minutos por pessoa nas redes sociais
    public static int calcular_total(int[] linha){
        int total = 0;
        for (int j=0; j < linha.length; j++){
            total +=linha[j];

        }
        return total;
    }

    //Descrição: Imprimir o total de minutos de cada pessoa
    public static void imprimir_totais(String[] nomes, int[][] minutos) {
        System.out.println("c) Total Minutes per Person");
        for (int i = 0; i < nomes.length; i++) {
            int total = calcular_total(minutos[i]);   // passa a linha da pessoa i
            System.out.printf("%-12s%d%n", nomes[i], total);
        }
        System.out.println();
    }
}

