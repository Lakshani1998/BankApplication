package com.example.Bank.Application.service.impl;

import com.example.Bank.Application.dto.AccountInfo;
import com.example.Bank.Application.dto.BankResponse;
import com.example.Bank.Application.dto.UserRequest;
import com.example.Bank.Application.entity.User;
import com.example.Bank.Application.repository.UserRepository;
import com.example.Bank.Application.utils.AccountUtils;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
//        createAccount - save new user into the DB

        if(userRepository.existsByEmail(userRequest.getEmail())){
                BankResponse response = BankResponse.builder()
                        .ResponseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                        .ResponseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                        .accountInfo(null)
                        .build();
                return response;
        }
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalence(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("Active")
                .build();

        User savedUser = userRepository.save(newUser);
        return BankResponse.builder()
                .ResponseCode(AccountUtils.ACCOUNT_CREATION_SUCESS)
                .ResponseMessage(AccountUtils.ACCOUNT_CRAETION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalence(savedUser.getAccountBalence())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                        .build())
                .build();
    }
}
