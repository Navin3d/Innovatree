package gmc.project.innovatree.usersws.service;

import gmc.project.innovatree.usersws.model.DeleteUserModel;
import gmc.project.innovatree.usersws.model.UsersEditModel;
import gmc.project.innovatree.usersws.model.UsersInfoModel;
import gmc.project.innovatree.usersws.shared.UsersDto;

public interface UsersService {
	UsersDto findOne(String userId);
	void deleteUserById(String userId);
	UsersDto findUserByUserName(String userName);
	UsersInfoModel logedInUser(String userId);
	UsersEditModel logedInUserEdit(String userId);
	UsersInfoModel logedInUserSave(UsersEditModel usersEditModel);
	void deleteLogedInUser(DeleteUserModel deleteUserModel);
}
