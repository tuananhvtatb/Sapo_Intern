package com.sapo.edu.ex5dbspringboot.entities;

import com.sapo.edu.ex5dbspringboot.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setCodeCategory(resultSet.getString("id_category"));
        category.setName(resultSet.getString("name"));
        category.setDescription(resultSet.getString("description"));
        category.setCreatedDate(resultSet.getTimestamp("created_date"));
        category.setUpdatedDate(resultSet.getTimestamp("updated_date"));
        return category;
    }
}
