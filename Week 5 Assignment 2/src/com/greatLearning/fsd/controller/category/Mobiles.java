package com.greatLearning.fsd.controller.category;

public class Mobiles extends Category {
    public Mobiles() {
        super("Mobiles");
    }
    
    @Override
    protected void setDescription() {
        description = "This category includes all the mobile devices !!!";
    }
}