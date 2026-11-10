package Trabalho_Grupo;
import java.util.Scanner;

public class DD_1261357_1261084 {

    // Descrição:
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] nomes = ler_matrizesStrings(sc);
        String [] redes_sociais = ler_matrizesStrings(sc);
        int[][] minutos = ler_minutos(sc, nomes.length, redes_sociais.length);
        sc.close();
    }

    //Descrição: Ler Listas Strings (Nomes e Redes Sociais)
    public static String[] ler_matrizesStrings (Scanner sc){
        String linha = sc.nextLine();
        String[] elementos = linha.split(",");
        for (int i = 0; i < elementos.length; i++){
            elementos[i] = elementos[i].trim();
        }
        return elementos;
    }

    //Descrição: Ler Matriz dos Minutos (uma linha por pessoa e uma coluna por rede social)
    public static int [][] ler_minutos(Scanner sc, int numero_pessoas, int numero_redes){
        int[][] minutos = new int[numero_pessoas][numero_redes];
        for (int linha = 0; linha < minutos.length; linha++) {
            for (int coluna = 0; coluna < minutos[linha].length; coluna++) {
                minutos[linha][coluna] = sc.nextInt();
            }
        }
        return minutos;
    }

}