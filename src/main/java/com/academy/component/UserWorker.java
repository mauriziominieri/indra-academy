package com.academy.component;

import com.academy.dto.UserDto;
import com.academy.exception.ProductException;
import com.academy.exception.UserException;
import com.academy.mapper.UserMapper;
import com.academy.model.Product;
import com.academy.model.User;
import com.academy.repository.ProductRepository;
import com.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Component
public class UserWorker {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto buy(Long userId, Long productId) throws UserException, ProductException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("Utente con id " + userId + " non trovato"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductException("Prodotto con id " + productId + " non trovato"));
        user.getOrdini().add(product);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}