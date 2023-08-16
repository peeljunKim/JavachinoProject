package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminAccomodationDAO;
import com.example.demo.admin.dao.AdminAccomodationFileDAO;
import com.example.demo.admin.dao.AdminAccomodationInfoDAO;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationFile;
import com.example.demo.entity.AccomodationInfo;

import lombok.Setter;

@Service
@Setter
public class AdminAccomodationService {
	private final AdminAccomodationDAO accomodationdao;
    private final AdminAccomodationInfoDAO infodao;
    private final AdminAccomodationFileDAO filedao;

    @Autowired
    public AdminAccomodationService(AdminAccomodationDAO accomodationdao, AdminAccomodationInfoDAO infodao, AdminAccomodationFileDAO filedao) {
        this.accomodationdao = accomodationdao;
        this.infodao = infodao;
        this.filedao = filedao;
    }

    public void insertAccomodationAndInfo(Accomodation a, AccomodationInfo ai, AccomodationFile af) {
        accomodationdao.save(a); // Saves Accomodation entity
        ai.setAccomodation(a); // Set the relationship
        infodao.save(ai); // Saves AccomodationInfo entity
        af.setAccomodation(a);
        filedao.save(af);
    }

}
