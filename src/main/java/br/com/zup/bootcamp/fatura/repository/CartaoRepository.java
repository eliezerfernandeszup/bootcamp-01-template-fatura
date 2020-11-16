package br.com.zup.bootcamp.fatura.repository;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.UUID;

public interface CartaoRepository extends PagingAndSortingRepository<Cartao, UUID> {

    Collection<Cartao> findById(String id);
}
