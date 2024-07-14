package br.ufms;

// Importe para operações FTP, através do Apache Commmons Net
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

// Importe para exceções
import java.io.*;

public class FTPCliente {
    // Cria uma instância do cliente FTP
    private FTPClient ftpClient = new FTPClient();

    // Conectar e fazer login no servidor FTP
    public void conectandoLogando(String servidor, int porta, String usuario, String senha) throws IOException {
        try {

            // Conecta ao servidor FTP
            ftpClient.connect(servidor, porta);

            // Faz login no servidor FTP com o usuário e senha (Anonymous)
            ftpClient.login(usuario, senha);

            // Modo passivo de conexão
            ftpClient.enterLocalPassiveMode();

            // Tipo de arquivo binário
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        } catch (IOException e) {
            // Em caso de erro, imprime-o
            System.err.println("Erro ao conectar e fazer login no servidor FTP: " + e.getMessage());
            throw e;
        }
    }

    // Criar diretório no servidor FTP
    public boolean criarDiretorio(String caminhoDiretorio) {
        try {
            // Cria um diretório no caminho
            return ftpClient.makeDirectory(caminhoDiretorio);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao criar diretório " + caminhoDiretorio + ": " + e.getMessage());
            return false;
        }
    }

    // Renomear diretório e arquivo
    public boolean renomear(String antigo, String novo) {
        try {
            // Renomeia
            return ftpClient.rename(antigo, novo);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao renomear arquivo de " + antigo + " para " + novo + ": " + e.getMessage());
            return false;
        }
    }

    // Listar os nomes de arquivos e diretórios no servidor FTP
    public String[] listar(String caminho) {
        try {
            // Retorna uma lista de nomes
            return ftpClient.listNames(caminho);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna null
            System.err.println("Erro ao listar arquivos no diretório " + caminho + ": " + e.getMessage());
            return null;
        }
    }

    // Remover diretório no servidor FTP
    public boolean removerDiretorio(String caminhoDiretorio) {
        try {
            // Remove o diretório
            return ftpClient.removeDirectory(caminhoDiretorio);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao criar diretório " + caminhoDiretorio + ": " + e.getMessage());
            return false;
        }
    }

    // Upload de um arquivo local para o servidor FTP
    public boolean uploadArquivo(String caminhoLocal, String caminhoRemoto) {
        try (InputStream inputStream = new FileInputStream(caminhoLocal)) {
            // Faz upload do arquivo local para o caminho remoto
            return ftpClient.storeFile(caminhoRemoto, inputStream);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao fazer upload do arquivo " + caminhoLocal + ": " + e.getMessage());
            return false;
        }
    }

    // Download de um arquivo do servidor FTP
    public boolean downloadArquivo(String caminhoRemoto, String caminhoLocal) {
        try (OutputStream outputStream = new FileOutputStream(caminhoLocal)) {
            // Faz download do arquivo remoto para o caminho local
            return ftpClient.retrieveFile(caminhoRemoto, outputStream);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao fazer download do arquivo " + caminhoRemoto + ": " + e.getMessage());
            return false;
        }
    }

    // Criar um arquivo no servidor FTP
    public boolean criarArquivo(String caminhoArquivo, String conteudo) {
        try (InputStream inputStream = new ByteArrayInputStream(conteudo.getBytes())) {
            // Faz upload do conteúdo como um arquivo para o caminho especificado
            return ftpClient.storeFile(caminhoArquivo, inputStream);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao criar arquivo " + caminhoArquivo + ": " + e.getMessage());
            return false;
        }
    }

    // Deletar um arquivo no servidor FTP
    public boolean deletarArquivo(String caminhoArquivo) {
        try {
            // Deleta o arquivo
            return ftpClient.deleteFile(caminhoArquivo);
        } catch (IOException e) {
            // Em caso de erro, imprime-o e retorna false
            System.err.println("Erro ao deletar arquivo " + caminhoArquivo + ": " + e.getMessage());
            return false;
        }
    }

    // Desconectar do servidor FTP
    public void desconectar() {
        try {
            // Verifica se está conectado ao servidor FTP
            if (ftpClient.isConnected()) {
                // Faz logout e desconecta do servidor FTP
                ftpClient.logout();
                ftpClient.disconnect();
                // Imprime a desconecção
                System.out.println("Desconectado do servidor FTP.");
            }
        } catch (IOException e) {
            // Em caso de erro, imprime-o
            e.printStackTrace();
        }
    }
}
