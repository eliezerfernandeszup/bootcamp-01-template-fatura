package br.com.zup.bootcamp.fatura.repository;

import br.com.zup.bootcamp.fatura.entity.Fatura;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface FaturaRepository extends PagingAndSortingRepository<Fatura, UUID> {

    List<Fatura> findByCartaoIdAndMesAndAno(String id, Integer mesTransacao, Integer anoTransacao);
}
