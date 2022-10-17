package starterrestapimongodbspringboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import starterrestapimongodbspringboot.entity.User;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code Prism Technologies Pvt Ltd
 **/

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
