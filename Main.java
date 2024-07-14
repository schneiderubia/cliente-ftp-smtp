//
//        |───────────────────────────────────────────────────|
//        | Curso: Sistemas de Informação - UFMS/CPCX 2024    |
//        | Matéria: Fundamentos de Redes de Computadores     |
//        | Profº: Ekler Paulino de Mattos                    |
//        | Aluna: Ana Rubia Schneider Sampaio                |
//        | RGA: 2022.0803.0335                               |
//        | Aluno: Matheus da Silva Souza                     |
//        | RGA: 2022.0803.0173                               |
//        |───────────────────────────────────────────────────|

package br.ufms;

// Importe para tratamento de execeçôes
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        FTPCliente clienteFTP = new FTPCliente();
        String servidor = "192.168.2.102"; // Endereço IP do servidor FTP
        int porta = 21; // Porta do FTP
        String usuario = "Anonymous"; // Nome de usuário do FTP
        String senha = ""; // Senha do FTP


        try {
            System.out.println();
            System.out.println("=======================================");
            System.out.println("======CONECTANDO COM O SERVIDOR========");
            System.out.println("---------------------------------------");
            clienteFTP.conectandoLogando(servidor, porta, usuario, senha);
            System.out.println("Conectado e logado no servidor FTP.");
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Criação de Diretório
            System.out.println("=======================================");
            System.out.println("===========CRIANDO DIRETÓRIO===========");
            System.out.println("---------------------------------------");
            String diretorioNovo = "/diretorioftp/novoDiretorio";
            System.out.println("Criando diretório: " + diretorioNovo);
            boolean diretorioCriado = clienteFTP.criarDiretorio(diretorioNovo);
            System.out.println("Criação de diretório: " + (diretorioCriado ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Renomear Diretório
            System.out.println("=======================================");
            System.out.println("=========RENOMEANDO DIRETÓRIO==========");
            System.out.println("---------------------------------------");
            String antigoDiretorio = "/diretorioftp/novoDiretorio";
            String novoDiretorio = "/diretorioftp/diretorioRenomeado";
            System.out.println("Renomeando diretório de " + antigoDiretorio + " para " + novoDiretorio);
            boolean diretorioRenomeado = clienteFTP.renomear(antigoDiretorio, novoDiretorio);
            System.out.println("Renomear arquivo: " + (diretorioRenomeado ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Listagem de Diretório
            System.out.println("***************************************");
            System.out.println("=======================================");
            System.out.println("==========LISTAGEM DE DIRETORIO=========");
            System.out.println("---------------------------------------");
            String caminhoDiretorio = "/diretorioftp";
            String[] nomesDiretorio = clienteFTP.listar(caminhoDiretorio);
            System.out.println("Diretórios:");
            if (nomesDiretorio != null) {
                for (String nome : nomesDiretorio) {
                    System.out.println(nome);
                }
            } else {
                System.out.println("Nenhum arquivo encontrado.");
            }
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Deletar Diretório
            System.out.println("=======================================");
            System.out.println("==========DELETANDO DIRETÓRIO==========");
            System.out.println("---------------------------------------");
            String deletarDiretorio = "/diretorioftp/diretorioRenomeado";
            System.out.println("Deletando diretório: " + deletarDiretorio);
            boolean deletou = clienteFTP.removerDiretorio(deletarDiretorio);
            System.out.println("Deletar diretório: " + (deletou ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Criando Arquivo
            System.out.println("=======================================");
            System.out.println("============CRIANDO ARQUIVO============");
            System.out.println("---------------------------------------");
            String caminhoArquivo = "/diretorioftp/arquivo.txt";
            String conteudoArquivo = "Redes de Computadores!";
            System.out.println("Criando arquivo: " + caminhoArquivo);
            boolean sucesso = clienteFTP.criarArquivo(caminhoArquivo, conteudoArquivo);
            System.out.println("Criar arquivo: " + (sucesso ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Renomear Arquivo
            System.out.println("=======================================");
            System.out.println("==========RENOMEANDO ARQUIVO===========");
            System.out.println("---------------------------------------");
            String antigoArquivo = "/diretorioftp/arquivo.txt";
            String novoArquivo = "/diretorioftp/renomeado.txt";
            System.out.println("Renomeando arquivo de " + antigoArquivo + " para " + novoArquivo);
            boolean renomeado = clienteFTP.renomear(antigoArquivo, novoArquivo);
            System.out.println("Renomear arquivo: " + (renomeado ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Listagem de Arquivos
            System.out.println("***************************************");
            System.out.println("=======================================");
            System.out.println("==========LISTAGEM DE ARQUIVOS=========");
            System.out.println("---------------------------------------");
            String caminho = "/diretorioftp";
            String[] nomesArquivos = clienteFTP.listar(caminho);
            System.out.println("Arquivos no diretório:");
            if (nomesArquivos != null) {
                for (String nome : nomesArquivos) {
                    System.out.println(nome);
                }
            } else {
                System.out.println("Nenhum arquivo encontrado.");
            }
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Deletar Arquivo
            System.out.println("=======================================");
            System.out.println("===========DELETANDO ARQUIVO===========");
            System.out.println("---------------------------------------");
            String deletarArquivo = "/diretorioftp/renomeado.txt";
            System.out.println("Deletando arquivo: " + deletarArquivo);
            boolean deletar = clienteFTP.deletarArquivo(deletarArquivo);
            System.out.println("Deletar arquivo: " + (deletar ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Upload de Anexo
            System.out.println("=======================================");
            System.out.println("============UPLOAD DE ANEXO============");
            System.out.println("---------------------------------------");
            String caminhoAnexoLocal = "C:\\UFMS\\diretorio\\local.txt";
            String caminhoAnexoServidor = "/diretorioftp/remote.txt";
            System.out.println("Upload do arquivo " + caminhoAnexoLocal + " para " + caminhoAnexoServidor);
            boolean uploadAnexo = clienteFTP.uploadArquivo(caminhoAnexoLocal, caminhoAnexoServidor);
            System.out.println("Upload do arquivo: " + (uploadAnexo ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Download de Anexo
            System.out.println("=======================================");
            System.out.println("============DOWNLOAD ANEXO=============");
            System.out.println("---------------------------------------");
            String caminhoServidorAnexo = "/diretorioftp/remote.txt";
            String caminhoAnexo = "C:\\UFMS\\diretorio\\anexo.txt";
            System.out.println("Download do arquivo " + caminhoServidorAnexo + " para " + caminhoAnexo);
            boolean downloadAnexo = clienteFTP.downloadArquivo(caminhoServidorAnexo, caminhoAnexo);
            System.out.println("Download do arquivo: " + (downloadAnexo ? "sucesso" : "falha"));
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Desconectar do servidor FTP
            System.out.println("=======================================");
            System.out.println("=======DESCONECTANDO DO SERVIDOR=======");
            System.out.println("---------------------------------------");
            clienteFTP.desconectar();
            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();


            // Instância e envio do email
            System.out.println("=======================================");
            System.out.println("============ENVIANDO EMAIL=============");
            System.out.println("---------------------------------------");

            // Instância
            String destinatario = "mtsz.email@gmail.com";
            String assunto = "Arquivo do servidor FTP";
            String mensagem = "Segue o arquivo do servidor FTP, em anexo.";

            // Envio do email
            Email email = new Email();
            email.enviarEmail(destinatario, assunto, mensagem, caminhoAnexo);

            System.out.println("---------------------------------------");
            System.out.println("=======================================");
            System.out.println("***************************************");
            System.out.println();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
