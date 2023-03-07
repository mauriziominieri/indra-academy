package com.academy.component;

import com.academy.dto.ProductDto;
import com.academy.dto.UserDto;
import com.academy.exception.ProductException;
import com.academy.exception.UserException;
import com.academy.mapper.ProductMapper;
import com.academy.mapper.UserMapper;
import com.academy.service.ProductService;
import com.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    public UserDto buy(Long userId, Long productId) throws UserException, ProductException {
        UserDto userDto = userService.getById(userId);
        ProductDto productDto = productService.getById(productId);
//        user.getOrdini().add(product);
//        userRepoitory.save(user);

        userService.buy(userDto, productMapper.toModel(productDto));

        String from = "academy@minsait.com";
        String to = userDto.getEmail();
        String subject = "Acquisto Prodotto";
        String body = "Prodotto " + productDto.getName() + " acquistato correttamente.";

        String smtpHost = "localhost";
        int smtpPort = 25;

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }

        return userDto;
    }
}