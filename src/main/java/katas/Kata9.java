package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .map(lm -> lm.getVideos())
                .flatMap(videos -> videos.stream())
                .map(mapInfo ->
                        ImmutableMap.of(
                                "id", mapInfo.getId(),
                                "title", mapInfo.getTitle(),
                                "time", mapInfo.getInterestingMoments()
                                        .stream()
                                        .filter(moment -> moment.getType().equals("Middle"))
                                        .findFirst()
                                        .orElseThrow()
                                        .getTime(),
                                "url", mapInfo.getBoxarts()
                                        .stream()
                                        .min((box1, box2) -> (box1.getWidth() * box1.getHeight()) - (box2.getWidth() * box2.getHeight()))
                                        .orElseThrow()
                                        .getUrl()
                        )
                )
                .collect(Collectors.toList());
    }
}


