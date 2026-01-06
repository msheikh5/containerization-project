package com.example.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataAccessRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void addToDatabase(int id, String name,int mark) {
        jdbcTemplate.update("insert into student (id,nam,mark) values(?,?,?);",id,name,mark);
    }
}
