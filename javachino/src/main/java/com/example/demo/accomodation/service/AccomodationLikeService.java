package com.example.demo.accomodation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.AccomodationLikeRepository;
import com.example.demo.accomodation.repository.AccomodationRepository;
import com.example.demo.accomodation.repository.UsersRepository;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationLike;
import com.example.demo.entity.Users;

import jakarta.transaction.Transactional;

@Service
public class AccomodationLikeService {
	@Autowired
	private AccomodationLikeRepository accomodationLikeRepository;
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;

	@Transactional
	public void likeAccomodation(int usersNo, int accomodationNo) {
		Users user = usersRepository.findByUsersNo(usersNo);
		Accomodation accomodation= accomodationRepository.findAccomodationByNo(accomodationNo);

		if (accomodationLikeRepository.existsByUsersAndAccomodation(user, accomodation)) {
			accomodationLikeRepository.deleteByUsersAndAccomodation(user, accomodation);
		} else {
			AccomodationLike accomodationLike= new AccomodationLike();
			accomodationLike.setUsers(user);
			accomodationLike.setAccomodation(accomodation);
			accomodationLikeRepository.save(accomodationLike);
		}
	}

	@Transactional
	public void insertActivityLike(int accomodationNo, int usersNo) {
		accomodationLikeRepository.insertAccomodationLike(accomodationNo, usersNo);
	}
}
