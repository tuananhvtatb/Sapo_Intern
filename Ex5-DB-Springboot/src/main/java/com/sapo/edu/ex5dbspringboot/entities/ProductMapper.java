package com.sapo.edu.ex5dbspringboot.entities;

import com.sapo.edu.ex5dbspringboot.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    // Mapping product in database to product object
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setCodeProduct(resultSet.getString("id_product"));
        product.setCategoryID(resultSet.getInt("category_id"));
        product.setRepositoryID(resultSet.getInt("repository_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPathName(resultSet.getString("path_image"));
        product.setAmount(resultSet.getInt("amount"));
        product.setAmountSell(resultSet.getInt("amount_sell"));
        product.setCreatedDate(resultSet.getTimestamp("created_date"));
        product.setUpdatedDate(resultSet.getTimestamp("updated_date"));
        return product;
    }
}
