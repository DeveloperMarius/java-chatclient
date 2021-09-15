package net.atos.bscs211.client.objects;

public class Group {

    protected int id;
    protected String title;

    public Group(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
