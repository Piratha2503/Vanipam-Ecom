package com.service.Users.Service.Impl;

import com.service.Users.Repositories.BuyerRepository;
import com.service.Users.Service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
}
