package com.github.biancacristina.Forum.resources;

import com.github.biancacristina.Forum.domain.Message;
import com.github.biancacristina.Forum.domain.Topic;
import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.dto.MessageDTO;
import com.github.biancacristina.Forum.services.MessageService;
import com.github.biancacristina.Forum.services.TopicService;
import com.github.biancacristina.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/messages")
public class MessageResource {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Message> findById (@PathVariable Integer id) {
        Message obj = messageService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<MessageDTO>> findAllPage (
            @RequestParam(value="page", defaultValue= "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage
    ) {
        Page<Message> list = messageService.findAllPage(page, linesPerPage);
        Page<MessageDTO> listDTO = list.map(
                obj -> new MessageDTO(
                            obj.getId(),
                            obj.getText(),
                            obj.getLastEdited()));

        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value="/{idTopic}/{idUser}", method= RequestMethod.POST)
    public ResponseEntity<Void> insert (
            @Valid @RequestBody MessageDTO objDTO,
            @PathVariable Integer idTopic,
            @PathVariable Integer idUser) {
        User user = userService.findById(idUser);
        Topic topic = topicService.findById(idTopic);

        Message obj = messageService.fromDTO(topic, user, objDTO);
        obj = messageService.insert(user, topic, obj);

        // Returns the object's URI
        URI uri1 = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        String uriString = uri1.toString();

        String[] str = uriString.split("messages/");
        uriString = str[0] + "messages/" + obj.getId();

        URI uri2 = ServletUriComponentsBuilder
                .fromUriString(uriString)
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri2).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update (
            @Valid @RequestBody MessageDTO objDTO,
            @PathVariable Integer id
    ) {
        messageService.update(objDTO, id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById (@PathVariable Integer id) {
        messageService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
