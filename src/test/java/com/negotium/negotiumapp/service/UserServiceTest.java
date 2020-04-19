package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.model.user.User;
import com.negotium.negotiumapp.model.user.UserDto;
import com.negotium.negotiumapp.model.user.UserMapper;
import com.negotium.negotiumapp.repository.UserRepository;
import com.negotium.negotiumapp.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Mock
    UserRepository userRepository;
    @Mock
    UserRoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UserService userService;

    @Test
    public void add_new_user(){
        //given
        UserDto userDto = creatNewUserDto();
        given(userRepository.save(any(User.class))).willReturn(UserMapper.toEntity(userDto));
        //when
        Boolean isUserAdded = userService.addWithDefaultRole(userDto);
        //then
        assertEquals(true, isUserAdded);
    }

    private UserDto creatNewUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("hello@op.pl");
        userDto.setUsername("Mike12");
        userDto.setPassword("12345678");
       return userDto;
    }

    @Test
    public void find_all(){
     //given
     List<User> users = getUsers();
     given((userRepository.findAll())).willReturn(users);
     //when
     List<UserDto> usersToFound = userService.findAll();
     //then
     assertEquals(4, usersToFound.size());
    }

    @Test
    public void find_all_by_username() {
        //given
        List<User> users = getUsers();
        String name = "Mi";
        given(userRepository.findByUsernameContaining(any(String.class))).willReturn(users);
        //when
        List<UserDto> usersToFound = userService.findAllByUsername(name);
        //then
        assertEquals(3, usersToFound.size());

    }

    @Test
    public void find_by_id() {
        //given
        List<User> users = getUsers();
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(users.get(0)));
        //when
        Optional<UserDto> userDto = userService.findById(1L);
        //then
        assertEquals("Mike", userDto.get().getUsername());
    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("Mike", "MyPassword", "helllo@hi.pl"));
        users.add(new User("MikeMike", "MyPassword", "helllo@hi.pl"));
        users.add(new User("Johny", "SecretPassword", "John@jo.pl"));
        users.add(new User("Mia", "WeakPassword", "Mia@Mia.pl"));
        return users;
    }
}