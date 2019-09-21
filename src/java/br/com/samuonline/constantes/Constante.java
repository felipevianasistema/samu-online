package br.com.samuonline.constantes;
import javax.faces.application.FacesMessage;

public class Constante {
    /*
     * Mensagens de sucesso.
     */

    public static final String MSG_CADASTRAR_SUCESSO = "Cadastrado com sucesso!";
    public static final String MSG_EXCLUIR_SUCESSO = "Excluído com sucesso!";
    public static final String MSG_ALTERAR_SUCESSO = "Alterado com sucesso!";
    public static final String MSG_CODIGO_ATENCIMENTO_SUCESSO = "Número do chamado: ";
    /*
     * Mensagens de falha.
     */
    public static final String MSG_EDITAR_FALHA = "Não foi possivel editar.";
    public static final String MSG_CADASTRAR_FALHA = "Não foi possível cadastrar.";
    public static final String MSG_EXCLUIR_FALHA = "Não foi possível excluir.";
    public static final String MSG_ALTERAR_FALHA = "Não foi possível alterar.";
    public static final String MSG_CONSULTA_ERRO = "Não foi possível consultar as informações.";
    public static final String MSG_LISTAR_CHAMADO_ERRO = "Não foi possível listar os últimos chamados.";
    public static final String MSG_SISTEMA_INDISPONIVEL = "Sistema indisponível.";
    public static final String MSG_USUARIO_INEXISTENTE = "Usuário/senha não localizado.";
    public static final String MSG_SESSAO_ENCERRADA_SUCESSO = "Sessão encerrada com sucesso.";
    public static final String MSG_FILTRO_PESQUISA_ERRO = "Não foi possível concluir a pesquisa.";
    public static final String MSG_USUARIO_INATIVO = "Usuário inativo.";
    public static final String MSG_USUARIO_JA_CADASTRADO = "Usuário já cadastrado.";
    public static final String MSG_SESSAO_EXPIRADA = "Por favor, autentique-se.";
    public static final String MSG_REPETIR_SENHA_FALHA = "As duas senhas informada não são iguais.";
    /*
     * Mensagem de preenchimento de campo.
     */
    public static final String MSG_PREENCHER_CAMPO = "Preencha as informações necessária.";
    /*
     * Permissão acesso página.
     */
    public static final String MSG_PERMISSAO_PAGINA = "O usuário não possui permissão para acessar a página.";
    /*
     * Tipos de mensagens exibidas para o usuário.
     */
    public static final FacesMessage.Severity TIPO_MENSAGEM_INFO = FacesMessage.SEVERITY_INFO;
    public static final FacesMessage.Severity TIPO_MENSAGEM_FATAL = FacesMessage.SEVERITY_FATAL;
    public static final FacesMessage.Severity TIPO_MENSAGEM_WARN = FacesMessage.SEVERITY_WARN;
    public static final FacesMessage.Severity TIPO_MENSAGEM_ERROR = FacesMessage.SEVERITY_ERROR;
    /*
     * Campos inválido.
     */
    public static final String MSG_CPF_INVALIDO = "Cpf inválido.";
    /*
     * Constraint
     */
    public static final String UN_USUARIO = "un_usuario";
    /*
     * Validação.
     */
    public static final String MSG_ADICIONAR_PACIENTE = "Adicione o(s) paciente(s).";
    public static final String MSG_DADOS_NAO_LOCALIZADO = "Registro não localizado.";
    /*
     * Mensagens de aviso. 
     */
    public static final String MSG_FICHA_JA_CADATRADA = "Ficha já cadastrada.";
}
