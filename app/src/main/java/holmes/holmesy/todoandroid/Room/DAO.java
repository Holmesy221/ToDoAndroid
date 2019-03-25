package holmes.holmesy.todoandroid.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM task ORDER by priority")
    List<Entity> loadAllTasks();

    @Insert
    void insertTask(Entity entity);
    @Update
    void updateTask(Entity entity);

    @Delete
    void deleteTask(Entity entity);

}
