package br.com.zup.bootcamp.fatura.service;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import br.com.zup.bootcamp.fatura.entity.Fatura;
import br.com.zup.bootcamp.fatura.repository.FaturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class FaturaService {

    private final Logger logger = LoggerFactory.getLogger(FaturaService.class);
    private final FaturaRepository faturaRepository;

    public FaturaService(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }

    public Fatura existeFatura(Cartao cartao, Integer mesTransacao, Integer anoTransacao) {
        Assert.notNull(cartao , "O cartão não pode ser nulo");
        Assert.notNull(mesTransacao , "O mês da transação não pode ser nulo!");
        Assert.notNull(anoTransacao , "O ano da transação não pode ser nulo!");

        logger.info("[Fatura - processando]");

        List<Fatura> faturas = faturaRepository.findByCartaoIdAndMesAndAno(cartao.getId(), mesTransacao, anoTransacao);

        Fatura fatura;

        if (faturas.isEmpty()) {
            fatura = new Fatura(mesTransacao, anoTransacao, cartao);
            faturaRepository.save(fatura);
            return fatura;
        }

        fatura = faturas.get(0);
        return fatura;
    }
}
