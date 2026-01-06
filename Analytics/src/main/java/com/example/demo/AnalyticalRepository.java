package com.example.demo;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AnalyticalRepository {
    Analytics analytics;
    JdbcTemplate jdbcTemplate;
    AnalyticalRepository(Analytics analytics,JdbcTemplate jdbcTemplate){
        this.analytics=analytics;
        this.jdbcTemplate=jdbcTemplate;
    }

    public Analytics getAnalytics(){
        jdbcTemplate.query("select avg(mark) , count(mark) , max(mark) , min(mark) from student;",rs ->{
            analytics.setAverage(rs.getDouble("avg(mark)"));
            analytics.setCount(rs.getDouble("count(mark)"));
            analytics.setMax(rs.getDouble("max(mark)"));
            analytics.setMin(rs.getDouble("min(mark)"));
        } );

        return analytics;
    }

}
