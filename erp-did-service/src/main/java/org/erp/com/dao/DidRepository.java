package org.erp.com.dao;


import org.erp.com.entity.Did;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DidRepository extends JpaRepository<Did, String> {

}
