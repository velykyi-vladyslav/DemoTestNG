package velykyi.vladyslav.DemoTestNJ.service;

import velykyi.vladyslav.DemoTestNJ.model.UserModel;

public interface UserService {

    UserModel save(UserModel user);

    UserModel getUser(Long userId);

    void delete(long id);

    void deleteAll();
}
