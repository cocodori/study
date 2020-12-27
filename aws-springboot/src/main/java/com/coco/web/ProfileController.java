package com.coco.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/*
* 8081을 쓸 것인지, 8082를 쓸것인지 결정하는 API
* */
@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        /*env.getActiveProfiles()는 현재 실행 중인 ActiveProfile을 모두 가져온다.
        즉 real, oauth, real-db등이 활성화되어 있다면(active) 3개가 모두 담겨 있다.
        여기서 real, real1, real2는 모두 배포에 사용될 profile이라 이 중 하나라도 있으면 그 값을 반환하도록 한다.
        실제로 무중단 배포에는 real1과 real2만 사용되지만, step2 디렉토리를 다시 사용해볼 수도 있으므로 real도 남겨둔다.*/
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
