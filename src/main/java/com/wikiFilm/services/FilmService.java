package com.wikiFilm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wikiFilm.exception.FilmNotFoundException;
import com.wikiFilm.models.Film;
import com.wikiFilm.repositories.FilmRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FilmService implements BaseService<Film> {
    private final FilmRepository filmRepository;

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id: " + id));
    }

    public Film findByTitle(String title) {
        return filmRepository.findByTitle(title)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id: " + title));
    }

    @Override
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film filmDetails) {
        Film film = findById(id);
        film.setTitle(filmDetails.getTitle());
        film.setReleaseYear(filmDetails.getReleaseYear());
        film.setRating(filmDetails.getRating());
        film.setDescription(filmDetails.getDescription());
        return save(film);
    }

    @Override
    public void deleteById(Long id) {
        Film film = findById(id);
        filmRepository.deleteById(film.getId());
    }

    @Override
    public Page<Film> findAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }
}