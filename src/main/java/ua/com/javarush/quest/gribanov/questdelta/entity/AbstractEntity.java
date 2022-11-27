package ua.com.javarush.quest.gribanov.questdelta.entity;


public abstract class AbstractEntity {
    private Long id;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

}
