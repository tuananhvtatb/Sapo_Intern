package com.sapo.edu.ex5dbspringboot.manage;

import com.sapo.edu.ex5dbspringboot.entities.RepositoryS;
import com.sapo.edu.ex5dbspringboot.service.jpa.RepositorySevice;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RepositoryConduct extends BaseMenu {

    private final RepositorySevice repositorySevice;

    public RepositoryConduct(RepositorySevice repositorySevice) {
        this.repositorySevice = repositorySevice;
    }

    public void menuManageRepository() {
        do {
            logger.info("1. Hiển thị danh sách");
            logger.info("2. Thêm kho");
            logger.info("3. Sửa kho");
            logger.info("4. Xóa kho");
            logger.info("5. Thoát");
            logger.info("Chọn 1 thao tác:");
            chon = check();

            switch (chon) {
                case 1:
                    showRepositories();
                    break;
                case 2:
                    repositorySevice.creat(inputRepo());
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

    public void showRepositories() {
        List<RepositoryS> repositoryS = repositorySevice.getRepositories();
        printData(repositoryS);
    }

    public void update() {
        logger.info("Nhập mã kho cần sửa");
        sc.nextLine();
        String code = sc.nextLine();
        if (repositorySevice.getCodeRepo().contains(code)) {
            RepositoryS repoOld = repositorySevice.findByCode(code);
            logger.info("Nhập tên kho:");
            repoOld.setName(sc.nextLine());
            logger.info("Nhập vị trí kho:");
            repoOld.setPlace(sc.nextLine());
            repoOld.setUpdatedDate(LocalDateTime.now());
            repositorySevice.save(repoOld);
            logger.info("Sửa thành công!");
        } else {
            logger.info("Kho không tồn tại!");
        }
    }

    public void delete() {
        logger.info("Nhập mã kho cần xóa");
        sc.nextLine();
        String code = sc.nextLine();
        if (repositorySevice.getCodeRepo().contains(code)) {
            repositorySevice.delete(repositorySevice.findByCode(code));
            logger.info("Xóa thành công");
        } else {
            logger.info("Kho không tồn tại!");
        }
    }

    public void printData(List<RepositoryS> repositoryS) {

        logger.info("-------------------------List Repository------------------------------");
        logger.info(String.format("%10s %20s %10s %10s %20s %20s", "ID", "ID Repository", "Name", "Place", "Created Date", "Updated Date"));
        for (RepositoryS s : repositoryS
        ) {
            logger.info(String.format("%10d %20s %10s %10s %20s %20s", s.getId(), s.getIdRepository(), s.getName(), s.getPlace(), s.getCreatedDate(), s.getUpdatedDate()));

        }
    }

    public RepositoryS inputRepo() {
        RepositoryS repositoryS = new RepositoryS();
        logger.info("Nhập mã kho:");
        sc.nextLine();
        String code;
        while (true) {
            code = sc.nextLine();
            if (repositorySevice.getCodeRepo().contains(code)) {
                logger.info("Trùng mã!Nhập lại");
            } else {
                break;
            }
        }
        repositoryS.setIdRepository(code);
        logger.info("Nhập tên kho:");
        repositoryS.setName(sc.nextLine());
        logger.info("Nhập vị trí kho:");
        repositoryS.setPlace(sc.nextLine());
        return repositoryS;
    }
}
