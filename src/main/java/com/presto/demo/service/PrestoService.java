package com.presto.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrestoService {

    private final JdbcTemplate jdbcTemplate;

    public void logAllData() {
        // Record start time in nanoseconds
        long startTime = System.nanoTime();

        String sql = "SELECT * FROM iceberg_data.demo_schema.products";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        int i =1;

        for (Map<String, Object> row : rows) {
            System.out.println("row = "+(i++)+"= "+row+"\n\n");
        }

        // Record end time in nanoseconds
        long endTime = System.nanoTime();

        // Calculate elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;
        System.out.println("Query execution time: " + elapsedTime + " nanoseconds");

        // Optionally convert to milliseconds for easier interpretation
        double elapsedTimeInMillis = elapsedTime / 1_000_000.0;
        System.out.println("Query execution time: " + elapsedTimeInMillis + " milliseconds");
    }
}