package com.project.bulmaze.init;

import com.project.bulmaze.model.entity.SeasonEntity;
import com.project.bulmaze.model.entity.UserEntity;
import com.project.bulmaze.model.entity.UserRoleEntity;
import com.project.bulmaze.model.enums.UserRoleEnum;
import com.project.bulmaze.repository.SeasonRepository;
import com.project.bulmaze.repository.UserRepository;
import com.project.bulmaze.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SeasonRepository seasonRepository;

    @Autowired
    public InitDB(UserRepository userRepository,
                  UserRoleRepository userRoleRepository,
                  PasswordEncoder passwordEncoder,
                  SeasonRepository seasonRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public void run(String... args) {
        initSeasons();
        initRoles();
        initUsers();
    }

    private void initSeasons() {
        if (this.seasonRepository.count() == 0) {
            SeasonEntity season = new SeasonEntity()
                    .setName("Sofia Summer 2024")
                    .setStartDate(LocalDate.of(2024, 4, 21))
                    .setEndDate(LocalDate.of(2024, 9, 21));
            this.seasonRepository.save(season);
        }
    }

    private void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(moderatorRole);
            this.userRoleRepository.save(userRole);
        }
    }

    private void initUsers() {
        if (this.userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initCommonUser();
        }
    }

    private void initAdmin() {
        List<UserRoleEntity> all = this.userRoleRepository.findAll();
        UserEntity adminUser = new UserEntity()
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@test.com")
                .setCountry("Bulgaria")
                .setUsername("admin")
                .setScore(0)
                .setPassword(passwordEncoder.encode("testpass"))
                .setAnsweredQuestions(new ArrayList<>())
                .setRoles(all)
                .setUserProgress(0L)
                .setSeasons(this.seasonRepository.findAll());

        this.userRepository.save(adminUser);
    }

    private void initModerator() {
        UserRoleEntity moderatorRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow();
        UserRoleEntity userRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

        UserEntity moderatorUser = new UserEntity()
                .setFirstName("Moderator")
                .setLastName("Moderatorov")
                .setEmail("moderator@test.com")
                .setCountry("Bulgaria")
                .setUsername("moderator")
                .setScore(0)
                .setPassword(passwordEncoder.encode("testpass"))
                .setAnsweredQuestions(new ArrayList<>())
                .setRoles(List.of(moderatorRole))
                .setRoles(List.of(userRole))
                .setUserProgress(0L)
                .setSeasons(this.seasonRepository.findAll());

        this.userRepository.save(moderatorUser);
    }

    private void initCommonUser() {
        UserRoleEntity userRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();

        UserEntity commonUser = new UserEntity()
                .setFirstName("User")
                .setLastName("Userov")
                .setEmail("user@test.com")
                .setCountry("Japan")
                .setUsername("user")
                .setScore(0)
                .setPassword(passwordEncoder.encode("testpass"))
                .setAnsweredQuestions(new ArrayList<>())
                .setRoles(List.of(userRole))
                .setUserProgress(0L)
                .setSeasons(this.seasonRepository.findAll());

        this.userRepository.save(commonUser);
    }

}
