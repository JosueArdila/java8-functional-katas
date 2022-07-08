package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
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
                                        .filter(box -> box.getWidth() == 150 && box.getHeight() == 200)
                                        .findFirst()
                                        .orElseThrow()
                        )
                )
                .collect(Collectors.toList());
    }
}
