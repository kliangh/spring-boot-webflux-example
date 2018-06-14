package code.kliangh.webflux.configuration;

import code.kliangh.webflux.model.Chapter;
import code.kliangh.webflux.repository.ChapterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner init(ChapterRepository repository) {
        return args -> Flux.just(
                new Chapter("Quick start with java"),
                new Chapter("Chapter 1"),
                new Chapter("Chapter 2"))
                .flatMap(repository::save)
                .subscribe(System.out::println);
    }
}
