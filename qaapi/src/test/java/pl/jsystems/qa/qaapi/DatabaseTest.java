package pl.jsystems.qa.qaapi;

import org.junit.jupiter.api.*;
import pl.jsystems.qa.qadatabase.database.UserDao;
import pl.jsystems.qa.qadatabase.database.model.UserDb;

import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;

@Tags({@Tag("Api"), @Tag("database")})
public class DatabaseTest {

    @DisplayName("Database get all users.")
    @Test
    public void getAllDatabaseUsers() {

        List<UserDb> userDbs = UserDao.getAllUsers();
        System.out.println(userDbs);

        assertThat(userDbs.get(0).id).isEqualTo("1");
        assertThat(userDbs.get(0).name).isEqualTo("Piotr");
        assertThat(userDbs.get(0).surname).isEqualTo("Kowalski");
    }

    @DisplayName("Get user by id.")
    @Test
    public void getUserById() {
        UserDb userDb = UserDao.getUserById("1");
        assertThat(userDb.id).isEqualTo("1");
        assertThat(userDb.name).isEqualTo("Piotr");
        assertThat(userDb.surname).isEqualTo("Kowalski");

    }


    @DisplayName("Post user.")
//    @RepeatedTest(5)
    @Test
    public void saveUser() {
        final UserDb userDb = new UserDb("4", "Jan", "Nowak");

        UserDao.saveUser(userDb);

        UserDb userDbFromDatabase = UserDao.getUserById(userDb.id);

        assertThat(userDbFromDatabase.id).isEqualTo(userDb.id);
        assertThat(userDbFromDatabase.name).isEqualTo("Jan");
        assertThat(userDbFromDatabase.surname).isEqualTo("Nowak");
        UserDao.deleteUser(userDb.id);
    }

    @DisplayName("Delete user by id.")
    @Test
    public void deleteUser() {
        //given
        UserDb userDb = new UserDb("5", "Jan", "Nowak");
        UserDao.saveUser(userDb);

        UserDb userDbFromDatabase = UserDao.getUserById(userDb.id);
        assertThat(userDbFromDatabase.id).isEqualTo(userDb.id);
        assertThat(userDbFromDatabase.name).isEqualTo("Jan");
        assertThat(userDbFromDatabase.surname).isEqualTo("Nowak");

        //when
        UserDao.deleteUser(userDb.id);

        //then
        userDbFromDatabase = UserDao.getUserById(userDb.id);
        assertThat(userDbFromDatabase).isNull();

        List<UserDb> userDbs = UserDao.getAllUsers();

        boolean userExist = false;

        for (UserDb user :  userDbs) {
            if (user.id.equals(userDb.id)) {
                userExist = true;
            }
        }

        Assertions.assertFalse(userExist);
    }

    @DisplayName("Update user test.")
    @Test
    public void updateTest() {
        final String userId = "6";
        final UserDb userDb = new UserDb(userId, "Jan", "Nowak");
        UserDao.saveUser(userDb);

        final String updatedId = "7";
        final UserDb updatedUser = new UserDb(updatedId, "Jolanta", "Nowak");

        UserDao.updateUser(updatedUser, userId);

        UserDb resultUser = UserDao.getUserById(updatedId);

        assertThat(resultUser.id).isEqualTo(updatedId);
        assertThat(resultUser.name).isEqualTo("Jolanta");
        assertThat(resultUser.surname).isEqualTo("Nowak");

        UserDb notExists = UserDao.getUserById(userId);

        assertThat(notExists).isNull();
        UserDao.deleteUser(updatedId);

    }

}
