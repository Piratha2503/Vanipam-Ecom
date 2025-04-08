package com.service.Users.Controller;

import com.service.Users.Service.SellerService;
import com.service.Users.Utils.APIEndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(APIEndPoints.baseAPI)
public class SellerController {

    @Autowired
    private SellerService sellerService;
}
