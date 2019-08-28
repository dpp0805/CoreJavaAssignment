package com.greatLearning.fsd.controller.category;

public class Fashion extends Category {
    public Fashion() {
        super("Fashion");
    }

    @Override
    protected void setDescription() {
        description = "This category includes all the fashion items !!!";
    }
}