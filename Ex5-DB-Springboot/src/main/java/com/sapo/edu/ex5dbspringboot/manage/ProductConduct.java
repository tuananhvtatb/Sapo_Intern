package com.sapo.edu.ex5dbspringboot.manage;

import com.sapo.edu.ex5dbspringboot.model.Product;
import com.sapo.edu.ex5dbspringboot.service.jdbc.ProductService;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ProductConduct extends BaseMenu {

    private final ProductService productService;

    public ProductConduct(ProductService productService) {
        this.productService = productService;
    }

    public void menuManageProduct() {
        do {
            logger.info("1. Hiển thị danh sách");
            logger.info("2. Thêm sản phẩm");
            logger.info("3. Sửa sản phẩm");
            logger.info("4. Hiển thị 10 sản phẩm có số lượng bán cao nhất");
            logger.info("5. Thoát");
            logger.info("Chọn 1 thao tác:");
            chon = check();

            switch (chon) {
                case 1:
                    showProducts(productService.getProducts());
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    showProducts(productService.executeProcedure());
                    break;
                case 5:
                    break;
                default:
                    logger.info("Yêu cầu sai!");
            }
        } while (chon != 5);
    }

    public void showProducts(List<Product> products) {
        logger.info("------------------------------------List Product------------------------------------");
        logger.info(String.format("%10s%20s%20s%20s%20s%20s%10s%10s%10s%25s%25s",
                "ID", "ID Product", "Category ID", "Repository ID", "Name", "Description", "Image",
                "Amount", "Amount Sell", "Created Date", "Updated Date"));
        for (Product c : products
        ) {
            logger.info(String.format("%10s%20s%20s%20s%20s%20s%10s%10s%10s%25s%25s",
                    c.getId(), c.getCodeProduct(), c.getCategoryID(), c.getRepositoryID(), c.getName(),
                    c.getDescription(), c.getPathName(), c.getAmount(), c.getAmountSell(), c.getCreatedDate(), c.getUpdatedDate()));
        }
    }

    public void create() {
        try {
            productService.add(inputProduct());
        } catch (Exception e) {
            logger.info("Có lỗi: " + e.getMessage());
        }
    }

    public void update() {
        logger.info("Nhập mã sản phẩm cần sửa:");
        sc.nextLine();
        String code = checkString();
        if (productService.getCodes().contains(code)) {
            Product product = productService.findByCode(code);
            logger.info("Nhập tên sản phẩm:");
            product.setName(checkString());
            logger.info("Nhập mô tả");
            product.setDescription(sc.nextLine());
            logger.info("Nhập ảnh:");
            product.setPathName(sc.nextLine());
            logger.info("Nhập số lượng");
            product.setAmount(check());
            logger.info("Nhập số lượng bán:");
            product.setAmountSell(check());
            product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            productService.update(product);
        }
    }

    public Product inputProduct() {
        Product product = new Product();
        logger.info("Nhập mã sản phẩm:");
        sc.nextLine();
        product.setCodeProduct(checkString());
        logger.info("Nhập danh mục sản phẩm:");
        product.setCategoryID(check());
        logger.info("Nhập kho:");
        product.setRepositoryID(check());
        logger.info("Nhập tên sản phẩm:");
        sc.nextLine();
        product.setName(checkString());
        logger.info("Nhập mô tả:");
        product.setDescription(sc.nextLine());
        logger.info("Thêm ảnh:");
        product.setPathName(sc.nextLine());
        logger.info("Nhập số lượng:");
        product.setAmount(check());
        logger.info("Nhập số lượng bán:");
        product.setAmountSell(check());
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return product;
    }
}
