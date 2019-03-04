package com.github.biancacristina.Forum.services;

import com.github.biancacristina.Forum.domain.Category;
import com.github.biancacristina.Forum.domain.Topic;
import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.dto.TopicDTO;
import com.github.biancacristina.Forum.repositories.CategoryRepository;
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
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Topic findById(Integer id) {
        return topicRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Objeto nao encontrado! Id: " + id + ", Tipo: " + Topic.class.getName()));
    }

    public Page<Topic> findAllPage(
            Integer page,
            Integer linesPerPage
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return topicRepository.findAll(pageRequest);
    }

    public Topic insert (
            User user,
            Category category,
            Topic obj) {
        obj.setId(null);
        obj.setUser(user);
        obj.setCategory(category);
        user.addTopic(obj);
        category.addTopic(obj);

        // Save the changes made in message, user and category too
        Topic newObj = topicRepository.save(obj);
        messageRepository.save(newObj.getMessages().get(0));
        userRepository.save(user);
        categoryRepository.save(category);

        return newObj;
    }

    public void deleteById(Integer id) {
        this.findById(id);

        try {
            topicRepository.deleteById(id);
        }

        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse tópico");
        }
    }

    public Topic fromDTO(User user, Category category, TopicDTO objDTO) {
        Topic newObj = new Topic(
                objDTO.getId(),
                objDTO.getTitle(),
                objDTO.getText(),
                category,
                user);

        newObj.setCreationDate(objDTO.getDate());
        newObj.setLastEdited(objDTO.getDate());

        return newObj;
    }
}
