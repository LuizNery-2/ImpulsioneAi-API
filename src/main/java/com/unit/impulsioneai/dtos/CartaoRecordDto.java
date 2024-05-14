package com.unit.impulsioneai.dtos;

import java.util.UUID;

public record CartaoRecordDto(UUID idEmpreendedor, String numeroCartao, String PrimeirosDigitos,  String nomeCartao, String dataValidade, String cvv, String bandeira) {

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCvv() {
        return cvv;
    }

    // //converter a data de validade para o formato "MM/yy"
    // public static String formatarDataValidade(Date dataValidade) {
    //     SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
    //     return sdf.format(dataValidade);
    // }

    // // converter a data de validade do formato "MM/yy" para Date
    // public static Date parseDataValidade(String dataValidade) throws ParseException {
    //     SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
    //     return sdf.parse(dataValidade);
    // }
}
