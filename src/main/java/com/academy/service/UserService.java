package com.academy.service;

import com.academy.dto.UserDto;
import com.academy.exception.UserException;
import com.academy.mapper.UserMapper;
import com.academy.model.Product;
import com.academy.model.User;
import com.academy.repository.UserRepository;
import com.academy.utils.PropertiesUtils;
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
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new UserException(PropertiesUtils.getMessage("message.user.notFound", new Object[]{id}))));
    }

    public List<UserDto> getBySurname(String surname) {
        return userMapper.toDtos(userRepository.findBySurnameIgnoreCase(surname));
    }

    public List<UserDto> getMaggiorenni() {
        return userMapper.toDtos(userRepository.findByYearsGreaterThanOrderByYearsDesc(18L));
    }

    public UserDto add(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toModel(userDto)));
    }

    public UserDto buy(UserDto userDto, Product product) {
        User user = userMapper.toModel(userDto);
        user.getOrdini().add(product);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto update(Long id, UserDto userDto) throws UserException {
        userRepository.findById(id).orElseThrow(() -> new UserException(PropertiesUtils.getMessage("message.user.notFound", new Object[]{id})));
        userDto.setId(id);
        return userMapper.toDto(userRepository.save(userMapper.toModel(userDto)));
    }

    public UserDto delete(Long id) throws UserException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(PropertiesUtils.getMessage("message.user.notFound", new Object[]{id})));
        userRepository.deleteById(id);
        return userMapper.toDto(user);
    }
}