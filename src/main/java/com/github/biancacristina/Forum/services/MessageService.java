package com.github.biancacristina.Forum.services;

import com.github.biancacristina.Forum.domain.Message;
import com.github.biancacristina.Forum.domain.Message;
import com.github.biancacristina.Forum.domain.Topic;
import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.dto.MessageDTO;
import com.github.biancacristina.Forum.repositories.MessageRepository;
import com.github.biancacristina.Forum.repositories.TopicRepository;
import com.github.biancacristina.Forum.repositories.UserRepository;
import com.github.biancacristina.Forum.services.exceptions.DataIntegrityException;
import com.github.biancacristina.Forum.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public Message findById(Integer id) {
        return messageRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Objeto nao encontrado! Id: " + id + ", Tipo: " + Message.class.getName()));
    }

    public Page<Message> findAllPage(
            Integer page,
            Integer linesPerPage
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return messageRepository.findAll(pageRequest);
    }

    public Message insert (
            User user,
            Topic topic,
            Message obj
    ) {
        obj.setId(null);
        obj.setTopic(topic);
        obj.setUser(user);
        topic.addMessage(obj);
        user.addMessage(obj);

        // Save changes in topic and user
        topicRepository.save(topic);
        userRepository.save(user);

        return messageRepository.save(obj);
    }

    public Message update (MessageDTO objDTO, Integer id) {
        Message newObj = this.findById(id);

        updateData(newObj, objDTO);

        return messageRepository.save(newObj);
    }

    public void updateData(Message newObj, MessageDTO objDTO) {
        if (!objDTO.getText().isEmpty()) {
            newObj.setText(objDTO.getText());
            newObj.setLastEdited(objDTO.getDate());
        }
    }

    public void deleteById(Integer id) {
        this.findById(id);

        try {
            messageRepository.deleteById(id);
        }

        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria com tópicos associado a ela.");
        }
    }

    public Message fromDTO (
            Topic topic,
            User user,
            MessageDTO objDTO
    ) {
        return new Message(objDTO.getId(), objDTO.getText(), topic, user);
    }
}
