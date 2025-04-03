package br.com.jbusiness.eventos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbusiness.eventos.domain.address.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
