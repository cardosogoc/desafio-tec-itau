package com.dasafiotec.transacao_api.controller;

import com.dasafiotec.transacao_api.business.services.TransacaoService;
import com.dasafiotec.transacao_api.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint responsável por adcionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trasação gravada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transações"),
            @ApiResponse(responseCode = "400", description = "Erro de Requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adcionarTrasacao(@RequestBody TransacaoRequestDTO dto){
        transacaoService.adcionarTrasacoes(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trasações deletadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de Requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletaTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
