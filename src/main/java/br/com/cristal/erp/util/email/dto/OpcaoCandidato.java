package br.com.cristal.erp.util.email.dto;

public enum OpcaoCandidato {

    ACEITAR("Parabéns você foi aceito na refinária!"),
    RECUSAR("Agradecemos seu interesse no projeto refinária," +
            " Infelizmente não continuaremos com sua candidatura " +
            "mas agradecemos o tempo dedicado e o interesse na" +
            " CristalAcademy.");

    private final String mensagem;

    OpcaoCandidato(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }
}
