package gmc.project.innovatree.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.auth.entity.UsersEntity;

public interface UserDao extends JpaRepository<UsersEntity, Long> {
	UsersEntity findByEmail(String email);
	UsersEntity findByPhoneNumber(String phoneNumber);
}
