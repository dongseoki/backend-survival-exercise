package com.example.springredisexample.repositories;

import com.example.springredisexample.dtos.CartDto;
import org.springframework.data.repository.CrudRepository;

public interface CartDtoRepository extends CrudRepository<CartDto, String> {

}