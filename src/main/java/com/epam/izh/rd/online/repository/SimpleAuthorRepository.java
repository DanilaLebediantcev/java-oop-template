package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        Author[] authors_copy;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors_copy = new Author[authors.length + 1];
            System.arraycopy(authors, 0, authors_copy, 0, authors.length);
            authors_copy[authors_copy.length - 1] = author;
            authors = authors_copy;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author current_author : authors) {
            if (name == current_author.getName() && lastName == current_author.getLastName()) {
                return current_author;
            } else {
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        Author[] authors_copy;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            authors_copy = new Author[authors.length - 1];
            for (int i = 0; i < authors.length - 1 ; i++) {
                if (author.getName() == authors[i].getName() && author.getLastName() == authors[i].getLastName()) {
                    for (int index = i; index < authors.length -1; index++) {
                        //В данном цикле я  удаляю найденного автора, сохраняя тот же порядок в массиве authors
                        //т.е. например массив [1,2,3,4,5]
                        //удаляем 4, порядок будет [1,2,3,5]
                        authors_copy[index] = authors[index + 1];
                    }
                   i = authors.length;
                } else {
                    authors_copy[i] = authors[i];
                }
            }
            authors=authors_copy;
            return true;
        }
    }
    @Override
    public int count() {
        return authors.length;
    }
}
