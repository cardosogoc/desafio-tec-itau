package com.dasafiotec.transacao_api.business.services;

import com.dasafiotec.transacao_api.controller.dtos.EstatiscasResponseDTO;
import com.dasafiotec.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EstasticasService {

    public final TransacaoService transacaoService;

    public EstatiscasResponseDTO calcularEstatisticas(Integer intervaloBusca) {
        log.info("Iniciada a busca de estatisticas por intervalo de: " + intervaloBusca);
        List<TransacaoRequestDTO> trasacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (trasacoes.isEmpty()){
            return new EstatiscasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticasTrasacoes = trasacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");
        return new EstatiscasResponseDTO(
                estatisticasTrasacoes.getCount(),
                estatisticasTrasacoes.getSum(),
                estatisticasTrasacoes.getAverage(),
                estatisticasTrasacoes.getMin(),
                estatisticasTrasacoes.getMax()
        );
    }
}
