package holmes.holmesy.todoandroid.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@android.arch.persistence.room.Entity(tableName = "task")
public class Entity  {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private int priority;
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @Ignore
    public Entity(String description, int priority, Date updatedAt) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public Entity(int id, String description, int priority, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

