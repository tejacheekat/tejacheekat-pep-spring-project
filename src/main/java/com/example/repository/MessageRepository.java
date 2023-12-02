package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;
import com.example.entity.Message;
@Transactional
public interface MessageRepository extends JpaRepository<Message,Integer> {
     List<Message> findAll();
       @Query("SELECT m FROM Message m WHERE m.message_id = :message_id")
    Message findMessageMessageId(Integer message_id);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.message_id = :messageId")
    int deleteByMessageIdAndReturnCount(@Param("messageId") Integer messageId);

    @Modifying
    @Query("UPDATE Message m SET m.message_text = :newMessageText WHERE m.message_id = :messageId")
    int updateMessageTextByMessageId(Integer messageId, String newMessageText);

    @Query("SELECT m FROM Message m Where m.posted_by = :postedBy")
    List<Message> findByUser(Integer postedBy);
    // @Query("SELECT m FROM Message m WHERE m.postedBy = :accountId")
    // List<Message> findMessagesByPostedBy(Long accountId);

}
