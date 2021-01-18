package com.sapo.edu.ex5dbspringboot.manage;

import org.springframework.stereotype.Component;

@Component
public class MainMenu extends BaseMenu {
    private final CategoryConduct categoryConduct;
    private final ProductConduct productConduct;
    private final RepositoryConduct repositoryConduct;

    public MainMenu(CategoryConduct categoryConduct, ProductConduct productConduct, RepositoryConduct repositoryConduct) {
        this.categoryConduct = categoryConduct;
        this.productConduct = productConduct;
        this.repositoryConduct = repositoryConduct;
    }

    public void menuMain() {
        do {
            logger.info("1. Sản phẩm");
            logger.info("2. Danh mục");
            logger.info("3. Kho");
            logger.info("4. Thoát");
            logger.info("Bạn muốn làm gì?");
            chon = check();

            switch (chon) {
                case 1:
                    productConduct.menuManageProduct();
                    break;
                case 2:
                    categoryConduct.menuManageCategory();
                    break;
                case 3:
                    repositoryConduct.menuManageRepository();
                    break;
                case 4:
                    break;
                default:
                    logger.info("Không có yêu cầu này!");
            }
        } while (chon != 4);
    }
}
