package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .map(movies -> movies.getVideos())
                .flatMap(movie -> movie.stream())
                .map(mapa ->
                        ImmutableMap.of("id", mapa.getId(),
                                "tile", mapa.getTitle(),
                                "boxart", mapa.getBoxarts()
                                        .stream()
                                        .min((box1, box2) -> (box1.getWidth() * box1.getHeight()) - (box2.getWidth() * box2.getHeight()))
                                        .get()
                        )
                )
                .collect(Collectors.toList());
    }
}
