package br.com.zup.bootcamp.fatura.repository;

import br.com.zup.bootcamp.fatura.entity.Cartao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartaoRepository extends PagingAndSortingRepository<Cartao, UUID> {

    Optional<Cartao> findById(String id);
}
