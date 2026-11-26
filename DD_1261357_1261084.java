package Trabalho_Grupo;
import java.util.Scanner;
import java.util.Locale;
import java.io.File;
import java.io.FileNotFoundException;

public class DD_1261357_1261084 {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        menu(sc);
        sc.close();
    }

    // Descrição: Mostrar o menu, ler a opção escolhida
    public static void menu(Scanner sc) {
        int opcao = -1;
        while (opcao != 0 && opcao != 1) {
            System.out.println("0 - Ler dados de ficheiro");
            System.out.println("1 - Introduzir dados manualmente");
            System.out.print("Opcao: ");
            String linha = sc.nextLine().trim();
            if (linha.equals("0")) {
                opcao = 0;
            } else if (linha.equals("1")) {
                opcao = 1;
            } else {
                System.out.println("Opcao invalida.");
            }
        }
        System.out.println();

        if (opcao == 1) {
            executar_manual(sc);
        } else {
            executar_ficheiro(sc);
        }
    }

    // Descrição: Pedir o nome do ficheiro, ler os dados a partir dele e executar todas as alíneas por ordem
    public static void executar_ficheiro(Scanner sc) {
        System.out.print("Nome do ficheiro: ");
        String nome_ficheiro = sc.nextLine().trim();
        System.out.println();

        try {
            Scanner ficheiro = new Scanner(new File(nome_ficheiro));
            String[] nomes = ler_matrizesStrings(ficheiro);
            String[] redes_sociais = ler_matrizesStrings(ficheiro);
            int[][] minutos = ler_minutos(ficheiro, nomes.length, redes_sociais.length);
            ficheiro.close();

            executar_alineas(nomes, redes_sociais, minutos);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado: " + nome_ficheiro);
        }
    }

    // Descrição: Ler os dados introduzidos pelo teclado e executar todas as alíneas por ordem
    public static void executar_manual(Scanner sc) {
        String[] nomes = ler_matrizesStrings(sc);
        String[] redes_sociais = ler_matrizesStrings(sc);
        int[][] minutos = ler_minutos(sc, nomes.length, redes_sociais.length);

        executar_alineas(nomes, redes_sociais, minutos);
    }

    public static void executar_alineas(String[] nomes, String[] redes_sociais, int[][] minutos) {
        imprimir_tabela(nomes, redes_sociais, minutos);
        imprimir_totais(nomes, redes_sociais, minutos);
        imprimir_mais_ativos(nomes, redes_sociais, minutos);
        imprimir_acima_media_todas(nomes, redes_sociais, minutos);
        imprimir_mais_utilizada(nomes, redes_sociais, minutos);
        imprimir_percentagem_utilizacao(nomes, redes_sociais, minutos);
        imprimir_perfis_utilizadores(nomes, minutos);
        imprimir_ranking_redes_sociais(redes_sociais, minutos);
        imprimir_utilizadores_mais_equilibrados(nomes, minutos);
    }

    // Descrição: Ler Listas Strings (Nomes e Redes Sociais), separadas por vírgulas
    public static String[] ler_matrizesStrings(Scanner sc) {
        String linha = sc.nextLine();
        String[] elementos = linha.split(",");
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = elementos[i].trim();
        }
        return elementos;
    }

    // Descrição: Ler Matriz dos Minutos (uma linha por pessoa e uma coluna por rede social)
    public static int[][] ler_minutos(Scanner sc, int numero_pessoas, int numero_redes) {
        int[][] minutos = new int[numero_pessoas][numero_redes];
        for (int linha = 0; linha < minutos.length; linha++) {
            for (int coluna = 0; coluna < minutos[linha].length; coluna++) {
                minutos[linha][coluna] = sc.nextInt();
            }
        }
        return minutos;
    }

    // Descrição: Calcular o total de minutos de uma linha (pessoa)
    public static int calcular_total(int[] linha) {
        int total = 0;
        for (int j = 0; j < linha.length; j++) {
            total += linha[j];
        }
        return total;
    }

    // Descrição: Encontrar o valor máximo de uma linha (pessoa)
    public static int encontrar_maximo_linha(int[] linha) {
        int max = linha[0];
        for (int j = 1; j < linha.length; j++) {
            if (linha[j] > max) {
                max = linha[j];
            }
        }
        return max;
    }

    // Descrição: Encontrar o valor mínimo de uma linha (pessoa)
    public static int encontrar_minimo_linha(int[] linha) {
        int min = linha[0];
        for (int j = 1; j < linha.length; j++) {
            if (linha[j] < min) {
                min = linha[j];
            }
        }
        return min;
    }

    // Descrição: Encontrar o valor máximo de minutos numa determinada coluna (rede social)
    public static int encontrar_maximo_coluna(int[][] minutos, int coluna) {
        int max = minutos[0][coluna];
        for (int i = 1; i < minutos.length; i++) {
            if (minutos[i][coluna] > max) {
                max = minutos[i][coluna];
            }
        }
        return max;
    }

    // Descrição: Calcular o total de minutos de uma coluna (rede social)
    public static int calcular_total_coluna(int[][] minutos, int coluna) {
        int soma = 0;
        for (int i = 0; i < minutos.length; i++) {
            soma += minutos[i][coluna];
        }
        return soma;
    }

    // Descrição: Calcular a média de uma coluna (rede social)
    public static double calcular_media_coluna(int[][] minutos, int coluna) {
        return (double) calcular_total_coluna(minutos, coluna) / minutos.length;
    }

    // Descrição: Contar o número de pessoas que utilizam uma rede social (minutos > 0)
    public static int contar_utilizadores_coluna(int[][] minutos, int coluna) {
        int contador = 0;
        for (int i = 0; i < minutos.length; i++) {
            if (minutos[i][coluna] > 0) {
                contador++;
            }
        }
        return contador;
    }

    // Descrição: Imprimir o cabeçalho com os nomes das redes sociais (usado em b e g)
    public static void imprimir_cabecalho_redes(String[] redes_sociais) {
        System.out.printf("%-13s", "");
        for (int j = 0; j < redes_sociais.length; j++) {
            System.out.printf("%12s", redes_sociais[j]);
        }
        System.out.println();
    }

    // Descrição: Devolver uma String centrada numa largura fixa
    public static String centrar(String texto, int largura) {
        if (texto.length() >= largura) {
            return texto;
        }
        int esquerda = (largura - texto.length()) / 2;
        int direita = largura - texto.length() - esquerda;
        return repetir(' ', esquerda) + texto + repetir(' ', direita);
    }

    // Descrição: Devolver uma String com um carácter repetido n vezes
    public static String repetir(char c, int n) {
        String resultado = "";
        for (int i = 0; i < n; i++) {
            resultado += c;
        }
        return resultado;
    }

    // Descrição: Imprimir todos os dados em formato de tabela
    public static void imprimir_tabela(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("b) Social Media Usage (minutes/day)");
        imprimir_cabecalho_redes(redes_sociais);
        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("%-13s", nomes[i]);
            for (int j = 0; j < redes_sociais.length; j++) {
                System.out.printf("%12d", minutos[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Imprimir o total de minutos de cada pessoa e a média por rede social
    public static void imprimir_totais(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("c) Daily usage");
        for (int i = 0; i < nomes.length; i++) {
            int total = calcular_total(minutos[i]);
            double media = (double) total / redes_sociais.length;
            System.out.printf("%-13s:%6d min (%5.1f min average/network)%n", nomes[i], total, media);
        }
        System.out.println();
    }

    // Descrição: Imprimir a pessoa ou pessoas que mais utilizaram cada rede social
    public static void imprimir_mais_ativos(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("d) Most active Users");
        for (int j = 0; j < redes_sociais.length; j++) {
            int max = encontrar_maximo_coluna(minutos, j);
            System.out.printf("%-13s:", redes_sociais[j]);
            for (int i = 0; i < nomes.length; i++) {
                if (minutos[i][j] == max) {
                    System.out.printf(" \"%s\"", nomes[i]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Imprimir as pessoas cujo tempo é superior à média em todas as redes sociais (ou "none")
    public static void imprimir_acima_media_todas(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("e) Users consistently above average");

        double[] medias_redes = new double[redes_sociais.length];
        for (int j = 0; j < redes_sociais.length; j++) {
            medias_redes[j] = calcular_media_coluna(minutos, j);
        }

        boolean encontrou = false;
        for (int i = 0; i < nomes.length; i++) {
            if (acima_media_todas(minutos[i], medias_redes)) {
                System.out.println(nomes[i]);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("none");
        }
        System.out.println();
    }

    // Descrição: Verificar se uma pessoa está acima da média em todas as redes sociais
    public static boolean acima_media_todas(int[] linha, double[] medias_redes) {
        for (int j = 0; j < linha.length; j++) {
            if (linha[j] <= medias_redes[j]) {
                return false;
            }
        }
        return true;
    }

    // Descrição: Imprimir, para cada pessoa, a(s) sua(s) rede(s) social(is) mais utilizada(s)
    public static void imprimir_mais_utilizada(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("f) Most used social network");
        for (int i = 0; i < nomes.length; i++) {
            int max = encontrar_maximo_linha(minutos[i]);
            System.out.printf("%-13s:", nomes[i]);
            for (int j = 0; j < redes_sociais.length; j++) {
                if (minutos[i][j] == max) {
                    System.out.printf(" [%s]", redes_sociais[j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Imprimir, para cada pessoa, a percentagem de tempo em cada rede social utilizada
    public static void imprimir_percentagem_utilizacao(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("g) Percentage of use");
        imprimir_cabecalho_redes(redes_sociais);
        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("%-13s", nomes[i]);
            int total = calcular_total(minutos[i]);
            for (int j = 0; j < redes_sociais.length; j++) {
                if (total > 0 && minutos[i][j] > 0) {
                    double perc = (double) minutos[i][j] / total * 100;
                    System.out.printf("%12s", String.format("%.2f%%", perc));
                } else {
                    System.out.printf("%12s", "");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Determinar o perfil (0 a 3) de uma pessoa a partir do seu tempo total diário
    public static int determinar_perfil(int total) {
        if (total < 90) {
            return 0;
        } else if (total <= 180) {
            return 1;
        } else if (total <= 300) {
            return 2;
        }
        return 3;
    }

    // Descrição: Imprimir o gráfico com o perfil de cada pessoa alinhado na coluna respetiva
    public static void imprimir_perfis_utilizadores(String[] nomes, int[][] minutos) {
        final int LARGURA = 12;
        String[] perfis = {"Moderate", "Regular", "Intensive", "Very Intensive"};

        System.out.println("h) Users profile");
        for (int p = 0; p < perfis.length; p++) {
            if (p > 0) {
                System.out.print(" ");
            }
            System.out.print(centrar(perfis[p], LARGURA));
        }
        System.out.println();

        for (int p = 0; p < perfis.length; p++) {
            if (p > 0) {
                System.out.print("|");
            }
            System.out.print(repetir('-', LARGURA));
        }
        System.out.println();

        for (int i = 0; i < nomes.length; i++) {
            int perfil = determinar_perfil(calcular_total(minutos[i]));
            String linha = repetir(' ', perfil * (LARGURA + 1)) + centrar(nomes[i], LARGURA);
            System.out.println(linha.replaceAll("\\s+$", ""));
        }
        System.out.println();
    }

    // Descrição: Imprimir as redes sociais ordenadas e o histograma da percentagem de utilização
    public static void imprimir_ranking_redes_sociais(String[] redes_sociais, int[][] minutos) {
        System.out.println("i) Use of social networks");

        int n_redes = redes_sociais.length;
        int[] total_tempo = new int[n_redes];
        int[] total_utilizadores = new int[n_redes];
        int total_global = 0;
        for (int j = 0; j < n_redes; j++) {
            total_tempo[j] = calcular_total_coluna(minutos, j);
            total_utilizadores[j] = contar_utilizadores_coluna(minutos, j);
            total_global += total_tempo[j];
        }

        int[] ordem = ordenar_redes(redes_sociais, total_tempo, total_utilizadores);

        int largura = 0;
        for (int j = 0; j < n_redes; j++) {
            if (redes_sociais[j].length() > largura) {
                largura = redes_sociais[j].length();
            }
        }
        largura++;

        for (int k = 0; k < n_redes; k++) {
            int j = ordem[k];
            double perc = total_global > 0 ? (double) total_tempo[j] / total_global * 100 : 0;
            int asteriscos = (int) Math.round(perc / 5);
            System.out.printf("%-" + largura + "s|%s %.2f%%%n", redes_sociais[j], repetir('*', asteriscos), perc);
        }

        imprimir_legenda_histograma(largura);
        System.out.println();
    }

    // Descrição: Ordenar os índices das redes sociais por tempo (desc), nº de utilizadores (desc) e nome (asc)
    public static int[] ordenar_redes(String[] redes_sociais, int[] total_tempo, int[] total_utilizadores) {
        int n = redes_sociais.length;
        int[] ordem = new int[n];
        for (int j = 0; j < n; j++) {
            ordem[j] = j;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int k = i + 1; k < n; k++) {
                if (vem_antes(ordem[k], ordem[i], redes_sociais, total_tempo, total_utilizadores)) {
                    int aux = ordem[i];
                    ordem[i] = ordem[k];
                    ordem[k] = aux;
                }
            }
        }
        return ordem;
    }

    // Descrição: Verificar se a rede a deve aparecer antes da rede b, segundo os critérios da alínea i)
    public static boolean vem_antes(int a, int b, String[] redes_sociais, int[] total_tempo, int[] total_utilizadores) {
        if (total_tempo[a] != total_tempo[b]) {
            return total_tempo[a] > total_tempo[b];
        }
        if (total_utilizadores[a] != total_utilizadores[b]) {
            return total_utilizadores[a] > total_utilizadores[b];
        }
        return redes_sociais[a].compareTo(redes_sociais[b]) < 0;
    }

    // Descrição: Imprimir a legenda horizontal do histograma (intervalos de 25%, escala 1/5)
    public static void imprimir_legenda_histograma(int largura) {
        System.out.println(repetir(' ', largura) + "+----+----+----+----+");
        System.out.print(repetir(' ', largura) + "%");
        for (int valor = 25; valor <= 100; valor += 25) {
            System.out.printf("%5d", valor);
        }
        System.out.println();
    }

    // Descrição: Imprimir o(s) utilizador(es) com a menor diferença entre a rede mais e a menos usada
    public static void imprimir_utilizadores_mais_equilibrados(String[] nomes, int[][] minutos) {
        System.out.println("j) Most balanced users");

        int[] diferencas = new int[nomes.length];
        int menor_dif = 0;
        for (int i = 0; i < nomes.length; i++) {
            diferencas[i] = encontrar_maximo_linha(minutos[i]) - encontrar_minimo_linha(minutos[i]);
            if (i == 0 || diferencas[i] < menor_dif) {
                menor_dif = diferencas[i];
            }
        }

        boolean primeiro = true;
        System.out.print("{");
        for (int i = 0; i < nomes.length; i++) {
            if (diferencas[i] == menor_dif) {
                if (!primeiro) {
                    System.out.print(", ");
                }
                System.out.printf("\"%s\"", nomes[i]);
                primeiro = false;
            }
        }
        System.out.println("}");
    }
}