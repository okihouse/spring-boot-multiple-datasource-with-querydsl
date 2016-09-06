package com.rest.dao.second;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.dao.second.custom.SecondCustomRepository;

public interface SecondRepository extends JpaRepository<Second, Long>, SecondCustomRepository {

}
