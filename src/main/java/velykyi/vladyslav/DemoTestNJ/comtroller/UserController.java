package velykyi.vladyslav.DemoTestNJ.comtroller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import velykyi.vladyslav.DemoTestNJ.model.UserModel;
import velykyi.vladyslav.DemoTestNJ.service.UserService;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("users")
public class UserController {

    protected static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("create")
    @ResponseStatus(CREATED)
    public UserModel saveUser(@RequestBody UserModel userModel) {
        log.info(format("Save user: %s", userModel));

        return userService.save(userModel);
    }

    @GetMapping("get/{id}")
    @ResponseStatus(OK)
    public UserModel getUser(@PathVariable long id) {
        log.info(format("Get user with id: %o", id));

        return userService.getUser(id);
    }

    @GetMapping("get/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        log.info(format("Get user with id: %o", id));
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
