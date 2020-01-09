package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] book_copy = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, book_copy, 0, schoolBooks.length);
        book_copy[book_copy.length - 1] = book;
        schoolBooks = book_copy;
        return true;
    }


    @Override
    public SchoolBook[] findByName(String name) {
        int count = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName() == name) {
                count++;
            }
        }
        SchoolBook[] finderBooks = new SchoolBook[count];
        int j = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (name == schoolBooks[i].getName()) {
                finderBooks[j] = schoolBooks[i];
                j++;
            }
        }
        if (finderBooks.length > 0) {
            return finderBooks;
        } else {
            return new SchoolBook[0];
        }
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] books_copy;
        int count = 0;
        if (findByName(name) == null) {
            return false;
        } else {
            for (SchoolBook bookBuf : schoolBooks) {
                if (bookBuf.getName() == name) {
                    count++;
                }
            }
            books_copy = new SchoolBook[schoolBooks.length - count];
            for (int i = 0; i < schoolBooks.length; i++) {
                int j = 0;
                if (schoolBooks[i].getName() != name) {
                    books_copy[j] = schoolBooks[i];
                    j++;
                }
            }
            schoolBooks = books_copy;
            return true;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
