package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = Arrays.asList(
                User.builder().userId(1).userName("John Doe").build(),
                User.builder().userId(2).userName("Jane Doe").build()
        );

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<UserDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getUserName());
        assertEquals("Jane Doe", result.get(1).getUserName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Success() {
        User user = User.builder().userId(1).userName("John Doe").build();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("John Doe", result.getUserName());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertNull(result.getUserId());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUserById_Error() {
        when(userRepository.findById(1)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1);
        });

        assertEquals("Database error", exception.getCause().getMessage());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetCategoriesByUserId_Success() {
        Category category1 = Category.builder().id(1).name("Movies").build();
        Category category2 = Category.builder().id(2).name("Sports").build();
        User user = User.builder().userId(1).categories(Arrays.asList(category1, category2)).build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        List<Category> result = userService.getCategoriesByUserId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Movies", result.get(0).getName());
        assertEquals("Sports", result.get(1).getName());
        verify(userRepository, times(1)).findById(1);
    }
}
