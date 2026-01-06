package com.example.showdata;

import org.springframework.stereotype.Repository;


@Repository
public interface showDataRepository extends org.springframework.data.mongodb.repository.MongoRepository<Analytics,String>{

}
