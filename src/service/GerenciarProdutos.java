package service;


import entities.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GerenciarProdutos {
    List<Produto> estoqueProdutos = new ArrayList<>();

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);
        GerenciarProdutos gp = new GerenciarProdutos();

        int userOption = 0;

        do{
            userOption = gp.menu(scanner);

            switch(userOption) {
                case 1:
                    gp.cadastrarProduto(scanner);
                break;
                case 2:
                    gp.entradaProdutos(scanner);
                break;
                case 3:
                    gp.saidaProduto(scanner);
                break;
                case 4:
                    gp.estoqueAtual();
                break;
                case 5:
                    System.out.println("\nSaindo do sistema...");
                break;
            }
        } while(userOption != 5);
    }

    public int menu(Scanner scanner) {
        System.out.println("\n\t--Gerenciar Produtos --\n");

        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Entrada de Produtos");
        System.out.println("3. Saída de Produtos");
        System.out.println("4. Estoque Atual da loja (inventário)");
        System.out.println("5. Sair");

        System.out.print("Entre uma das opções acima: ");
        int userOption = scanner.nextInt();

        if(userOption > 5 || userOption < 1) {
            System.out.println("\n!!! Esta é uma opção inválida !!!\n");
            return 0;
        }
        else {
            return userOption;
        }
    }

    public void cadastrarProduto(Scanner scanner) {
        Produto produto;

        System.out.println("\n\t-- Cadastro de Produtos --\n");

        System.out.print("Entre o código do produto: ");
        int codigoProduto = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entre o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Entre o valor do produto: R$");
        double valorProduto = scanner.nextDouble();

        System.out.print("Entre a quantidade no estoque: ");
        int quantidadeEstoque = scanner.nextInt();

        estoqueProdutos.add(produto = new Produto(codigoProduto, nomeProduto, valorProduto, quantidadeEstoque));

        System.out.println("Produto cadastrado com sucesso !\n");
    }

    public void entradaProdutos(Scanner scanner) {

        System.out.println("\n\t-- Entrada de Produtos no Estoque --\n");

        System.out.print("Entre o código do produto: ");
        int codigoProduto = scanner.nextInt();
        boolean achouProduto = false;

        for (Produto produto : estoqueProdutos) {
            if(codigoProduto == produto.getCodigo()) {
                System.out.println("O estoque atual é de: " + produto.getQuantidade());
                System.out.print("Quantos produtos serão adicionados ? Entre o valor: ");
                produto.setQuantidade(produto.getQuantidade() + scanner.nextInt());
                System.out.println("Nova quantidade: " + produto.getQuantidade());
                achouProduto = true;
            }

        }
        if(achouProduto == false) {
            System.out.println("\n!!! Produto não encontrado !!!");
        }
    }

    public void saidaProduto(Scanner scanner) {
        System.out.println("\n\t -- Saída de Produtos no Estoque --\n");

        System.out.print("Entre o código do produto: ");
        int codigoProduto = scanner.nextInt();
        boolean achouProduto = false;

        for (Produto produto : estoqueProdutos) {
            if(codigoProduto == produto.getCodigo()) {
                estoqueProdutos.remove(produto);
                System.out.println("Produto removido do estoque com sucessso !\n");
                achouProduto = true;
            }
        }
        if(achouProduto == false) {
            System.out.println("\n!!! Produto não encontrado !!!");
        }
    }

    public void estoqueAtual() {

        System.out.println("\n\t-- Estoque de Produtos (Inventário) --\n");

        double valorTotalEstoque = 0;

        for (Produto produto : estoqueProdutos) {
            System.out.println(produto);
            valorTotalEstoque += (produto.getValor() * produto.getQuantidade());
        }
        System.out.println("Valor total em estoque: R$" + String.format("%.2f", valorTotalEstoque));
    }

}
