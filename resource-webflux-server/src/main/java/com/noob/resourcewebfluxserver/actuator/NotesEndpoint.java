package com.noob.resourcewebfluxserver.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
// @Endpoint(id = "notes") // 可以通过 HTTP 或 JXM 交互
@WebEndpoint(id = "notes")
public class NotesEndpoint {
    private final List<Note> notes = new ArrayList<>();

    // /actuator/notes
    @ReadOperation
    public List<Note> notes() {
        return notes;
    }

    @WriteOperation
    public List<Note> addNote(String text) {
        notes.add(new Note(text));
        return notes;
    }

    @DeleteOperation
    public List<Note> deleteNote(int index) {
        if (index < notes.size()) {
            notes.remove(index);
        }
        return notes;
    }

    static class Note {
        private final Date time = new Date();
        private final String text;

        public Note(String text) {
            this.text = text;
        }

        public Date getTime() {
            return time;
        }

        public String getText() {
            return text;
        }
    }
}
