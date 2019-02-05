package com.hellokoding.chat;

import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<ChatUnit, String>{
}
