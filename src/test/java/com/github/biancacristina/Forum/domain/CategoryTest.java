package com.github.biancacristina.Forum.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryTest {
    @Test
    public void shouldNotHaveEmptyName() {
        Category category = new Category(null, "Category Test");

        assertNotEquals(0, category.getName().length());
    }

    @Test
    public void shouldNotHaveEqualIds() {
        Category cat1 = new Category(null, "Category 1");
        Category cat2 = new Category(null, "Category 2");

        assertNotEquals(cat1.getId(), cat2.getId());
    }
}
