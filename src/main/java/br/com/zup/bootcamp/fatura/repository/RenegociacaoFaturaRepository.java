package br.com.zup.bootcamp.fatura.repository;

import br.com.zup.bootcamp.fatura.entity.Renegociacao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RenegociacaoFaturaRepository extends PagingAndSortingRepository<Renegociacao, UUID> {
}
