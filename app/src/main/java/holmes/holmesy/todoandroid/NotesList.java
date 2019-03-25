package holmes.holmesy.todoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import holmes.holmesy.todoandroid.Room.AppDatabase;
import holmes.holmesy.todoandroid.Room.Entity;

import static android.widget.LinearLayout.VERTICAL;

public class NotesList extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recylerView;
    NoteAdapter noteAdapter;
    AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        floatingActionButton = findViewById(R.id.fab);


        recylerView = findViewById(R.id.recyclerViewTasks);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this, this);
        recylerView.setAdapter(noteAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recylerView.addItemDecoration(decoration);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                // TODO (1) Get the diskIO Executor from the instance of AppExecutors and
                // call the diskIO execute method with a new Runnable and implement its run method

                // TODO (3) get the position from the viewHolder parameter
                // TODO (4) Call deleteTask in the taskDao with the task at that position
                // TODO (6) Call retrieveTasks method to refresh the UI
            }
        }).attachToRecyclerView(recylerView);









        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesList.this, AddTask.class);
                startActivity(intent);
            }
        });

        mDb = AppDatabase.getInstance(getApplicationContext());






    }



    @Override
    protected void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<Entity> tasks = mDb.taskDao().loadAllTasks();
                        noteAdapter.setTasks(tasks);
                    }
                });
            }
        });
    }


    @Override
    public void onItemClickListener(int itemId) {
        // Launch AddTaskActivity adding the itemId as an extra in the intent
    }

}
