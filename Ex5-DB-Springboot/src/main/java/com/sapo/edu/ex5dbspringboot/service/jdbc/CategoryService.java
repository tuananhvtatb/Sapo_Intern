package com.sapo.edu.ex5dbspringboot.service.jdbc;

import com.sapo.edu.ex5dbspringboot.entities.CategoryMapper;
import com.sapo.edu.ex5dbspringboot.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CategoryService {
    Logger logger = Logger.getLogger(String.valueOf(CategoryService.class));
    private final JdbcTemplate jdbcTemplate;

    public CategoryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> getCategories() {
        String sql = "select * from tbl_category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }

    // Luu 1 category in database
    public void create(Category category) {
        String sql = "insert into tbl_category(id_category,name,description,created_date,updated_date) values(?,?,?,?,?)";
        int in = jdbcTemplate.update(sql, category.getCodeCategory(), category.getName(), category.getDescription(), category.getCreatedDate(), category.getUpdatedDate());
        if (in == 1) {
            logger.info("Thêm thành công!");
        }

    }

    public void update(Category category, String code) {
        String sql = "update tbl_category set name =?, description=?,updated_date=?  where id_category=?";
        int update = jdbcTemplate.update(sql, category.getName(), category.getDescription(), new Timestamp(System.currentTimeMillis()), code);
        if (update == 1) {
            logger.info("Sửa thành công!");
        }
    }

    public void delete(String code) {
        String sql = "delete from tbl_category where id_category=?";
        int delete = jdbcTemplate.update(sql, code);
        if (delete == 1) {
            logger.info("Xóa thành công!");
        }
    }

    public List<String> getCodeCateList() {
        List<String> s = new ArrayList<>();
        List<Category> categories = getCategories();
        for (Category c : categories
        ) {
            s.add(c.getCodeCategory());
        }
        return s;
    }

    public Category findByCodeCate(String code) {
        String sql = "select * from tbl_category where id_category=?";
        return jdbcTemplate.queryForObject(sql, new CategoryMapper(), code);
    }
}
