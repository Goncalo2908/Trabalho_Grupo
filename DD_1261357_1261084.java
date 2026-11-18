package Trabalho_Grupo;
import java.util.Scanner;
import java.util.Locale;

public class DD_1261357_1261084 {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        String[] nomes = ler_matrizesStrings(sc);
        String[] redes_sociais = ler_matrizesStrings(sc);
        int[][] minutos = ler_minutos(sc, nomes.length, redes_sociais.length);
        imprimir_tabela(nomes, redes_sociais, minutos);
        imprimir_totais(nomes, minutos, redes_sociais);
        imprimir_mais_ativos(nomes, redes_sociais, minutos); // Alínea d)
        imprimir_acima_media_todas(nomes, redes_sociais, minutos); // Alínea e)
        sc.close();
    }

    // Descrição: Ler Listas Strings (Nomes e Redes Sociais)
    public static String[] ler_matrizesStrings(Scanner sc) {
        String linha = sc.nextLine();
        String[] elementos = linha.split(",");
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = elementos[i].trim();
        }
        return elementos;
    }

    // Descrição: Ler Matriz dos Minutos (uma linha por pessoa e uma coluna por rede social)[cite: 103]
    public static int[][] ler_minutos(Scanner sc, int numero_pessoas, int numero_redes) {
        int[][] minutos = new int[numero_pessoas][numero_redes];
        for (int linha = 0; linha < minutos.length; linha++) {
            for (int coluna = 0; coluna < minutos[linha].length; coluna++) {
                minutos[linha][coluna] = sc.nextInt();
            }
        }
        return minutos;
    }

    // Descrição: Imprimir todos os dados em formato de tabela[cite: 104]
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

    // Descrição: Calcular total de minutos por pessoa nas redes sociais
    public static int calcular_total(int[] linha){
        int total = 0;
        for (int j=0; j < linha.length; j++){
            total += linha[j];
        }
        return total;
    }

    // Descrição: Imprimir o total de minutos de cada pessoa[cite: 104]
    public static void imprimir_totais(String[] nomes, int[][] minutos, String[] redes_sociais) {
        System.out.println("c) Daily usage");
        for (int i = 0; i < nomes.length; i++) {
            int total = calcular_total(minutos[i]);
            double media = (double) total / redes_sociais.length;
            System.out.printf(Locale.US, "%-10s: %d min (%5.1f min average/network)%n", nomes[i], total, media);
        }
        System.out.println();
    }

    // Descrição: Encontrar o valor máximo de minutos numa determinada coluna (rede social)[cite: 103, 104]
    public static int encontrar_maximo_coluna(int[][] minutos, int coluna) {
        int max = minutos[0][coluna];
        for (int i = 1; i < minutos.length; i++) {
            if (minutos[i][coluna] > max) {
                max = minutos[i][coluna];
            }
        }
        return max;
    }

    // Descrição: Imprimir a pessoa ou pessoas que mais utilizaram cada rede social (Alínea d)[cite: 104]
    public static void imprimir_mais_ativos(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("d) Most active");
        System.out.println("Users");
        for (int j = 0; j < redes_sociais.length; j++) {
            int max = encontrar_maximo_coluna(minutos, j);
            System.out.printf("%-12s : ", redes_sociais[j]);

            boolean primeiro = true;
            for (int i = 0; i < nomes.length; i++) {
                if (minutos[i][j] == max) {
                    if (!primeiro) {
                        System.out.print(" ");
                    }
                    System.out.printf("\"%s\"", nomes[i]);
                    primeiro = false;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Calcular a média de uma coluna (rede social) específica
    public static double calcular_media_coluna(int[][] minutos, int coluna) {
        int soma = 0;
        for (int i = 0; i < minutos.length; i++) {
            soma += minutos[i][coluna];
        }
        return (double) soma / minutos.length;
    }

    // Descrição: Visualizar pessoas cujo tempo de utilização é superior à média em todas as redes sociais (Alínea e)[cite: 104]
    public static void imprimir_acima_media_todas(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("e) Users consistently above average");

        double[] medias_redes = new double[redes_sociais.length];
        for (int j = 0; j < redes_sociais.length; j++) {
            medias_redes[j] = calcular_media_coluna(minutos, j);
        }

        boolean encontrou = false;
        for (int i = 0; i < nomes.length; i++) {
            boolean acima_em_todas = true;
            for (int j = 0; j < redes_sociais.length; j++) {
                if (minutos[i][j] <= medias_redes[j]) {
                    acima_em_todas = false;
                    break;
                }
            }
            if (acima_em_todas) {
                System.out.println(nomes[i]);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("none");
        }
        System.out.println();
    }
}