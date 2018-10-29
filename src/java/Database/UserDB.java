package Database;

import Models.Note;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDB {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            String preparedQ = "insert into notes(datecreated,content) values(?,?)";
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting note");
        } finally {
            em.close();
        }
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating note");
        } finally {
            em.close();
        }
    }

    /**
     * ***************************************************************
     */
    public List<Note> getAll() throws NotesDBException, ParseException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Note> notes = em.createNamedQuery("User.findAll", Note.class).getResultList();
            return notes;
        } finally {
            em.close();
        }

    }

    public Note getNote(int noteId) throws NotesDBException, ParseException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteId);
            return note;
        } finally {
            em.close();
        }
    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        String preparedQuery = "DELETE FROM notes WHERE noteId = ?";
        PreparedStatement ps;

        try {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting User");
        } finally {
            em.close();
        }
    }
}
