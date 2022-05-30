package org.example.taxes.repository;

import org.example.taxes.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesRepository extends JpaRepository<Tax, Long>, JpaSpecificationExecutor<Tax> {

}