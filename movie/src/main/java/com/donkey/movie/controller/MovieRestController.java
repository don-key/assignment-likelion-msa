package com.donkey.movie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RequestMapping(value = "/api/movies")
@RestController
public class MovieRestController {
    static List<Movie> movies = new ArrayList<>();

    static {
        movies.add(new Movie(0, "도깨비 깃발", "2022-01-26"));
        movies.add(new Movie(1, "킹메이커", "2022-01-26"));
        movies.add(new Movie(2, "특송", "2022-01-12"));
        movies.add(new Movie(3, "씽2게더", "2022-01-05"));
        movies.add(new Movie(4, "노 웨이 홈", "2021-12-15"));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> list() {
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> detail(@PathVariable int id) {
        Movie movie = movies.stream().filter(movie1 -> movie1.getId() == id).findFirst().get();
        return ResponseEntity.ok().body(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        Movie lastMovie = movies.stream()
                .max(Comparator.comparingInt(Movie::getId))
                .orElseThrow(NoSuchElementException::new);

        int i = lastMovie.getId();
        Movie create = new Movie(++i, movie.subject, movie.release);
        movies.add(create);
        return ResponseEntity.ok().body(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable int id, @RequestBody Movie updateMovie) {
        Movie movie = movies.stream().filter(movie1 -> movie1.getId() == id).findFirst().get();
        movie.setSubject(updateMovie.getSubject());
        movie.setRelease(updateMovie.getRelease());
        return ResponseEntity.ok().body(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Movie movie = movies.stream().filter(movie1 -> movie1.getId() == id).findFirst().get();
        movies.remove(movie);
        return ResponseEntity.noContent().build();
    }

    static class Movie {
        int id;
        String subject;
        String release;

        public Movie(int id, String subject, String release) {
            this.id = id;
            this.subject = subject;
            this.release = release;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getRelease() {
            return release;
        }

        public void setRelease(String release) {
            this.release = release;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "id=" + id +
                    ", subject='" + subject + '\'' +
                    ", release='" + release + '\'' +
                    '}';
        }
    }
}
