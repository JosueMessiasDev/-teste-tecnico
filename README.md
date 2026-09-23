# Teste Técnico - Iniflex

Esse é o meu teste prático de Java pro processo seletivo da Iniflex (via Gupy).

## O que o projeto faz

O enunciado pedia pra criar uma lista de funcionários e fazer várias operações com ela, tipo remover um funcionário, dar aumento de 10%, agrupar por função, achar o mais velho, ordenar por nome, somar os salários, etc. Fui seguindo os itens do PDF um por um.

## Estrutura

- `Pessoa.java` - classe com nome e data de nascimento
- `Funcionario.java` - estende Pessoa e adiciona salário e função
- `Main.java` - onde fiz toda a lógica pedida no teste

## Tecnologias

- Java
- LocalDate pra trabalhar com datas
- BigDecimal pra trabalhar com salário (pesquisei e vi que não é legal usar double com dinheiro)
- Stream/Collectors pra filtrar, agrupar e ordenar a lista

## O que consegui fazer

- [x] Cadastrar os funcionários
- [x] Remover o João
- [x] Imprimir formatado (data e valor)
- [x] Aumento de 10% no salário
- [x] Agrupar por função
- [x] Aniversariantes de outubro e dezembro
- [x] Funcionário mais velho
- [x] Lista em ordem alfabética
- [x] Total dos salários
- [x] Quantos salários mínimos cada um ganha

## Como rodar

Só abrir o projeto numa IDE (usei o VS Code) e executar o `Main.java`. O resultado de cada parte aparece no terminal.

## Autor

Josué Messias
GitHub: github.com/JosueMessiasDev
