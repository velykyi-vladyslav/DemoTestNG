package velykyi.vladyslav.DemoTestNJ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velykyi.vladyslav.DemoTestNJ.dao.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Long> {

    @Override
    void deleteAll();
}
