package gmc.project.innovatree.usersws.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.usersws.entity.UsersEntity;

public interface UserDao extends JpaRepository<UsersEntity, Long> {
	UsersEntity findByUserId(String userId);
	UsersEntity findByEmail(String email);
	UsersEntity findByPhoneNumber(String phoneNumber);
}
