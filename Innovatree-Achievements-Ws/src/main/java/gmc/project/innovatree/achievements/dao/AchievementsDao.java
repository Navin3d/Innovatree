package gmc.project.innovatree.achievements.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.achievements.entities.AchievementsEntity;

public interface AchievementsDao extends JpaRepository<AchievementsEntity, Long> {

}
