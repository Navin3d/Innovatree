package gmc.project.innovatree.usersws.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersEntity, Long> {
	UsersEntity findByEmail(String email);
	UsersEntity findByPhoneNumber(String phoneNumber);
}
