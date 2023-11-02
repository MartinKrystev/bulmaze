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
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
        initSeasons();

    }

    private void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);

            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(moderatorRole);
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
        UserEntity adminUser = new UserEntity()
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@test.com")
                .setCountry("Bulgaria")
                .setUsername("admin")
                .setScore(0)
                .setPassword(passwordEncoder.encode("testpass"))
                .setRoles(this.userRoleRepository.findAll());

        this.userRepository.save(adminUser);
    }

    private void initModerator() {
        UserRoleEntity moderatorRole = this.userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow();

        UserEntity moderatorUser = new UserEntity()
                .setFirstName("Moderator")
                .setLastName("Moderatorov")
                .setEmail("moderator@test.com")
                .setCountry("Bulgaria")
                .setUsername("moderator")
                .setScore(0)
                .setPassword(passwordEncoder.encode("testpass"))
                .setRoles(List.of(moderatorRole));

        userRepository.save(moderatorUser);
    }

    private void initCommonUser() {
        UserEntity commonUser = new UserEntity()
                .setFirstName("User")
                .setLastName("Userov")
                .setEmail("user@test.com")
                .setCountry("Japan")
                .setUsername("user")
                .setScore(0)
                .setPassword(passwordEncoder.encode("testpass"));

        userRepository.save(commonUser);
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



}
