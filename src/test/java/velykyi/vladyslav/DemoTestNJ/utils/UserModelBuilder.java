package velykyi.vladyslav.DemoTestNJ.utils;

import velykyi.vladyslav.DemoTestNJ.model.UserModel;

public class UserModelBuilder {
    private long id = 1;

    private String name = "name";

    private String surname = "surname";

    private String email = "something@gmail.com";

    public UserModel build() {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(name);
        userModel.setSurname(surname);
        userModel.setEmail(this.email);

        return userModel;
    }

    public UserModelBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public UserModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserModelBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserModelBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public static UserModelBuilder getBuilder() {
        return new UserModelBuilder();
    }
}
