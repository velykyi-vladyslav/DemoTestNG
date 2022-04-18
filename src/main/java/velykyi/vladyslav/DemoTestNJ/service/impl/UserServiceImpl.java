package velykyi.vladyslav.DemoTestNJ.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.DemoTestNJ.exception.EmailNotAllowedException;
import velykyi.vladyslav.DemoTestNJ.exception.UserNotFoundException;
import velykyi.vladyslav.DemoTestNJ.model.UserModel;
import velykyi.vladyslav.DemoTestNJ.repository.UserRepository;
import velykyi.vladyslav.DemoTestNJ.service.UserService;
import velykyi.vladyslav.DemoTestNJ.service.mapper.UserMapper;

import static java.util.Objects.isNull;
import static velykyi.vladyslav.DemoTestNJ.service.impl.ServiceValidator.validateEmail;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Override
    public UserModel save(UserModel user) {
        validate(user);

        return userMapper.userDaoToUserModel(userRepository.save(userMapper.userModelToUserDao(user)));
    }

    @Override
    public UserModel getUser(Long userId) {
        return userMapper.userDaoToUserModel(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void delete(long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    private void validate(UserModel user) {
        if (isNull(user.getEmail())){
            throw new EmailNotAllowedException();
        }
        validateEmail(user.getEmail());
    }
}
