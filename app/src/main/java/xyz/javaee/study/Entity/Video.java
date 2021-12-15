package xyz.javaee.study.Entity;

public class Video {
    //层级
    private int level;
    //id
    private int id;
    //父id
    private int p_id;
    //章节名字
    private String name;
    //视频URL
    private String URL;

    public Video() {
    }

    public Video(int level, int id, int p_id, String name, String URL) {
        this.level = level;
        this.id = id;
        this.p_id = p_id;
        this.name = name;
        this.URL = URL;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
