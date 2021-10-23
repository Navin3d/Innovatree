package gmc.project.innovatree.payments.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.payments.entities.UsersEntity;

public interface UsersDao extends JpaRepository<UsersEntity, Long> {

}
