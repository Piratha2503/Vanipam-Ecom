package com.service.Users.Service.Impl;

import com.service.Users.Repositories.SellerRepository;
import com.service.Users.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;
}
