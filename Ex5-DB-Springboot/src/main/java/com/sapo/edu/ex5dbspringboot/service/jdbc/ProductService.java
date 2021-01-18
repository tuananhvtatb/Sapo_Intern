package com.sapo.edu.ex5dbspringboot.service.jdbc;

import com.sapo.edu.ex5dbspringboot.entities.ProductMapper;
import com.sapo.edu.ex5dbspringboot.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductService(JdbcTemplate jdbcTemplate, DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_product");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Product> getProducts() {
        String sql = "select * from tbl_product";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    // call procedure
    public List<Product> executeProcedure() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("proc_select_10_product_amout_sell_max")
                .returningResultSet("product", new ProductMapper());
        Map map = simpleJdbcCall.execute(new HashMap<>());
        return (List<Product>) map.get("product");
    }


    // add san pham vao database su dung SimppleJdbcInsert
    @Transactional
    public void add(Product product) {
        Map<String, Object> param = new HashMap<>();
        param.put("id_product", product.getCodeProduct());
        param.put("category_id", product.getCategoryID());
        param.put("repository_id", product.getRepositoryID());
        param.put("name", product.getName());
        param.put("description", product.getDescription());
        param.put("path_image", product.getPathName());
        param.put("amount", product.getAmount());
        param.put("amount_sell", product.getAmountSell());
        param.put("created_date", product.getCreatedDate());
        param.put("updated_date", product.getUpdatedDate());
        simpleJdbcInsert.execute(param);
    }

    // sửa sản phẩm sử dụng NamedParameterJdbcTemplate
    public int update(Product product) {
        String sql = "update tbl_product set name =:name, description=:description,path_image=:path_image, amount=:amount, amount_sell=:amount_sell,updated_date=:updated_date  where id_product=:id_product";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("description", product.getDescription())
                .addValue("path_image", product.getPathName())
                .addValue("amount", product.getAmount())
                .addValue("amount_sell", product.getAmountSell())
                .addValue("updated_date", product.getUpdatedDate())
                .addValue("id_product", product.getCodeProduct());
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public Product findByCode(String code) {
        String sql = "select * from tbl_product where id_product=:id_product";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id_product", code);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new ProductMapper());
    }

    public List<String> getCodes() {
        List<String> list = new ArrayList<>();
        for (Product p : getProducts()
        ) {
            list.add(p.getCodeProduct());
        }
        return list;
    }

}
