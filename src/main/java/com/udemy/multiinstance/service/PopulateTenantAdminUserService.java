package com.udemy.multiinstance.service;

import com.udemy.multiinstance.model.DoctorUser;
import com.udemy.multiinstance.model.types.UserTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.logging.Logger;

@Component
public class PopulateTenantAdminUserService implements InitializingBean {
    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    DoctorUserRepository doctorUserRepository;

    public Logger logger = Logger.getLogger("PopulateTenantAdminUserService");

    @Override
    public void afterPropertiesSet() throws Exception {
        DoctorUser user = new DoctorUser();
        user.setUserName("tenant_admin");

        user.setUserTypeEnum(UserTypeEnum.HOSPITAL_ADMIN);
        user.setMobileNumber("9898989898");
        user.setPassword(bcryptEncoder.encode("password"));
        user.setEmailId("admin@gmail.com");
        user.setSpecialization("ADMIN");
        user.setCreateDateTime(Instant.now());
        user.setUpdateDateTime(Instant.now());

        doctorUserRepository.save(user);
        logger.info("CREATED TENANT ADMIN USER");
    }
}
