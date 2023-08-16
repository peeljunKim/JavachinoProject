package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminAccomodationDAO;
import com.example.demo.admin.dao.AdminAccomodationFacDAO;
import com.example.demo.admin.dao.AdminAccomodationFileDAO;
import com.example.demo.admin.dao.AdminAccomodationInfoDAO;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationFac;
import com.example.demo.entity.AccomodationFile;
import com.example.demo.entity.AccomodationInfo;

import lombok.Setter;

@Service
@Setter
public class AdminAccomodationService {
	private final AdminAccomodationDAO accomodationdao;
    private final AdminAccomodationInfoDAO infodao;
    private final AdminAccomodationFacDAO facdao;
    private final AdminAccomodationFileDAO filedao;

    @Autowired
    public AdminAccomodationService(AdminAccomodationDAO accomodationdao, AdminAccomodationInfoDAO infodao, AdminAccomodationFacDAO facdao, AdminAccomodationFileDAO filedao) {
        this.accomodationdao = accomodationdao;
        this.infodao = infodao;
        this.facdao = facdao;
        this.filedao = filedao;
    }

    public void insertAccomodationAndInfo(Accomodation a, AccomodationInfo ai, AccomodationFac afc, AccomodationFile af) {
        accomodationdao.save(a); // Saves Accomodation entity
        ai.setAccomodation(a); // Set the relationship
        infodao.save(ai); // Saves AccomodationInfo entity
        afc.setAccomodation(a);
        facdao.save(afc);
        af.setAccomodation(a);
        filedao.save(af);
    }
}
