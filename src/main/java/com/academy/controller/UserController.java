package com.academy.controller;

import com.academy.component.UserWorker;
import com.academy.dto.UserDto;
import com.academy.exception.ProductException;
import com.academy.exception.UserException;
import com.academy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Api("REST Controller per le operazioni sugli Utenti")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserWorker userWorker;

    @ApiOperation("Restituisce tutti gli utenti")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @ApiOperation("Restituisce l'utente tramite id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) throws UserException {
        return ResponseEntity.ok(userService.getById(id));
    }

    @ApiOperation("Restituisce gli utenti tramite cognome")
    @GetMapping(value = "/surname/{surname}")
    public ResponseEntity<List<UserDto>> getBySurname(@PathVariable String surname) {
        return ResponseEntity.ok(userService.getBySurname(surname));
    }

    @ApiOperation("Restituisce gli utenti maggiorenni")
    @GetMapping(value = "/maggiorenni")
    public ResponseEntity<List<UserDto>> getMaggiorenni() {
        return ResponseEntity.ok(userService.getMaggiorenni());
    }

    @ApiOperation("Aggiunge un nuovo utente")
    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.add(userDto));
    }

    @ApiOperation("Associa utente a prodotto")
    @PostMapping(value = "buy/{idUser}/{idProduct}")
    public ResponseEntity<UserDto> buy(@PathVariable Long idUser, @PathVariable Long idProduct) throws UserException, ProductException {
        return ResponseEntity.ok(userWorker.buy(idUser, idProduct));
    }

    @ApiOperation("Aggiorna l'utente tramite id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @ApiOperation("Cancella l'utente tramite id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) throws UserException {
        return ResponseEntity.ok(userService.delete(id));
    }
}