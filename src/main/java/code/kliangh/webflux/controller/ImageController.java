package code.kliangh.webflux.controller;

import code.kliangh.webflux.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ImageController {
    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    private static final String API_BASE_PATH = "/api";

    @GetMapping(API_BASE_PATH + "/images")
    Flux<Image> images() {
        return Flux.just(
                new Image("1", "image-1"),
                new Image("2", "image-2"),
                new Image("3", "image-3")
        );
    }

    @PostMapping(API_BASE_PATH + "/images")
    Mono<Void> create(@RequestBody Flux<Image> images) {
        return images
                .map(image -> {
                    LOG.info(String.format("Image saved. %s", image.toString()));
                    return image;
                })
                .then();
    }
}
