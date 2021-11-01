package gmc.project.innovatree.achievements.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.achievements.dao.AchievementsDao;
import gmc.project.innovatree.achievements.entities.AchievementsEntity;
import gmc.project.innovatree.achievements.exceptions.AchievementsNotFoundException;
import gmc.project.innovatree.achievements.models.AchievementModel;
import gmc.project.innovatree.achievements.service.AchievementsService;
import gmc.project.innovatree.achievements.shared.AchievementsDto;

@Service
public class AchievementsServiceImpl implements AchievementsService {
	
	private final AchievementsDao achievementsDao;

	public AchievementsServiceImpl(AchievementsDao achievementsDao) {
		super();
		this.achievementsDao = achievementsDao;
	}

	@Override
	public AchievementsDto findAchievementByAchievementId(String achievementId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AchievementsEntity foundAchievement = achievementsDao.findByAchievementId(achievementId);
		
		if(foundAchievement == null)
			throw new AchievementsNotFoundException(achievementId);
		
		AchievementsDto returnValue = modelMapper.map(foundAchievement, AchievementsDto.class);
				
		return returnValue;
	}

	@Override
	public List<AchievementsDto> findAchievementByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchievementsDto addAchievement(AchievementModel achievementModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AchievementsEntity detachedAchievement = modelMapper.map(achievementModel, AchievementsEntity.class);
		detachedAchievement.setAchievementId(UUID.randomUUID().toString());
		
		AchievementsEntity savedAchievement = achievementsDao.save(detachedAchievement);
		AchievementsDto returnValue = modelMapper.map(savedAchievement, AchievementsDto.class);
		
		return returnValue;
	}
	
	@Override
	public AchievementsDto editAchievement(AchievementsDto achievementsDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AchievementsEntity foundAchievement = achievementsDao.findByAchievementId(achievementsDto.getAchievementId());
		
		if(foundAchievement == null)
			throw new AchievementsNotFoundException(achievementsDto.getAchievementId()); 
				
		foundAchievement = modelMapper.map(achievementsDto, AchievementsEntity.class);
		AchievementsEntity savedAchievement = achievementsDao.save(foundAchievement);		
		AchievementsDto returnValue = modelMapper.map(savedAchievement, AchievementsDto.class);
		
		return returnValue;
	}

	@Override
	public void deleteAchievement(String achievementId) {
		AchievementsEntity toDelete = achievementsDao.findByAchievementId(achievementId);
		achievementsDao.delete(toDelete);
	}

}
