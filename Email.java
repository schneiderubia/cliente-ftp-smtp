package br.ufms;

// Importe para conectar com o servidor STMP do GMAIL e enviar o email
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Email {
    private final String usuarioEmail = "rxptmatheus@gmail.com"; // e-mail
    private final String senhaEmail = "mhnb tddy ggom vntm"; // senha

    // Método para enviar email
    public void enviarEmail(String destinatario, String titutlo, String mensagem, String arquivoAnexoFTP) {

        // Cria propriedades para configurar a conexão com o servidor SMTP da classe Properties
        Properties props = new Properties();
        // Estabelece conexão com o servidor SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); // Ativa a autenticação SMTP
        props.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para iniciar uma coneção segura
        props.put("mail.smtp.host", "smtp.gmail.com"); // Define o servidor SMTP do Gmail como host
        props.put("mail.smtp.port", "587"); // Define a porta SMTP
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Define o protocolo SSL

        // Sessão de email com as propriedades e autenticação
        Session sessao = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        // Retorna a autenticação com o email e senha definidos
                        return new PasswordAuthentication(usuarioEmail, senhaEmail);
                    }
                });

        try {
            // Cria um novo email
            MimeMessage email = new MimeMessage(sessao);
            // Define o remetente
            email.setFrom(new InternetAddress(usuarioEmail));
            // Define o destinatário
            email.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            // Define o assunto
            email.setSubject(titutlo);

            // Cria o corpo do email
            MimeBodyPart corpoEmail = new MimeBodyPart();
            // Define o texto do email
            corpoEmail.setText(mensagem);

            // Cria um objeto Multipart para adicionar o corpo e o anexo
            Multipart multipart = new MimeMultipart();
            // Adiciona o corpo do email ao Multipart
            multipart.addBodyPart(corpoEmail);

            // Cria o anexo
            MimeBodyPart anexo = new MimeBodyPart();
            // Anexa o arquivo especificado ao email
            anexo.attachFile(new File(arquivoAnexoFTP));
            // Adiciona o anexo ao Multipart
            multipart.addBodyPart(anexo);

            // Define o conteúdo do email como o Multipart (corpo e anexo)
            email.setContent(multipart);

            // Envia o email
            Transport.send(email);

            // Mensagem de confirmação
            System.out.println("Email enviado com sucesso para: " + destinatario);
        } catch (MessagingException | IOException e) {
            // Em caso de erro, mostra-o
            e.printStackTrace();
        }
    }
}
