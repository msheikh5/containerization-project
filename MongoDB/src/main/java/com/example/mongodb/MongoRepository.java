package com.example.mongodb;

import org.springframework.stereotype.Repository;


@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Analytics,String> {

}
