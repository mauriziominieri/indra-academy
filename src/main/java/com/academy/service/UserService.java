package com.academy.service;

import com.academy.dto.UserDto;
import com.academy.exception.UserException;
import com.academy.mapper.UserMapper;
import com.academy.model.User;
import com.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAll() {
        return userMapper.toDtos(userRepository.findAll());
    }

    public UserDto getById(Long id) throws UserException {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new UserException("Utente con id " + id + " non trovato")));
    }

    public UserDto add(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toModel(userDto)));
    }

    public UserDto update(Long id, UserDto userDto) throws UserException {
        userRepository.findById(id).orElseThrow(() -> new UserException("Utente con id " + id + " non trovato"));
        userDto.setId(id);
        return userMapper.toDto(userRepository.save(userMapper.toModel(userDto)));
    }

    public UserDto delete(Long id) throws UserException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("Utente con id " + id + " non trovato"));
        userRepository.deleteById(id);
        return userMapper.toDto(user);
    }
}