package com.rest.dao.first;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.dao.first.custom.FirstCustomRepository;

public interface FirstRepository extends JpaRepository<First, Long>, FirstCustomRepository{

}
