package com.dasafiotec.transacao_api.business.services;

import com.dasafiotec.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.dasafiotec.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> trasacaoList = new ArrayList<>();

    public void adcionarTrasacoes(TransacaoRequestDTO dto) {
        log.info("inciado o processamento de gravar transações" + dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data/Hora maiores que a Data/Hora atuais");
            throw new UnprocessableEntity("Data/Hora maiores que a Data/Hora atuais");
        }

        if (dto.valor() < 0) {
            log.error("Valor informado menor que Zero");
            throw new UnprocessableEntity("Valor informado menor que Zero");
        }

        trasacaoList.add(dto);
        log.info("Trasação adcionada com sucesso!");
    }

    public void limparTransacoes() {
        log.info("Iniciado o processamento para limpar transações");
        trasacaoList.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciado a busca de transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return trasacaoList.stream().filter(transacoes ->
                transacoes.dataHora().isAfter(dataHoraIntervalo)).toList();
    }

}
