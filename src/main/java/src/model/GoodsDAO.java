package src.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GoodsDAO extends CrudRepository<Good, Integer> {

    public Good findByName(String name);

    public Good findById(Integer id);


}