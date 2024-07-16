package br.com.connectfy.EurofarmaCliente.dtos;



public record UserConfirmAssinatureDTO(Long userId,
                                       String code,
                                       String password,
                                       String assinatura) {
}
