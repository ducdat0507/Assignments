package com.example.project_8.repository;

import com.example.project_8.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findTop50ByOrderByTimestampDesc();

}
