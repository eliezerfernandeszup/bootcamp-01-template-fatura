package br.com.zup.bootcamp.fatura.repository;

import br.com.zup.bootcamp.fatura.entity.ParcelamentoFatura;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ParcelarFaturaRepository extends PagingAndSortingRepository<ParcelamentoFatura, UUID> {
}
