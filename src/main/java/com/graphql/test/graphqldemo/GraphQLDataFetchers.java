package com.graphql.test.graphqldemo;

import com.google.common.collect.ImmutableBiMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableBiMap.of("id", "book-1",
                    "name", "Name of book 1",
                    "pageCount", "223",
                    "authorId", "author-1"
                    ),
            ImmutableBiMap.of("id", "book-2",
                    "name", "Name of book 2",
                    "pageCount", "665",
                    "authorId", "author-2"
            ),
            ImmutableBiMap.of("id", "book-3",
                    "name", "Name of book 3",
                    "pageCount", "456",
                    "authorId", "author-3"
            )
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableBiMap.of("id", "author-1",
                    "firstName", "Potter",
                    "lastName", "Harry"
            ),
            ImmutableBiMap.of("id", "author-2",
                    "firstName", "Herrington",
                    "lastName", "Billy"
            ),
            ImmutableBiMap.of("id", "author-3",
                    "firstName", "Darkholme",
                    "lastName", "van"
            )
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books.stream().filter(book -> book.get("id").equals(bookId)).findFirst().orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors.stream().filter(author -> author.get("id").equals(authorId)).findFirst().orElse(null);
        };
    }
}
