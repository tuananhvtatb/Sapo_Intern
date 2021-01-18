package com.sapo.edu.ex5dbspringboot.service.jpa;

import com.sapo.edu.ex5dbspringboot.entities.RepositoryS;
import com.sapo.edu.ex5dbspringboot.repository.RepositoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorySevice {

    Logger logger = LoggerFactory.getLogger(RepositorySevice.class);

    private final RepositoryRepo repositoryRepo;

    public RepositorySevice(RepositoryRepo repositoryRepo) {
        this.repositoryRepo = repositoryRepo;
    }

    public List<RepositoryS> getRepositories() {
        return repositoryRepo.findAll();
    }

    public void creat(RepositoryS repositoryS) {
        if (repositoryS.getIdRepository() != null) {
            repositoryS.setCreatedDate(LocalDateTime.now());
            repositoryRepo.save(repositoryS);
        }
    }

    public void save(RepositoryS repositoryS) {
        repositoryRepo.save(repositoryS);
    }

    public void delete(RepositoryS repositoryS) {
        repositoryRepo.delete(repositoryS);
    }

    public RepositoryS findByCode(String code) {
        RepositoryS repositoryS = new RepositoryS();
        for (RepositoryS r : getRepositories()
        ) {
            if (r.getIdRepository().equalsIgnoreCase(code)) {
                repositoryS = r;
            }
        }
        return repositoryS;
    }

    public List<String> getCodeRepo() {
        List<String> codeRepoList = new ArrayList<>();
        List<RepositoryS> repositorySList = getRepositories();
        for (RepositoryS r : repositorySList
        ) {
            codeRepoList.add(r.getIdRepository());
        }
        return codeRepoList;
    }
}
