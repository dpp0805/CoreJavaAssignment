package com.greatLearning.fsd.controller.category;

public abstract class Category {
    private String name;
    protected String description;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected abstract void setDescription();
    public String getDescription(){
        return description;
    }
}