package com.github.biancacristina.Forum.resources;

import com.github.biancacristina.Forum.domain.Category;
import com.github.biancacristina.Forum.domain.Topic;
import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.dto.TopicDTO;
import com.github.biancacristina.Forum.services.CategoryService;
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
@RequestMapping(value="/topics")
public class TopicResource {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Topic> findById (@PathVariable Integer id) {
        Topic obj = topicService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<TopicDTO>> findAllPage (
            @RequestParam(value="page", defaultValue= "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage
    ) {
        Page<Topic> list = topicService.findAllPage(page, linesPerPage);
        Page<TopicDTO> listDTO = list.map(obj -> new TopicDTO(
                obj.getId(),
                obj.getTitle(),
                obj.getMessages().get(0).getText(),
                obj.getCreationDate()));

        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value="/{idCategory}/{idUser}", method= RequestMethod.POST)
    public ResponseEntity<Void> insert (
            @PathVariable Integer idCategory,
            @PathVariable Integer idUser,
            @Valid @RequestBody TopicDTO objDTO) {

        Category category = categoryService.findById(idCategory);
        User user = userService.findById(idUser);

        Topic obj = topicService.fromDTO(user, category,objDTO);
        obj = topicService.insert(user, category, obj);

        // Returns the object's URI
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        String uriString = uri.toString();

        uriString = uriString.split("topics/") + "topics/" + obj.getId();

        uri = ServletUriComponentsBuilder
                .fromUriString(uriString)
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById (@PathVariable Integer id) {
        topicService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
