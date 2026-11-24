package Trabalho_Grupo;
import java.util.Scanner;
import java.util.Locale;

public class DD_1261357_1261084 {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int opcao = ler_opcao_menu(sc);
        if (opcao == 0) {
            sc.close();
            return;
        }

        String[] nomes = ler_matrizesStrings(sc);
        String[] redes_sociais = ler_matrizesStrings(sc);
        int[][] minutos = ler_minutos(sc, nomes.length, redes_sociais.length);

        imprimir_tabela(nomes, redes_sociais, minutos);
        imprimir_totais(nomes, minutos, redes_sociais);
        imprimir_mais_ativos(nomes, redes_sociais, minutos);
        imprimir_acima_media_todas(nomes, redes_sociais, minutos);
        imprimir_mais_utilizada(nomes, redes_sociais, minutos);
        imprimir_percentagem_utilizacao(nomes, redes_sociais, minutos);
        imprimir_perfis_utilizadores(nomes, minutos);
        imprimir_ranking_redes_sociais(redes_sociais, minutos);
        imprimir_utilizadores_mais_equilibrados(nomes, redes_sociais, minutos);

        sc.close();
    }

    // Descrição: Mostrar o menu e ler a opção escolhida (0 = ficheiro, 1 = manual)
    public static int ler_opcao_menu(Scanner sc) {
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
        return opcao;
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

    // Descrição: Imprimir todos os dados em formato de tabela
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
        for (int j = 0; j < linha.length; j++){
            total += linha[j];
        }
        return total;
    }

    // Descrição: Imprimir o total de minutos de cada pessoa
    public static void imprimir_totais(String[] nomes, int[][] minutos, String[] redes_sociais) {
        System.out.println("c) Daily usage");
        for (int i = 0; i < nomes.length; i++) {
            int total = calcular_total(minutos[i]);
            double media = (double) total / redes_sociais.length;
            System.out.printf(Locale.US, "%-10s: %d min (%5.1f min average/network)%n", nomes[i], total, media);
        }
        System.out.println();
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

    // Descrição: Imprimir a pessoa ou pessoas que mais utilizaram cada rede social
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

    // Descrição: Visualizar pessoas cujo tempo de utilização é superior à média em todas as redes sociais
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

    // Descrição: Visualizar, para cada pessoa, a sua rede social mais utilizada
    public static void imprimir_mais_utilizada(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("f) Most used social network");
        for (int i = 0; i < nomes.length; i++) {
            int max = minutos[i][0];
            for (int j = 1; j < redes_sociais.length; j++) {
                if (minutos[i][j] > max) {
                    max = minutos[i][j];
                }
            }
            System.out.printf("%-10s : ", nomes[i]);
            for (int j = 0; j < redes_sociais.length; j++) {
                if (minutos[i][j] == max) {
                    System.out.printf("[%s] ", redes_sociais[j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Visualizar, para cada pessoa, a percentagem do tempo de utilização de cada uma das suas redes sociais
    public static void imprimir_percentagem_utilizacao(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("g) Percentage of use");
        System.out.printf("%-12s", "");
        for (int j = 0; j < redes_sociais.length; j++) {
            System.out.printf("%-12s", redes_sociais[j]);
        }
        System.out.println();

        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("%-12s", nomes[i]);
            int total = calcular_total(minutos[i]);
            for (int j = 0; j < redes_sociais.length; j++) {
                if (total > 0 && minutos[i][j] > 0) {
                    double perc = ((double) minutos[i][j] / total) * 100;
                    System.out.printf("%-12s", String.format("%.2f%%", perc));
                } else {
                    System.out.printf("%-12s", "");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Visualizar os perfis de utilizador com base no tempo total diário
    public static void imprimir_perfis_utilizadores(String[] nomes, int[][] minutos) {
        System.out.println("h) Users profile");
        String[] categorias = {"Moderate", "Regular", "Intensive", "Very Intensive"};

        for (int j = 0; j < categorias.length; j++) {
            System.out.printf("%-16s", categorias[j]);
        }
        System.out.println();

        String[][] perfis = new String[nomes.length][4];
        int[] contagens = new int[4];

        for (int i = 0; i < nomes.length; i++) {
            int total = calcular_total(minutos[i]);
            int col = -1;
            if (total < 90) {
                col = 0;
            } else if (total <= 180) {
                col = 1;
            } else if (total <= 300) {
                col = 2;
            } else {
                col = 3;
            }
            perfis[contagens[col]][col] = nomes[i];
            contagens[col]++;
        }

        int max_linhas = 0;
        for (int j = 0; j < 4; j++) {
            if (contagens[j] > max_linhas) {
                max_linhas = contagens[j];
            }
        }

        for (int linha = 0; linha < max_linhas; linha++) {
            for (int col = 0; col < 4; col++) {
                if (linha < contagens[col]) {
                    System.out.printf("%-16s", perfis[linha][col]);
                } else {
                    System.out.printf("%-16s", "");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Descrição: Visualizar graficamente as redes sociais ordenadas e o respetivo histograma
    public static void imprimir_ranking_redes_sociais(String[] redes_sociais, int[][] minutos) {
        System.out.println("i) Use of social networks");

        int n_redes = redes_sociais.length;
        int[] total_tempo = new int[n_redes];
        int[] total_utilizadores = new int[n_redes];
        int global_total_tempo = 0;

        for (int j = 0; j < n_redes; j++) {
            for (int i = 0; i < minutos.length; i++) {
                total_tempo[j] += minutos[i][j];
                if (minutos[i][j] > 0) {
                    total_utilizadores[j]++;
                }
            }
            global_total_tempo += total_tempo[j];
        }

        String[] redes_ord = new String[n_redes];
        int[] tempo_ord = new int[n_redes];
        for (int j = 0; j < n_redes; j++) {
            redes_ord[j] = redes_sociais[j];
            tempo_ord[j] = total_tempo[j];
        }

        for (int i = 0; i < n_redes - 1; i++) {
            for (int k = i + 1; k < n_redes; k++) {
                boolean trocar = false;
                if (tempo_ord[k] > tempo_ord[i]) {
                    trocar = true;
                } else if (tempo_ord[k] == tempo_ord[i]) {
                    if (total_utilizadores[k] > total_utilizadores[get_original_index(redes_sociais, redes_ord[i])]) {
                        trocar = true;
                    } else if (total_utilizadores[k] == total_utilizadores[get_original_index(redes_sociais, redes_ord[i])]) {
                        if (redes_ord[k].compareTo(redes_ord[i]) < 0) {
                            trocar = true;
                        }
                    }
                }
                if (trocar) {
                    String aux_r = redes_ord[i];
                    redes_ord[i] = redes_ord[k];
                    redes_ord[k] = aux_r;

                    int aux_t = tempo_ord[i];
                    tempo_ord[i] = tempo_ord[k];
                    tempo_ord[k] = aux_t;
                }
            }
        }

        for (int j = 0; j < n_redes; j++) {
            int idx_orig = get_original_index(redes_sociais, redes_ord[j]);
            double perc = global_total_tempo > 0 ? ((double) total_tempo[idx_orig] / global_total_tempo) * 100 : 0;
            System.out.printf("%-12s %6.2f%% ", redes_ord[j], perc);

            int asteriscos = (int) (perc / 5);
            for (int a = 0; a < asteriscos; a++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("            %   25   50   75   100");
        System.out.println();
    }

    public static int get_original_index(String[] redes, String nome) {
        for (int j = 0; j < redes.length; j++) {
            if (redes[j].equals(nome)) {
                return j;
            }
        }
        return 0;
    }

    // Descrição: Determinar o(s) utilizador(es) com utilização mais equilibrada entre redes sociais
    public static void imprimir_utilizadores_mais_equilibrados(String[] nomes, String[] redes_sociais, int[][] minutos) {
        System.out.println("j) Most balanced users");

        double menor_dif = -1;
        int[] diferencas = new int[nomes.length];

        for (int i = 0; i < nomes.length; i++) {
            int max = minutos[i][0];
            int min = minutos[i][0];
            for (int j = 1; j < redes_sociais.length; j++) {
                if (minutos[i][j] > max) max = minutos[i][j];
                if (minutos[i][j] < min) min = minutos[i][j];
            }
            diferencas[i] = max - min;
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
        System.out.println();
    }
}