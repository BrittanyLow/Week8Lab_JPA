package Services;

import Database.UserDB;
import Models.Note;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public Note get(int noteId) throws Exception {
        return userDB.getNote(noteId);
    }

    public List<Note> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(int noteId, String content) throws Exception {
//        Note note = get(noteId);
//        note.setContent(content);
        Note note = new Note(noteId, Calendar.getInstance().getTime(), content);
        return userDB.update(note);
    }

    public int delete(int noteId) throws Exception {
        Note deletednote = userDB.getNote(noteId);
//        if (deletednote.g)
        return userDB.delete(deletednote);
    }

    public int insert(String content) throws Exception {
        Note note = new Note(0, Calendar.getInstance().getTime(), content);
//        note.set
        return userDB.insert(note);
    }
}
