package br.com.musicbrain.server.utils;

public enum StatusCode{
    BANCO_SEM_RESPOSTA(150),
    USUARIO_SENHA_INVALIDO(151),
    ERRO_EXEC_FUNCION(152),
    TIMEOUT_FUNCION(153),
    ERRO_JSON_TOKEN(104),
    ERRO_JSON_DATA(104),
    ERRO_JSON_HORA(104),
    ERRO_JSON_VALOR(104),
    ERRO_JSON_GENERICO(104),
    ERRO_SEM_CONFIG(198),
    ERRO_DESCONHECIDO(199),
    SUCESSO(200); 

    private Integer code;

    StatusCode(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
}