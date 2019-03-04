package com.github.biancacristina.Forum.services;

import com.github.biancacristina.Forum.domain.Category;
import com.github.biancacristina.Forum.dto.CategoryDTO;
import com.github.biancacristina.Forum.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Page<Category> findAllPage(
            Integer page,
            Integer linesPerPage
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return categoryRepository.findAll(pageRequest);
    }

    public Category insert (Category obj) {
        obj.setId(null);

        return categoryRepository.save(obj);
    }

    public Category update (CategoryDTO objDTO, Integer id) {
        Category newObj = this.findById(id);

        updateData(objDTO, newObj);

        return categoryRepository.save(newObj);
    }

    public void updateData(CategoryDTO objDTO, Category newObj) {
        if (objDTO.getName() != null) {
            newObj.setName(objDTO.getName());
        }
    }

    public void deleteById(Integer id) {
        this.findById(id);
        categoryRepository.deleteById(id);
    }

    public Category fromDTO (CategoryDTO objDTO) {
        return new Category(objDTO.getId(), objDTO.getName());
    }
}
