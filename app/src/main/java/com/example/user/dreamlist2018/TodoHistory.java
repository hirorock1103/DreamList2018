package com.example.user.dreamlist2018;

public class TodoHistory {

    private int id;
    private int todoId;
    private String todoDetails;
    private String createdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoDetails() {
        return todoDetails;
    }

    public void setTodoDetails(String todoDetails) {
        this.todoDetails = todoDetails;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
