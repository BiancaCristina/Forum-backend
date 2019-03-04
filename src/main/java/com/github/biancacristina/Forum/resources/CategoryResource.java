package com.github.biancacristina.Forum.resources;

import com.github.biancacristina.Forum.domain.Category;
import com.github.biancacristina.Forum.dto.CategoryDTO;
import com.github.biancacristina.Forum.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Category> findById (@PathVariable Integer id) {
        Category obj = categoryService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findAllPage (
        @RequestParam(value="page", defaultValue= "0") Integer page,
        @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage
    ) {
        Page<Category> list = categoryService.findAllPage(page, linesPerPage);
        Page<CategoryDTO> listDTO = list.map( obj -> new CategoryDTO(obj.getId(), obj.getName()));
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody CategoryDTO objDTO) {
        Category obj = categoryService.fromDTO(objDTO);
        obj = categoryService.insert(obj);

        // Returns the object's URI
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update (
            @Valid @RequestBody CategoryDTO objDTO,
            @PathVariable Integer id
    ) {
        categoryService.update(objDTO, id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById (@PathVariable Integer id) {
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
