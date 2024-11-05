package com.vinotech.rediscache.service;

import com.vinotech.rediscache.model.User;
import com.vinotech.rediscache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        log.info("Attempting to fetch user with ID {} from  Cache. iDatabase.", id);
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @CachePut(value = "users", key = "#user.id")
    public User saveUser(User user) {
        log.info("Saving user with ID {} to the database and updating cache.", user.getId());
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        log.info("Deleting user with ID {} from the database and evicting from cache.", id);
        userRepository.deleteById(id);
    }
}
