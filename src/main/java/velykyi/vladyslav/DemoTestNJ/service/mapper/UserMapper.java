package velykyi.vladyslav.DemoTestNJ.service.mapper;

import org.mapstruct.Mapper;
import velykyi.vladyslav.DemoTestNJ.dao.UserDao;
import velykyi.vladyslav.DemoTestNJ.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel userDaoToUserModel(UserDao userDao);

    UserDao userModelToUserDao(UserModel userModel);
}
