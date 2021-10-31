package gmc.project.innovatree.payments.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.payments.dao.UsersDao;
import gmc.project.innovatree.payments.entities.UsersEntity;
import gmc.project.innovatree.payments.models.BillPayingModel;
import gmc.project.innovatree.payments.models.RechargeModel;
import gmc.project.innovatree.payments.services.PaymentsService;
import gmc.project.innovatree.payments.shared.WalletDto;
import gmc.project.innovatree.payments.exception.*;

@Service
public class PaymentsServiceImpl implements PaymentsService {
	
	private final UsersDao usersDao;

	public PaymentsServiceImpl(UsersDao usersDao) {
		super();
		this.usersDao = usersDao;
	}

	@Override
	public WalletDto findUserBalanceByUserId(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity foundUser = usersDao.findByUserId(userId);
		WalletDto returnValue = modelMapper.map(foundUser, WalletDto.class);
		
		return returnValue;
	}

	@Override
	public WalletDto rechargeWallet(RechargeModel rechargeModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity foundUser = null;		
		try {
			foundUser = usersDao.findByUserId(rechargeModel.getUserId());
		} catch (Exception e) {
			throw new UserNotFoundException(e);
		}
		if(rechargeModel.getPinNumber().equals("2002"))
			foundUser.setWalletBalence(foundUser.getWalletBalence() + rechargeModel.getRechargeAmount());
		else
			throw new RuntimeException("Wrong Pin Number");
		
		UsersEntity savedUser = usersDao.save(foundUser);
		WalletDto returnValue = modelMapper.map(savedUser, WalletDto.class);
		
		return returnValue;
	}

	@Override
	public WalletDto reduceAmountFromWallet(BillPayingModel billPayingModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity admin = usersDao.findByUserId("0df2ef42-95c4-4472-adc2-93e4925e45c2");
		
		UsersEntity foundUser = null;		
		try {
			foundUser = usersDao.findByUserId(billPayingModel.getUserId());
		} catch (Exception e) {
			throw new UserNotFoundException(e);
		}
		
		if(foundUser.getWalletBalence() < billPayingModel.getTotalAmount())
			throw new InsufficientBalanceException("Current Balance: "+foundUser.getWalletBalence());
		
		if(billPayingModel.getPinNumber().equals("9771")) {
			
			foundUser.setWalletBalence(foundUser.getWalletBalence() - billPayingModel.getTotalAmount());
			foundUser.setAmountPaid(foundUser.getAmountPaid() + billPayingModel.getTotalAmount());
			foundUser.setPlantsCount(foundUser.getPlantsCount() + 1);
			
			admin.setWalletBalence(admin.getWalletBalence() + billPayingModel.getTotalAmount());
			admin.setAmountEarned(admin.getAmountEarned() + billPayingModel.getTotalAmount());
		
		} else
			throw new RuntimeException("Wrong Pin Number");
		
		usersDao.save(admin);
		
		UsersEntity savedUser = usersDao.save(foundUser);
		WalletDto returnValue = modelMapper.map(savedUser, WalletDto.class);
		
		return returnValue;
	}

}
