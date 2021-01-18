package com.sapo.edu.ex5dbspringboot.manage;

import com.sapo.edu.ex5dbspringboot.model.Category;
import com.sapo.edu.ex5dbspringboot.service.jdbc.CategoryService;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class CategoryConduct extends BaseMenu {

    private final CategoryService categoryService;

    public CategoryConduct(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void menuManageCategory() {
        do {
            logger.info("1. Hiển thị danh sách");
            logger.info("2. Thêm danh muc");
            logger.info("3. Sửa danh muc");
            logger.info("4. Xóa danh muc");
            logger.info("5. Thoát");
            logger.info("Chọn 1 thao tác:");
            chon = check();

            switch (chon) {
                case 1:
                    showCategory();
                    break;
                case 2:
                    Category category = inputCategory();
                    category.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    category.setUpdatedDate(null);
                    categoryService.create(category);
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    break;
                default:
                    logger.info("Không có yêu cầu này!");
            }
        } while (chon != 5);
    }

    // hiển thị danh mục
    private void showCategory() {
        List<Category> categories = categoryService.getCategories();

        logger.info("-----------------------------------------------List Category-------------------------------------");
        logger.info(String.format("%10s%10s%20s%10s%25s%25s", "ID", "ID Category", "Name",
                "Description", "Created Date", "Updated Date"));
        for (Category c : categories
        ) {
            logger.info(String.format("%10d%10s%20s%10s%25s%25s", c.getId(), c.getCodeCategory(), c.getName(), c.getDescription(), c.getCreatedDate(), c.getUpdatedDate()));
        }
    }

    // Nhập danh mục
    public Category inputCategory() {
        Category category = new Category();
        logger.info("Nhập mã danh mục:");
        sc.nextLine();
        String code;
        while (true) {
            code = checkString();
            if (categoryService.getCodeCateList().contains(code)) {
                logger.info("Trùng mã!Nhập lại");
            } else {
                break;
            }
        }
        category.setCodeCategory(code);
        logger.info("Nhập tên danh mục:");
        category.setName(checkString());
        logger.info("Nhập mô tả:");
        category.setDescription(checkString());
        return category;
    }


    // Sửa danh mục
    public void update() {
        logger.info("Nhập mã danh mục cần sửa:");
        sc.nextLine();
        String code = checkString();
        if (categoryService.getCodeCateList().contains(code)) {
            Category category = categoryService.findByCodeCate(code);
            logger.info("Nhập tên danh mục:");
            category.setName(checkString());
            logger.info("Nhập mô tả:");
            category.setDescription(checkString());
            category.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            categoryService.update(category, code);
        } else {
            logger.info("Không có mã danh mục " + code + " !");
        }
    }

    // Xóa danh mục
    public void delete() {
        logger.info("Nhập mã danh mục cần xóa:");
        sc.nextLine();
        String code = checkString();
        if (categoryService.getCodeCateList().contains(code)) {
            categoryService.delete(code);
        } else {
            logger.info("Không có danh mục này!");
        }
    }
}
