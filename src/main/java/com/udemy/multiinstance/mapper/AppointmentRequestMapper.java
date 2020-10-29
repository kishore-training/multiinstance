package com.udemy.multiinstance.mapper;

import com.udemy.multiinstance.model.Appointment;
import com.udemy.multiinstance.vo.AppointmentRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentRequestMapper {

    AppointmentRequestMapper INSTANCE = Mappers.getMapper(AppointmentRequestMapper.class);



    Appointment appointmentVOToModel(AppointmentRequestVO vo);


    AppointmentRequestVO appointmentModelToVO(Appointment appointment);
}
