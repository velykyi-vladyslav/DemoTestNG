package velykyi.vladyslav.DemoTestNJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import velykyi.vladyslav.DemoTestNJ.DemoTestNjApplication;
import velykyi.vladyslav.DemoTestNJ.exception.EmailNotAllowedException;
import velykyi.vladyslav.DemoTestNJ.exception.UserNotFoundException;
import velykyi.vladyslav.DemoTestNJ.model.UserModel;
import velykyi.vladyslav.DemoTestNJ.utils.UserModelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static org.testng.AssertJUnit.*;

@SpringBootTest(classes = DemoTestNjApplication.class)

public class UserServiceTest extends AbstractTestNGSpringContextTests {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    private static final UserModel VALID_MODEL = UserModelBuilder.getBuilder().build();

    @Autowired
    UserService userService;

    @BeforeClass
    void init() {
        userService.save(VALID_MODEL);
        VALID_MODEL.setId(2L);
    }

    @Test(dependsOnMethods = {"shouldSaveValidData"})
    void shouldFindUserIfExists() {
        log.info(format("Method shouldFindUserIfExists run from thread: %o", Thread.currentThread().getId()));

        assertNotNull(userService.getUser(1L));
        assertNotNull(userService.getUser(2L));
    }

    @Test
    void shouldSaveValidData() {
        log.info(format("Method shouldSaveValidData run from thread: %o", Thread.currentThread().getId()));

        UserModel actualModel = userService.save(VALID_MODEL);

        assertNotNull(actualModel);
        assertEquals(VALID_MODEL.getName(), actualModel.getName());
        assertEquals(VALID_MODEL.getSurname(), actualModel.getSurname());
        assertEquals(VALID_MODEL.getEmail(), actualModel.getEmail());
    }

    @Test(dataProvider = "invalid_models", expectedExceptions = {EmailNotAllowedException.class})
    void shouldRejectRequestWithInvalidEmail(UserModel actual) {
        log.info(format("Method shouldRejectRequestWithInvalidEmail run from thread: %o", Thread.currentThread().getId()));

        userService.save(actual);
    }

    @Test(dependsOnMethods = {"shouldFindUserIfExists"}, expectedExceptions = UserNotFoundException.class)
    void shouldDeleteUser() {
        log.info(format("Method shouldDeleteUser run from thread: %o", Thread.currentThread().getId()));

        userService.delete(VALID_MODEL.getId());

        //Should throw the exception
        userService.getUser(VALID_MODEL.getId());
    }

    @DataProvider(name = "invalid_models")
    Object[][] dpMethod() {
        return new Object[][] {{UserModelBuilder.getBuilder().withEmail(null).build()},
                               {UserModelBuilder.getBuilder().withEmail(".something@gmail.com").build()},
                               {UserModelBuilder.getBuilder().withEmail(".user.name@domain.com").build()},
                               {UserModelBuilder.getBuilder().withEmail("user-name@domain.com.").build()},
                               {UserModelBuilder.getBuilder().withEmail("username@.com").build()}};
    }

    @AfterClass
    void destroy() {
        userService.deleteAll();
    }
}
