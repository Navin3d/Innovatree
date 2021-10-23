package gmc.project.innovatree.usersws.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.usersws.entity.AddressEntity;

public interface AddressDao extends JpaRepository<AddressEntity, Long> {

}
