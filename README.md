# Trabalho_Grupo.Trabalho_Grupo

## 📱 Análise da Utilização Diária de Redes Sociais

> **Algoritmia e Programação (APROG)** · Trabalho Prático 2026-2027
> Programa em **Java** que analisa o tempo que um grupo de pessoas passa nas redes sociais.

---

## 📖 Sobre o projeto

As redes sociais têm hoje um papel importante na comunicação, no entretenimento e no acesso à informação, mas podem ocupar uma parte significativa do dia de algumas pessoas.

Este programa recebe o tempo diário (em **minutos**) que cada pessoa passa em cada rede social e transforma esses dados em informação útil:

- 📊 caracterizar os hábitos de utilização de cada pessoa;
- ⚠️ identificar situações de **uso intensivo**;
- 👥 comparar os perfis dos diferentes utilizadores;
- 🌐 perceber o comportamento geral do grupo.

---

## 🗂️ Estrutura dos dados

A informação fica guardada numa **matriz**: cada **linha** é uma pessoa e cada **coluna** é uma rede social.

|            | Instagram | TikTok | YouTube | Facebook |
|------------|:---------:|:------:|:-------:|:--------:|
| **Ana**    | 45        | 80     | 30      | 10       |
| **Barros** | 215       | 215    | 200     | 0        |
| **Carla**  | 60        | 40     | 245     | 35       |
| **Daniel** | 0         | 120    | 0       | 35       |
| **Elsa**   | 85        | 20     | 45      | 15       |

> Por exemplo, a **Ana** passou 45 min no Instagram, 80 no TikTok, 30 no YouTube e 10 no Facebook, num total de **165 minutos** por dia.

---

## 📥 Formato de entrada

Os dados podem vir do **teclado** ou de um **ficheiro de texto**:

| Linha            | Conteúdo                                                  |
|------------------|-----------------------------------------------------------|
| 1                | Nomes das pessoas (**P**), separados por vírgulas         |
| 2                | Nomes das redes sociais (**R**), separados por vírgulas   |
| Próximas **P**   | **R** inteiros por linha (minutos/dia), separados por espaços |

```text
Ana,Barros,Carla,Daniel,Elsa
Instagram,TikTok,YouTube,Facebook
45 80 30 10
215 215 200 0
60 40 245 35
0 120 0 35
85 20 45 15
```

---

## ⚙️ Funcionalidades

O programa executa todas as alíneas **por ordem** e mostra cada resultado no ecrã.

| Alínea | Funcionalidade |
|:------:|----------------|
| **a)** | Ler os dados e guardá-los em memória com arrays e uma matriz (sem variáveis globais) |
| **b)** | Mostrar os dados numa tabela formatada (pessoas × redes sociais) |
| **c)** | Tempo diário total de cada pessoa e média por rede social |
| **d)** | Para cada rede social, a(s) pessoa(s) que a usou(aram) mais |
| **e)** | Pessoas acima da média em **todas** as redes sociais (ou `none`) |
| **f)** | Rede(s) social(is) mais usada(s) por cada pessoa |
| **g)** | Percentagem de tempo de cada pessoa em cada rede (só as redes usadas) |
| **h)** | Gráfico com o perfil de cada utilizador, de acordo com o tempo total |
| **i)** | Histograma das redes sociais ordenadas por utilização (escala de 1/5) |
| **j)** | Utilizador(es) com o uso mais equilibrado (menor diferença entre a rede mais e a menos usada) |

### 🏷️ Perfis de utilização (alínea h)

| Perfil                | Tempo total diário            |
|-----------------------|-------------------------------|
| 🟢 **Moderate**       | menos de 90 min               |
| 🟡 **Regular**        | entre 90 e 180 min (inclusive)|
| 🟠 **Intensive**      | mais de 180 e até 300 min     |
| 🔴 **Very Intensive** | mais de 300 min               |

### 📶 Critérios de ordenação (alínea i)

1. Tempo de utilização (decrescente)
2. Maior número de utilizadores
3. Ordem alfabética

---

## 🖥️ Exemplo de execução

<details>
<summary><b>Ver saída completa</b></summary>

```text
b) Social Media Usage (minutes/day)
         Instagram    TikTok   YouTube  Facebook
Ana             45        80        30        10
Barros         215       215       200         0
Carla           60        40       245        35
Daniel           0       120         0        35
Elsa            85        20        45        15

c) Daily usage
Ana    : 165 min ( 41.3 min average/network)
Barros : 630 min (157.5 min average/network)
Carla  : 380 min ( 95.0 min average/network)
Daniel : 155 min ( 38.8 min average/network)
Elsa   : 165 min ( 41.3 min average/network)

d) Most active Users
Instagram : "Barros"
TikTok    : "Barros"
YouTube   : "Carla"
Facebook  : "Carla" "Daniel"

e) Users consistently above average
none

f) Most used social network
Ana    : [TikTok]
Barros : [Instagram] [TikTok]
Carla  : [YouTube]
Daniel : [TikTok]
Elsa   : [Instagram]

g) Percentage of use
         Instagram    TikTok   YouTube  Facebook
Ana        27.27%    48.48%    18.18%     6.06%
Barros     34.13%    34.13%    31.75%
Carla      15.79%    10.53%    64.47%     9.21%
Daniel               77.42%              22.58%
Elsa       51.52%    12.12%    27.27%     9.09%

h) Users profile
  Moderate     Regular     Intensive  Very Intensive
------------|------------|------------|------------
                 Ana
                                           Barros
                                           Carla
                 Daniel
                 Elsa

i) Use of social networks
YouTube   |******* 34.78%
TikTok    |****** 31.77%
Instagram |***** 27.09%
Facebook  |* 6.35%
          +----+----+----+----+
          %   25   50   75  100

j) Most balanced users
{"Ana", "Elsa"}
```

</details>

---

## 🚀 Como compilar e executar

A classe está no pacote `Trabalho_Grupo`, por isso os comandos correm a partir da pasta **acima** de `Trabalho_Grupo/`:

```bash
# Compilar
javac Trabalho_Grupo/Trabalho_Grupo.java

# Executar (introduzir os dados pelo teclado)
java Trabalho_Grupo.Trabalho_Grupo

# Executar com os dados de um ficheiro
java Trabalho_Grupo.Trabalho_Grupo < dados.txt
```

---

## 👥 Autores

| Aluno   | Nº | Alíneas            |
|---------|----|--------------------|
| Aluno 1 |    | a) · c) · e) · g) · i) |
| Aluno 2 |    | b) · d) · f) · h) · j) |

---

## 📅 Entrega

- 📤 **Submissão:** Moodle, até às **23:30 de 28 de novembro de 2026**
- 📄 **Ficheiro:** `<turma>_<nºaluno1>_<nºaluno2>.java` (ex.: `DA_11223344_55667788.java`)
- ⏰ **Atraso:** penalização de 20% por dia; não é aceite depois de 2 dias
- 🎤 **Defesa:** obrigatória, nas aulas práticas seguintes à entrega

### 📝 Critérios de avaliação (trabalho de grupo)

| Critério                             | Peso |
|--------------------------------------|:----:|
| Funcionalidades                      | 30%  |
| Modularização                        | 30%  |
| Estruturas de dados                  | 10%  |
| Organização do código e boas práticas| 30%  |

> **Nota final individual** = Desempenho individual × Trabalho de grupo

---

<p align="center"><i>ISEP · Algoritmia e Programação · 2026-2027</i></p>