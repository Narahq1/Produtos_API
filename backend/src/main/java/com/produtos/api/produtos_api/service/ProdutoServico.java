package com.produtos.api.produtos_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.produtos.api.produtos_api.modules.ProdutoModelo;
import com.produtos.api.produtos_api.modules.RespostaModelo;
import com.produtos.api.produtos_api.repository.ProdutoRepositorio;

@Service
public class ProdutoServico {
    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    public Iterable<ProdutoModelo> listar() {
        return pr.findAll();
    }

    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao) {
        if (pm.getNome().equals("")) {
            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getMarca().equals("")) {
            rm.setMensagem("A marca do produto é obrigatória!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<RespostaModelo> remover(long codigo) {
        pr.deleteById(codigo);
        rm.setMensagem("O produto foi removido com sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }
}

// 400 do lado do cliente
// 500 do lado do servidor
// 200 sucesso