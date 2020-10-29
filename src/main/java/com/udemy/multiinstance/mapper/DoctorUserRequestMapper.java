package com.udemy.multiinstance.mapper;

import com.udemy.multiinstance.model.DoctorUser;
import com.udemy.multiinstance.vo.DoctorUserRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorUserRequestMapper {

    DoctorUserRequestMapper INSTANCE = Mappers.getMapper(DoctorUserRequestMapper.class);


    DoctorUser organizationStaffVOToModel(DoctorUserRequestVO vo);

    DoctorUserRequestVO organizationStaffModelToVO(DoctorUser doctorUser);
}
